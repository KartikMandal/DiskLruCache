package com.kartik.gause;

import java.util.Random;
import static com.kartik.gause.GaussJordanElimination.isNotFeasible;

/**
 * This class implements a demonstration.
 */
public class Demo {

    private static final String GAY_BAR;

    static {
        final StringBuilder sb = new StringBuilder(80);

        for (int i = 0; i < 80; ++i) {
            sb.append('-');
        }

        GAY_BAR = sb.toString();
        
    }

    public static void main(final String... args) {
        helloWorldDemo();
        bar();
        laaargggeeeDemmoo();
    }

    private static void helloWorldDemo() {
        Matrix m = new Matrix(new double[][] {
            { 1.0, 3.0, -2.0, 5.0 }, 
            { 3.0, 5.0, 6.0, 7.0  }, 
            { 2.0, 4.0, 3.0, 8.0  }, 
        });

        System.out.println(m);

        int rank = GaussJordanElimination.solve(m);

        System.out.println(m);
        System.out.println("Rank: " + rank);
        System.out.println("Feasible: " + isNotFeasible(m));

        bar();

        m = new Matrix(new double[][] {
            { 1.0, 3.0, -2.0, 5.0 }, 
            { 3.0, 5.0, 6.0, 7.0  }, 
            { 4.0, 8.0, 6.0, 16.0 },
            { 2.0, 4.0, 3.0, 8.0  }, 
            { 1.0, 1.0, 1.0, -5.0 },
        });

        System.out.println(m);

        rank = GaussJordanElimination.solve(m);

        System.out.println(m);
        System.out.println("Rank: " + rank);
        System.out.println("Feasible: " + isNotFeasible(m));

        bar();

        m = new Matrix(new double[][] {
            { 1.0, 3.0, -2.0, 5.0 }, 
            { 3.0, 5.0, 6.0, 7.0  },
            { 3.0, 5.0, 6.0, 8.0  },
        });

        System.out.println(m);

        rank = GaussJordanElimination.solve(m);

        System.out.println(m);
        System.out.println("Rank: " + rank);
        System.out.println("Feasible: " + isNotFeasible(m));
    }

    private static void bar() {
        System.out.println(GAY_BAR);
    }

    private static void laaargggeeeDemmoo() {
        final long seed = System.currentTimeMillis();
        final Random rnd = new Random(seed);
        final Matrix m = new Matrix(1000, 500);

        System.out.println("Seed: " + seed);

        for (int r = 0; r < m.getHeight(); ++r) {
            for (int c = 0; c < m.getWidth(); ++c) {
                m.set(c, r, rnd.nextInt(101) - 50);
            }
        }

        long ta = System.currentTimeMillis();
        int rank = GaussJordanElimination.solve(m);
        long tb = System.currentTimeMillis();

        System.out.println("Rank: " + rank + ", time: " + (tb - ta) + " ms.");
        System.out.println("Feasible: " + isNotFeasible(m));
    }
}