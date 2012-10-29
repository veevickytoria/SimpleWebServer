/*
 * Blacklist.java
 * Oct 28, 2012
 *
 * Simple Web Server (SWS) for EE407/507 and CS455/555
 * 
 * Copyright (C) 2011 Chandan Raj Rupakheti, Clarkson University
 * 
 * This program is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License 
 * as published by the Free Software Foundation, either 
 * version 3 of the License, or any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/lgpl.html>.
 * 
 * Contact Us:
 * Chandan Raj Rupakheti (rupakhcr@clarkson.edu)
 * Department of Electrical and Computer Engineering
 * Clarkson University
 * Potsdam
 * NY 13699-5722
 * http://clarkson.edu/~rupakhcr
 */
 
package server;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

/**
 * 
 * @author Chandan R. Rupakheti (rupakhcr@clarkson.edu)
 */
public class Blacklist {
	private ArrayList<InetAddress> list;
	private Logger log;
	
	public Blacklist()
	{
		this.log = Logger.getLogger(Blacklist.class);
		this.list = new ArrayList<InetAddress>();
		/*
		try {
			list.add(Inet4Address.getByAddress(getBytesFromAddress("10.0.0.5")));
			this.log.info("Added 10.0.0.5 to the blacklist");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		*/
	}
	
	public boolean isBlacklisted(InetAddress check)
	{
		if(list.contains(check))
			return true;
		return false;
	}
	
	public void addAddressToBlacklist(InetAddress add)
	{
		this.log.info(add.toString() + " added to blacklist");
		list.add(add);
	}
	
	/**
	 * Function to parse a string to a byte-array representing its IPv4 address.
	 * Used for testing.
	 * Taken from the Apache test library at: 
	 * http://svn.apache.org/repos/asf/james/server/trunk/dnsservice-library/src/test/java/org/apache/james/dnsservice/library/inetnetwork/model/InetNetworkTest.java
	 */
	private static byte[] getBytesFromAddress(String address)
	{
		if (address.contains(".")) {
            StringTokenizer st = new StringTokenizer(address, ".");
            byte[] bytes = new byte[st.countTokens()];
            int i = 0;
            while (st.hasMoreTokens()) {
                Integer inb = Integer.parseInt(st.nextToken());
                bytes[i] = inb.byteValue();
                i++;
            }
            return bytes;
        } else if (address.contains(":")) {
            StringTokenizer st = new StringTokenizer(address, ":");
            byte[] bytes = new byte[st.countTokens() * 2];
            int i = 0;
            while (st.hasMoreTokens()) {
                String token = st.nextToken();
                bytes[i] = (byte) Integer.parseInt(token.substring(0, 2), 16);
                i++;
                bytes[i] = (byte) Integer.parseInt(token.substring(2, 4), 16);
                i++;
            }
            return bytes;
        }
		return new byte[1];
	}
}
