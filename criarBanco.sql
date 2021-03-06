-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.7.9-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.5046
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Copiando estrutura do banco de dados para transpobrasil
CREATE DATABASE IF NOT EXISTS `transpobrasil` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `transpobrasil`;

-- Copiando estrutura para tabela transpobrasil.item
CREATE TABLE IF NOT EXISTS `item` (
  `oid` int(11) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela transpobrasil.lancamento
CREATE TABLE IF NOT EXISTS `lancamento` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `dt_inicial` datetime DEFAULT NULL,
  `dt_final` datetime DEFAULT NULL,
  `vl_total` decimal(8,2) DEFAULT NULL,
  `observacao` text,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
-- Copiando estrutura para tabela transpobrasil.lancamento_item
CREATE TABLE IF NOT EXISTS `lancamento_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lancamento_oid` int(11) NOT NULL,
  `item_oid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lancamento_item_lancamento_idx` (`lancamento_oid`),
  KEY `fk_lancamento_item_item1_idx` (`item_oid`),
  CONSTRAINT `fk_lancamento_item_item1` FOREIGN KEY (`item_oid`) REFERENCES `item` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lancamento_item_lancamento` FOREIGN KEY (`lancamento_oid`) REFERENCES `lancamento` (`oid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
