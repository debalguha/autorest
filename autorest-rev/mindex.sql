-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: mindex.me    Database: mindex_main
-- ------------------------------------------------------
-- Server version	5.6.27-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_entities`
--

DROP TABLE IF EXISTS `tbl_entities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_entities` (
  `EntityID` int(10) NOT NULL AUTO_INCREMENT,
  `EntityFacebookID` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `EntityFacebookName` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `EntityFacebookImage` varchar(255) CHARACTER SET latin1 DEFAULT NULL,
  `EntityCreationDate` date DEFAULT NULL,
  PRIMARY KEY (`EntityID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_es_setting`
--

DROP TABLE IF EXISTS `tbl_es_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_es_setting` (
  `ESID` int(11) NOT NULL AUTO_INCREMENT,
  `ESIndexName` char(22) CHARACTER SET latin1 NOT NULL,
  `ESCreationDate` datetime NOT NULL,
  `ESModifyDate` datetime DEFAULT NULL,
  `isActive` bit(1) NOT NULL,
  `ESIndexType` tinyint(4) NOT NULL,
  `isNew` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ESID`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mailinglist`
--

DROP TABLE IF EXISTS `tbl_mailinglist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mailinglist` (
  `MailinglistID` int(10) NOT NULL AUTO_INCREMENT,
  `MailinglistName` varchar(255) DEFAULT NULL,
  `MailinglistEmail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MailinglistID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_files`
--

DROP TABLE IF EXISTS `tbl_mindex_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_files` (
  `MindexFileID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexFileMID` varchar(16) NOT NULL,
  `MindexFileMindex` int(10) DEFAULT '0',
  `MindexFileName` varchar(255) DEFAULT NULL,
  `MindexFileTitle` varchar(255) DEFAULT NULL,
  `MindexFileDescription` mediumtext,
  `MindexFileCreationDate` date DEFAULT NULL,
  `MindexFileUpdateStatus` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`MindexFileID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_items`
--

DROP TABLE IF EXISTS `tbl_mindex_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_items` (
  `MindexItemID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexItemMID` varchar(16) NOT NULL,
  `MindexItemSet` int(10) DEFAULT '0',
  `MindexItemTitle` varchar(255) DEFAULT NULL,
  `MindexItemDescription` mediumtext,
  `MindexItemStart` int(10) DEFAULT NULL,
  `MindexItemEND` int(10) DEFAULT NULL,
  `MindexItemDuration` int(10) DEFAULT NULL,
  `MindexItemCreationDate` date DEFAULT NULL,
  `MindexItemLastUpdated` date DEFAULT NULL,
  `MindexItemUpdateStatus` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`MindexItemID`)
) ENGINE=InnoDB AUTO_INCREMENT=471635 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_like`
--

