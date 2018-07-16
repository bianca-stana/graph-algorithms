package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Vector;

public class FloydWarshall {
    private static Vector<Vector<Integer>> P; // matrix of predecessors
    private static Vector<Vector<Integer>> D; // matrix of distances
    private static Vector<Vector<Integer>> B; // matrix of values

    public FloydWarshall() {
        P = new Vector<>();
        D = new Vector<>();
        B = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }

        int n = N.size();

        for (int i = 0; i < n; i++) {
            B.add(new Vector<>());

            for (int j = 0; j < n; j++) {
                if (i == j) {
                    B.elementAt(i).add(0);
                } else {
                    Arch a = Util.getOrientedArch(A, i + 1, j + 1);

                    if (a != null) {
                        B.elementAt(i).add(a.getValue());
                    } else {
                        B.elementAt(i).add(999);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            P.add(new Vector<>());
            D.add(new Vector<>());

            for (int j = 0; j < n; j++) {
                D.elementAt(i).add(B.elementAt(i).elementAt(j));

                if (i != j && D.elementAt(i).elementAt(j) < 999) {
                    P.elementAt(i).add(i + 1);
                } else {
                    P.elementAt(i).add(0);
                }
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (D.elementAt(i).elementAt(k) + D.elementAt(k).elementAt(j) < D.elementAt(i).elementAt(j)) {
                        D.elementAt(i).set(j, D.elementAt(i).elementAt(k) + D.elementAt(k).elementAt(j));
                        P.elementAt(i).set(j, P.elementAt(k).elementAt(j));
                    }
                }
            }
        }

        System.out.println("P: ");
        for (Vector<Integer> row : P) {
            for (int nr : row) {
                System.out.print(nr + " ");
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("D: ");
        for (Vector<Integer> row : D) {
            for (int nr : row) {
                System.out.print(nr + " ");
            }
            System.out.println();
        }
        System.out.println();

        Vector<Integer> path = Util.path(P, 5, 2);
        System.out.print("The minimum path from " + 5 + " to " + 2 + " in the resulted tree is: ");
        for (int node : path) {
            System.out.print(node + " ");
        }
        System.out.println();

        System.out.print("The minimum distance from " + 5 + " to " + 2 + " is: " +
                D.elementAt(5 - 1).elementAt(2 - 1));
        System.out.println();
        System.out.println();
    }
}
