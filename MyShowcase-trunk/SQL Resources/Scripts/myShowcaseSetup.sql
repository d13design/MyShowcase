--
-- Table structure for table `myshowcase_owner`
--

DROP TABLE IF EXISTS `myshowcase_owner`;
CREATE TABLE `myshowcase_owner` (
  `OWNER_ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` varchar(255) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `FULLNAME` varchar(255) default NULL,
  `FORENAME` varchar(255) default NULL,
  `SURNAME` varchar(255) default NULL,
  `USERNAME` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `EMAIL` varchar(255) default NULL,
  PRIMARY KEY  (`OWNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;


--
-- Table structure for table `myshowcase_showcase_theme`
--

DROP TABLE IF EXISTS `myshowcase_showcase_theme`;
CREATE TABLE `myshowcase_showcase_theme` (
  `SHOWCASE_THEME_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(100) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `USAGE_DESC` text,
  `illustration` varchar(100) default NULL,
  `VIEW` varchar(100) default NULL,
  PRIMARY KEY  (`SHOWCASE_THEME_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


--
-- Table structure for table `myshowcase_artefact_type`
--

DROP TABLE IF EXISTS `myshowcase_artefact_type`;
CREATE TABLE `myshowcase_artefact_type` (
  `ARTEFACT_TYPE_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  PRIMARY KEY  (`ARTEFACT_TYPE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


--
-- Table structure for table `myshowcase_account_source`
--

DROP TABLE IF EXISTS `myshowcase_account_source`;
CREATE TABLE `myshowcase_account_source` (
  `ACCOUNT_SOURCE_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `LOCATION` varchar(100) default NULL,
  PRIMARY KEY  (`ACCOUNT_SOURCE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Table structure for table `myshowcase_account`
--

DROP TABLE IF EXISTS `myshowcase_account`;
CREATE TABLE `myshowcase_account` (
  `ACCOUNT_ID` bigint(20) NOT NULL auto_increment,
  `USER_ID` varchar(100) default NULL,
  `SOURCE` varchar(100) default NULL,
  `OWNER_ID` bigint(20) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `LOCATION` varchar(100) default NULL,
  `ACCOUNT_SOURCE_ID` bigint(20) default NULL,
  PRIMARY KEY  (`ACCOUNT_ID`),
  KEY `FKC1E87127AD0ECF10` (`OWNER_ID`),
  KEY `FKC1E87127C484310B` (`ACCOUNT_SOURCE_ID`),
  CONSTRAINT `FKC1E87127C484310B` FOREIGN KEY (`ACCOUNT_SOURCE_ID`) REFERENCES `myshowcase_account_source` (`ACCOUNT_SOURCE_ID`),
  CONSTRAINT `FKC1E87127AD0ECF10` FOREIGN KEY (`OWNER_ID`) REFERENCES `myshowcase_owner` (`OWNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Table structure for table `myshowcase_showcase`
--

DROP TABLE IF EXISTS `myshowcase_showcase`;
CREATE TABLE `myshowcase_showcase` (
  `SHOWCASE_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(100) NOT NULL,
  `SHORT_DESC` varchar(100) default NULL,
  `FULL_DESC` varchar(100) default NULL,
  `KEYWORD` varchar(100) default NULL,
  `OWNER_ID` bigint(20) NOT NULL,
  `SHOWCASE_THEME_ID` bigint(20) NOT NULL,
  `PUBLISHED` bit(1) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `PUBLISHED_DATE` datetime default NULL,
  `REVIEW_START_DATE` datetime default NULL,
  `REVIEW_END_DATE` datetime default NULL,
  PRIMARY KEY  (`SHOWCASE_ID`),
  KEY `FKE65C87D3AD0ECF10` (`OWNER_ID`),
  KEY `FKE65C87D3839A91F5` (`SHOWCASE_THEME_ID`),
  CONSTRAINT `FKE65C87D3839A91F5` FOREIGN KEY (`SHOWCASE_THEME_ID`) REFERENCES `myshowcase_showcase_theme` (`SHOWCASE_THEME_ID`),
  CONSTRAINT `FKE65C87D3AD0ECF10` FOREIGN KEY (`OWNER_ID`) REFERENCES `myshowcase_owner` (`OWNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;


--
-- Table structure for table `myshowcase_artefact`
--

DROP TABLE IF EXISTS `myshowcase_artefact`;
CREATE TABLE `myshowcase_artefact` (
  `ARTEFACT_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(100) NOT NULL,
  `SHORT_DESC` varchar(100) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `KEYWORD` varchar(100) default NULL,
  `OWNER_ID` bigint(20) NOT NULL,
  `ARTEFACT_TYPE_ID` bigint(20) NOT NULL,
  `SHOWCASE_ID` bigint(20) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `LOCKED` bit(1) default NULL,
  PRIMARY KEY  (`ARTEFACT_ID`),
  KEY `FKB1119FF4AD0ECF10` (`OWNER_ID`),
  KEY `FKB1119FF446E044A4` (`SHOWCASE_ID`),
  KEY `FKB1119FF4923C0E01` (`ARTEFACT_TYPE_ID`),
  CONSTRAINT `FKB1119FF446E044A4` FOREIGN KEY (`SHOWCASE_ID`) REFERENCES `myshowcase_showcase` (`SHOWCASE_ID`),
  CONSTRAINT `FKB1119FF4923C0E01` FOREIGN KEY (`ARTEFACT_TYPE_ID`) REFERENCES `myshowcase_artefact_type` (`ARTEFACT_TYPE_ID`),
  CONSTRAINT `FKB1119FF4AD0ECF10` FOREIGN KEY (`OWNER_ID`) REFERENCES `myshowcase_owner` (`OWNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=936 DEFAULT CHARSET=utf8;

--
-- Table structure for table `myshowcase_artefact_detail`
--

DROP TABLE IF EXISTS `myshowcase_artefact_detail`;
CREATE TABLE `myshowcase_artefact_detail` (
  `ARTEFACT_ID` bigint(20) NOT NULL,
  `DETAIL` varchar(100) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `URL` varchar(100) default NULL,
  `FILE_NAME` varchar(100) default NULL,
  `FILE_PATH` varchar(100) default NULL,
  `FILE_TYPE` varchar(100) default NULL,
  `FLICKR_USER_NAME` varchar(100) default NULL,
  `TWITTER_USER_NAME` varchar(100) default NULL,
  `FILE_SIZE` bigint(20) default NULL,
  PRIMARY KEY  (`ARTEFACT_ID`),
  KEY `FK20B94EFC59B84504` (`ARTEFACT_ID`),
  CONSTRAINT `FK20B94EFC59B84504` FOREIGN KEY (`ARTEFACT_ID`) REFERENCES `myshowcase_artefact` (`ARTEFACT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `myshowcase_artefact_mapping`
--

DROP TABLE IF EXISTS `myshowcase_artefact_mapping`;
CREATE TABLE `myshowcase_artefact_mapping` (
  `ARTEFACT_MAPPING_ID` bigint(20) NOT NULL auto_increment,
  `ARTEFACT_ID` bigint(20) NOT NULL,
  `COMPETENCY_ID` bigint(20) default NULL,
  `MAPPING_ID` bigint(20) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  PRIMARY KEY  (`ARTEFACT_MAPPING_ID`),
  KEY `FKCB83B76359B84504` (`ARTEFACT_ID`),
  CONSTRAINT `FKCB83B76359B84504` FOREIGN KEY (`ARTEFACT_ID`) REFERENCES `myshowcase_artefact` (`ARTEFACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=utf8;

--
-- Table structure for table `myshowcase_tag`
--

DROP TABLE IF EXISTS `myshowcase_tag`;
CREATE TABLE `myshowcase_tag` (
  `TAG_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(100) NOT NULL,
  `searchCount` int(11) default NULL,
  `OWNER_ID` bigint(20) default NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  PRIMARY KEY  (`TAG_ID`),
  KEY `FK30183B94AD0ECF10` (`OWNER_ID`),
  CONSTRAINT `FK30183B94AD0ECF10` FOREIGN KEY (`OWNER_ID`) REFERENCES `myshowcase_owner` (`OWNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
--
-- Table structure for table `myshowcase_artefact_tag`
--

DROP TABLE IF EXISTS `myshowcase_artefact_tag`;
CREATE TABLE `myshowcase_artefact_tag` (
  `ARTEFACT_TAG_ID` bigint(20) NOT NULL auto_increment,
  `ARTEFACT_ID` bigint(20) NOT NULL,
  `TAG_ID` bigint(20) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  PRIMARY KEY  (`ARTEFACT_TAG_ID`),
  KEY `FK8EC4F8AF199A6730` (`TAG_ID`),
  KEY `FK8EC4F8AF59B84504` (`ARTEFACT_ID`),
  CONSTRAINT `FK8EC4F8AF199A6730` FOREIGN KEY (`TAG_ID`) REFERENCES `myshowcase_tag` (`TAG_ID`),
  CONSTRAINT `FK8EC4F8AF59B84504` FOREIGN KEY (`ARTEFACT_ID`) REFERENCES `myshowcase_artefact` (`ARTEFACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;


--
-- Table structure for table `myshowcase_item`
--

DROP TABLE IF EXISTS `myshowcase_item`;
CREATE TABLE `myshowcase_item` (
  `id` bigint(20) NOT NULL auto_increment,
  `title` varchar(255) NOT NULL,
  `ownerId` varchar(255) NOT NULL,
  `locationId` varchar(255) NOT NULL,
  `hidden` bit(1) NOT NULL,
  `dateCreated` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;



--
-- Table structure for table `myshowcase_reviewer`
--

DROP TABLE IF EXISTS `myshowcase_reviewer`;
CREATE TABLE `myshowcase_reviewer` (
  `REVIEWER_ID` bigint(20) NOT NULL auto_increment,
  `NAME` varchar(100) NOT NULL,
  `EMAIL` varchar(100) NOT NULL,
  `OWNER_ID` bigint(20) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  PRIMARY KEY  (`REVIEWER_ID`),
  KEY `FKEAF6862BAD0ECF10` (`OWNER_ID`),
  CONSTRAINT `FKEAF6862BAD0ECF10` FOREIGN KEY (`OWNER_ID`) REFERENCES `myshowcase_owner` (`OWNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;



--
-- Table structure for table `myshowcase_showcase_reviewer`
--

DROP TABLE IF EXISTS `myshowcase_showcase_reviewer`;
CREATE TABLE `myshowcase_showcase_reviewer` (
  `SHOWCASE_REVIEWER_ID` bigint(20) NOT NULL auto_increment,
  `SHOWCASE_ID` bigint(20) NOT NULL,
  `REVIEWER_ID` bigint(20) NOT NULL,
  `CREATED_DATE` datetime default NULL,
  `CREATED_BY` varchar(255) default NULL,
  `UPDATED_DATE` datetime default NULL,
  `UPDATED_BY` varchar(255) default NULL,
  `REVIEWED_DATE` datetime default NULL,
  PRIMARY KEY  (`SHOWCASE_REVIEWER_ID`),
  KEY `FKB8CD44F146E044A4` (`SHOWCASE_ID`),
  KEY `FKB8CD44F1C7DF85A4` (`REVIEWER_ID`),
  CONSTRAINT `FKB8CD44F146E044A4` FOREIGN KEY (`SHOWCASE_ID`) REFERENCES `myshowcase_showcase` (`SHOWCASE_ID`),
  CONSTRAINT `FKB8CD44F1C7DF85A4` FOREIGN KEY (`REVIEWER_ID`) REFERENCES `myshowcase_reviewer` (`REVIEWER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;



