package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Vector;

/**
 * Generic Graph Traversal Algorithm
 */
public class GGT {
    private static Vector<Node> U; // the nodes that are not visited
    private static Vector<Node> V; // the nodes that are visited but not analyzed
    private static Vector<Node> W; // the nodes that are both visited and analyzed
    private static Vector<Integer> p; // vector of predecessors
    private static Vector<Integer> o; // vector of order

    public GGT() {
        U = new Vector<>();
        V = new Vector<>();
        W = new Vector<>();
        p = new Vector<>();
        o = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A, int sNumber) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }

        Node s = Util.getNode(N, sNumber);

        U.addAll(N);
        U.remove(s);
        V.add(s);

        for (int i = 0; i < N.size(); i++) {
            p.add(0);
            o.add(999);
        }

        int k = 1;
        o.set(s.getNumber() - 1, k);

        while (W.size() != N.size()) {
            while (!V.isEmpty()) {
                Node x = V.firstElement();

                boolean archExists = false;
                for (Arch a : A) {
                    if (a.getNodeStart() == x && U.contains(a.getNodeEnd())) {
                        Node y = a.getNodeEnd();

                        U.remove(y);
                        V.add(y);
                        p.set(y.getNumber() - 1, x.getNumber());
                        k++;
                        o.set(y.getNumber() - 1, k);

                        archExists = true;
                        break;
                    }
                }

                if (!archExists) {
                    V.remove(x);
                    W.add(x);
                }
            }

            if (!U.isEmpty()) {
                s = U.firstElement();
                U.remove(s);
                V.add(s);
                k++;
                o.set(s.getNumber() - 1, k);
            }
        }

        System.out.print("p: ");
        for (int nr : p) {
            System.out.print(nr + " ");
        }
        System.out.println();

        System.out.print("o: ");
        for (int nr : o) {
            System.out.print(nr + " ");
        }
        System.out.println();
        System.out.println();

        Vector<Integer> path = Util.path(p, N.lastElement().getNumber());
        System.out.print("The unique path from " + sNumber + " to "
                + N.lastElement().getNumber() + " in the resulted tree is: ");
        for (int node : path) {
            System.out.print(node + " ");
        }
        System.out.println();
        System.out.println();
    }
}
