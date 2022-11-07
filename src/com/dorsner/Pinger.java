package com.dorsner;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pinger implements Runnable {

	private static Pinger pinger = null;

	public static Pinger getInstance() {
		if (pinger == null) {
			pinger = new Pinger();
		}
		return pinger;
	}

	public static void main(String[] args) {
		Pinger p = Pinger.getInstance();
		p.run();
	}

	@Override
	public void run() {
		try {
			while (true) {
				ping();
				Thread.sleep(15000);
			}
		} catch (InterruptedException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
    final String ipAddress = "www.cnn.com";
    InetAddress inet;
	long start;
	boolean reachable;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	private void ping () {
		try {
			start = System.currentTimeMillis();
			inet = InetAddress.getByName(ipAddress);
			reachable = inet.isReachable(1000);
			printResults(reachable, start, System.currentTimeMillis());
			
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}
	
	LocalDateTime now;  
	private void printResults(boolean reachable, long start, long stop) {
		now = LocalDateTime.now();
		if (reachable) {
			System.out.println(dtf.format(now) + " : " + String.valueOf(stop-start) );
		} else {
			System.err.println(dtf.format(now) + " : " + String.valueOf(stop-start) );
		}
	}

}
