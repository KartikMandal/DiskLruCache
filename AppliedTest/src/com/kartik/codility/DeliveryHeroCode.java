package com.kartik.codility;

import java.util.Scanner;

public class DeliveryHeroCode {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			// First line contains T which indicates the number of days for
			// which the delivery is to be made.
			int T = scanner.nextInt();
			// 1 <= T <= 400
			if (T <= 0 && T > 400) {
				return;
			}
			// System.out.println(T);
			for (int j = 0; j < T; j++) {
				// Second line contains M + 1 space separated integers
				// in which the first integer denotes the number of cakes to be
				// made in one day
				// and the following M integers denote the weight of each cake.
				int M = scanner.nextInt();
				// 1 <= M <= 1000
				if (M <= 0 && M > 1000) {
					return;
				} else {
					// System.out.printf(M+" ");
					int[] cakeWeight = new int[M];
					for (int k = 0; k < M; k++) {
						int weight = scanner.nextInt();
						// 1 <= weight of each cake <= 1000
						if (weight <= 0 && weight > 1000) {
							return;
						} else {
							// System.out.printf(weight+" ");
							cakeWeight[k] = weight;
						}
					}
					// System.out.println();
					int N = scanner.nextInt();
					// 1 <= N <= 10000
					if (N <= 0 && N > 1000) {
						return;
					}
					// System.out.println(N);

					if (isSubsetSum(cakeWeight, M, N) == true) {
						System.out.println("YES");
					} else {
						System.out.println("NO");
					}
				}

			}

		} catch (Exception e) {
			System.err.println("Exception occur");
		}
	}

	/**
	 * 
	 * @param cakeWeight
	 * @param numberOfCakes
	 * @param deliveryCakeWight
	 * @return
	 */
	static boolean isSubsetSum(int cakeWeight[], int numberOfCakes,
			int deliveryCakeWight) {
		// Base Cases
		if (deliveryCakeWight == 0)
			return true;
		if (numberOfCakes == 0 && deliveryCakeWight != 0)
			return false;

		// If last element is greater than
		// sum, then ignore it
		if (cakeWeight[numberOfCakes - 1] > deliveryCakeWight)
			return isSubsetSum(cakeWeight, numberOfCakes - 1, deliveryCakeWight);

		/*
		 * else, check if sum can be obtained by any of the following (a)
		 * including the last element (b) excluding the last element
		 */
		return isSubsetSum(cakeWeight, numberOfCakes - 1, deliveryCakeWight)
				|| isSubsetSum(cakeWeight, numberOfCakes - 1, deliveryCakeWight
						- cakeWeight[numberOfCakes - 1]);
	}

}
