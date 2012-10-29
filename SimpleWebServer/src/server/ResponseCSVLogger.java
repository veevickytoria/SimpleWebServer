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

/**
 * 
 * @author Michael Eaton, Victoria Zheng
 */
public class ResponseCSVLogger {
	
	private static ResponseCSVLogger instance;
	private static final String FILE_NAME = "ResponseLog.csv";
	private File logFile;
	private FileWriter writer;
	
	public static ResponseCSVLogger getInstance()
	{
		if(instance == null)
		{
			instance = new ResponseCSVLogger();
		}
		return instance;
	}
	
	private ResponseCSVLogger()
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
				this.logFile = log;
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		}
		
		try {
			this.writer = new FileWriter(this.logFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void logTurnaroundValue(long message)
	{
		try {
			this.writer.write(message + ",");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeWriter()
	{
		try {
			this.writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
