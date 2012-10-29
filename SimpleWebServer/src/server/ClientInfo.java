/*
 * ClientInfo.java
 * Oct 29, 2012
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

import org.apache.log4j.Logger;

/**
 * 
 * @author Victoria Zheng (zhengc@rose-hulman.edu)
 */
public class ClientInfo {
	private static final Logger logger = Logger.getLogger(Server.class);
	private InetAddress IP;
	private Date startTime;
	private int request;
	private static int TIMEOUT_IN_MINS = 1;
	private static int REQUEST_COUNT_LIMIT = 10000;
	
	public ClientInfo(InetAddress ip)
	{
		this.IP = ip;
		this.startTime = new Date();
		this.request = 1;
	}
	
	public synchronized void incrementRequest()
	{
		this.request++;
		logger.info(this.IP.toString() + "has requested from the server "+this.request+" times.");
	}
	
	public boolean isIP(InetAddress ip)
	{
		return this.IP.equals(ip);
	}
	
	public boolean isAnAttacker()
	{
		//REQUEST_COUNT_LIMIT requests of the same file in a TIMEOUT_IN_MINS window.
		if (this.request > REQUEST_COUNT_LIMIT){
			Date now = new Date();
			long difference = now.getTime() - this.startTime.getTime();
			return difference < TIMEOUT_IN_MINS*60*1000;
		}else{
			return false;
		}
	}
}
