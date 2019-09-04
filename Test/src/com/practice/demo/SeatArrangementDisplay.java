package com.practice.demo;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 
 * @author kmandal
 *
 */
public class SeatArrangementDisplay {

	public static void main(String[] args) {
		String seats[] = { "10A", "3C", "2E", "3A", "4C", "4F", "3B", "9G",
				"10B", "10C", "10E", "2A", "2C", "2D", "3E", "1A", "3D", "2B",
				"98A", "98B", "98C" };
		SeatArrangementDisplay seat = new SeatArrangementDisplay();
		System.out.println(seat.seatArrangementList(seats));// seat.seatArrangementList(seats);
		

	}

	/**
	 *
	 * @param str
	 *            str.
	 * @return Boolean.FALSE.
	 */
	public boolean stringSequenceValidation(String str) {
		boolean b = Boolean.FALSE;
		if ((str != null) && (str.trim().length() > 1)) {
			// byte[] ary = str.toUpperCase()
			// .getBytes(Charset.forName(“US-ASCII”));
			byte[] ary = str.toUpperCase().getBytes(
					Charset.forName("iso-8859-1"));

			for (int i = 1; i < ary.length; i++) {
				if ((ary[i - 1] + 1) == ary[i]) {
					if (Character.isLetter(str.charAt(i - 1))) {
						b = Boolean.TRUE;
					}
				} else {
					b = Boolean.FALSE;
					break;
				}
			}
		}
		return b;
	}

	/**
	 *
	 * @param seats
	 *            seats.
	 * @return string data.
	 */
	public String seatArrangementList(String[] seats) {
		// String
		// seats[]={"4F","10A","10E","2A","2C","10C","2E","3A","4C","2D"};
		List<String> wordList = Arrays.asList(seats);
		ArrayList tempList = new ArrayList();
		Map<String, HashMap<String, ArrayList>> map = new HashMap<String, HashMap<String, ArrayList>>();
		HashMap<String, ArrayList> map2 = new HashMap<String, ArrayList>();
		HashMap<String, ArrayList> map3 = new HashMap<String, ArrayList>();
		Collections.sort(wordList);
		String tempSequence = null;
		int count = 0;
		String rowIndex = null;
		int countMap2 = 0;
		int countMap3 = 0;
		for (String string : wordList) {
			String squence = null;
			String tempRowIndex = null;
			squence = string.substring(string.length() - 1);
			tempRowIndex = string.substring(0, string.length() - 1);
			if (count == 0) {
				tempList.add(string);
				tempSequence = squence;
				count++;
			} else if (rowIndex.equals(tempRowIndex) && count > 0) {
				tempSequence += squence;
				boolean flagData = stringSequenceValidation(tempSequence);
				if (flagData) {
					tempList.add(string);
					count++;
				} else {
					countMap2++;
					map2.put(String.valueOf(countMap2), tempList);
					tempList = null;
					tempList = new ArrayList();
					tempList.add(string);
					tempSequence = squence;
					count = 0;
					count++;
				}
			} else if (!rowIndex.equals(tempRowIndex)) {
				if (map2.containsKey(rowIndex)) {
					countMap3++;
					map3.put(String.valueOf(countMap3), tempList);
					map.put(rowIndex, map3);
					map3 = null;
					map3 = new HashMap<String, ArrayList>();
					countMap3 = 0;
				} else {
					countMap2++;
					map2.put(String.valueOf(countMap2), tempList);
					map.put(rowIndex, map2);
					map2 = null;
					map2 = new HashMap<String, ArrayList>();
					countMap2 = 0;
				}
				tempList = null;
				tempList = new ArrayList();
				tempList.add(string);
				tempSequence = squence;
				count = 0;
				count++;
			}

			rowIndex = tempRowIndex;
		}
		countMap2++;
		map2.put(String.valueOf(countMap2), tempList);
		map.put(rowIndex, map2);
		map2 = null;
		map3 = null;
		tempList = null;
		Map<String, HashMap<String, ArrayList>> treeMap = new TreeMap<String, HashMap<String, ArrayList>>(
				map);

		StringBuffer sb = new StringBuffer();
		for (Object key : treeMap.keySet()) {
			HashMap<String, ArrayList> mapData = new HashMap<String, ArrayList>();
			mapData = map.get(key);
			for (Object keyValue : mapData.keySet()) {
				ArrayList<String> listData = new ArrayList<String>();
				listData = mapData.get(keyValue);
				Collections.sort(listData);
				int sizeVal = listData.size();
				int countData = 0;
				for (String string : listData) {
					if (countData == 0) {
						sb.append(string);
						sb.append(" ");
					} else if (countData == sizeVal - 1) {
						sb.append(string);
						sb.append(" ");
					} else if (countData == 1) {
						sb.delete(sb.toString().length() - 1, sb.toString()
								.length());
						sb.append("-");
					}
					countData++;
				}
			}
		}

		StringBuffer br = new StringBuffer();
		String[] splits = sb.toString().split(" ");
		Arrays.sort(splits);
		Map unsortedMap = new HashMap();
		int countSplit = 0;
		long value = 0;
		for (String asset : splits) {
			String[] split = asset.split("-");
			if (split.length > 1) {
				for (String newSplit : split) {
					if (countSplit == 0) {
						byte[] bytes = newSplit.getBytes();
						for (int i = 0; i < bytes.length; i++) {
							value = (value << 8) + (bytes[i] & 0xff);
						}
					}
					countSplit++;
				}
			} else {
				byte[] bytes = asset.getBytes();

				for (int i = 0; i < bytes.length; i++) {
					value = (value << 8) + (bytes[i] & 0xff);
				}
			}
			countSplit = 0;
			unsortedMap.put(String.valueOf(value), asset);
			value = 0;
		}

		Map<String, byte[]> treeMapSort = new TreeMap<String, byte[]>(
				unsortedMap);
		for (Map.Entry entry : treeMapSort.entrySet()) {
			br.append(entry.getValue()).append(" ");
		}
		br.delete(br.toString().length() - 1, br.toString().length());
		return br.toString();

	}

}
