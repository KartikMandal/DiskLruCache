package com.mandal.org;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIpAddress {

	public static void main(String[] args) throws UnknownHostException {

		// print the IP Address of your machine (inside your local network)
		System.out.println(InetAddress.getLocalHost().getHostAddress());
		
		//System.out.println(InetAddress.getByName("http://10.79.4.168/sps/"));

		// print the IP Address of a web site
		System.out.println(InetAddress.getByName("www.javacodegeeks.com"));

		// print all the IP Addresses that are assigned to a certain domain
		InetAddress[] inetAddresses = InetAddress.getAllByName("www.google.com");

		for (InetAddress ipAddress : inetAddresses) {
			System.out.println(ipAddress);
		}
	}
}
