package com.cte.test;

import java.util.ArrayList;

/**
 * Created by Artyom on 8/19/2015.
 */
public class Node {

    private int vertexNumber;   //node position according breadth-first traverse
    private int vertexValue;    //node numeric value
    private int vertexLevel;    //node tree graph level

    public Node(int vertexNumber, int vertexLevel, int vertexValue) {
        this.vertexNumber = vertexNumber;
        this.vertexLevel = vertexLevel;
        this.vertexValue = vertexValue;
    }

    public int getVertexNumber() {
        return vertexNumber;
    }

    public void setVertexNumber(int vertexNumber) {
        this.vertexNumber = vertexNumber;
    }

    public int getVertexValue() {
        return vertexValue;
    }

    public void setVertexValue(int vertexValue) {
        this.vertexValue = vertexValue;
    }

    public int getVertexLevel() {
        return vertexLevel;
    }

    public void setVertexLevel(int vertexLevel) {
        this.vertexLevel = vertexLevel;
    }

    public int getFirstChild() {

        return this.getVertexNumber() + this.getVertexLevel() + 1;
    }

    public int getSecondChild() {

        return this.getVertexNumber() + this.getVertexLevel() + 2;
    }

    public boolean isLeaf(int levelCounter) {

        return this.getVertexLevel() == levelCounter;
    }

}
