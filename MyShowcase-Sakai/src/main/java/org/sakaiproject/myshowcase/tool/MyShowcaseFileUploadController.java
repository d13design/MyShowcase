//***************************************************************************
//*MyShowcase                                                               * 
//*Copyright (C) 2010 MyKnowledgeMap Ltd.                                   *
//*                                                                         *
//*This program is free software: you can redistribute it and/or modify     *
//*it under the terms of the GNU Affero General Public License as           *
//*published by the Free Software Foundation, either version 3 of the       *
//*License, or (at your option) any later version.                          *
//*                                                                         *
//*This program is distributed in the hope that it will be useful,          *
//*but WITHOUT ANY WARRANTY; without even the implied warranty of           *
//*MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the            * 
//*GNU Affero General Public License for more details.                      *
//*                                                                         *
//*You should have received a copy of the GNU Affero General Public License *
//*along with this program.  If not, see <http://www.gnu.org/licenses/>.    *
//*                                                                         *
//*Web: <http://www.my-showcase.org/>.                                      *
//*Email: <myshowcase@myknowledgemap.com>                                   *
//*                                                                         *
//***************************************************************************
package org.sakaiproject.myshowcase.tool;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.BindException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.sakaiproject.myshowcase.logic.*;
import org.sakaiproject.myshowcase.model.*;

import com.google.gson.Gson;

//public class MyShowcaseFileUploadController extends AbstractController {
	public class MyShowcaseFileUploadController extends SimpleFormController {

	final protected Log log = LogFactory.getLog(getClass());

    protected ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            org.springframework.validation.BindException errors) throws ServletException, IOException {

    		System.out.println("MyShowcaseFileUploadController onSubmit Start") ;
//    		System.out.println("Content type: " + request.getContentType()) ;
//   		System.out.println("Content length: " + request.getContentLength()) ;
            response.setContentType("text/html");
			try {
//				String contentType = request.getContentType();
	
//				if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) {
					System.out.println("We have a file") ;
//					System.out.println("Request Content Length: " + request.getContentLength()) ;
					ServletInputStream reader = request.getInputStream() ;


			      while (reader.read() != -1) {
				        System.out.println("Loop");
			      }
//				}
			}
			catch (Exception e) {
				System.out.println("It didn't work") ;
				System.out.println(e.toString()) ;
			}
		    return null ;
/*
				    DataInputStream in = new DataInputStream(request.getInputStream());
					int formDataLength = request.getContentLength();
					System.out.println("FormDataLength: " + formDataLength) ;
					int totalBytes = 0 ;
					int maxBlockSize = 16577856 ;
					//maxBlockSize = 20 ;
					if (formDataLength < maxBlockSize) { // Do it in one hit
						byte dataBytes[] = new byte[formDataLength];
						int byteRead = 0;
						int totalBytesRead = 0;
						int c = 0 ;
						while ((totalBytesRead < formDataLength) && (c < 10)){
							byteRead = in.read(dataBytes, totalBytesRead,formDataLength);
							totalBytesRead += byteRead;
							System.out.println("ByteRead : " + byteRead) ;
							System.out.println("TotalBytes : " + totalBytesRead) ;
							c++ ;
						}
						String file = new String(dataBytes) ;
						String saveFile = file.substring(file.indexOf("filename=\"") + 10);
						saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
						saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));
						int lastIndex = contentType.lastIndexOf("=");
						String boundary = contentType.substring(lastIndex + 1,contentType.length());
						int pos;
						pos = file.indexOf("filename=\"");
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						int boundaryLocation = file.indexOf(boundary, pos) - 4;
						int startPos = ((file.substring(0, pos)).getBytes()).length;
						int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
						saveFile = "C:\\temp\\" + saveFile ;
						System.out.println(saveFile) ;
						FileOutputStream fileOut = new FileOutputStream(saveFile);
						fileOut.write(dataBytes, startPos, (endPos - startPos));
						fileOut.flush();
						fileOut.close();
	
					} else {
						byte buf[] = new byte[2048];
						System.out.println("Do it in chunks") ;
						int lastIndex = contentType.lastIndexOf("=");
						String boundary = contentType.substring(lastIndex + 1,contentType.length());
						System.out.print("Last Index >" + lastIndex + "<") ;
//						out.print("Boundary >" + boundary + "<") ;
						BufferedInputStream bin = new BufferedInputStream(in);
						int totalBuffers = 0 ;
						System.out.print("Start") ;
						String saveFile ;
						//Do initial header
						bin.read(buf) ;
						System.out.println( " Buffer 0 ");
						String file = new String(buf) ;
						saveFile = file.substring(file.indexOf("filename=\"") + 10);
						saveFile = saveFile.substring(0, saveFile.indexOf("\n"));
						saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));
						lastIndex = contentType.lastIndexOf("=");
						boundary = contentType.substring(lastIndex + 1,contentType.length());
						int pos;
						pos = file.indexOf("filename=\"");
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						pos = file.indexOf("\n", pos) + 1;
						int boundaryLocation = file.indexOf(boundary, pos) - 4;
						int startPos = ((file.substring(0, pos)).getBytes()).length;
						int endPos = ((file.substring(0, boundaryLocation)).getBytes()).length;
						saveFile = "C:\\temp\\" + saveFile ;
						System.out.print("Save File >" + saveFile) ;
						FileOutputStream fileOut = new FileOutputStream(saveFile);
						fileOut.write(buf) ;
						
						while ((bin.read(buf)) != -1) {
							fileOut.write(buf);
							totalBuffers++ ;
						}
						System.out.print("End") ;
						System.out.print("Totalbuffers : " + totalBuffers) ;
						bin.close();
						fileOut.flush();
						fileOut.close();
					}
	
				}
			}
			catch (Exception e) {
				System.out.println("It didn't work") ;
				System.out.println(e.toString()) ;
			}
            System.out.println("Bye bye") ;
            return null ;
*/
    }

    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    	throws ServletException {
		System.out.println("MyShowcaseFileUploadController initBinder Start") ;
		try {
	        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
		catch (Exception e) {
			System.out.println("MyShowcaseFileController initBinder Error") ;
			System.out.println(e.toString()) ;
		}
		System.out.println("MyShowcaseFileUploadController initBinder End") ;
    }

    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }


}
