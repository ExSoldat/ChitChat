-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Jeu 22 Décembre 2016 à 15:50
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

--
-- Contenu de la table `chat_group`
--

INSERT INTO `chat_group` (`id`, `name`, `description`, `discussion_id`) VALUES
(21, 'Reclaim my throne', 'Just a group where we plot against Niflheim', 54);

-- --------------------------------------------------------

--
-- Structure de la table `chat_group_administrator`
--

CREATE TABLE `chat_group_administrator` (
  `administrator_id` int(10) NOT NULL,
  `group_id` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `chat_group_administrator`
--

INSERT INTO `chat_group_administrator` (`administrator_id`, `group_id`) VALUES
(33, 21);

-- --------------------------------------------------------

--
-- Structure de la table `chat_group_participant`
--

CREATE TABLE `chat_group_participant` (
  `participant_id` int(30) NOT NULL,
  `group_id` int(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `chat_group_participant`
--

INSERT INTO `chat_group_participant` (`participant_id`, `group_id`) VALUES
(31, 21),
(32, 21),
(33, 21),
(34, 21),
(35, 21);

-- --------------------------------------------------------

--
-- Structure de la table `discussion`
--

CREATE TABLE `discussion` (
  `discussion_id` int(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `discussion`
--

INSERT INTO `discussion` (`discussion_id`) VALUES
(33),
(34),
(35),
(36),
(37),
(38),
(39),
(40),
(41),
(42),
(43),
(44),
(45),
(46),
(47),
(48),
(49),
(50),
(51),
(52),
(53),
(54),
(55),
(56);

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

--
-- Contenu de la table `message`
--

INSERT INTO `message` (`message_id`, `discussion_id`, `content`, `sender_id`, `date_sent`, `encrypted`, `expiration`, `urgent`, `receipt`, `is_read`) VALUES
(230, 54, 'Seems good to me', 31, '2016-12-22 16:47:00', 0, 0, 0, 0, 0),
(229, 54, 'Once i finish this project we can go in Leide ok ?', 33, '2016-12-22 16:45:17', 0, 0, 0, 0, 0),
(228, 50, 'Yeah but i have to finish my COO project first :(', 33, '2016-12-22 16:44:55', 0, 0, 0, 0, 0),
(227, 50, 'Hello Noctis, time to go for some training', 31, '2016-12-22 16:43:59', 0, 0, 0, 0, 0),
(226, 35, 'Bonjour', 28, '2016-12-16 11:17:12', 0, 0, 0, 0, 0),
(225, 34, 'Hello', 28, '2016-12-16 11:16:33', 0, 0, 0, 0, 0),
(224, 33, 'Bonjour', 3, '2016-12-16 11:15:07', 0, 0, 0, 0, 0);

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

--
-- Contenu de la table `notification`
--

INSERT INTO `notification` (`id`, `receiver_id`, `seen`, `type`, `subject_id`) VALUES
(34, 33, 1, 2, 35),
(33, 33, 1, 2, 34),
(32, 33, 1, 2, 31),
(31, 34, 1, 1, 33),
(30, 31, 1, 1, 33),
(29, 35, 1, 1, 33),
(28, 33, 1, 2, 34),
(27, 33, 1, 2, 35),
(26, 33, 1, 2, 31),
(25, 35, 1, 1, 33),
(24, 34, 1, 1, 33),
(23, 31, 1, 1, 33),
(22, 32, 1, 2, 33),
(21, 33, 1, 1, 32),
(20, 28, 1, 2, 3),
(19, 3, 1, 1, 28),
(35, 34, 1, 1, 33),
(36, 35, 1, 1, 33),
(37, 31, 1, 1, 33),
(38, 33, 1, 2, 35),
(39, 33, 1, 2, 31),
(40, 33, 1, 2, 34),
(41, 33, 1, 1, 31),
(42, 31, 1, 2, 33),
(43, 32, 1, 1, 33),
(44, 34, 1, 1, 33),
(45, 35, 1, 1, 33),
(46, 33, 0, 2, 32),
(47, 33, 0, 2, 34),
(48, 31, 0, 1, 34),
(49, 35, 0, 1, 34),
(50, 33, 0, 2, 35);

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
(35, 'Prompto', 'Argentum', 'Prompto', 'azerty', 0),
(3, 'Administrateur', 'Administrateur', 'admin', 'admin', 1),
(32, 'Lunafreya', 'Nox Fleuret', 'Luna', 'azerty', 0),
(31, 'Gladiolus', 'Amicitia', 'Gladio', 'azerty', 0),
(33, 'Noctis', 'Lucis Caelum', 'Noct', 'azerty', 0),
(34, 'Ignis', 'Stupeo Scientia', 'Ignis', 'azerty', 0),
(28, 'Mathieu', 'Saab', 'saabm', 'azerty', 0);

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
-- Contenu de la table `user_friendslist`
--

INSERT INTO `user_friendslist` (`user_id`, `friend_id`, `discussion_id`, `is_valid`) VALUES
(34, 31, 55, 0),
(33, 35, 53, 1),
(33, 34, 52, 1),
(33, 32, 51, 1),
(31, 33, 50, 1),
(28, 3, 33, 1),
(34, 35, 56, 0);

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
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT pour la table `discussion`
--
ALTER TABLE `discussion`
  MODIFY `discussion_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=231;
--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
