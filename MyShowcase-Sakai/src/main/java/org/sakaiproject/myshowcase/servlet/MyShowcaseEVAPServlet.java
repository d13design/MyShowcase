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

package org.sakaiproject.myshowcase.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sakaiproject.entitybroker.access.EntityViewAccessProvider;
import org.sakaiproject.entitybroker.access.EntityViewAccessProviderManager;
import org.sakaiproject.entitybroker.entityprovider.EntityProvider;
import org.sakaiproject.entitybroker.entityprovider.EntityProviderManager;
import org.sakaiproject.myshowcase.tool.MyShowcaseEntityViewAccessProvider;

/**
 * Servlet implementation class MyShowcaseEVAPServlet
 * 
 * Sample spring config: 
    <bean id="myShowcaseEVAPServlet" 
            class="org.sakaiproject.myshowcase.servlet.MyShowcaseEVAPServlet" 
            init-method="init"
            destroy-method="destroy">
        <property name="evapm" ref="org.sakaiproject.entitybroker.access.EntityViewAccessProviderManager"/>
        <property name="providerManager" ref="org.sakaiproject.entitybroker.entityprovider.EntityProviderManager" />
    </bean>
 *
 */
public class MyShowcaseEVAPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyShowcaseEVAPServlet() {
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

    private EntityViewAccessProviderManager evapm;
    public void setEvapm(EntityViewAccessProviderManager evapmm) {
        this.evapm = evapmm;
    }

    private EntityProviderManager providerManager;
    public void setProviderManager(EntityProviderManager providerManager) {
        this.providerManager = providerManager;
    }

    private String PREFIX = "myshowcase";
    private EntityViewAccessProvider msevap = new MyShowcaseEntityViewAccessProvider();
    private EntityProvider msep = new EntityProvider() {
        public String getEntityPrefix() {
            return PREFIX;
        }
    };

    public void init() {

        try {

	        providerManager.registerEntityProvider(msep); // need to have a provider which matches the evap
            evapm.registerProvider(PREFIX, msevap) ;

        } catch (Exception e) {
        	System.out.println(e) ;
        	throw new RuntimeException(e);
        }
    }

    public void destroy() {

        if (providerManager != null) {
            providerManager.unregisterEntityProviderByPrefix(PREFIX);
        }
        if (evapm != null) {
            evapm.unregisterProvider(PREFIX);
        }
    }
    
}
