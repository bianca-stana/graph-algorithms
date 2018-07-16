package GUI;

import AppLogic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GraphWindow extends JFrame {

    private GraphPanel graphPanel;

    public GraphWindow(String title) {
        this.setTitle(title);
        this.setBounds(700, 200, 500, 500);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        graphPanel = new GraphPanel();
        graphPanel.setBounds(10, 30, 464, 375);
        graphPanel.setLayout(null);
        graphPanel.setBackground(Color.WHITE);
        graphPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        if (title.contains("Traversal") || title.contains("Dijkstra") || title.contains("Bellman-Ford") ||
                title.contains("Floyd-Warshall") || title.contains("Hamiltonian")) {
            graphPanel.setOrientedGraph(true);
        }
        this.add(graphPanel);

        JLabel lbText = new JLabel("Draw your graph below!");
        lbText.setBounds(180, 10, 150, 15);
        lbText.setFont(new Font(lbText.getFont().getName(), Font.PLAIN, lbText.getFont().getSize()));
        this.add(lbText);


        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(10, 410, 465, 45);
        controlPanel.setLayout(null);
        this.add(controlPanel);

        JTextField tfSource = null;
        if (title.contains("Traversal") || title.contains("Dijkstra") || title.contains("Bellman-Ford")) {
            JLabel lbSource = new JLabel("Source node:");
            lbSource.setBounds(150, 15, 100, 15);
            controlPanel.add(lbSource);

            tfSource = new JTextField();
            tfSource.setBounds(235, 10, 30, 25);
            controlPanel.add(tfSource);
        }

        JButton btnStart = new JButton("Get the result");
        JTextField finalTfSource = tfSource;
        btnStart.addActionListener((ActionEvent e) -> {
            switch (title) {
                case "Generic Graph Traversal":
                    getSourceNodeAndStart(finalTfSource, title);
                    break;
                case "Breadth First Traversal (BFT)":
                    getSourceNodeAndStart(finalTfSource, title);
                    break;
                case "Depth First Traversal (DFT)":
                    getSourceNodeAndStart(finalTfSource, title);
                    break;
                case "Generic Algorithm":
                    ValuesWindow valuesWindow = new ValuesWindow(title, graphPanel.getNodesList(), graphPanel.getArchesList(), -1);
                    valuesWindow.setVisible(true);
                    break;
                case "Prim's Algorithm":
                    valuesWindow = new ValuesWindow(title, graphPanel.getNodesList(), graphPanel.getArchesList(), -1);
                    valuesWindow.setVisible(true);
                    break;
                case "Kruskal's Algorithm":
                    valuesWindow = new ValuesWindow(title, graphPanel.getNodesList(), graphPanel.getArchesList(), -1);
                    valuesWindow.setVisible(true);
                    break;
                case "Dijkstra's Algorithm":
                    getSourceNodeAndStart(finalTfSource, title);
                    break;
                case "Bellman-Ford Algorithm":
                    getSourceNodeAndStart(finalTfSource, title);
                    break;
                case "Floyd-Warshall Algorithm":
                    valuesWindow = new ValuesWindow(title, graphPanel.getNodesList(), graphPanel.getArchesList(), -1);
                    valuesWindow.setVisible(true);
                    break;
                case "Hamiltonian Path Algorithm":
                    HPath alg10 = new HPath();
                    alg10.start(graphPanel.getNodesList(), graphPanel.getArchesList());
                    break;
            }
        });
        btnStart.setBounds(10, 10, 110, 25);
        btnStart.setForeground(Color.RED);
        btnStart.setBackground(Color.LIGHT_GRAY);
        btnStart.setFocusPainted(false);
        controlPanel.add(btnStart);

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(e -> graphPanel.resetGraph());
        btnReset.setBounds(302, 10, 70, 25);
        btnReset.setForeground(Color.BLACK);
        btnReset.setBackground(Color.LIGHT_GRAY);
        controlPanel.add(btnReset);

        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> {
            this.dispose();
            String args[] = {""};
            MainWindow.main(args);
        });
        btnBack.setBounds(382, 10, 70, 25);
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(Color.LIGHT_GRAY);
        controlPanel.add(btnBack);
    }

    private void getSourceNodeAndStart(JTextField finalTfSource, String algorithm) {
        String tfNumber = null;
        if (finalTfSource != null) {
            tfNumber = finalTfSource.getText();
        }

        if (tfNumber != null) {
            if (!tfNumber.equals("")) {
                try {
                    int sNumber = Integer.parseInt(tfNumber);

                    switch (algorithm) {
                        case "Generic Graph Traversal":
                            GGT alg1 = new GGT();
                            alg1.start(graphPanel.getNodesList(), graphPanel.getArchesList(), sNumber);
                            break;
                        case "Breadth First Traversal (BFT)":
                            BFT alg2 = new BFT();
                            alg2.start(graphPanel.getNodesList(), graphPanel.getArchesList(), sNumber);
                            break;
                        case "Depth First Traversal (DFT)":
                            DFT alg3 = new DFT();
                            alg3.start(graphPanel.getNodesList(), graphPanel.getArchesList(), sNumber);
                            break;
                        case "Dijkstra's Algorithm":
                            ValuesWindow valuesWindow = new ValuesWindow(algorithm, graphPanel.getNodesList(), graphPanel.getArchesList(), sNumber);
                            valuesWindow.setVisible(true);
                            break;
                        case "Bellman-Ford Algorithm":
                            valuesWindow = new ValuesWindow(algorithm, graphPanel.getNodesList(), graphPanel.getArchesList(), sNumber);
                            valuesWindow.setVisible(true);
                            break;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "You have inserted an invalid value in the \"Source node\" field!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill in the \"Source node\" field!");
            }
        }
    }
}
