package whoisTestRFCed;

import java.io.*;
import java.net.*;

public class WhoisTest implements whoisConstants {
	
	protected InputStream inStreamFromNet;
	protected OutputStream outStreamToNet;
	private String hostAddress;
	private Socket netSocket;
	
	protected void finalize() throws Exception {
		netSocket.close();
	}
	
	void displayResultAboutHost() throws Exception {
		int c;
		while((c = inStreamFromNet.read()) != -1)
			System.out.print((char) c);
	}
	
	private void setAddressHost(String addressHost) {
		hostAddress = addressHost;
	}

	public WhoisTest(String addressHost) throws Exception {
		netSocket = new Socket(whoisAddress, whoisPort);
		
		setAddressHost(addressHost);
		
		getStreamsFromSocket();
	}
	
	private void getStreamsFromSocket() throws Exception {
		inStreamFromNet = netSocket.getInputStream();
		outStreamToNet = netSocket.getOutputStream();
	}
	
	void writeAddressToWhois() throws Exception {
		hostAddress += '\n';
		byte addressHost[] = hostAddress.getBytes();
		
		outStreamToNet.write(addressHost);
	}
	
	public static String downloadAddress() throws IOException {
		return (new BufferedReader(new InputStreamReader(System.in))).readLine();
	}

	public static void main(String[] args)
	throws IOException {
			System.out.println("Podaj nazwe domeny(adres) o ktorym chcesz uzyskac informacje: ");
			
			try { 
			WhoisTest whInstance = new WhoisTest(downloadAddress());
			whInstance.writeAddressToWhois();
			whInstance.displayResultAboutHost();
			}
			catch(Exception exc) { exc.printStackTrace(); }
			
			
		}

}

interface whoisConstants {
	int whoisPort = 43;
	String whoisAddress = "whois.internic.net";
}