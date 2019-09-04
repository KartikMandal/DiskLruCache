package com.kartik.org;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SolutionSolv {

    // Complete the maxSubarray function below.
    static int[] maxSubarray(int[] A) {
    				int n = A.length;
                    List<Integer> s=new ArrayList<>();
                    int path[] =new int[A.length];
                    // base case
                    if (n == 1) {
                        path[0]=A[0];
                        return path;
                    }
                    // create an auxiliary array to store solution of sub-problems
                    int[] lookup = new int[n];
                    // lookup[i] stores the maximum sum possible till index i
                    // trivial case
                    lookup[0] = A[0];
                    lookup[1] = Integer.max(A[0], A[1]);

                    // traverse array from index 2
                    for (int i = 2; i < n; i++)
                    {
                        // lookup[i] stores the maximum sum we get by
                        // 1. excluding current element & take maximum sum till index i-1
                        // 2. including current element A[i] and take maximum sum
                        //    till index i-2
                        lookup[i] = Integer.max(lookup[i - 1], lookup[i - 2] + A[i]);
                        // if current element is more than max sum till current element
                        lookup[i] = Integer.max(lookup[i], A[i]);
                    }
                    for (int i=n-1;i>=0;i=i-2) {
                        s.add(lookup[i]);
                    }
                    Collections.reverse(s);
                    path =new int[s.size()];
                    int start=0, count=0;
                    
                    for (int i : s) {
                        path[count]=i-start;
                        start=i;
                        count++;
                    }
                    
                    return path;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       // BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int[] result = maxSubarray(arr);

            System.out.println(Arrays.toString(result));
            /*for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();*/
        }

       // bufferedWriter.close();

        scanner.close();
    }
}