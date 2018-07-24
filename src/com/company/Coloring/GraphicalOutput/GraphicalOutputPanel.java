package com.company.Coloring.GraphicalOutput;

import javax.swing.*;
import java.awt.*;

public class GraphicalOutputPanel extends JPanel {
    private static final int SIZE = 256;
    private int a = SIZE / 2;
    private int b = a;
    private int r = 4 * SIZE / 5;
    private int[] verticesColor;
    private boolean[][] adjacencyMatrix;
    private int verticesNumber;


    public GraphicalOutputPanel(int[] verticesColor, boolean[][] adjacencyMatrix) {
        super(true);
        setBackground(Color.white);
        this.setPreferredSize(new Dimension(SIZE, SIZE));
        this.verticesColor = verticesColor;
        this.verticesNumber = verticesColor.length;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] verticesCoordination = new int[verticesNumber][2];
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.white);
        a = getWidth() / 2;
        b = getHeight() / 2;
        int m = Math.min(a, b);
        r = 4 * m / 5;
        int r2 = Math.abs(m - r) / 2;
        g2d.drawOval(a - r, b - r, 2 * r, 2 * r);
        for (int i = 0; i < verticesNumber; i++) {
            Color currentVertexColor = new Color(Math.round((39 * (verticesColor[i]) + 150) % 255),Math.round((63 * (verticesColor[i]) + 50) % 255),Math.round((19 * (verticesColor[i]) + 250) % 255));
            g2d.setColor(currentVertexColor);
            double t = 2 * Math.PI * i / verticesNumber;
            int x = (int) Math.round(a + r * Math.cos(t));
            int y = (int) Math.round(b + r * Math.sin(t));
            verticesCoordination[i][0] = x;
            verticesCoordination[i][1] = y;
            g2d.fillOval(x - r2, y - r2, 2 * r2, 2 * r2);
            g2d.setColor(Color.WHITE);
            g2d.drawString("" + (i + 1), x, y);

        }

        //to draw lines between adjacent vertices
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < verticesNumber; i++) {
            for (int j = i; j < verticesNumber; j++) {
                if (adjacencyMatrix[i][j]) {
                    g2d.drawLine(verticesCoordination[i][0], verticesCoordination[i][1], verticesCoordination[j][0], verticesCoordination[j][1]);
                }
            }
        }
    }
}