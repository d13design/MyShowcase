<?php
/*
 * MyShowcase Moodle Connector Plugin
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

class block_myshowcase extends block_base {
	function init() {
		
		$this->title   = get_string('myshowcase', 'block_myshowcase');
		$this->version = 2010093010;
		$this->cron = 5;
		
       
	}//function init

	function get_content() {
		global $THEME, $CFG, $USER;
		$this->adminpage = "{$CFG->wwwroot}/blocks/myshowcase/myshowcase.php";
		if ($this->content !== NULL) {
			return $this->content;
		}
		// check to see if user has admin rights
		$context = get_context_instance(CONTEXT_SYSTEM);
		$this->content         =  new stdClass;	
		$this->content->text   .= "<div style='text-align:center;'><img src=\"{$CFG->wwwroot}/blocks/myshowcase/images/MyShowcaseLogo.png\" ></div>";		
		$this->content->text   .= "<div style='text-align:center;padding:5px;'>"; 
		if ( !empty( $CFG->block_myshowcase_moodleinstance ) ) {
			// print launch button
			$this->content->text   .= print_single_button( $this->adminpage,array(),get_string('button_label_1', 'block_myshowcase'),'post','_self',true );       	
		}	
		else {
			// MUST set the Moodle instance number
			$this->content->text   .= "<h5 style=\"color:#FF0000;text-align:left\" >Warning: You must set the Moodle Instance Name in the block settings to use this tool.<br/>Go to SiteAdmin -> Modules -> Blocks -> Manage blocks and click the settings link for MyShowcase</h5>";
		}
		$this->content->text   .= "</div>";
		return $this->content;	
	}
  /**
   *  allows this plugin to be configured at settings level
   *  
   */
    function has_config() {
        return true;
    }
}//class block_myshowcase
?>
