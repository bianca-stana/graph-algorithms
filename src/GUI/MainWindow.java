package GUI;

import javax.swing.*;
import java.awt.*;


public class MainWindow {

    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                MainWindow mainWindow = new MainWindow();
                mainWindow.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public MainWindow() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Graph Algorithms");
        frame.setBounds(700, 200, 500, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel menuPanel = new JPanel();
        menuPanel.setBounds(10, 5, 465, 450);
        menuPanel.setLayout(null);
        frame.add(menuPanel);


        JLabel lbSection1 = new JLabel("Graph Traversal Algorithms");
        lbSection1.setBounds(146, 10, 300, 15);
        lbSection1.setForeground(Color.RED);
        menuPanel.add(lbSection1);

        JButton btnGGT = new JButton("Generic Graph Traversal");
        btnGGT.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnGGT.getText());
            graphWindow.setVisible(true);
        });
        btnGGT.setBounds(133, 35, 200, 25);
        btnGGT.setForeground(Color.BLACK);
        btnGGT.setContentAreaFilled(false);
        btnGGT.setFocusPainted(false);
        menuPanel.add(btnGGT);

        JButton btnBFT = new JButton("Breadth First Traversal (BFT)");
        btnBFT.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnBFT.getText());
            graphWindow.setVisible(true);
        });
        btnBFT.setBounds(133, 65, 200, 25);
        btnBFT.setForeground(Color.BLACK);
        btnBFT.setContentAreaFilled(false);
        menuPanel.add(btnBFT);

        JButton btnDFT = new JButton("Depth First Traversal (DFT)");
        btnDFT.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnDFT.getText());
            graphWindow.setVisible(true);
        });
        btnDFT.setBounds(133, 95, 200, 25);
        btnDFT.setForeground(Color.BLACK);
        btnDFT.setContentAreaFilled(false);
        menuPanel.add(btnDFT);


        JLabel lbSection2 = new JLabel("Minimum Spanning Tree Algorithms");
        lbSection2.setBounds(120, 135, 300, 15);
        lbSection2.setForeground(Color.RED);
        menuPanel.add(lbSection2);

        JButton btnMST_G = new JButton("Generic Algorithm");
        btnMST_G.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnMST_G.getText());
            graphWindow.setVisible(true);
        });
        btnMST_G.setBounds(133, 160, 200, 25);
        btnMST_G.setForeground(Color.BLACK);
        btnMST_G.setContentAreaFilled(false);
        menuPanel.add(btnMST_G);

        JButton btnMST_P = new JButton("Prim's Algorithm");
        btnMST_P.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnMST_P.getText());
            graphWindow.setVisible(true);
        });
        btnMST_P.setBounds(133, 190, 200, 25);
        btnMST_P.setForeground(Color.BLACK);
        btnMST_P.setContentAreaFilled(false);
        menuPanel.add(btnMST_P);

        JButton btnMST_K = new JButton("Kruskal's Algorithm");
        btnMST_K.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnMST_K.getText());
            graphWindow.setVisible(true);
        });
        btnMST_K.setBounds(133, 220, 200, 25);
        btnMST_K.setForeground(Color.BLACK);
        btnMST_K.setContentAreaFilled(false);
        menuPanel.add(btnMST_K);


        JLabel lbSection3 = new JLabel("Minimum Path Algorithms");
        lbSection3.setBounds(150, 260, 300, 15);
        lbSection3.setForeground(Color.RED);
        menuPanel.add(lbSection3);

        JButton btnDijkstra = new JButton("Dijkstra's Algorithm");
        btnDijkstra.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnDijkstra.getText());
            graphWindow.setVisible(true);
        });
        btnDijkstra.setBounds(133, 285, 200, 25);
        btnDijkstra.setForeground(Color.BLACK);
        btnDijkstra.setContentAreaFilled(false);
        menuPanel.add(btnDijkstra);

        JButton btnBF = new JButton("Bellman-Ford Algorithm");
        btnBF.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnBF.getText());
            graphWindow.setVisible(true);
        });
        btnBF.setBounds(133, 315, 200, 25);
        btnBF.setForeground(Color.BLACK);
        btnBF.setContentAreaFilled(false);
        menuPanel.add(btnBF);

        JButton btnFW = new JButton("Floyd-Warshall Algorithm");
        btnFW.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnFW.getText());
            graphWindow.setVisible(true);
        });
        btnFW.setBounds(133, 345, 200, 25);
        btnFW.setForeground(Color.BLACK);
        btnFW.setContentAreaFilled(false);
        menuPanel.add(btnFW);


        JLabel lbSection4 = new JLabel("Eulerian & Hamiltonian Problems");
        lbSection4.setBounds(128, 385, 300, 15);
        lbSection4.setForeground(Color.RED);
        menuPanel.add(lbSection4);

        JButton btnECircuit = new JButton("Hamiltonian Path Algorithm");
        btnECircuit.addActionListener(e -> {
            frame.dispose();
            GraphWindow graphWindow = new GraphWindow(btnECircuit.getText());
            graphWindow.setVisible(true);
        });
        btnECircuit.setBounds(133, 410, 200, 25);
        btnECircuit.setForeground(Color.BLACK);
        btnECircuit.setContentAreaFilled(false);
        menuPanel.add(btnECircuit);



        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 13);
        lbSection1.setFont(font);
        lbSection2.setFont(font);
        lbSection3.setFont(font);
        lbSection4.setFont(font);
    }
}
