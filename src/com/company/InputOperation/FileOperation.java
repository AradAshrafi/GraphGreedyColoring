package com.company.InputOperation;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class FileOperation {
    private static final String inputPath = "testCases/karatecsv.txt";
    private static final String outputPath = "testCases/out.txt";
    private static ArrayList<Integer[]> edges = new ArrayList<>();
    private static int numberOfVertices = 0;
    private static boolean[][] adjacencyMatrix;

    /**
     * to read from file and save number of vertices and all edges
     */
    public static void readFromFile() {
        File file = new File(inputPath);

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                String[] line = input.nextLine().trim().split("\\s+");
                Integer[] currentEdge = new Integer[2];
                currentEdge[0] = Integer.parseInt(line[0]);
                currentEdge[1] = Integer.parseInt(line[1]);
                int biggerVertexNumber = Math.max(currentEdge[0], currentEdge[1]);
                if (biggerVertexNumber > numberOfVertices)
                    numberOfVertices = biggerVertexNumber;
                edges.add(currentEdge);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * build adjMat as following :
     * if we have u - v  edge =>  adjacencyMatrix[u][v] = true ,  adjacencyMatrix[v][u] = true
     */
    private static void buildAdjMat() {
        adjacencyMatrix = new boolean[numberOfVertices][numberOfVertices];
        Iterator<Integer[]> it = edges.iterator();
        while (it.hasNext()) {
            Integer[] currentEdge = it.next();
            int vertexOne = currentEdge[0];
            int vertexTwo = currentEdge[1];
            adjacencyMatrix[vertexOne - 1][vertexTwo - 1] = true;
            adjacencyMatrix[vertexTwo - 1][vertexOne - 1] = true;
        }
    }

    public static boolean[][] getAdjMat() {
        buildAdjMat();
        return adjacencyMatrix;
    }

    public static int getNumberOfVertices() {
        return numberOfVertices;
    }

    public static void writeToFile(int[] verticesColor) {
        File file = new File(outputPath);
        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int i = 0; i < verticesColor.length; i++) {
                bufferedWriter.write((i + 1) + "  " + verticesColor[i] + '\n');
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
