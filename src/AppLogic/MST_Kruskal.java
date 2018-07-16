package AppLogic;

import GUI.Arch;
import GUI.Node;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.Vector;

/**
 * Kruskal's Algorithm for determining a Minimum Spanning Tree
 */
public class MST_Kruskal {
    private static Vector<Arch> Amin; // the new list of arches
    private static Vector<MutablePair<Integer, Integer>> subsets;

    public MST_Kruskal() {
        Amin = new Vector<>();
        subsets = new Vector<>();
    }

    public void start(Vector<Node> N, Vector<Arch> A) {
        if (N == null || N.isEmpty()) {
            throw new IllegalArgumentException("N");
        }

        if (A == null || A.isEmpty()) {
            throw new IllegalArgumentException("A");
        }



        // Step 1: Sort all the arches in non-decreasing order of their weight
        Arch.sort(A);

        // Create |N| subsets with single elements
        for (int i = 0; i <= N.size(); i++) {
            subsets.add(new MutablePair<>(i, 0));
        }

        int i = 0; // An index variable, used for A
        int j = 0; // An index variable, used for Amin

        // The number of arches to be taken is equal to |N| - 1
        while (j < N.size() - 1) {
            // Step 2: Pick the smallest arch, and increment the index for the next iteration
            Arch arch = A.elementAt(i++);

            int x = find(arch.getNodeStart().getNumber());
            int y = find(arch.getNodeEnd().getNumber());

            // If including this arch doesn't cause any cycle, then include it in Amin
            // and increment the index of Amin for the next arch
            if (x != y) {
                Amin.add(arch);
                union(x, y);
                j++;
            }
            // Else, discard the arch
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

    // A utility function to find set of an element i
    // (uses path compression technique)
    private static int find(int i)
    {
        // find root and make root as parent of i (path compression)
        if (subsets.elementAt(i).getLeft() != i)
            subsets.elementAt(i).setLeft(find(subsets.elementAt(i).getLeft()));

        return subsets.elementAt(i).getLeft();
    }

    // A function that does union of two sets of x and y
    // (uses union by rank)
    private static void union(int x, int y) {
        int xroot = find(x);
        int yroot = find(y);

        // Attach smaller rank tree under root of high rank tree
        // (union by rank)
        if (subsets.elementAt(xroot).getRight() < subsets.elementAt(yroot).getRight()){
            subsets.elementAt(xroot).setLeft(yroot);
        } else if (subsets.elementAt(xroot).getRight() > subsets.elementAt(yroot).getRight()) {
            subsets.elementAt(yroot).setLeft(xroot);
        } else {
            // If ranks are same, then make one as root and increment its rank by one
            subsets.elementAt(yroot).setLeft(xroot);
            subsets.elementAt(xroot).setRight(subsets.elementAt(xroot).getRight() + 1);
        }
    }
}
