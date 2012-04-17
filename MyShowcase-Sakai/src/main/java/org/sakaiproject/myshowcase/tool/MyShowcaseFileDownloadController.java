package org.sakaiproject.myshowcase.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.sakaiproject.myshowcase.logic.IMyShowcaseService;
import org.sakaiproject.myshowcase.model.Artefact;

import com.google.gson.Gson;


public class MyShowcaseFileDownloadController extends AbstractController {

	final protected Log log = LogFactory.getLog(getClass());

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println("MyShowcaseFileDownloadController Start") ;
		
		String artefactId = request.getParameter("artefactId");
		
		System.out.println("artefactId: " + artefactId);
		
        Artefact artefact =  myshowcaseService.getArtefactById(new Long(artefactId));
        
        String fileName = artefact.getArtefactDetail().getFileName() ;
        
        String filePath = artefact.getArtefactDetail().getFilePath() ;
        
        List<String> messages = new ArrayList<String>();

        
        File file = new File(filePath + fileName);
        
        if (file.exists())
        	System.out.println("File found : " + filePath + fileName) ;
        else
        	System.out.println("File not found : " + filePath + fileName) ;
        
        FileInputStream fileIn = new FileInputStream(file);
        
        response.reset();
        
        String mimetype = this.getServletContext().getMimeType(filePath + fileName);
        
        int fileSize = (int) file.length();
        
        response.setContentType(mimetype);
        
        response.setContentLength(fileSize);
        
        response.setBufferSize(fileSize);
        
        System.out.println("Filename " + fileName) ;
        
        response.setHeader("Content-Disposition","attachment; filename=\"" + filePath + fileName + "\"");
        
        ServletOutputStream out = response.getOutputStream();
        
        System.out.println("File size : " + fileSize);
        
        byte[] outputByte = new byte[fileSize];
        
        System.out.println("000") ;
        
        int bytesRead = 0 ;
        
        while(fileIn.read(outputByte, 0, fileSize) != -1)
        {
        	System.out.println("Bytes read : " + outputByte.length);
        	out.write(outputByte, 0, fileSize);
        	bytesRead += fileSize ;
        }

        fileIn.close();
        
        out.flush();
        
        out.close();
        
        System.out.println("Bytes read : " + bytesRead) ;
/*
        response.setContentType("application/json"); 
	    response.setCharacterEncoding("UTF-8"); 
	    PrintWriter out = response.getWriter();
	    out.write(new Gson().toJson(messages)); 
    	out.flush();
		out.close();
*/
		System.out.println("MyShowcaseFileDownloadController End") ;
		
		return null ;
	}

/*

try {	
response.reset();
response.setContentType("APPLICATION/OCTET-STREAM/GIF/XML");
if (request.getParameter("fileName") != null) {
out.println(request.getParameter("fileName"));
String str= request.getParameter("fileName");
byte[] bytes = Repository.getFile(str); // thi function(personal) gives me direct byte array of file
str=str.substring(0,str.indexOf("~"));	
Integer fileLength = Integer.decode(String.valueOf(bytes.length));
response.setContentLength(fileLength.intValue());	
response.setHeader("Content-Disposition","attachment; filename=\""str"\"");	
response.getOutputStream().write(bytes);
response.getOutputStream().flush();
response.getOutputStream().close();
response.sendRedirect("FileInfo.jsp");	
 */
    private IMyShowcaseService myshowcaseService;
    public void setMyshowcaseService(IMyShowcaseService service) {
        this.myshowcaseService = service;
    }

}
