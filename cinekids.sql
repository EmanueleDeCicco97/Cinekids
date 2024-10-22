-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Ott 22, 2024 alle 16:20
-- Versione del server: 10.1.8-MariaDB
-- Versione PHP: 5.6.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinekids`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `admins`
--

CREATE TABLE `admins` (
                          `id` int(11) NOT NULL,
                          `email` varchar(50) NOT NULL,
                          `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `admins`
--

INSERT INTO `admins` (`id`, `email`, `password`) VALUES
    (1, 'alessiortuso@gmail.com', '123');

-- --------------------------------------------------------

--
-- Struttura della tabella `films`
--

CREATE TABLE `films` (
                         `id` int(11) NOT NULL,
                         `titolo` varchar(80) NOT NULL,
                         `genere` varchar(20) NOT NULL,
                         `regista` varchar(30) NOT NULL,
                         `sinossi` text NOT NULL,
                         `locandina` longtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `films`
--

INSERT INTO `films` (`id`, `titolo`, `genere`, `regista`, `sinossi`, `locandina`) VALUES
    (1, 'buonase', 'hsdjkhs', 'wuehwuie', 'wdifuwiwu', ' ');

-- --------------------------------------------------------

--
-- Struttura della tabella `proiezioni`
--

CREATE TABLE `proiezioni` (
                              `id` int(11) NOT NULL,
                              `data` date NOT NULL,
                              `fk_id_film` int(11) NOT NULL,
                              `fk_id_sala` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `proiezioni`
--

INSERT INTO `proiezioni` (`id`, `data`, `fk_id_film`, `fk_id_sala`) VALUES
    (7, '2024-10-06', 1, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `sale`
--

CREATE TABLE `sale` (
                        `id` int(11) NOT NULL,
                        `citta` varchar(50) NOT NULL,
                        `nome_sala` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `sale`
--

INSERT INTO `sale` (`id`, `citta`, `nome_sala`) VALUES
    (2, 'jkahsk', 'jhfjkd');

-- --------------------------------------------------------

--
-- Struttura della tabella `suggerimenti`
--

CREATE TABLE `suggerimenti` (
                                `id` int(11) NOT NULL,
                                `email` varchar(50) NOT NULL,
                                `titolo_film` varchar(80) NOT NULL,
                                `data_invio` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `admins`
--
ALTER TABLE `admins`
    ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `films`
--
ALTER TABLE `films`
    ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `proiezioni`
--
ALTER TABLE `proiezioni`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_id_film` (`fk_id_film`),
  ADD KEY `fk_id_sala` (`fk_id_sala`);

--
-- Indici per le tabelle `sale`
--
ALTER TABLE `sale`
    ADD PRIMARY KEY (`id`);

--
-- Indici per le tabelle `suggerimenti`
--
ALTER TABLE `suggerimenti`
    ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `admins`
--
ALTER TABLE `admins`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `films`
--
ALTER TABLE `films`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `proiezioni`
--
ALTER TABLE `proiezioni`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT per la tabella `sale`
--
ALTER TABLE `sale`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `suggerimenti`
--
ALTER TABLE `suggerimenti`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `proiezioni`
--
ALTER TABLE `proiezioni`
    ADD CONSTRAINT `proiezione_film` FOREIGN KEY (`fk_id_film`) REFERENCES `films` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `proiezione_sala` FOREIGN KEY (`fk_id_sala`) REFERENCES `sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
