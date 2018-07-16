package AppLogic;

import GUI.Arch;
import GUI.Node;

import java.util.Collections;
import java.util.Vector;

public class Util {

    public static Arch getArch(Vector<Arch> A, int x, int y) {
        Arch arch = null;

        for (Arch a : A) {
            if (a.getNodeStart().getNumber() == x && a.getNodeEnd().getNumber() == y ||
                    a.getNodeStart().getNumber() == y && a.getNodeEnd().getNumber() == x) {
                arch = a;
                break;
            }
        }

        return arch;
    }

    public static Arch getOrientedArch(Vector<Arch> A, int x, int y) {
        Arch arch = null;

        for (Arch a : A) {
            if (a.getNodeStart().getNumber() == x && a.getNodeEnd().getNumber() == y) {
                arch = a;
                break;
            }
        }

        return arch;
    }

    public static Node getNode(Vector<Node> N, int nr) {
        Node node = null;

        for (Node n : N) {
            if (n.getNumber() == nr) {
                node = n;
                break;
            }
        }

        return node;
    }

    public static Vector<Integer> path(Vector<Integer> p, int x) {
        Vector<Integer> path = new Vector<>();
        path.add(x);

        while (p.elementAt(x - 1) != 0) {
            x = p.elementAt(x - 1);
            path.add(x);
        }

        Collections.reverse(path);

        return path;
    }

    public static Vector<Integer> path(Vector<Vector<Integer>> P, int i, int j) {
        int k = P.size();

        Vector<Integer> path = new Vector<>();
        for (int h = 0; h < k; h++) {
            path.add(0);
        }

        path.set(k - 1, j);

        while (path.elementAt(k - 1) != i) {
            path.set(k - 2, P.elementAt(i - 1).elementAt(path.elementAt(k - 1) - 1));
            k--;
        }

        for (int h = k; h >= 0; h--) {
            if (path.elementAt(h) == 0) {
                path.removeElementAt(h);
            }
        }

        return path;
    }
}
