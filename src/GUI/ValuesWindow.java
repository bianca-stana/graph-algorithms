package GUI;

import AppLogic.*;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;


public class ValuesWindow extends JFrame {

    public ValuesWindow(String algorithm, Vector<Node> nodesList, Vector<Arch> archesList, int sNumber) {
        this.setBounds(700, 200, 200, 500);
        this.setLayout(null);

        JPanel valuesPanel = new JPanel();
        valuesPanel.setBounds(0, 0, 200, 500);
        valuesPanel.setLayout(null);
        this.add(valuesPanel);

        JLabel lbText = new JLabel("To continue the algorithm,");
        lbText.setBounds(15, 15, 270, 15);
        Font font = new Font(lbText.getFont().getName(), Font.ITALIC, lbText.getFont().getSize());
        lbText.setFont(font);
        valuesPanel.add(lbText);

        lbText = new JLabel("insert the value of each arch,");
        lbText.setBounds(15, 30, 270, 15);
        lbText.setFont(font);
        valuesPanel.add(lbText);

        lbText = new JLabel("then click \"Done\".");
        lbText.setBounds(15, 45, 270, 15);
        lbText.setFont(font);
        valuesPanel.add(lbText);

        JPanel inputPanel = new JPanel();
        inputPanel.setPreferredSize(new Dimension(150, archesList.size() * 35));
        inputPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(inputPanel);
        scrollPane.setBounds(5, 65, 170, 350);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setForeground(Color.LIGHT_GRAY);
        valuesPanel.add(scrollPane);

        Vector<JTextField> tfValues = new Vector<>();
        int diff = 0;
        for (Arch a : archesList) {
            JLabel lbValue = new JLabel("Value of [" + a.getNodeStart().getNumber() + ", " + a.getNodeEnd().getNumber() + "]:");
            lbValue.setBounds(10, 10 + diff, 100, 15);
            inputPanel.add(lbValue);

            JTextField tfValue = new JTextField();
            tfValue.setBounds(100, 6 + diff, 40, 25);
            inputPanel.add(tfValue);
            tfValues.add(tfValue);

            diff += 35;
        }

        JButton doneBtn = new JButton("Done");
        doneBtn.addActionListener(e -> {
            boolean canStart = false;

            for (int i = 0; i < tfValues.size(); i++) {
                String tfText = tfValues.elementAt(i).getText();

                if (!tfText.equals("")) {
                    try {
                        archesList.elementAt(i).setValue(Integer.parseInt(tfText));
                        canStart = true;
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "You have inserted some invalid values!");
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in all of the fields!");
                    break;
                }
            }

            if (canStart) {
                this.dispose();

                switch (algorithm) {
                    case "Generic Algorithm":
                        MST_Generic alg4 = new MST_Generic();
                        alg4.start(nodesList, archesList);
                        break;
                    case "Prim's Algorithm":
                        MST_Prim alg5 = new MST_Prim();
                        alg5.start(nodesList, archesList);
                        break;
                    case "Kruskal's Algorithm":
                        MST_Kruskal alg6 = new MST_Kruskal();
                        alg6.start(nodesList, archesList);
                        break;
                    case "Dijkstra's Algorithm":
                        Dijkstra alg7 = new Dijkstra();
                        alg7.start(nodesList, archesList, sNumber);
                        break;
                    case "Bellman-Ford Algorithm":
                        BellmanFord alg8 = new BellmanFord();
                        alg8.start(nodesList, archesList, sNumber);
                        break;
                    case "Floyd-Warshall Algorithm":
                        FloydWarshall alg9 = new FloydWarshall();
                        alg9.start(nodesList, archesList);
                        break;
                }
            }
        });
        doneBtn.setBounds(55, 430, 70, 25);
        doneBtn.setForeground(Color.BLACK);
        doneBtn.setBackground(Color.ORANGE);
        valuesPanel.add(doneBtn);
    }
}
