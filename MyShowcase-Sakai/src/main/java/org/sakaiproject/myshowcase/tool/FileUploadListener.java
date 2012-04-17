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
import org.apache.commons.fileupload.ProgressListener;

public class FileUploadListener implements ProgressListener{
	private volatile long bytesRead = 0L, contentLength = 0L, item = 0L;   

	public FileUploadListener() {
		super();
	}
	
	public void update(long aBytesRead, long aContentLength, int anItem) {
		bytesRead = aBytesRead;
		contentLength = aContentLength;
		item = anItem;
	}
	
	public long getBytesRead() {
		return bytesRead;
	}
	
	public long getContentLength() {
		return contentLength;
	}
	
	public long getItem() {
		return item;
	}
}