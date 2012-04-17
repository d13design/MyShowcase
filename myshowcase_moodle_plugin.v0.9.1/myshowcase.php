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

require_once('../../config.php');
require_once('../myshowcase/lib.php');
global $THEME, $CFG, $USER;


	$navlinks = array();
    $navlinks[] = array('name' => "MyShowcase", 'link' => "index.php", 'type' => 'misc');
    $navigation = build_navigation($navlinks);
	print_header_simple(  get_string('myshowcase', 'block_myshowcase'),": ".get_string('myshowcase', 'block_myshowcase'), $navigation, "", "", false );
	echo "<link rel=\"SHORTCUT ICON\" href=\"images/favicon.ico\" type=\"image/x-icon\">";
	echo "<script language=\"JavaScript\" type=\"text/JavaScript\">
     // Resize iframe to full height
     function resizeIframe(height)
    {
    // \"+60\" is a general rule of thumb to allow for differences in
    // IE & and FF height reporting, can be adjusted as required..
    document.getElementById('myshowcase').height = parseInt(height)+60;
    }
    </script>
	"; 
	echo "<div style=\"text-align:center\">";
	echo "<iframe id=\"myshowcase\" src=\"http://demo.my-showcase.org/myshowcase/SSO.htm?source=Moodle&location=$CFG->block_myshowcase_moodleinstance&user=$USER->id&parent={$CFG->wwwroot}/blocks/myshowcase/communicate.html\" type=\"text/html\" width=\"100%\" height=\"768px\" >";
	echo "</iframe></div>";
	print_footer( );

?>