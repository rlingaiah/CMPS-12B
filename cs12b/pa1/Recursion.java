//-----------------------------------------------------------------------------
// Rhea Lingaiah
//rlingaia@ucsc.edu
//12B
//-----------------------------------------------------------------------------

class Recursion {
    // main()
    public static void main(String[] args) {

        int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
        //int[] A = {-1, 7,6,12,32};
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        int minIndex = minArrayIndex(A, 0, A.length - 1);
        int maxIndex = maxArrayIndex(A, 0, A.length - 1);
        reverseArray2(A, A.length, B);

        for (int x : A) System.out.print(x + " ");
        System.out.println();

        System.out.println("minIndex = " + minIndex);
        System.out.println("maxIndex = " + maxIndex);

        reverseArray1(A, A.length, B);
        for (int x : B) System.out.print(x + " ");
        System.out.println();

        reverseArray2(A, A.length, C);
        for (int x : C) System.out.print(x + " ");
        System.out.println();

        reverseArray3(A, 0, A.length - 1);
        for (int x : A) System.out.print(x + " ");
        System.out.println();

    }

    // reverseArray1()
    // Places the leftmost n elements of X[] into the rightmost n positions in
    // Y[] in reverse order
    static void reverseArray1(int[] X, int n, int[] Y) {
        int xtemp;
        if (n > 0) {
            xtemp = X[Y.length - n];
            Y[n - 1] = xtemp;
            reverseArray1(X, n - 1, Y);
        }
    }

    // reverseArray2()
    // Places the rightmost n elements of X[] into the leftmost n positions in
    // Y[] in reverse order.
    static void reverseArray2(int[] X, int n, int[] Y) {
        int xtemp;
        if (n > 0) {
            xtemp = X[n - 1];
            Y[Y.length - n] = xtemp;
            reverseArray2(X, n - 1, Y);
        }

    }

    // reverseArray3()
    // Reverses the subarray X[i...j].
    static void reverseArray3(int[] X, int i, int j) {
        int xtemp;
        if (i < j) {
            xtemp = X[i];
            X[i] = X[j];
            X[j] = xtemp;
            reverseArray3(X, i + 1, j - 1);
        }
    }

    // maxArrayIndex()
    // returns the index of the largest value in int array X
    static int maxArrayIndex(int[] X, int p, int r) {
        if (p < r) {
            if (X[p] < X[r]) {
                p++;

            } else {
                r--;
            }
            return maxArrayIndex(X, p, r);
        } else
            return p;
    }

    // minArrayIndex()
    // returns the index of the smallest value in int array X
    static int minArrayIndex(int[] X, int p, int r) {
        if (p < r) {
            if (X[p] < X[r]) {
                r--;
            } else {
                p++;
            }
            return minArrayIndex(X, p, r);
        } else
            return p;
    }

}