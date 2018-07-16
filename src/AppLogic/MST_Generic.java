package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Vector;

/**
 * Generic Algorithm for determining a Minimum Spanning Tree
 */
public class MST_Generic {
    private static Vector<Arch> Amin; // the new list of arches
    private static Vector<Node> V; // the nodes that are visited
    private static Vector<Node> U; // the nodes that are not visited

    public MST_Generic() {
        Amin = new Vector<>();
        V = new Vector<>();
        U = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }

        int n = N.size();

        V.add(N.firstElement());
        for (int i = 1; i < n; i++) {
            U.add(N.elementAt(i));
        }

        for (int i = 1; i < n; i++) {
            Node node = null;
            Arch arch = new Arch(100);
            for (Node x : V) {
                for (Node y : U) {
                    Arch a = Util.getArch(A, x.getNumber(), y.getNumber());

                    if (a != null && a.getValue() < arch.getValue()) {
                            arch = a;
                            node = y;
                    }
                }
            }

            V.add(node);
            U.remove(node);

            Amin.add(arch);
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