DROP TABLE IF EXISTS `tbl_mindex_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_like` (
  `MindexLikeID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexLikeMindex` int(10) DEFAULT '0',
  `MindexLikeUser` int(10) DEFAULT '0',
  `MindexLikeDate` date DEFAULT NULL,
  PRIMARY KEY (`MindexLikeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_links`
--

DROP TABLE IF EXISTS `tbl_mindex_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_links` (
  `MindexLinkID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexLinkMID` varchar(16) NOT NULL,
  `MindexLinkMindex` int(10) DEFAULT '0',
  `MindexLinkURL` varchar(255) DEFAULT NULL,
  `MindexLinkTitle` varchar(255) DEFAULT NULL,
  `MindexLinkDescription` mediumtext,
  `MindexLinkCreationDate` date DEFAULT NULL,
  `MindexLinkUpdateStatus` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`MindexLinkID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_products`
--

DROP TABLE IF EXISTS `tbl_mindex_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_products` (
  `MindexProductID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexProductSet` int(10) NOT NULL DEFAULT '0',
  `MindexProductFrom` int(10) NOT NULL DEFAULT '0',
  `MindexProductTo` int(10) NOT NULL DEFAULT '0',
  `MindexProductTitle` varchar(255) NOT NULL,
  `MindexProductPrice` varchar(100) NOT NULL,
  `MindexProductUrl` varchar(255) NOT NULL,
  `MindexProductImage` varchar(255) NOT NULL,
  `MindexProductUpdateStatus` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`MindexProductID`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_sets`
--

DROP TABLE IF EXISTS `tbl_mindex_sets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_sets` (
  `MindexSetID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexSetMID` varchar(16) NOT NULL,
  `MindexSetUser` int(10) DEFAULT '0',
  `MindexSetVideo` int(10) DEFAULT '0',
  `MindexSetTitle` varchar(255) DEFAULT NULL,
  `MindexSetDescription` mediumtext,
  `MindexSetCreationDate` date DEFAULT NULL,
  `MindexSetUpdateStatus` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`MindexSetID`)
) ENGINE=InnoDB AUTO_INCREMENT=36566 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_socialtags`
--

DROP TABLE IF EXISTS `tbl_mindex_socialtags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_socialtags` (
  `MindexSocialtagID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexSocialtagSet` int(10) DEFAULT '0',
  `MindexSocialtagUser` int(10) DEFAULT '0',
  `MindexSocialtagEntity` int(10) DEFAULT '0',
  `MindexSocialtagStart` int(10) NOT NULL DEFAULT '0',
  `MindexSocialtagEnd` int(10) NOT NULL DEFAULT '0',
  `MindexSocialtagCreationDate` date DEFAULT NULL,
  `MindexSocialtagUserUpdateStatus` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`MindexSocialtagID`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_mindex_views`
--

DROP TABLE IF EXISTS `tbl_mindex_views`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_mindex_views` (
  `MindexViewID` int(10) NOT NULL AUTO_INCREMENT,
  `MindexViewMindex` int(10) DEFAULT '0',
  `MindexViewUser` int(10) DEFAULT '0',
  `MindexViewDate` date DEFAULT NULL,
  PRIMARY KEY (`MindexViewID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_users`
--

DROP TABLE IF EXISTS `tbl_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_users` (
  `UserID` int(10) NOT NULL AUTO_INCREMENT,
  `UserMID` varchar(16) NOT NULL,
  `UserFullName` varchar(100) DEFAULT NULL,
  `UserEmail` varchar(100) DEFAULT NULL,
  `UserFacebookID` varchar(100) NOT NULL,
  `UserFacebookLink` varchar(255) NOT NULL,
  `UserFacebookImage` varchar(255) DEFAULT NULL,
  `UserActive` tinyint(1) DEFAULT '1',
  `UserEnabled` tinyint(1) DEFAULT '1',
  `UserCreationDate` date DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_users_social`
--

DROP TABLE IF EXISTS `tbl_users_social`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_users_social` (
  `UserSocialID` int(10) NOT NULL AUTO_INCREMENT,
  `UserSocialNetwork` int(10) DEFAULT '0',
  `UserSocialIdentifier` varchar(100) NOT NULL,
  `UserSocialName` varchar(100) DEFAULT NULL,
  `UserSocialImage` varchar(255) DEFAULT NULL,
  `UserSocialUpdateStatus` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`UserSocialID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_videos`
--

DROP TABLE IF EXISTS `tbl_videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_videos` (
  `VideoID` int(10) NOT NULL AUTO_INCREMENT,
  `VideoMID` varchar(16) NOT NULL,
  `VideoSource` int(10) DEFAULT '0',
  `VideoCode` varchar(100) NOT NULL,
  `VideoTitle` varchar(255) DEFAULT NULL,
  `VideoDescription` mediumtext,
  `VideoThumbnail` varchar(255) DEFAULT NULL,
  `VideoUploadedBy` varchar(100) DEFAULT NULL,
  `VideoCategory` varchar(100) DEFAULT NULL,
  `VideoViews` int(10) DEFAULT '0',
  `VideoLikes` int(10) DEFAULT '0',
  `VideoDislikes` int(10) DEFAULT '0',
  `VideoLength` int(10) DEFAULT NULL,
  `VideoComments` int(10) DEFAULT '0',
  `VideoUploadedDate` date DEFAULT NULL,
  `VideoTags` varchar(1000) DEFAULT NULL,
  `SearchTerm` varchar(80) DEFAULT NULL,
  `VideoUpdateStatus` tinyint(1) DEFAULT '1',
  `VideoYTChannel` char(24) DEFAULT '',
  PRIMARY KEY (`VideoID`)
) ENGINE=InnoDB AUTO_INCREMENT=36556 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_videos_like`
--

DROP TABLE IF EXISTS `tbl_videos_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_videos_like` (
  `VideoLikeID` int(10) NOT NULL AUTO_INCREMENT,
  `VideoLikeVideo` int(10) DEFAULT '0',
  `VideoLikeUser` int(10) DEFAULT '0',
  `VideoLikeDate` date DEFAULT NULL,
  PRIMARY KEY (`VideoLikeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_videos_sources`
--

DROP TABLE IF EXISTS `tbl_videos_sources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_videos_sources` (
  `VideoSourceID` int(10) NOT NULL AUTO_INCREMENT,
  `VideoSourceName` varchar(100) DEFAULT NULL,
  `VideoSourceEmbedCode` mediumtext,
  PRIMARY KEY (`VideoSourceID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_videos_views`
--

DROP TABLE IF EXISTS `tbl_videos_views`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_videos_views` (
  `VideoViewID` int(10) NOT NULL AUTO_INCREMENT,
  `VideoViewVideo` int(10) DEFAULT '0',
  `VideoViewUser` int(10) DEFAULT '0',
  `VideoViewDate` date DEFAULT NULL,
  PRIMARY KEY (`VideoViewID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ytm_Channels_scan`
--

DROP TABLE IF EXISTS `ytm_Channels_scan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ytm_Channels_scan` (
  `ChannelID` char(24) COLLATE utf8mb4_unicode_ci NOT NULL,
  `EntryDate` datetime NOT NULL,
  `ScanHistory` longtext COLLATE utf8mb4_unicode_ci,
  `ScanComplete` tinyint(1) NOT NULL DEFAULT '0',
  `ProviderID` smallint(6) NOT NULL COMMENT 'Provider is the website : Youtube, vimeo etc\nYoutube- 1',
  `isWorking` bit(1) NOT NULL DEFAULT b'0',
  `ChannelTitle` varchar(300) COLLATE utf8mb4_unicode_ci DEFAULT '',
  `VideosCount` int(11) DEFAULT '0',
  `GoogleID` char(21) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TotalVideos` int(11) DEFAULT NULL,
  `CommentCount` int(11) DEFAULT NULL,
  `ViewCount` bigint(20) DEFAULT NULL,
  `SubscriberCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`ChannelID`),
  UNIQUE KEY `ChannelID_UNIQUE` (`ChannelID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ytm_Maintenance`
--

DROP TABLE IF EXISTS `ytm_Maintenance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ytm_Maintenance` (
  `ismaintenance` bit(1) NOT NULL DEFAULT b'0',
  `iid` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`iid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ytm_UpdateInfo`
--

DROP TABLE IF EXISTS `ytm_UpdateInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ytm_UpdateInfo` (
  `key` int(11) NOT NULL AUTO_INCREMENT,
  `PrevViews` bigint(20) NOT NULL,
  `CurrentViews` bigint(20) NOT NULL,
  `UpdateDate` datetime NOT NULL,
  `VideoDeleteCount` int(11) NOT NULL DEFAULT '0',
  `RunTime` varchar(8) NOT NULL DEFAULT '"0"',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ytm_channel_videos`
--

DROP TABLE IF EXISTS `ytm_channel_videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ytm_channel_videos` (
  `YCVID` int(15) NOT NULL AUTO_INCREMENT,
  `ChannelID` char(24) NOT NULL,
  `ProviderID` smallint(6) NOT NULL,
  `VideoJSON` text,
  `EntryDate` datetime NOT NULL,
  `VideoID` varchar(20) NOT NULL,
  `SearchTerm` varchar(45) DEFAULT NULL,
  `HasProduct` tinyint(4) DEFAULT '-1',
  `HasSocial` tinyint(4) DEFAULT '-1',
  `HasMindex` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`YCVID`),
  UNIQUE KEY `VideoID_UNIQUE` (`VideoID`)
) ENGINE=InnoDB AUTO_INCREMENT=1790944 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ytm_junkvideo`
--

DROP TABLE IF EXISTS `ytm_junkvideo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ytm_junkvideo` (
  `JunkID` int(11) NOT NULL AUTO_INCREMENT,
  `VideoID` varchar(45) NOT NULL,
  `VideoUploadedBy` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`JunkID`)
) ENGINE=InnoDB AUTO_INCREMENT=1949 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ytm_regextamplate`
--

DROP TABLE IF EXISTS `ytm_regextamplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ytm_regextamplate` (
  `RegexID` int(11) NOT NULL AUTO_INCREMENT,
  `RegexString` varchar(45) NOT NULL,
  `RegexTitle` varchar(45) NOT NULL,
  PRIMARY KEY (`RegexID`),
  UNIQUE KEY `RegexID_UNIQUE` (`RegexID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ytm_word_list`
--

DROP TABLE IF EXISTS `ytm_word_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ytm_word_list` (
  `ywlID` int(11) NOT NULL AUTO_INCREMENT,
  `Word` varchar(45) NOT NULL,
  `isComplete` bit(1) NOT NULL DEFAULT b'0',
  `isWorking` bit(1) NOT NULL DEFAULT b'0',
  `LastDate` datetime DEFAULT NULL,
  PRIMARY KEY (`ywlID`)
) ENGINE=InnoDB AUTO_INCREMENT=681956 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-09 22:45:41
