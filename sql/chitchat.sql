-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 15 Décembre 2016 à 19:41
-- Version du serveur :  5.7.14
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `chitchat`
--

-- --------------------------------------------------------

--
-- Structure de la table `chat_group`
--

CREATE TABLE `chat_group` (
  `id` int(10) NOT NULL,
  `name` varchar(300) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `discussion_id` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `chat_group_administrator`
--

CREATE TABLE `chat_group_administrator` (
  `administrator_id` int(10) NOT NULL,
  `group_id` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `chat_group_participant`
--

CREATE TABLE `chat_group_participant` (
  `participant_id` int(30) NOT NULL,
  `group_id` int(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `discussion`
--

CREATE TABLE `discussion` (
  `discussion_id` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `message_id` int(10) NOT NULL,
  `discussion_id` int(10) NOT NULL,
  `content` varchar(300) NOT NULL,
  `sender_id` int(30) NOT NULL,
  `date_sent` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `encrypted` tinyint(1) NOT NULL DEFAULT '0',
  `expiration` tinyint(1) NOT NULL DEFAULT '0',
  `urgent` tinyint(1) NOT NULL DEFAULT '0',
  `receipt` tinyint(1) NOT NULL DEFAULT '0',
  `is_read` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `receiver_id` int(11) NOT NULL,
  `seen` tinyint(1) NOT NULL,
  `type` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `notification_type`
--

CREATE TABLE `notification_type` (
  `type_id` int(11) NOT NULL,
  `label` varchar(60) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `notification_type`
--

INSERT INTO `notification_type` (`type_id`, `label`) VALUES
(1, 'Friend request'),
(2, 'Friend request acceptation'),
(3, 'Private Message'),
(4, 'Group message');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `isadmin` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`id`, `firstname`, `lastname`, `username`, `password`, `isadmin`) VALUES
(3, 'Administrateur', 'Administrateur', 'admin', 'admin', 1);

-- --------------------------------------------------------

--
-- Structure de la table `user_friendslist`
--

CREATE TABLE `user_friendslist` (
  `user_id` int(10) NOT NULL,
  `friend_id` int(10) NOT NULL,
  `discussion_id` int(11) NOT NULL,
  `is_valid` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `chat_group`
--
ALTER TABLE `chat_group`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `chat_group_administrator`
--
ALTER TABLE `chat_group_administrator`
  ADD PRIMARY KEY (`administrator_id`,`group_id`);

--
-- Index pour la table `chat_group_participant`
--
ALTER TABLE `chat_group_participant`
  ADD PRIMARY KEY (`participant_id`,`group_id`);

--
-- Index pour la table `discussion`
--
ALTER TABLE `discussion`
  ADD PRIMARY KEY (`discussion_id`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `notification_type`
--
ALTER TABLE `notification_type`
  ADD PRIMARY KEY (`type_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Index pour la table `user_friendslist`
--
ALTER TABLE `user_friendslist`
  ADD PRIMARY KEY (`user_id`,`friend_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `chat_group`
--
ALTER TABLE `chat_group`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `discussion_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=221;
--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
