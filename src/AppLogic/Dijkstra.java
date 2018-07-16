package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Vector;

public class Dijkstra {
    private static Vector<Node> V; // the nodes that are visited
    private static Vector<Integer> p; // vector of predecessors
    private static Vector<Integer> d; // vector of distances

    public Dijkstra() {
        V = new Vector<>();
        p = new Vector<>();
        d = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A, int sNumber) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }

        V.addAll(N);

        for (int i = 0; i < N.size(); i++) {
            p.add(0);
            d.add(999);
        }

        Node s = Util.getNode(N, sNumber);
        d.set(s.getNumber() - 1, 0);

        while (!V.isEmpty()) {
            Node x = V.firstElement();

            for (int i = 1; i < V.size(); i++) {
                Node y = V.elementAt(i);
                if (d.elementAt(y.getNumber() - 1) < d.elementAt(x.getNumber() - 1)) {
                    x = y;
                }
            }

            V.remove(x);

            for (Node y : V) {
                Arch a = Util.getOrientedArch(A, x.getNumber(), y.getNumber());

                if (a != null && d.elementAt(x.getNumber() - 1) + a.getValue() < d.elementAt(y.getNumber() - 1)) {
                    d.set(y.getNumber() - 1, d.elementAt(x.getNumber() - 1) + a.getValue());
                    p.set(y.getNumber() - 1, x.getNumber());
                }
            }
        }

        System.out.print("p: ");
        for (int nr : p) {
            System.out.print(nr + " ");
        }
        System.out.println();

        System.out.print("d: ");
        for (int nr : d) {
            System.out.print(nr + " ");
        }
        System.out.println();
        System.out.println();

        Vector<Integer> path = Util.path(p, N.lastElement().getNumber());
        System.out.print("The minimum path from " + sNumber + " to "
                + N.lastElement().getNumber() + " in the resulted tree is: ");
        for (int node : path) {
            System.out.print(node + " ");
        }
        System.out.println();

        System.out.print("The minimum distance from " + sNumber + " to "
                + N.lastElement().getNumber() + " is: "
                + d.elementAt(N.lastElement().getNumber() - 1));
        System.out.println();
        System.out.println();
    }
}
