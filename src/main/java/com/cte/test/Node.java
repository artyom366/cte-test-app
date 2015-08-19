package com.cte.test;

/**
 * Created by Artyom on 8/19/2015.
 */
public class Node {

    private int vertexNumber;   //node position according breadth-first traverse
    private int vertexValue;    //node numeric value
    private int vertexLevel;    //node tree graph level
    private int[] vertexEdges;  //adjacency nodes that share edge with current node

    public Node(int vertexNumber, int vertexValue, int vertexLevel) {
        this.vertexNumber = vertexNumber;
        this.vertexValue = vertexValue;
        this.vertexLevel = vertexLevel;
        this.vertexEdges = new int[3];  //3 is the maximum number of adjacency nodes in that graph
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

    public int[] getVertexEdges() {
        return vertexEdges;
    }

    public void setVertexEdges(int[] vertexEdges) {
        this.vertexEdges = vertexEdges;
    }
}
