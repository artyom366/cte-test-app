package com.cte.test;

import com.cte.test.utils.CacheGateway;
import com.cte.test.utils.StatisticsGateway;
import com.cte.test.utils.StatisticsValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Artyom on 8/4/2015.
 */
public class CashTriangleServlet extends HttpServlet implements Runnable {

    private volatile int triangleProcessedCount, maxSum, minSum, maxTriangle, minTriangle;
    private CacheGateway cacheGateway = CacheGateway.getCache(this.getClass());
    private Thread statisticsSender;

    @Override
    public void init() throws ServletException {

        triangleProcessedCount = 0;
        maxSum = 0;
        minSum = 10000;
        maxTriangle = 0;
        minTriangle = 101;

        statisticsSender = new Thread(this);
        statisticsSender.start();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        String key = req.getParameter("triangleID");

        if (key == null) {
            out.print("err_no_id");
            return;
        }

        int winningValue;

        try {
            winningValue = (int) cacheGateway.read(key);
        } catch (Exception e) {
            out.print("err_no_data");
            return;
        }

        out.print(winningValue);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();

        String key = req.getParameter("triangleID");

        if (key == null) {
            out.print("err_no_id");
            return;
        }

        BufferedReader reader = req.getReader();
        String line;
        int winningValue = 0, triangleSize = 0;

        while ((line = reader.readLine()) != null) {

            String[] spitedLine = line.split(" ");
            int largestInLine = -1;

            for (String stringElement : spitedLine) {

                int numberElement;
                try {
                    numberElement = Integer.valueOf(stringElement);
                } catch (Exception e) {
                    System.out.print(e.getCause() + "\n" + e.getMessage());
                    continue;
                }

                if (largestInLine < numberElement) {
                    largestInLine = numberElement;
                }
            }

            winningValue += largestInLine;
            triangleSize++;
        }

        boolean result;
        do {
            result = cacheGateway.write(key, winningValue);
        } while (!result);

        triangleProcessedCount++;

        if (winningValue > maxSum) maxSum = winningValue;
        if (winningValue < minSum) minSum = winningValue;
        if (triangleSize > maxTriangle) maxTriangle = triangleSize;
        if (triangleSize < minTriangle) minTriangle = triangleSize;

        out.print(winningValue);

    }

    @Override
    public void run() {

        do {
            try {
                Thread.sleep(60000);
            } catch (InterruptedException e) {
                System.out.println(e.getCause() + "\n" + e.getMessage());
            }

            Date date = new Date();

            Map<String, Integer> stats = new HashMap<>();
            stats.put("count", triangleProcessedCount);
            stats.put("max_sum", maxSum);
            stats.put("min_sum", minSum);
            stats.put("max_triangle", maxTriangle);
            stats.put("min_triangle", minTriangle);

            StatisticsGateway statisticsGateway = new StatisticsGateway();
            try {
                statisticsGateway.sendStats(date.getTime(), stats);

                System.out.println("stasts has been sent...");
                System.out.println("count...: " + triangleProcessedCount);
                System.out.println("max_sum...: " + maxSum);
                System.out.println("min_sum...: " + minSum);
                System.out.println("max_triangle...: " + maxTriangle);
                System.out.println("min_triangle...: " + minTriangle);

            } catch (StatisticsValidationException e) {
                System.out.println(e.getCause() + "\n" + e.getMessage());
            }

            triangleProcessedCount = 0;
            maxSum = 0;
            minSum = 10000;
            maxTriangle = 0;
            minTriangle = 101;

        } while (true);
    }

    @Override
    public void destroy() {

        statisticsSender.interrupt();
    }
}
