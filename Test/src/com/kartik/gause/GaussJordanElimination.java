package com.kartik.gause;

import static com.kartik.gause.Utils.checkNotInfinite;
import static com.kartik.gause.Utils.checkNotNaN;
import static com.kartik.gause.Utils.checkNotNegative;
import static com.kartik.gause.Utils.checkNotNull;

/**
 * This class provides a static method performing Gauss-Jordan elimination on an
 * input matrix.
 */
public class GaussJordanElimination {

    /**
     * Defines the default epsilon for comparison.
     */
    private static final double DEFAULT_EPSILON = 1E-6;

    /**
     * Defines the sentinel value for any index of a non-valid row.
     */
    private static final int ROW_NOT_FOUND = -1;

    /**
     * Caches the actual matrix.
     */
    private final double[][] content;

    /**
     * The epsilon value for comparisons.
     */
    private final double epsilon;

    /**
     * Performs Gauss-Jordan elimination on the input matrix using given 
     * epsilon.
     * 
     * @param matrix  the matrix to eliminate.
     * @param epsilon the epsilon value used for comparisons.
     * @return        the rank of the resulting matrix.
     */
    public static int solve(final Matrix matrix, final double epsilon) {
        return new GaussJordanElimination(matrix.content, epsilon).eliminate();
    }

    /**
     * Performs Gauss-Jordan elimination on the input matrix using default 
     * epsilon.
     * 
     * @param matrix the matrix to eliminate.
     * @return       the rank of the resulting matrix.
     */
    public static int solve(final Matrix matrix) {
        return solve(matrix, DEFAULT_EPSILON);
    }

    /**
     * Returns <code>true</code> if it is certain that the system of linear 
     * equations represented by the input matrix has no solutions. If there is 
     * a chance of feasibility, returns <code>false</code>. Uses the default 
     * epsilon.
     * 
     * @param matrix  the matrix to check.
     * @return <code>true</code> if there is no solution of the system
     *         represented by the matrix and otherwise <code>false</code> is 
     *         returned.
     */
    public static boolean isNotFeasible(final Matrix matrix) {
        return isNotFeasible(matrix, DEFAULT_EPSILON);
    }

    /**
     * Returns <code>true</code> if it is certain that the system of linear 
     * equations represented by the input matrix has no solutions. If there is 
     * a chance of feasibility, returns <code>false</code>.
     * 
     * @param matrix  the matrix to check.
     * @param epsilon the comparison epsilon.
     * @return <code>true</code> if there is no solution of the system
     *         represented by the matrix and otherwise <code>false</code> is 
     *         returned.
     */
    public static boolean isNotFeasible(final Matrix matrix,
                                        final double epsilon) {
        checkNotNaN(epsilon, "The input epsilon is NaN.");
        checkNotInfinite(epsilon, "The input epsilon is infinite: " + epsilon);
        checkNotNegative(epsilon, "The input epsilon is negative: " + epsilon);

        outer:
        for (int r = 0; r < matrix.getHeight(); ++r) {
            for (int c = 0; c < matrix.getWidth() - 1; ++c) {
                if (!isInEpsilonRange(0.0, matrix.get(c, r), epsilon)) { 
                    continue outer;
                }
            }

            if (!isInEpsilonRange(0.0, 
                               matrix.get(matrix.getWidth() - 1, r), 
                               epsilon)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Constructs this eliminator.
     * 
     * @param content       the matrix to eliminate.
     * @param epsilon the epsilon value for comparisons.
     */
    private GaussJordanElimination(final double[][] content, final double epsilon) {
        checkNotNull(content, "The input matrix is null.");
        checkNotNaN(epsilon, "The input epsilon is NaN.");
        checkNotInfinite(epsilon, "The input epsilon is infinite: " + epsilon);
        checkNotNegative(epsilon, "The input epsilon is negative: " + epsilon);

        this.content = content;
        this.epsilon = epsilon;
    }

    /**
     * Performs the actual elimination.
     * 
     * @return the rank of the resulting matrix.
     */
    private int eliminate() {
        int rowsProcessed = 0;

        for (int k = 0; k != content[0].length; ++k) {
            int ur = findUpmostRowWithPivotAtColumn(k, rowsProcessed);

            if (ur == ROW_NOT_FOUND) {
                continue;
            }

            swapRows(ur, rowsProcessed);
            scaleRow(rowsProcessed, 1.0 / content[rowsProcessed][k]);

            for (int r = 0; r != content.length; ++r) {
                if (r != rowsProcessed) {
                    addToRowMultipleOfAnotherRow(
                            r,  
                            rowsProcessed, 
                            -content[r][k] / content[rowsProcessed][k]);
                }
            }

            ++rowsProcessed;
        }

        return rowsProcessed;
    }

    /**
     * Returns <code>true</code> if <code>a</code> and <code>b</code> are within
     * <code>epsilon</code> from each other.
     * 
     * @param a       the first value.
     * @param b       the second value.
     * @param epsilon the maximum allowed distance.
     * @return        <code>true</code> if <code>a</code> and <code>b</code> are
     *                within <code>epsilon</code> from each other.
     */
    public static boolean isInEpsilonRange(final double a, 
                                        final double b,
                                        final double epsilon) {
        return Math.abs(a - b) <= epsilon;
    }

    /**
     * Checks the multiplication factor.
     * 
     * @param factor the factor value to check.
     * @throws IllegalArgumentException if the factor is <code>NaN</code> or is
     *                                  infinite.
     */
    private static void checkFactor(final double factor) {
        checkNotNaN(factor, "The factor is NaN.");
        checkNotInfinite(factor, "The factor is infinite: " + factor);
    }

    /**
     * Implements an elementary matrix operation of adding a multiple of one row
     * to another.
     * 
     * @param targetRowIndex the index of the row to which to add.
     * @param sourceRowIndex the index of the row which is added.
     * @param factor         the factor by which to multiply each entry of the
     *                       source row.
     */
    private void addToRowMultipleOfAnotherRow(final int targetRowIndex,
                                              final int sourceRowIndex,
                                              final double factor) {
        checkFactor(factor);
        for (int i = 0; i != content[0].length; ++i) {
            content[targetRowIndex][i] += content[sourceRowIndex][i] * factor;
        }
    }

    /**
     * Swaps to rows with given indices.
     * 
     * @param firstRowIndex the index of a row.
     * @param secondRowIndex the index of another row.
     */
    private void swapRows(final int firstRowIndex, final int secondRowIndex) {
        final double[] tmp = content[firstRowIndex];
        content[firstRowIndex] = content[secondRowIndex];
        content[secondRowIndex] = tmp;
    }

    /**
     * Skips the first <code>skipRows</code> rows in the matrix and returns 
     * the index of a row containing non-zero value at column 
     * <code>columnIndex</code>.
     * 
     * @param columnIndex the index of the target column.
     * @param skipRows    the amount of uppermost rows to skip.
     * @return            a row index.
     */
    private int findUpmostRowWithPivotAtColumn(final int columnIndex,
                                               final int skipRows) {
        for (int i = skipRows; i < content.length; ++i) {
            if (!isInEpsilonRange(content[i][columnIndex], 0.0, epsilon)) {
                return i;
            }
        }

        return ROW_NOT_FOUND;
    }

    /**
     * Multiplies each entry of the specified matrix row by a given factor.
     * 
     * @param rowIndex the index of the row.
     * @param factor   the multiplication factor.
     */
    private void scaleRow(final int rowIndex, final double factor) {
        checkFactor(factor);
        final double[] row = content[rowIndex];
        for (int i = 0; i != row.length; ++i) {
            row[i] *= factor;
        }
    }   
}