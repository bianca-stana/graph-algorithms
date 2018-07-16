package AppLogic;

import GUI.Arch;
import GUI.Node;
import sun.misc.Queue;

import java.util.Vector;

/**
 * Breadth First Traversal Algorithm (BFT)
 */
public class BFT {
    private static Vector<Node> U; // the nodes that are not visited
    private static Queue<Node> V; // the nodes that are visited but not analyzed
    private static Vector<Node> W; // the nodes that are both visited and analyzed
    private static Vector<Integer> p; // vector of predecessors
    private static Vector<Integer> l; // vector of lengths

    public BFT() {
        U = new Vector<>();
        V = new Queue<>();
        W = new Vector<>();
        p = new Vector<>();
        l = new Vector<>();
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

        V.enqueue(s);

        for (int i = 0; i < N.size(); i++) {
            p.add(0);
            l.add(999);
        }

        l.set(s.getNumber() - 1, 0);

        while (W.size() != N.size()) {
            while (!V.isEmpty()) {
                try {
                    Node x = V.dequeue();

                    for (Arch a : A) {
                        if (a.getNodeStart() == x) {
                            Node y = a.getNodeEnd();

                            if (U.contains(y)) {
                                U.remove(y);
                                V.enqueue(y);
                                p.set(y.getNumber() - 1, x.getNumber());
                                l.set(y.getNumber() - 1, l.elementAt(x.getNumber() - 1) + 1);
                            }
                        }
                    }

                    W.add(x);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (!U.isEmpty()) {
                s = U.firstElement();
                U.remove(s);
                V.enqueue(s);
                l.set(s.getNumber() - 1, 0);
            }
        }

        System.out.print("p: ");
        for (int nr : p) {
            System.out.print(nr + " ");
        }
        System.out.println();

        System.out.print("l: ");
        for (int nr : l) {
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
