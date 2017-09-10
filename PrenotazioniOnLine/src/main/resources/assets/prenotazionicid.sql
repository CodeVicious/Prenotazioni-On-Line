-- phpMyAdmin SQL Dump
-- version 4.7.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Creato il: Set 10, 2017 alle 21:05
-- Versione del server: 5.6.35
-- Versione PHP: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prenotazionicid`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `availability`
--

CREATE TABLE `availability` (
  `ID` int(11) NOT NULL,
  `Data` datetime NOT NULL,
  `FKplaces` int(11) NOT NULL,
  `reserved` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dump dei dati per la tabella `availability`
--

INSERT INTO `availability` (`ID`, `Data`, `FKplaces`, `reserved`) VALUES
(2, '2017-05-09 13:00:00', 1, 0),
(3, '2017-08-16 11:00:00', 1, 1),
(4, '2017-08-16 11:00:00', 2, 0),
(5, '2017-05-09 14:00:00', 1, 0),
(6, '2017-05-09 15:00:00', 1, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `clients`
--

CREATE TABLE `clients` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `surname` varchar(100) CHARACTER SET latin1 NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(200) CHARACTER SET latin1 NOT NULL,
  `FK_reservations` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Struttura della tabella `places`
--

CREATE TABLE `places` (
  `ID` int(11) NOT NULL,
  `name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `address` varchar(100) CHARACTER SET latin1 NOT NULL,
  `lat` float NOT NULL,
  `lon` float NOT NULL,
  `type` varchar(30) CHARACTER SET latin1 NOT NULL,
  `color` varchar(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dump dei dati per la tabella `places`
--

INSERT INTO `places` (`ID`, `name`, `address`, `lat`, `lon`, `type`, `color`) VALUES
(1, 'San Miniato Basso', 'Via dei Beccai, 54, 56028 San Miniato Basso PI', 43.6949, 10.8487, 'Anagrafe', '#FF9900'),
(2, 'Ponte a Egola', 'Via Ferrari, 5, 56024 Ponte a Egola', 43.6949, 10.8487, 'Anagrafe', '#EBF8A4'),
(3, 'San Miniato', 'Via vittime del Duomo, 8, 56028, San Miniato', 43.6949, 10.8487, 'Anagrafe', '#D3DCE3');

-- --------------------------------------------------------

--
-- Struttura della tabella `reservations`
--

CREATE TABLE `reservations` (
  `ID` int(11) NOT NULL,
  `FK_availability` int(11) NOT NULL,
  `date_of_reservation` datetime NOT NULL,
  `notes` longtext CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `name` varchar(100) CHARACTER SET latin1 NOT NULL,
  `surname` varchar(100) CHARACTER SET latin1 NOT NULL,
  `username` varchar(50) CHARACTER SET latin1 NOT NULL,
  `email` varchar(100) CHARACTER SET latin1 NOT NULL,
  `ID` int(11) NOT NULL,
  `PW` varchar(200) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`name`, `surname`, `username`, `email`, `ID`, `PW`) VALUES
('Gianni', 'Codevico', 'webmaster', 'gcodevico@comune.san-miniato.pi.it', 1, '50a9c7dbf0fa09e8969978317dca12e8');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `availability`
--
ALTER TABLE `availability`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `places`
--
ALTER TABLE `places`
  ADD PRIMARY KEY (`ID`) USING BTREE;

--
-- Indici per le tabelle `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`ID`);

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `availability`
--
ALTER TABLE `availability`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT per la tabella `clients`
--
ALTER TABLE `clients`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `places`
--
ALTER TABLE `places`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT per la tabella `reservations`
--
ALTER TABLE `reservations`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
