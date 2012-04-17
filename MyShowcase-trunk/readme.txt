/*
 * MyShowcase Core Application v0.1.1
 * Copyright (C) 2010 MyKnowledgeMap Ltd.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Web: <http://www.my-showcase.org/>.
 * Email: <support@mkmlabs.com>
 */



There are certain pre-requisites to building and developing using the MyShowcase open-source code.


1.Tomcat 
--------
Ensure you have a working installation of Tomcat 5.5 or 6.0.

$CATALINA_HOME will typically be /opt/tomcat.

Add a couple of new directories inside your installation of Tomcat.
   eg under a Tomcat instalation of c:/opt/tomcat add a new folder named MyShowcase
   eg under a Tomcat instalation of C:/opt/tomcat/webapps add a new folder FileStore/MyShowcase 

Into the opt/tomcat/MyShowcase folder add the myshowcase.properties and myshowcaseConfig.xml files.
These files can both be found in the downloaded source in folder named: Config Resources 

The myshowcaseConfig.xml file needs to be edited with your hostname.
The myshowcase.properties file need to be edited to reflect your database instance (see point 3 Database setup).


2.Java
------
Ensure you have a working installation of java 1.5 or 1.6


3.Database setup
----------------

Install MySQL 5.1 and create the myshowcase database.
In the context of this Readme document, it is assumed that your root password is "mysqlpwd1".

To create the database:

a.Create the myshowcase database and myshowcase user (password=ironchef)

b.Run the following from a command line prompt:
 
	mysql -uroot -pmysqlpwd1

•Note: You can also do the following if you prefer to be prompted for the password: mysql -uroot -p

c.Then run these commands from the mysql prompt (one command per line): 

	create database myshowcase default character set utf8;

	grant all privileges on myshowcase.* to 'myshowcase'@'localhost' identified by 'ironchef';

	flush privileges;

	quit


There are 2 scripts contained in the download source that will set up the MyShowcase database.
These are:

SQL Resources/Scripts/myShowcaseSetup.sql

SQL Resources/Scripts/myShowcaseDataInsert.sql

File myShowcaseSetup contains the table structure of the my showcase database and should be run first.
File myShowcaseDataInsert populates the myshowcase database with the required startup data.

Myshowcase requires that you dounload and install the MySQL Connect/J drivers.
The jar file needs to be copied into the $CATALINA_HOME/common/lib directory.
The source jar file is included in the download under RequiredBuildSource/mysql-connector-java-5.0.8-bin.jar

Finally, you will need to update the $CATALINA_HOME/myshowcase.properties file to match your database setup.



4.Project Build
---------------

You are welcome to build the source code using Ant or your own IDE.
The 3rd party source files used in the build of MyShowcase are contained in the downloaded code under folder: RequiredBuildSource

The download comes with a Maven build option. To use this option ensure you have working version of maven2 installed.
To build the project, from the command line navigate to the directory in the downloaded source that contains the pom.xml file.
Then from the command line execute the following command:
mvn clean install

Once finished the completed myshowcase.war file can be found inside the folder named: target. 
This war file can then be deployed into $CATALINA_HOME/webapps


5.MyShowcase Quickstart
-----------------------

If you do not want to develop the code of myshowcase and simply want to run the application locally you can.
To do this:

a. Ensure that you have Tomcat and Java installed.

b. Ensure theMyShowcase database has been created and populated with the startup data (see point 3 Database setup).

b. Add a couple of new directories inside your installation of Tomcat.

   eg under a Tomcat instalation of c:/opt/tomcat add a new folder named MyShowcase
   eg under a Tomcat instalation of C:/opt/tomcat/webapps add a new folder FileStore/MyShowcase 

   Into the opt/tomcat/MyShowcase folder add the myshowcase.properties and myshowcaseConfig.xml files.
   These files can both be found in the downloaded source in folder named: Config Resources 

c. Deploy the MyShowcase application by taking a copy of /MyShowcaseApp/myshowcase.war and pasting it into the $CATALINA_HOME/webapps directory.
 

6. Running MyShowcase
---------------------

On a local install of Tomcat, the MyShowcase application can be accessed using:

http://localhost:8080/myshowcase/login.htm

Alternatively, on a different host the address would be:

hostname/myshowcase/login.htm

The value of the hostname needs to be set in the myshowcaseConfig.xml file.


7. Running MyShowcase with Moodle
---------------------------------

If your instance of MyShowcase is going to be referenced from a Moodle installation you are required to do a small change to the database.
The moodle plugin will be configured with a unique location identifier eg Springfield.
In order to access MyShowcase from this moodle instance, the instance must be recognised inside MyShowcase.
To do this, an entry must be made into one of the MyShowcase database table.
An example insert script for a Springfield Moodle instance would be: 

INSERT INTO MYSHOWCASE_ACCOUNT_SOURCE (created_date, name, location, description, created_by) VALUE (CURDATE(), 'Moodle', 'Springfield', 'Moodle installation','Admin' );