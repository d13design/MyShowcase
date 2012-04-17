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

  //$Id: settings.php,v 1.1.2.2 2007-12-19 17:38:47 skodak Exp $
$settings->add( new admin_setting_configtext('block_myshowcase_moodleinstance', get_string('moodle_instance_label', 'block_myshowcase'),
                   get_string('moodle_instance_desc', 'block_myshowcase'), '' ) );

?>
