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

import org.apache.log4j.Logger;

/**
 * 
 * @author Michael Eaton, Victoria Zheng
 */
public class ResponseCSVLogger {
	
	private static ResponseCSVLogger instance;
	private static final String FILE_NAME = "ResponseLog";
	private static final String FILE_EXTENSION = ".csv";
	private File logFile;
	private Logger logger;
	
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
		this.logger = Logger.getLogger(ResponseCSVLogger.class);
		Date date = new Date();
		String dateString = date.getMonth() + "-" + date.getDate() + "-" + date.getTime();
		File log = new File(FILE_NAME + dateString + FILE_EXTENSION);
		if(log.exists())
		{
			this.logFile = log;
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
	}
	
	public void logTurnaroundValue(long message)
	{
		try {
			FileWriter writer = new FileWriter(this.logFile);
			writer.write(message + ",");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeWriter()
	{
		return;
	}
}
