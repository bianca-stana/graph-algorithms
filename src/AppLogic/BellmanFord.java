package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Vector;

public class BellmanFord {
    private static Vector<Integer> p; // vector of predecessors
    private static Vector<Integer> d; // vector of distances
    private static Vector<Integer> d1; // vector of distances

    public BellmanFord() {
        p = new Vector<>();
        d = new Vector<>();
        d1 = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A, int sNumber) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }

        for (int i = 0; i < N.size(); i++) {
            p.add(0);
            d.add(999);
            d1.add(999);
        }

        Node s = Util.getNode(N, sNumber);
        d.set(s.getNumber() - 1, 0);

        boolean repeat;
        do {
            d1 = new Vector<>(d);

            for (Node y : N) {
                Arch arch = null;
                Node x = null;

                for (Arch a : A) {
                    Node n = a.getNodeStart();

                    if (a.getNodeEnd() == y) {
                        if (arch == null || d1.elementAt(n.getNumber() - 1) + a.getValue() <
                                d1.elementAt(x.getNumber() - 1) + arch.getValue()) {
                            arch = a;
                            x = n;
                        }
                    }
                }

                if (arch != null && d1.elementAt(x.getNumber() - 1) + arch.getValue() <
                        d1.elementAt(y.getNumber() - 1)) {
                    d.set(y.getNumber() - 1, d1.elementAt(x.getNumber() - 1) + arch.getValue());
                    p.set(y.getNumber() - 1, x.getNumber());
                }
            }

            repeat = false;
            for (int i = 0; i < N.size(); i++) {
                if (!d.elementAt(i).equals(d1.elementAt(i))) {
                    repeat = true;
                    break;
                }
            }
        } while (repeat);

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
