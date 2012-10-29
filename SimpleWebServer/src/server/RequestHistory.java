/*
 * RequestHistory.java
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
import java.util.Date;
import java.util.HashMap;

/**
 * 
 * Assumes files in the server don't change
 * 
 * @author Michael Eaton (eatonmi@rose-hulman.edu)
 */
public class RequestHistory {
	
	private HashMap<InetAddress, AddressHistory> requests;
	
	public RequestHistory()
	{
		this.requests = new HashMap<InetAddress, AddressHistory>();
	}
	
	public boolean hasBeenRequested(String uri, InetAddress address)
	{
		if(this.requests.containsKey(address))
		{
			AddressHistory history = this.requests.get(address);
			if(history.hasBeenRequested(uri))
			{
				return true;
			}
			else
			{
				history.addRequest(uri);
				return false;
			}
		}
		else
		{
			AddressHistory newEntry = new AddressHistory(/*address*/);
			newEntry.addRequest(uri);
			this.requests.put(address, newEntry);
			return false;
		}
			
	}
	
	private class AddressHistory
	{
		//public InetAddress address;
		public HashMap<String, Date> requestTimestamps;
		
		AddressHistory(/*InetAddress address*/)
		{
			//this.address = address;
			this.requestTimestamps = new HashMap<String, Date>();
		}
		
		boolean hasBeenRequested(String uri)
		{
			return (this.requestTimestamps.containsKey(uri));
		}
		
		void addRequest(String uri)
		{
			this.requestTimestamps.put(uri, new Date());
		}
	}
	
}
