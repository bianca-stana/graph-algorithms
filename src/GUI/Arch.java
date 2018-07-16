package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Vector;


public class Arch {
    private Point start;
    private Point end;
    private Node nodeStart;
    private Node nodeEnd;
    private int value;

    public Arch(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Arch(Node start, Node end) {
        this.nodeStart = start;
        this.nodeEnd = end;
    }

    public Arch(int value) {
        this.value = value;
    }

    public Point getPointStart() {
        return start;
    }

    public Point getPointEnd() {
        return end;
    }

    public Node getNodeStart() {
        return nodeStart;
    }
    public void setNodeStart(Node nodeStart) {
        this.nodeStart = nodeStart;
    }

    public Node getNodeEnd() {
        return nodeEnd;
    }
    public void setNodeEnd(Node nodeEnd) {
        this.nodeEnd = nodeEnd;
    }

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    public static void drawArch(Graphics g, Point pointStart, Point pointEnd) {
        if (pointStart != null) {
            g.setColor(Color.BLACK);
            g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
        }
    }

    public static void drawOrientedArch(Graphics g, Point pointStart, Point pointEnd) {
        int h = 10; // the height of the arrow
        int w = 5;  // the width of the arrow

        int dx = pointEnd.x - pointStart.x;
        int dy = pointEnd.y - pointStart.y;
        double D = Math.sqrt(dx * dx + dy * dy);

        double xm = D - h;
        double xn = xm;
        double ym = w;
        double yn = -w;

        double sin = dy / D;
        double cos = dx / D;

        double x1 = xm * cos - ym * sin + pointStart.x;
        ym = xm * sin + ym * cos + pointStart.y;
        xm = x1;

        double x2 = xn * cos - yn * sin + pointStart.x;
        yn = xn * sin + yn * cos + pointStart.y;
        xn = x2;

        int[] xPoints = {pointEnd.x, (int) xm, (int) xn};
        int[] yPoints = {pointEnd.y, (int) ym, (int) yn};

        g.setColor(Color.RED);
        g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public static void sort(Vector<Arch> arches) {
        arches.sort((a1, a2) -> {
            int value1 = a1.getValue();
            int value2 = a2.getValue();

            if (value1 < value2)
                return -1;
            else if (value1 < value2)
                return 1;
            return 0;
        });
    }
}
