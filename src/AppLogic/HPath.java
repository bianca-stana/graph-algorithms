package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Vector;

// TO DO: Debug the algorithm!
public class HPath {
    private Vector<Vector<Vector<Vector<String>>>> S;
    private Vector<Vector<Vector<Integer>>> Q;

    public HPath() {
        S = new Vector<>();
        Q = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }

        int n = N.size();

        for (int k = 0; k <= n; k++) {
            S.add(new Vector<>());
            Q.add(new Vector<>());

            for (int i = 0; i < n; i++) {
                S.elementAt(k).add(new Vector<>());
                Q.elementAt(k).add(new Vector<>());

                for (int j = 0; j < n; j++) {
                    S.elementAt(k).elementAt(i).add(new Vector<>());
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arch a = Util.getOrientedArch(A, i + 1, j + 1);

                if (a != null) {
                    S.elementAt(0).elementAt(i).elementAt(j).add(Integer.toString(j + 1));
                    S.elementAt(1).elementAt(i).elementAt(j).add(Integer.toString(i + 1));
                    S.elementAt(1).elementAt(i).elementAt(j).add(Integer.toString(j + 1));
                    Q.elementAt(1).elementAt(i).add(1);
                } else {
                    Q.elementAt(1).elementAt(i).add(0);
                }
            }
        }

        for (int r = 1; r < n - 1; r++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n; k++) {
                        if (!S.elementAt(r).elementAt(i).elementAt(k).isEmpty() &&
                                !S.elementAt(0).elementAt(k).elementAt(j).isEmpty()) {
                            int m = Q.elementAt(r).elementAt(i).elementAt(k);
                            for (int l = 0; l < m; l++) {
                                boolean ok = true;

                                for (char nr : S.elementAt(r).elementAt(i).elementAt(k).elementAt(l).toCharArray()) {
                                    if (S.elementAt(0).elementAt(k).elementAt(j).contains(Character.toString(nr))) {
                                        ok = false;
                                    }
                                }

                                if (ok) {
                                    S.elementAt(r + 1).elementAt(i).elementAt(k).add(S.elementAt(r).elementAt(i).elementAt(k).elementAt(l).concat(S.elementAt(0).elementAt(k).elementAt(j).elementAt(0)));
                                    S.elementAt(r + 1).elementAt(i).elementAt(k).add(S.elementAt(r + 1).elementAt(i).elementAt(k).elementAt(l));
                                }
                            }

                            S.elementAt(r + 1).elementAt(i).elementAt(j).addAll(S.elementAt(r + 1).elementAt(i).elementAt(k));
                        }
                    }

                    Q.elementAt(r + 1).elementAt(i).add(S.elementAt(r + 1).elementAt(i).elementAt(j).size());
                }
            }
        }

        for (int k = 0; k <= n; k++) {
            System.out.println("S(" + (k + 1) + "):");

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!S.elementAt(k).elementAt(i).elementAt(j).isEmpty()) {
                        System.out.print("{" + S.elementAt(k).elementAt(i).elementAt(j) + "} ");
                    } else {
                        System.out.print(" 0 ");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
