package com.kartik.org;
/**
 * https://www.techiedelight.com/print-continuous-subarray-with-maximum-sum/
 * @author kmandal
 *
 */
public class ArrayMaximumContinousSumPrint {

		
	// Function to print contiguous subarray with the largest sum
		// in given set of integers
		public static void kadane(int[] A)
		{
			// stores maximum sum sub-array found so far
			int maxSoFar = 0;

			// stores maximum sum of sub-array ending at current position
			int maxEndingHere = 0;

			// stores end-points of maximum sum sub-array found so far
			int start = 0, end = 0;

			// stores starting index of a positive sum sequence
			int beg = 0;

			// traverse the given array
			for (int i = 0; i < A.length; i++)
			{
				// update maximum sum of sub-array "ending" at index i
				maxEndingHere = maxEndingHere + A[i];

				// if maximum sum is negative, set it to 0
				if (maxEndingHere < 0)
				{
					maxEndingHere = 0;	// empty sub-array
					beg = i + 1;
				}

				// update result if current sub-array sum is found to be greater
				if (maxSoFar < maxEndingHere)
				{
					maxSoFar = maxEndingHere;
					start = beg;
					end = i;
				}
			}

			System.out.println("The sum of contiguous sub-array with the " +
								"largest sum is " +	maxSoFar);

			System.out.print("The contiguous sub-array with the largest sum is ");
			for (int i = start; i <= end; i++) {
				System.out.print(A[i] + " ");
			}
		}

		// main function
		public static void main(String[] args)
		{
			int[] A = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
			kadane(A);
		}

}
