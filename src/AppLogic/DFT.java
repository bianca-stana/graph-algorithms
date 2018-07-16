package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Stack;
import java.util.Vector;

/**
 * Depth First Traversal Algorithm (DFT)
 */
public class DFT {
    private static Vector<Node> U; // the nodes that are not visited
    private static Stack<Node> V; // the nodes that are visited but not analyzed
    private static Vector<Node> W; // the nodes that are both visited and analyzed
    private static Vector<Integer> p; // vector of predecessors
    private static Vector<Integer> t1; // vector of time
    private static Vector<Integer> t2; // vector of time

    public DFT() {
        U = new Vector<>();
        V = new Stack<>();
        W = new Vector<>();
        p = new Vector<>();
        t1 = new Vector<>();
        t2 = new Vector<>();
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
        V.push(s);

        for (int i = 0; i < N.size(); i++) {
            p.add(0);
            t1.add(999);
            t2.add(999);
        }

        int t = 1;
        t1.set(s.getNumber() - 1, t);

        while (W.size() != N.size()) {
            while (!V.empty()) {
                t++;

                Node x = V.lastElement();
                Node y = null;
                if (!U.isEmpty()) {
                    y = U.firstElement();
                }

                boolean archExists = false;
                if (y != null) {
                    Arch a = Util.getOrientedArch(A, x.getNumber(), y.getNumber());

                    if (a != null) {
                        U.remove(y);
                        V.push(y);
                        p.set(y.getNumber() - 1, x.getNumber());
                        t1.set(y.getNumber() - 1, t);

                        archExists = true;
                    }
                }

                if (!archExists) {
                    V.remove(x);
                    W.add(x);
                    t2.set(x.getNumber() - 1, t);
                }
            }

            if (!U.isEmpty()) {
                s = U.firstElement();
                U.remove(s);
                V.push(s);
                t++;
                t1.set(s.getNumber() - 1, t);
            }
        }

        System.out.print("p: ");
        for (int nr : p) {
            System.out.print(nr + " ");
        }
        System.out.println();

        System.out.print("t1: ");
        for (int nr : t1) {
            System.out.print(nr + " ");
        }
        System.out.println();

        System.out.print("t2: ");
        for (int nr : t2) {
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
