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

$block_myshowcase_capabilities = array(
	'block/myshowcase:view' => array(
  		'captype'      => 'read',
  		'contextlevel' => CONTEXT_SYSTEM,
        	'legacy' => array(
        		'guest'          => CAP_PREVENT,
        		'student'        => CAP_PREVENT,
        		'teacher'        => CAP_PREVENT,
        		'editingteacher' => CAP_PREVENT,
        		'coursecreator'  => CAP_PREVENT,
        		'admin'          => CAP_ALLOW
		)
	)
);
?>
