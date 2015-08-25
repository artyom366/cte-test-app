package com.cte.test;

import java.util.*;

/**
 * Created by artyom on 15.24.8.
 */
public class GraphPathFinder {

    private int[][] matrix;

    public GraphPathFinder(int[][] matrix) {
        this.matrix = matrix;
    }

    public void createPath(Integer row, Integer col, Integer direction) {

        if (row == 1 && col == 1) {
            row = 6;
            return;
        }

        if(direction == 1) {

            for (; row > 0; row--) {

                if (matrix[row][col] == 1) {

                    System.out.print(row);

                    createPath(row, row, 0);

                }

            }

        } else {

            //iterate backwards over x axis
            for (; col > 0; col--) {

                //iterate backwards over y axis
                for (; row > 0; row--) {

                    //found the last node
                    if (matrix[row][col] == 1) {

                        System.out.print(col);
                        System.out.print(row);

                        createPath(row, row, 1);
                    }
                }
            }
        }
    }
}
