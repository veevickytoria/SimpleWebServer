/*
 * HttpResponseFactory.java
 * Oct 7, 2012
 *
 * Simple Web Server (SWS) for CSSE 477
 * 
 * Copyright (C) 2012 Chandan Raj Rupakheti
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
 */
 
package protocol;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

/**
 * This is a factory to produce various kind of HTTP responses.
 * 
 * @author Chandan R. Rupakheti (rupakhet@rose-hulman.edu)
 */
public class HttpResponseFactory {
	private static final Logger logger = Logger.getLogger(HttpResponseFactory.class);
	/**
	 * Convenience method for adding general header to the supplied response object.
	 * 
	 * @param response The {@link HttpResponse} object whose header needs to be filled in.
	 * @param connection Supported values are {@link Protocol#OPEN} and {@link Protocol#CLOSE}.
	 */
	private static void fillGeneralHeader(HttpResponse response, String connection) {
		// Lets add Connection header
		response.put(Protocol.CONNECTION, connection);

		// Lets add current date
		Date date = Calendar.getInstance().getTime();
		response.put(Protocol.DATE, date.toString());
		
		// Lets add server info
		response.put(Protocol.Server, Protocol.getServerInfo());

		// Lets add extra header with provider info
		response.put(Protocol.PROVIDER, Protocol.AUTHOR);
	}
	
	/**
	 * Creates a {@link HttpResponse} object for sending the supplied file with supplied connection
	 * parameter.
	 * 
	 * @param file The {@link File} to be sent.
	 * @param connection Supported values are {@link Protocol#OPEN} and {@link Protocol#CLOSE}.
	 * @return A {@link HttpResponse} object represent 200 status.
	 */
	public static HttpResponse create200OK(File file, String connection) {
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.OK_CODE, 
				Protocol.OK_TEXT, new HashMap<String, String>(), file);
		
		// Lets fill up header fields with more information
		fillGeneralHeader(response, connection);
		
		// Lets add last modified date for the file
		long timeSinceEpoch = file.lastModified();
		Date modifiedTime = new Date(timeSinceEpoch);
		response.put(Protocol.LAST_MODIFIED, modifiedTime.toString());
		
		// Lets get content length in bytes
		long length = file.length();
		response.put(Protocol.CONTENT_LENGTH, length + "");
		
		// Lets get MIME type for the file
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String mime = fileNameMap.getContentTypeFor(file.getName());
		// The fileNameMap cannot find mime type for all of the documents, e.g. doc, odt, etc.
		// So we will not add this field if we cannot figure out what a mime type is for the file.
		// Let browser do this job by itself.
		if(mime != null) { 
			response.put(Protocol.CONTENT_TYPE, mime);
		}
		logger.debug("Generating 200 OK response");
		return response;
	}
	
	/**
	 * Creates a {@link HttpResponse} object for sending bad request response.
	 * 
	 * @param connection Supported values are {@link Protocol#OPEN} and {@link Protocol#CLOSE}.
	 * @return A {@link HttpResponse} object represent 400 status.
	 */
	public static HttpResponse create400BadRequest(String connection) {
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.BAD_REQUEST_CODE, 
				Protocol.BAD_REQUEST_TEXT, new HashMap<String, String>(), null);
		
		// Lets fill up header fields with more information
		fillGeneralHeader(response, connection);
		
		logger.debug("Generating 400 Bad Request response");
		return response;
	}
	
	/**
	 * Creates a {@link HttpResponse} object for sending not found response.
	 * 
	 * @param connection Supported values are {@link Protocol#OPEN} and {@link Protocol#CLOSE}.
	 * @return A {@link HttpResponse} object represent 404 status.
	 */
	public static HttpResponse create404NotFound(String connection) {
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.NOT_FOUND_CODE, 
				Protocol.NOT_FOUND_TEXT, new HashMap<String, String>(), null);
		
		// Lets fill up the header fields with more information
		fillGeneralHeader(response, connection);
		
		logger.debug("Generating 404 Not Found response");
		return response;	
	}
	
	/**
	 * Creates a {@link HttpResponse} object for sending version not supported response.
	 * 
	 * @param connection Supported values are {@link Protocol#OPEN} and {@link Protocol#CLOSE}.
	 * @return A {@link HttpResponse} object represent 501 status.
	 */
	public static HttpResponse create501NotImplemented(String connection) {
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.NOT_IMPLEMENTED_CODE,
				Protocol.NOT_IMPLEMENTED_TEXT, new HashMap<String, String>(), null);
		fillGeneralHeader(response, connection);
		
		logger.debug("Generating 501 Not Implemented response");
		return response;
	}
	
	/**
	 * Creates a {@link HttpResponse} object for sending file not modified response.
	 * 
	 * @param connection Supported values are {@link Protocol#OPEN} and {@link Protocol#CLOSE}.
	 * @return A {@link HttpResponse} object represent 304 status.
	 */
	public static HttpResponse create304NotModified(String connection) {
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.NOT_MODIFIED,
				Protocol.NOT_MODIFIED_TEXT, new HashMap<String, String>(), null);
		fillGeneralHeader(response, connection);
		
		logger.debug("Generating 304 Not Modified response");
		return response;
	}
	
	public static HttpResponse create401AccessDenied(String connection)
	{
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.ACCESS_DENIED_CODE,
				Protocol.ACCESS_DENIED_TEXT, new HashMap<String, String>(), null);
		fillGeneralHeader(response, connection);
		
		logger.debug("Generating 401 Access Denied OK response");
		return response;
	}
	
	public static HttpResponse create403Forbidden(String connection)
	{
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.FORBIDDEN_CODE,
				Protocol.FORBIDDEN_TEXT, new HashMap<String, String>(), null);
		fillGeneralHeader(response, connection);
		
		logger.debug("Generating 403 Forbidden response");
		return response;
	}
	
	public static HttpResponse create504GatewayTimedout(String connection)
	{
		HttpResponse response = new HttpResponse(Protocol.VERSION, Protocol.GATEWAY_TIMEOUT_CODE,
				Protocol.GATEWAY_TIMEOUT_TEXT, new HashMap<String, String>(), null);
		fillGeneralHeader(response, connection);
		
		logger.debug("Generating 504 Gateway Timed Out response");
		return response;
	}
}
