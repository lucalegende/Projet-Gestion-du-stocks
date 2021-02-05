-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 05 fév. 2021 à 13:22
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `database`
--

-- --------------------------------------------------------

--
-- Structure de la table `achat`
--

DROP TABLE IF EXISTS `achat`;
CREATE TABLE IF NOT EXISTS `achat` (
  `Id_Vaccins` int(11) NOT NULL,
  `Id_Fournisseurs` int(11) NOT NULL,
  `Id_Commandes` int(11) NOT NULL,
  `Commande_Valider` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id_Vaccins`,`Id_Fournisseurs`,`Id_Commandes`),
  KEY `Achat_Fournisseurs0_FK` (`Id_Fournisseurs`),
  KEY `Achat_Commandes1_FK` (`Id_Commandes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `commandes`
--

DROP TABLE IF EXISTS `commandes`;
CREATE TABLE IF NOT EXISTS `commandes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Client` varchar(255) COLLATE utf8_bin NOT NULL,
  `Nombre_Commander` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `fournisseurs`
--

DROP TABLE IF EXISTS `fournisseurs`;
CREATE TABLE IF NOT EXISTS `fournisseurs` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom_fournisseur` varchar(255) COLLATE utf8_bin NOT NULL,
  `Total_de_stocks` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Structure de la table `vaccins`
--

DROP TABLE IF EXISTS `vaccins`;
CREATE TABLE IF NOT EXISTS `vaccins` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type_Vaccin` varchar(255) COLLATE utf8_bin NOT NULL,
  `Total_stocks` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `achat`
--
ALTER TABLE `achat`
  ADD CONSTRAINT `Achat_Commandes1_FK` FOREIGN KEY (`Id_Commandes`) REFERENCES `commandes` (`Id`),
  ADD CONSTRAINT `Achat_Fournisseurs0_FK` FOREIGN KEY (`Id_Fournisseurs`) REFERENCES `fournisseurs` (`Id`),
  ADD CONSTRAINT `Achat_Vaccins_FK` FOREIGN KEY (`Id_Vaccins`) REFERENCES `vaccins` (`Id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
