package com.company.Coloring.GraphicalOutput;

import java.awt.*;
import javax.swing.*;

public class GraphicalOutputFrame extends JFrame {
    private final int WIDTH = 330;
    private final int HEIGHT = 500;
    private Container drawable;
    private JPanel canvas;

    public GraphicalOutputFrame(int[] verticesColor, boolean[][] adjacencyMatrix) {
        super("SimpleGraph");
        drawable = getContentPane();
        canvas = new GraphicalOutputPanel(verticesColor, adjacencyMatrix);
        drawable.add(canvas);
        setBackground(Color.white);
        setSize(WIDTH, HEIGHT);
    }
}