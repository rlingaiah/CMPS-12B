/**
 * Rhea Lingaiah
 * rlingaia@ucsc.edu
 * 12m LAB7
 */
public class Dictionary implements DictionaryInterface {
    private class Node {
        Pair item;
        Node right;
        Node left;

        Node(Pair x) {
            item = x;
            right = null;
            left = null;
        }
    }

    private class Pair {
        String key;
        String value;

        Pair(String x, String y) {
            key = x;
            value = y;
        }
    }

    private Node root;
    private int numItems;

    public Dictionary() {
        root = null;
        numItems = 0;
    }

    private Node findkey(Node X, String target) {
        if (X == null || X.item.key.equals(target))
            return X;
        if (X.item.key.compareToIgnoreCase(target) > 0)
            return findkey(X.left, target);
        else
            return findkey(X.right, target);

    }

    Node findParent(Node N, Node X) {
        Node P = null;
        if (N != X) {
            P = X;
            while (P.left != N && P.right != N) {
                if (N.item.key.compareToIgnoreCase(P.item.key) < 0)
                    P = P.left;
                else
                    P = P.right;
            }
        }
        return P;
    }

    Node findLeftmost(Node X) {
        Node L = X;
        if (L != null) for (; L.left != null; L = L.left) ;
        return L;
    }

    void printInOrder(Node X) {
        if (X != null) {
            printInOrder(X.left);
            System.out.println(X.item.key + " " + X.item.value);
            printInOrder(X.right);
        }
    }

    void deleteAll(Node N) {
        if (N != null) {
            deleteAll(N.left);
            deleteAll(N.right);
        }
    }

    public boolean isEmpty() {
        return (numItems == 0);
    }

    public int size() {
        return numItems;
    }

    public String lookup(String key) {
        Node N;
        N = findkey(root, key);
        return (N == null ? null : N.item.value);

    }

    public void insert(String key, String value) throws DuplicateKeyException {
        Node A, B, N;
        if (findkey(root, key) != null) {
            throw new DuplicateKeyException("Dictionary Error: insert() can't insert duplicate keys");
        }
            N = new Node(new Pair(key, value));
            B = null;
            A = root;
            while (A != null) {
                B = A;
                if (A.item.key.compareToIgnoreCase(key) > 0)
                    A = A.left;
                else
                    A = A.right;
            }
            if (B == null)
                root = N;
            else if (B.item.key.compareToIgnoreCase(key) > 0)
                B.left = N;
            else B.right = N;
            numItems++;

        }


    public void delete(String key) throws KeyNotFoundException {
        Node P, S, N;
        if (findkey(root, key) == null) {
            throw new KeyNotFoundException("Dictionary Error:delete() can't delete non-existent keys");
        }
        N = findkey(root, key);
        if (N.left == null && N.right == null) {
            if (N == root) {
                root = null;

            } else {
                P = findParent(N, root);
                if (P.right == N)
                    P.right = null;
                else
                    P.left = null;
            }
        }
            else if (N.right == null) {
                if (N == root) {
                    root = N.left;
                } else {
                    P = findParent(N, root);
                    if (P.right == N)
                        P.right = N.left;
                    else
                        P.left = N.left;
                }
            } else if (N.left == null) {
                if (N == root) {
                    root = N.right;
                } else {
                    P = findParent(N, root);
                    if (P.right == N) P.right = N.right;
                    else P.left = N.right;
                }
            } else {
                S = findLeftmost(N.right);
                N.item.key = S.item.key;
                N.item.value = S.item.value;
                P = findParent(S, N);
                if (P.right == S) P.right = S.right;
                else P.left = S.right;
            }
            numItems--;
        }


    public void makeEmpty() {
        deleteAll(root);
        root = null;
        numItems = 0;
    }

    public String toString() {
        printInOrder(root);
        return "";
    }
}



