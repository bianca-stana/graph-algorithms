package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Vector;

/**
 * Prim's Algorithm for determining a Minimum Spanning Tree
 */
public class MST_Prim {
    private static Vector<Arch> Amin; // the new list of arches
    private static Vector<Node> U; // the nodes that are not visited
    private static Vector<Node> V; // the nodes that are visited
    private static Vector<Integer> v; // the least value of any arch starting from 'i'
    private static Vector<Arch> e; // the arch starting from 'i' with the least value
    private static Vector<Vector<Arch>> E; // all arches starting from 'i'

    public MST_Prim() {
        Amin = new Vector<>();
        U = new Vector<>();
        V = new Vector<>();
        v = new Vector<>();
        e = new Vector<>();
        E = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }

        for (Node n : N) {
            E.add(new Vector<>());
            for (Arch a : A) {
                if (a.getNodeStart() == n || a.getNodeEnd() == n) {
                    E.elementAt(n.getNumber() - 1).add(a);
                }
            }
        }

        U.addAll(N);

        for (int i = 0; i < N.size(); i++) {
            v.add(999);
            e.add(new Arch(0));
        }

        v.set(0, 0);

        while (V.size() != N.size()) {
            Node y = U.firstElement();

            for (int i = 1; i < U.size(); i++) {
                Node x = U.elementAt(i);
                if (v.elementAt(x.getNumber() - 1) < v.elementAt(y.getNumber() - 1)) {
                    y = x;
                }
            }

            V.add(y);
            U.remove(y);

            if (y.getNumber() != N.firstElement().getNumber()) {
                Amin.add(e.elementAt(y.getNumber() - 1));
            }

            for (Arch a : E.elementAt(y.getNumber() - 1)) {
                Node aStart = a.getNodeStart();
                Node aEnd = a.getNodeEnd();

                if (aStart == y && U.contains(aEnd) || aEnd == y && U.contains(aStart)) {
                    Node x;
                    if (aStart == y) {
                        x = aEnd;
                    } else {
                        x = aStart;
                    }

                    if (v.elementAt(x.getNumber() - 1) > a.getValue()) {
                        v.set(x.getNumber() - 1, a.getValue());
                        e.set(x.getNumber() - 1, a);
                    }
                }
            }
        }

        System.out.print("N: ");
        for (Node node : N) {
            System.out.print(node.getNumber() + " ");
        }
        System.out.println();

        System.out.print("Amin: ");
        for (Arch a : Amin) {
            System.out.print("[" + a.getNodeStart().getNumber() + ", " +
                    a.getNodeEnd().getNumber() + "](" + a.getValue() + ") ");
        }
        System.out.println();
        System.out.println();
    }
}
