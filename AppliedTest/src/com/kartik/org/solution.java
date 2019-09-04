package com.kartik.org;

import java.util.Scanner;

public class solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			// (This is the number of test cases)
			int t = scanner.nextInt();
			// 1 <= < t <= 100
			if (t <= 0 && t > 100) {
				return;
			}
			// (This is the size of search strings)
			int s = 6;

			// System.out.println(T);
			for (int j = 0; j < t; j++) {
				// (This is the number of search query strings + top strings)
				int c = scanner.nextInt();
				// 1 <= < c <= 10000
				if (c <= 0 && c > 10000) {
					return;
				} else {
					// (Number appearing next to top)
					String[] names = new String[c];
					for (int k = 0; k < c; k++) {
						Scanner scan = new Scanner(System.in);
						String name = "";
						name += scan.nextLine();
						// 1 <= n <= 1000
						if (name.length() <= 0 && name.length() > s) {
							return;
						} else if (name.toLowerCase().toString().trim()
								.startsWith("top")) {
							String sp[] = name.split("\\s+");
							if (Integer.parseInt(sp[1]) < 0
									&& Integer.parseInt(sp[1]) > 1000) {
								return;
							} else {
								names[k] = name.trim();
								searchObjectPrint(names, names.length,
										Integer.parseInt(sp[1]));
							}
						} else {
							names[k] = name.trim();
						}
					}

				}

			}

		} catch (Exception e) {
			System.err.println("Exception occur");
		}
	}

	static void searchObjectPrint(String[] names, int size, int n) {
		String temp;
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (names[i].compareTo(names[j]) > 0) {
					temp = names[i];
					names[i] = names[j];
					names[j] = temp;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			System.out.printf(names[i] + " ");
		}

	}

}
