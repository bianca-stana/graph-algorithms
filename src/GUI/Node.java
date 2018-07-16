package GUI;

import java.awt.Color;
import java.awt.Graphics;


public class Node {
    private int coordX;
    private int coordY;
    private int number;

    public Node(int coordX, int coordY, int number) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.number = number;
    }

    public Node(int number) {
        this.number = number;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public int getNumber() {
        return number;
    }

    public void drawNode(Graphics g, int node_diameter, int node_Diameter) {
        g.setColor(Color.DARK_GRAY);
        g.fillOval(coordX, coordY, node_diameter, node_Diameter);
        g.setColor(Color.WHITE);
        g.drawOval(coordX, coordY, node_diameter, node_Diameter);

        if (number < 10)
            g.drawString(((Integer) number).toString(), coordX + 13, coordY + 20);
        else
            g.drawString(((Integer) number).toString(), coordX + 8, coordY + 20);
    }
}
