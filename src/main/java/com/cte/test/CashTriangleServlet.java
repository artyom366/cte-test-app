package com.cte.test;

import com.cte.test.utils.CacheGateway;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Created by Artyom on 8/4/2015.
 */
public class CashTriangleServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        out.print("GET");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String key = req.getParameter("triangleID");

        if (key == null) {
            return;
        }

        CacheGateway cacheGateway = CacheGateway.getCache(this.getClass());
        PrintWriter out = resp.getWriter();

        BufferedReader reader = req.getReader();
        String line;
        int sum = 0;

        while ((line = reader.readLine()) != null) {

            String[] lineArray = line.split(" ");
            int amount = -1;

            for (String element : lineArray) {

                int number;
                try {
                    number = Integer.valueOf(element);
                } catch (Exception e) {
                    continue;
                }

                if (amount < number) {
                    amount = number;
                }
            }

            sum += amount;
        }

        boolean result;
        do {
            result = cacheGateway.write(key, sum);
        } while (!result);

        out.print(sum);

    }
}
