package com.kartik.hacker;

import java.util.Arrays;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    static final int MAX_CHARS = 256;

    // Function to find smallest window containing
    // all distinct characters
    public static int shortestSubstring(String s) {
        int n = s.length();

        // Count all distinct characters.
        int dist_count = 0;

        boolean[] visited = new boolean[MAX_CHARS];
        Arrays.fill(visited, false);
        for (int i = 0; i < n; i++) {
            if (visited[s.charAt(i)] == false) {
                visited[s.charAt(i)] = true;
                dist_count++;
            }
        }

        int start = 0, start_index = -1;
        int min_len = Integer.MAX_VALUE;

        int count = 0;
        int[] curr_count = new int[MAX_CHARS];
        for (int j = 0; j < n; j++) {
            // Count occurrence of characters of string
            curr_count[s.charAt(j)]++;

            // If any distinct character matched,
            // then increment count
            if (curr_count[s.charAt(j)] == 1)
                count++;

            // if all the characters are matched
            if (count == dist_count) {
                // Try to minimize the window i.e., check if
                // any character is occurring more no. of times
                // than its occurrence in pattern, if yes
                // then remove it from starting and also remove
                // the useless characters.
                while (curr_count[s.charAt(start)] > 1) {
                    if (curr_count[s.charAt(start)] > 1)
                        curr_count[s.charAt(start)]--;
                    start++;
                }

                // Update window size
                int len_window = j - start + 1;
                if (min_len > len_window) {
                    min_len = len_window;
                    start_index = start;
                }
            }
        }
        // Return substring starting from start_index
        // and length min_len
        return s.substring(start_index, start_index + min_len).length();
    }
}
public class Sol {
    public static void main(String[] args) throws IOException {
       /* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();*/
    	String s="aabcbcdbca"; 

        int result = Result.shortestSubstring(s);
        System.out.println(result);
       /* bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();*/
    }
}
