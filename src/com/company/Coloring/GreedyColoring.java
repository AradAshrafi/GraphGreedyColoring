package com.company.Coloring;

import com.company.Coloring.GraphicalOutput.GraphicalOutputFrame;
import com.company.InputOperation.FileOperation;

import javax.swing.*;
import java.util.HashSet;

public class GreedyColoring {
    private boolean[][] adjacencyMatrix;
    private int verticesNumber;
    private int[] verticesColor;

    public GreedyColoring() {
        FileOperation.readFromFile();
        adjacencyMatrix = FileOperation.getAdjMat();
        verticesNumber = FileOperation.getNumberOfVertices();
        verticesColor = new int[verticesNumber];
        graphColoring();
    }


    /**
     * main method of coloring
     */
    private void graphColoring() {
        verticesColor[0] = 0;
        /**
         * Color initialization
         */
        for (int i = 1; i < verticesNumber; i++) {
            verticesColor[i] = -1;
        }

        /**
         * paint current vertex with minimum possible color consider it's neighbours
         */
        for (int i = 0; i < verticesNumber; i++) {
            paintCurrentVertex(i);
        }

        FileOperation.writeToFile(verticesColor);

        //graphical operation
        JFrame graphicalOutputFrame = new GraphicalOutputFrame(verticesColor, adjacencyMatrix);
        graphicalOutputFrame.setVisible(true);
        graphicalOutputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    /**
     * returns suitable color for current vertex
     *
     * @param vertexNumber
     */
    private void paintCurrentVertex(int vertexNumber) {
        HashSet<Integer> neighboursColors = new HashSet<>();
        int suitableColor = -1;

        /**
         * create a set of neighbours color
         */
        for (int i = 0; i < verticesNumber; i++) {
            if (adjacencyMatrix[vertexNumber][i])
                neighboursColors.add(verticesColor[i]);
        }

        /**
         * find minimum color for current vertex
         */
        for (int i = 0; i < neighboursColors.size(); i++) {
            if (!neighboursColors.contains(i)) {
                suitableColor = i;
                break;
            }
        }

        /**
         * if suitableColor is -1 it means that all of it's neighbours are painted consecutively from 0 to neighboursColor.size()-1 .
         * so our minimum suitableColor will be neighboursColor.size()
         */
        if (suitableColor == -1)
            suitableColor = neighboursColors.size();

        verticesColor[vertexNumber] = suitableColor;
    }

}
