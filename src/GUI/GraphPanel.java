package GUI;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;


public class GraphPanel extends JPanel {
    private int nodeNr = 1;
    private int node_diameter = 30;
    private int node_Diameter = 30;
    private Vector<Node> nodesList;
    private Vector<Arch> archesList;
    private Point pointStart = null;
    private Point pointEnd = null;
    private boolean isOrientedGraph = false;
    private boolean isDragging = false;

    public GraphPanel() {
        nodesList = new Vector<>();
        archesList = new Vector<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pointStart = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pointEnd = e.getPoint();

                boolean canDrawNode = true;
                boolean canDrawArch = false;
                boolean canAddArch = false;
                boolean continue_pointStart = true;
                boolean continue_pointEnd = true;
                Node nodeStart = null;
                Node nodeEnd = null;

                for (Node node : nodesList) {
                    if (continue_pointStart) {
                        if (pointStart.getX() >= node.getCoordX() && pointStart.getX() <= (node.getCoordX() + node_diameter) &&
                                pointStart.getY() >= node.getCoordY() && pointStart.getY() <= (node.getCoordY() + node_Diameter)) {
                            canDrawNode = false;
                            canDrawArch = true;
                            nodeStart = node;
                            continue_pointStart = false;
                        }
                    }

                    if (continue_pointEnd) {
                        if (pointEnd.getX() >= node.getCoordX() && pointEnd.getX() <= (node.getCoordX() + node_diameter) &&
                                pointEnd.getY() >= node.getCoordY() && pointEnd.getY() <= (node.getCoordY() + node_Diameter)) {
                            canAddArch = true;
                            nodeEnd = node;
                            continue_pointEnd = false;
                        }
                    }

                    if (!continue_pointStart && !continue_pointEnd) {
                        break;
                    }
                }

                if (!isDragging) {
                    if (canDrawNode) {
                        addNode(e.getX(), e.getY());
                    }
                } else if (canDrawArch && canAddArch) {
                    addArch(nodeStart, nodeEnd);
                } else {
                    Graphics g = getGraphics();
                    g.clearRect(0, 0, 500, 500);
                    repaint();
                }

                pointStart = null;
                isDragging = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                pointEnd = e.getPoint();
                isDragging = true;
                repaint();
            }
        });
    }

    public Vector<Node> getNodesList() {
        return nodesList;
    }

    public void setNodesList(Vector<Node> nodesList) {
        this.nodesList = nodesList;
    }

    public Vector<Arch> getArchesList() {
        return archesList;
    }

    public void setArchesList(Vector<Arch> archesList) {
        this.archesList = archesList;
    }

    public boolean isOrientedGraph() {
        return isOrientedGraph;
    }

    public void setOrientedGraph(boolean orientedGraph) {
        isOrientedGraph = orientedGraph;
    }

    private void addNode(int x, int y) {
        Node node = new Node(x, y, nodeNr);
        nodesList.add(node);
        nodeNr++;
        repaint();
    }

    private void addArch(Node nodeStart, Node nodeEnd) {
        Arch arch = new Arch(pointStart, pointEnd);
        arch.setNodeStart(nodeStart);
        arch.setNodeEnd(nodeEnd);
        archesList.add(arch);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Arch a : archesList) {
            if (isOrientedGraph) {
                Arch.drawOrientedArch(g, a.getPointStart(), a.getPointEnd());
            } else {
                Arch.drawArch(g, a.getPointStart(), a.getPointEnd());
            }
        }

        if (pointStart != null) {
            if (isOrientedGraph) {
                Arch.drawOrientedArch(g, pointStart, pointEnd);
            } else {
                Arch.drawArch(g, pointStart, pointEnd);
            }
        }

        for (Node n : nodesList) {
            n.drawNode(g, node_diameter, node_Diameter);
        }
    }

    public void resetGraph() {
        archesList.clear();
        nodesList.clear();
        nodeNr = 1;
        repaint();
    }
}
