package com.cte.test;

import java.util.*;

/**
 * Created by artyom on 15.24.8.
 */
public class GraphPathFinder {

    private ArrayList<Integer> completedPaths = new ArrayList<>();


    public void createPath(ArrayList<Node> triangleArray, int levelCounter, StringBuilder stringBuilder) {

        for (int i = -1; i < levelCounter; i++) {

            Node node = triangleArray.get(1);


            do {

                if (!node.isLeaf(levelCounter)) {

                    stringBuilder.append(node.getVertexNumber()).append(node.getFirstChild());


                }

            } while(true);

        }




    }


}
