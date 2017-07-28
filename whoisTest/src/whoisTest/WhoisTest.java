package whoisTest;

import java.io.*;
import java.net.*;

public class WhoisTest {

	public static void main(String[] args)
	throws IOException
	{
		Socket s = new Socket("whois.internic.net", 43);
		
		InputStream in = s.getInputStream();
		OutputStream out = s.getOutputStream();
		
		System.out.println("Podaj nazwe domeny(adres) o ktorym chcesz uzyskac informacje: ");
		
		String address;
		BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
		address = rdr.readLine();
		address += "\n";
		
		byte addressByte[] = address.getBytes();
		
		out.write(addressByte);
		
		int c;
		while((c = in.read()) != -1)
			System.out.print((char) c);
		
		s.close();
	}

}
