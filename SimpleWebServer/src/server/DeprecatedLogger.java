/*
 * Logger.java
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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author Michael Eaton, Victoria Zheng
 */
public class DeprecatedLogger {
	
	private static DeprecatedLogger instance;
	private static final String FILE_NAME = "ServerLog.txt";
	private File logFile;
	
	public static DeprecatedLogger getInstance()
	{
		if(instance == null)
		{
			instance = new DeprecatedLogger();
		}
		return instance;
	}
	
	private DeprecatedLogger()
	{
		File log = new File(FILE_NAME);
		if(log.exists())
		{
			this.logFile = new File(FILE_NAME);
		}
		else
		{
			try {
				log.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void logMessage(String message)
	{
		try {
			FileWriter out;
			out = new FileWriter(this.logFile);
			out.write((new Date()).toString() + ": " + message);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
