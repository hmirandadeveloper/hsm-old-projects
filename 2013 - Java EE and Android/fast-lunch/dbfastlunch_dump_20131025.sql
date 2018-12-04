-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 25, 2013 at 09:53 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dbfastlunch`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_cardapio`
--

CREATE TABLE IF NOT EXISTS `tb_cardapio` (
  `id_cardapio` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(150) NOT NULL,
  `id_tipo_cardapio` bigint(20) NOT NULL,
  `validade` date DEFAULT NULL,
  PRIMARY KEY (`id_cardapio`),
  KEY `tb_cardapio_FKIndex1` (`id_tipo_cardapio`),
  KEY `FKABBB56E27CA257` (`id_tipo_cardapio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `tb_cardapio`
--

INSERT INTO `tb_cardapio` (`id_cardapio`, `nome`, `id_tipo_cardapio`, `validade`) VALUES
(1, 'Bebidas', 1, '2014-10-14'),
(2, 'Bebidas Alcolicas', 1, '2090-11-13'),
(3, 'pizzas', 2, '2014-10-14'),
(4, 'Sucos', 3, '2013-11-19'),
(5, 'Pizzas', 4, '2013-11-19'),
(6, 'Macarronadas', 4, '2013-11-19'),
(7, 'Sucos', 5, '2014-11-19'),
(8, 'Macarronadas', 6, '2014-11-19'),
(9, 'Carne Bovina', 7, '2014-11-19');

-- --------------------------------------------------------

--
-- Table structure for table `tb_cardapio_produto`
--

CREATE TABLE IF NOT EXISTS `tb_cardapio_produto` (
  `idcardapio_produto` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_produto` bigint(20) NOT NULL,
  `id_cardapio` bigint(20) NOT NULL,
  `ativo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`idcardapio_produto`),
  KEY `cardapio_produto_FKIndex1` (`id_produto`),
  KEY `cardapio_produto_FKIndex2` (`id_cardapio`),
  KEY `FK6BDB7B10B102365C` (`id_produto`),
  KEY `FK6BDB7B10E5FCEE20` (`id_cardapio`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Dumping data for table `tb_cardapio_produto`
--

INSERT INTO `tb_cardapio_produto` (`idcardapio_produto`, `id_produto`, `id_cardapio`, `ativo`) VALUES
(1, 1, 1, 1),
(2, 2, 2, 1),
(6, 1, 2, 1),
(7, 6, 1, 1),
(8, 7, 1, 1),
(9, 6, 2, 1),
(10, 7, 2, 1),
(11, 9, 1, 1),
(12, 2, 1, 1),
(13, 10, 2, 1),
(14, 11, 1, 1),
(15, 11, 2, 1),
(18, 14, 1, 1),
(19, 14, 2, 1),
(20, 15, 3, 1),
(21, 16, 2, 1),
(22, 17, 4, 1),
(23, 18, 4, 1),
(24, 19, 5, 1),
(25, 20, 7, 1),
(26, 21, 9, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_cargo`
--

CREATE TABLE IF NOT EXISTS `tb_cargo` (
  `id_cargo` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_estabelecimento` bigint(20) NOT NULL,
  `descricao` varchar(100) NOT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_cargo`),
  KEY `tb_cargo_FKIndex1` (`id_estabelecimento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=31 ;

--
-- Dumping data for table `tb_cargo`
--

INSERT INTO `tb_cargo` (`id_cargo`, `id_estabelecimento`, `descricao`, `ativo`) VALUES
(1, 1, 'Provisorio', 1),
(2, 1, 'Gerente', 1),
(3, 1, 'Supervisor', 1),
(4, 1, 'Faxineiro', 1),
(5, 1, 'Massagista', 1),
(6, 1, 'Maquinista', 1),
(12, 1, 'Pistoleiro', 1),
(13, 1, 'Pedreiro', 1),
(14, 1, 'Manobrista', 1),
(15, 1, 'Matador', 1),
(16, 1, 'Traficante', 1),
(17, 1, 'Diretor', 1),
(27, 12, 'Administrador', 1),
(28, 13, 'Administrador', 1),
(29, 14, 'Administrador', 1),
(30, 15, 'Administrador', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_cliente`
--

CREATE TABLE IF NOT EXISTS `tb_cliente` (
  `id_cliente` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(150) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `celular` varchar(11) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `data_nascimento` date NOT NULL,
  `sexo` char(1) NOT NULL,
  `ativo` tinyint(1) NOT NULL,
  `data_cadastro` date NOT NULL,
  `pontuacao_fidelidade` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `tb_cliente_index_cpf` (`cpf`),
  UNIQUE KEY `tb_cliente_index_email` (`email`),
  KEY `tb_cliente_FKIndex1` (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `tb_cliente`
--

INSERT INTO `tb_cliente` (`id_cliente`, `id_usuario`, `nome`, `sobrenome`, `cpf`, `telefone`, `celular`, `email`, `data_nascimento`, `sexo`, `ativo`, `data_cadastro`, `pontuacao_fidelidade`) VALUES
(7, 9, 'Helio', 'Miranda', '06789209409', '34281303', NULL, 'h3l10@hotmail.com', '2013-10-04', 'M', 1, '2013-10-04', NULL),
(8, 10, 'Kassimo', 'Junior', '12345678909', '33221122', NULL, 'k@ig.com.br', '2013-10-07', 'M', 1, '2013-10-07', NULL),
(9, 11, 'Paulo', 'Almeida', '01234567890', '33556677', NULL, 'pa@ig.com.br', '2013-10-07', 'M', 1, '2013-10-07', NULL),
(10, 12, 'Marcio', 'Luiz', '09876543210', '33221122', NULL, 'ml@ig.com.br', '2013-10-07', 'M', 1, '2013-10-07', NULL),
(11, 13, 'Antonio', 'Araujo', '12309845609', '34281303', NULL, 'antarau@ig.com.br', '2013-10-08', 'M', 1, '2013-10-08', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_cliente_endereco`
--

CREATE TABLE IF NOT EXISTS `tb_cliente_endereco` (
  `id_endereco` bigint(20) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  PRIMARY KEY (`id_endereco`,`id_cliente`),
  KEY `tb_cliente_has_tb_endereco_FKIndex1` (`id_cliente`),
  KEY `tb_cliente_has_tb_endereco_FKIndex2` (`id_endereco`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_cliente_endereco`
--

INSERT INTO `tb_cliente_endereco` (`id_endereco`, `id_cliente`) VALUES
(4, 7),
(5, 8),
(6, 9),
(7, 10),
(8, 11);

-- --------------------------------------------------------

--
-- Table structure for table `tb_delivery`
--

CREATE TABLE IF NOT EXISTS `tb_delivery` (
  `id_delivery` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_endereco` bigint(20) NOT NULL,
  `id_pedido` bigint(20) NOT NULL,
  `id_frete` bigint(20) NOT NULL,
  PRIMARY KEY (`id_delivery`),
  KEY `tb_delivery_FKIndex1` (`id_frete`),
  KEY `tb_delivery_FKIndex2` (`id_pedido`),
  KEY `tb_delivery_FKIndex3` (`id_endereco`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_endereco`
--

CREATE TABLE IF NOT EXISTS `tb_endereco` (
  `id_endereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `cep` varchar(8) NOT NULL,
  `logradouro` varchar(200) NOT NULL,
  `numero` decimal(10,0) NOT NULL,
  `complemento` varchar(100) DEFAULT NULL,
  `bairro` varchar(100) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `uf` varchar(2) NOT NULL,
  `ponto_referencia` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`),
  KEY `tb_endereco_index_cep` (`cep`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

--
-- Dumping data for table `tb_endereco`
--

INSERT INTO `tb_endereco` (`id_endereco`, `cep`, `logradouro`, `numero`, `complemento`, `bairro`, `cidade`, `uf`, `ponto_referencia`) VALUES
(4, '50760008', 'R. Nogueira de Melo', 88, 'casa', 'Afogados', 'Recife', 'PE', NULL),
(5, '50008990', 'R. de Teste', 10, 'casa', 'Ibura', 'Recife', 'PE', NULL),
(6, '50008990', 'R. de Teste 2', 22, 'casa', 'Ibura', 'Recife', 'PE', NULL),
(7, '50008990', 'R. de Teste 3', 33, 'casa', 'Ibura', 'Recife', 'PE', NULL),
(8, '50008990', 'R. de Teste 4', 88, 'casa', 'Ibura', 'Recife', 'PE', NULL),
(24, '50001000', 'R. de Teste Estabelecimento', 10, 'casa', 'Bongi', 'Recife', 'PE', NULL),
(25, '50001000', 'R. de Teste Estabelecimento', 10, 'casa', 'Bongi', 'Recife', 'PE', NULL),
(26, '50001000', 'R. de Teste Estabelecimento', 12, 'casa', 'Bongi', 'Recife', 'PE', NULL),
(28, '50760008', 'R. Nogueira de Melo', 38, 'casa', 'Afogados', 'Recife', 'PE', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_estabelecimento`
--

CREATE TABLE IF NOT EXISTS `tb_estabelecimento` (
  `id_estabelecimento` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_matriz` bigint(20) DEFAULT NULL,
  `razao_social` varchar(150) NOT NULL,
  `nome_fantasia` varchar(150) DEFAULT NULL,
  `cnpj` varchar(15) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tipo_estabelecimento` char(1) NOT NULL,
  `id_endereco` bigint(20) NOT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_estabelecimento`),
  UNIQUE KEY `tb_estabelecimento_index_cnpj` (`cnpj`),
  KEY `tb_estabelecimento_FKIndex1` (`id_matriz`),
  KEY `id_endereco` (`id_endereco`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Dumping data for table `tb_estabelecimento`
--

INSERT INTO `tb_estabelecimento` (`id_estabelecimento`, `id_matriz`, `razao_social`, `nome_fantasia`, `cnpj`, `telefone`, `email`, `tipo_estabelecimento`, `id_endereco`, `ativo`) VALUES
(1, NULL, 'Empresa Temporaria', 'Empresa Temporaria', '000111222000133', '33550000', 'vazio@vazio.com.br', 'M', 4, 1),
(12, NULL, 'Empresa de Teste A', 'TA Lanches', '111000111000111', '33443344', 'al@ig.com.br', 'M', 24, 1),
(13, NULL, 'Empresa de Teste B', 'TB Lanches', '111000111000122', '33443355', 'alb@ig.com.br', 'M', 25, 1),
(14, NULL, 'Empresa de Teste C', 'TC Lanches', '111000111000133', '33443366', 'alc@ig.com.br', 'M', 26, 1),
(15, NULL, 'Anthurium Alimentos LTDA', 'Anthurium Alimentos', '555222111000188', '33117788', 'aa@ig.com.br', 'M', 28, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_fidelidade_troca`
--

CREATE TABLE IF NOT EXISTS `tb_fidelidade_troca` (
  `id_fidelidade_troca` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_produto` bigint(20) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `data_hora` timestamp NULL DEFAULT NULL,
  `pontos` int(11) NOT NULL,
  PRIMARY KEY (`id_fidelidade_troca`),
  KEY `tb_fidelidade_troca_FKIndex1` (`id_cliente`),
  KEY `tb_fidelidade_troca_FKIndex2` (`id_produto`),
  KEY `id_cliente` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_frete`
--

CREATE TABLE IF NOT EXISTS `tb_frete` (
  `id_frete` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(100) NOT NULL,
  `valor` decimal(10,0) NOT NULL,
  `id_estabelecimento` bigint(20) NOT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_frete`),
  KEY `id_estabelecimento` (`id_estabelecimento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_funcionario`
--

CREATE TABLE IF NOT EXISTS `tb_funcionario` (
  `id_funcionario` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_usuario` bigint(20) NOT NULL,
  `id_cargo` bigint(20) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sobrenome` varchar(150) NOT NULL,
  `matricula` varchar(15) NOT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_funcionario`),
  UNIQUE KEY `tb_funcionario_index_matricula` (`matricula`),
  KEY `tb_funcionario_FKIndex1` (`id_cargo`),
  KEY `tb_funcionario_FKIndex2` (`id_usuario`),
  KEY `id_cargo` (`id_cargo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=26 ;

--
-- Dumping data for table `tb_funcionario`
--

INSERT INTO `tb_funcionario` (`id_funcionario`, `id_usuario`, `id_cargo`, `nome`, `sobrenome`, `matricula`, `ativo`) VALUES
(1, 11, 1, 'Paulo', 'Almeida', '123', 1),
(8, 21, 5, 'Marcia', 'Sampaio', '456', 1),
(9, 22, 16, 'Myllow', 'Marley das Ervas', '234', 1),
(10, 23, 5, 'Lucas', 'Ferreira', '654', 1),
(11, 24, 2, 'Eduardo', 'Miranda', '789', 1),
(14, 27, 12, 'Joao', 'Batista', '987', 1),
(15, 31, 13, 'Fabrimario', 'Torreio', '345', 1),
(16, 32, 13, 'Filipe', 'Barros', '890', 1),
(17, 33, 12, 'Mauricio', 'Moreira', '098', 1),
(18, 34, 3, 'Julia', 'Fernandes', '012', 1),
(19, 35, 14, 'Daniela', 'Morais', '023', 1),
(20, 36, 2, 'Luciana', 'Matias', '002', 1),
(21, 37, 3, 'Maria', 'Antonia', '003', 1),
(22, 38, 27, 'Lampreu', 'Fulticio', '004', 1),
(23, 41, 28, 'Fabiano', 'Almeida', '009', 1),
(24, 42, 29, 'Marcelo', 'Lins', '005', 1),
(25, 43, 30, 'Eduardo', 'C. A. de Miranda Filho', '011', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_horario_funcionamento`
--

CREATE TABLE IF NOT EXISTS `tb_horario_funcionamento` (
  `id_horario_funcionamento` bigint(20) NOT NULL AUTO_INCREMENT,
  `horario_abertura` time NOT NULL,
  `horario_fechamento` time NOT NULL,
  `dia_semana` enum('SEG','TER','QUA','QUI','SEX','SAB','DOM') NOT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_horario_funcionamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_horario_funcionamento_delivery`
--

CREATE TABLE IF NOT EXISTS `tb_horario_funcionamento_delivery` (
  `id_horario_funcionamento_delivery` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_horario_funcionamento` bigint(20) NOT NULL,
  `id_estabelecimento` bigint(20) NOT NULL,
  `disponivel` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_horario_funcionamento_delivery`),
  KEY `tb_horario_funcionamento_delivery_FKIndex1` (`id_horario_funcionamento`),
  KEY `id_estabelecimento` (`id_estabelecimento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_horario_funcionamento_estabelecimento`
--

CREATE TABLE IF NOT EXISTS `tb_horario_funcionamento_estabelecimento` (
  `id_horario_funcionamento_estabelecimento` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_estabelecimento` bigint(20) NOT NULL,
  `id_horario_funcionamento` bigint(20) NOT NULL,
  `disponivel` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_horario_funcionamento_estabelecimento`),
  KEY `tb_horario_funcionamento_estabelecimento_FKIndex1` (`id_estabelecimento`),
  KEY `tb_horario_funcionamento_estabelecimento_FKIndex2` (`id_horario_funcionamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tb_item_pedido`
--

CREATE TABLE IF NOT EXISTS `tb_item_pedido` (
  `id_pedido` bigint(20) NOT NULL,
  `id_item_pedido` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_cardapio_produto` bigint(20) NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`id_item_pedido`),
  KEY `tb_pedido_has_cardapio_produto_FKIndex1` (`id_pedido`),
  KEY `tb_pedido_has_cardapio_produto_FKIndex2` (`id_cardapio_produto`),
  KEY `id_cardapio_produto` (`id_cardapio_produto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `tb_item_pedido`
--

INSERT INTO `tb_item_pedido` (`id_pedido`, `id_item_pedido`, `id_cardapio_produto`, `quantidade`) VALUES
(19, 1, 7, 2),
(19, 2, 1, 1),
(20, 3, 8, 2),
(20, 4, 20, 3),
(25, 5, 11, 2),
(25, 6, 1, 1),
(31, 15, 22, 1),
(31, 16, 24, 1),
(32, 17, 25, 1),
(32, 18, 26, 1),
(32, 19, 26, 1),
(33, 20, 7, 2),
(34, 21, 2, 2),
(35, 22, 12, 1),
(35, 23, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `tb_pedido`
--

CREATE TABLE IF NOT EXISTS `tb_pedido` (
  `id_pedido` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero_pedido` bigint(20) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  `id_funcionario` bigint(20) NOT NULL,
  `id_estabelecimento` bigint(20) NOT NULL,
  `data_pedido` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `valor_total` decimal(10,2) NOT NULL,
  `origem_pedido` enum('MOBILE','MOBILE_DELIVERY','WEB','LOCAL') DEFAULT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `tb_pedido_FKIndex1` (`id_cliente`),
  KEY `tb_pedido_FKIndex2` (`id_estabelecimento`),
  KEY `tb_pedido_FKIndex3` (`id_funcionario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `tb_pedido`
--

INSERT INTO `tb_pedido` (`id_pedido`, `numero_pedido`, `id_cliente`, `id_funcionario`, `id_estabelecimento`, `data_pedido`, `valor_total`, `origem_pedido`) VALUES
(19, 1, 7, 1, 1, '2013-10-24 17:21:46', 3.50, 'LOCAL'),
(20, 2, 7, 1, 1, '2013-10-24 17:23:35', 3.50, 'LOCAL'),
(25, 3, 7, 1, 1, '2013-10-24 17:57:44', 3.50, 'LOCAL'),
(31, 1, 7, 24, 14, '2013-10-24 19:29:05', 8.00, 'LOCAL'),
(32, 1, 7, 25, 15, '2013-10-24 19:53:34', 38.00, 'LOCAL'),
(33, 4, 7, 1, 1, '2013-10-25 17:05:22', 5.00, 'LOCAL'),
(34, 5, 7, 1, 1, '2013-10-25 18:31:11', 4.00, 'LOCAL'),
(35, 6, 7, 1, 1, '2013-10-25 19:11:45', 7.00, 'LOCAL');

-- --------------------------------------------------------

--
-- Table structure for table `tb_produto`
--

CREATE TABLE IF NOT EXISTS `tb_produto` (
  `id_produto` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) NOT NULL,
  `descricao` varchar(150) NOT NULL,
  `disponibilidade_fidelidade` bit(1) DEFAULT NULL,
  `imagem` varchar(250) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `pontuacao_fidelidade` int(11) DEFAULT NULL,
  `preco` double NOT NULL,
  `preco_promocional` double DEFAULT NULL,
  PRIMARY KEY (`id_produto`),
  UNIQUE KEY `id_produto` (`id_produto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `tb_produto`
--

INSERT INTO `tb_produto` (`id_produto`, `ativo`, `descricao`, `disponibilidade_fidelidade`, `imagem`, `nome`, `pontuacao_fidelidade`, `preco`, `preco_promocional`) VALUES
(1, '1', 'Suco de Poupas', '1', 'Sem Imagem', 'Suco', 120, 3.5, 2.5),
(2, '1', 'Água Ardente', '1', 'Sem Imagem', 'Pitu', 90, 2, 0),
(6, '1', 'Suco de polpa, com leite', '1', 'Sem Imagem', 'Suco com leite', 120, 3.5, 2.5),
(7, '1', 'Refrigerante 300ml', '1', 'Sem Imagem', 'Sprite', 120, 3.5, 2.5),
(9, '1', 'Refrigerante 300ml', '1', 'Sem Imagem', 'fanta', 120, 3.5, 2.5),
(10, '1', 'Refrigerante 300ml', '1', 'Sem Imagem', 'Pitu Gold', 120, 3.5, 2.5),
(11, '1', 'Agua mineral', '1', 'Sem Imagem', 'Agua', 50, 1, 0.75),
(14, '1', 'Quartinho', '1', 'Sem Imagem', 'Conhaque', 90, 2, 1),
(15, '1', 'pizza com 04 fatias', '1', 'Sem Imagem', 'pizza pequena', 200, 8, 5),
(16, '1', 'Bebida tri destilada', '1', 'Sem Imagem', 'Vodka', 0, 9, 0),
(17, '1', 'Suco de Polpa', '1', 'Sem Imagem', 'Suco', 0, 2.5, 2),
(18, '1', 'Suco de Polpa', '1', 'Sem Imagem', 'Suco com leite', 0, 2.5, 0),
(19, '1', 'Pizza com 04 fatias', '1', 'Sem Imagem', 'Pizza Calabresa Pequena', 300, 8, 7.5),
(20, '1', 'Suco de Polpa', '1', 'Sem Imagem', 'Suco', 0, 2.5, 2),
(21, '1', 'Maminha fatiada para petisco', '1', 'Sem Imagem', 'Maminha', 0, 18, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_status_pedido`
--

CREATE TABLE IF NOT EXISTS `tb_status_pedido` (
  `id_status_pedido` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_hora` datetime NOT NULL,
  `status_pedido` varchar(12) NOT NULL,
  `id_pedido` bigint(20) NOT NULL,
  PRIMARY KEY (`id_status_pedido`),
  UNIQUE KEY `id_status_pedido` (`id_status_pedido`),
  KEY `tb_status_pedido_ibfk_1` (`id_pedido`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `tb_status_pedido`
--

INSERT INTO `tb_status_pedido` (`id_status_pedido`, `data_hora`, `status_pedido`, `id_pedido`) VALUES
(4, '2013-10-24 19:29:05', 'SOLICITACAO', 31),
(5, '2013-10-24 19:53:34', 'SOLICITACAO', 32),
(6, '2013-10-25 14:05:22', 'SOLICITACAO', 33),
(7, '2013-10-25 15:31:11', 'COZINHA', 34),
(8, '2013-10-25 16:11:45', 'COZINHA', 35);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tipo_cardapio`
--

CREATE TABLE IF NOT EXISTS `tb_tipo_cardapio` (
  `id_tipo_cardapio` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) DEFAULT NULL,
  `descricao` varchar(150) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `id_estabelecimento` bigint(20) NOT NULL,
  PRIMARY KEY (`id_tipo_cardapio`),
  UNIQUE KEY `id_tipo_cardapio` (`id_tipo_cardapio`),
  KEY `tb_tipo_cardapio_ibfk_2` (`id_estabelecimento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `tb_tipo_cardapio`
--

INSERT INTO `tb_tipo_cardapio` (`id_tipo_cardapio`, `ativo`, `descricao`, `nome`, `id_estabelecimento`) VALUES
(1, '1', 'Catalogo de Bebidas', 'Vinhos', 1),
(2, '1', 'Massas', 'Massas', 1),
(3, '1', 'Todos os Tipo de Bebidas', 'Bebidas', 14),
(4, '1', 'Todos os tipos de massas', 'Massas', 14),
(5, '1', 'Todos os Tipo de Bebidas', 'Bebidas', 15),
(6, '1', 'Todos os tipos de massas', 'Massas', 15),
(7, '1', 'Todos os tipos de carnes', 'Carnes', 15);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tipo_pagamento`
--

CREATE TABLE IF NOT EXISTS `tb_tipo_pagamento` (
  `id_tipo_pagamento` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` bit(1) DEFAULT NULL,
  `descricao` varchar(50) DEFAULT NULL,
  `id_estabelecimento` bigint(20) NOT NULL,
  PRIMARY KEY (`id_tipo_pagamento`),
  UNIQUE KEY `id_tipo_pagamento` (`id_tipo_pagamento`),
  KEY `tb_tipo_pagamento_ibfk_1` (`id_estabelecimento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `tb_tipo_pagamento`
--

INSERT INTO `tb_tipo_pagamento` (`id_tipo_pagamento`, `ativo`, `descricao`, `id_estabelecimento`) VALUES
(1, '1', 'Cheque', 1),
(2, '1', 'Dinheiro', 14),
(3, '1', 'Cartao', 14),
(4, '1', 'Cheque', 14),
(5, '1', 'Dinheiro', 15),
(6, '1', 'Cartao', 15),
(8, '1', 'Cheque', 1),
(9, '1', 'Cheque', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_tipo_pagamento_pedido`
--

CREATE TABLE IF NOT EXISTS `tb_tipo_pagamento_pedido` (
  `id_pedido` bigint(20) NOT NULL,
  `id_tipo_pagamento` bigint(20) NOT NULL,
  PRIMARY KEY (`id_pedido`,`id_tipo_pagamento`),
  KEY `tb_tipo_pagamento_pedido_ibfk_1` (`id_tipo_pagamento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_tipo_pagamento_pedido`
--

INSERT INTO `tb_tipo_pagamento_pedido` (`id_pedido`, `id_tipo_pagamento`) VALUES
(33, 1),
(31, 2),
(31, 3),
(32, 5),
(34, 8),
(35, 9);

-- --------------------------------------------------------

--
-- Table structure for table `tb_usuario`
--

CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `id_usuario` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `senha` varchar(50) DEFAULT NULL,
  `tipo_usuario` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `id_usuario` (`id_usuario`),
  UNIQUE KEY `login` (`login`),
  UNIQUE KEY `login_2` (`login`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=44 ;

--
-- Dumping data for table `tb_usuario`
--

INSERT INTO `tb_usuario` (`id_usuario`, `login`, `senha`, `tipo_usuario`) VALUES
(1, 'admin', 'admin', 'ADMIN'),
(2, 'user', 'user', 'USER'),
(9, 'h3l10', '123456', 'ADMIN'),
(10, 'kassimo', '123456', 'USER'),
(11, 'paulo', '123456', 'GER'),
(12, 'marcio', '123456', 'ADMIN'),
(13, 'antonio', '123456', 'ADMIN'),
(21, 'marcia', '123', 'FUNC'),
(22, 'myllow', '123', 'FUNC'),
(23, 'lucas', '123', 'FUNC'),
(24, 'edu', '123', 'FUNC'),
(27, 'joao', '123', 'FUNC'),
(31, 'fabri', '12', 'FUNC'),
(32, 'filipe', '123', 'FUNC'),
(33, 'mauri', '1', 'GER'),
(34, 'juju', '123', 'GER'),
(35, 'dani', '1', 'FUNC'),
(36, 'luci', '123', 'GER'),
(37, 'maria', '123', 'GER'),
(38, 'lam', '1', 'GER'),
(41, 'fabi', '123', 'GER'),
(42, 'marcelo', '123', 'GER'),
(43, 'eduagro', '123', 'GER');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_cardapio`
--
ALTER TABLE `tb_cardapio`
  ADD CONSTRAINT `tb_cardapio_ibfk_2` FOREIGN KEY (`id_tipo_cardapio`) REFERENCES `tb_tipo_cardapio` (`id_tipo_cardapio`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FKABBB56E27CA257` FOREIGN KEY (`id_tipo_cardapio`) REFERENCES `tb_tipo_cardapio` (`id_tipo_cardapio`);

--
-- Constraints for table `tb_cardapio_produto`
--
ALTER TABLE `tb_cardapio_produto`
  ADD CONSTRAINT `tb_cardapio_produto_ibfk_4` FOREIGN KEY (`id_cardapio`) REFERENCES `tb_cardapio` (`id_cardapio`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_cardapio_produto_ibfk_3` FOREIGN KEY (`id_produto`) REFERENCES `tb_produto` (`id_produto`) ON UPDATE CASCADE,
  ADD CONSTRAINT `FK6BDB7B10B102365C` FOREIGN KEY (`id_produto`) REFERENCES `tb_produto` (`id_produto`),
  ADD CONSTRAINT `FK6BDB7B10E5FCEE20` FOREIGN KEY (`id_cardapio`) REFERENCES `tb_cardapio` (`id_cardapio`);

--
-- Constraints for table `tb_cargo`
--
ALTER TABLE `tb_cargo`
  ADD CONSTRAINT `tb_cargo_ibfk_2` FOREIGN KEY (`id_estabelecimento`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_cliente`
--
ALTER TABLE `tb_cliente`
  ADD CONSTRAINT `tb_cliente_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_cliente_endereco`
--
ALTER TABLE `tb_cliente_endereco`
  ADD CONSTRAINT `tb_cliente_endereco_ibfk_4` FOREIGN KEY (`id_cliente`) REFERENCES `tb_cliente` (`id_cliente`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_cliente_endereco_ibfk_3` FOREIGN KEY (`id_endereco`) REFERENCES `tb_endereco` (`id_endereco`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_delivery`
--
ALTER TABLE `tb_delivery`
  ADD CONSTRAINT `tb_delivery_ibfk_6` FOREIGN KEY (`id_frete`) REFERENCES `tb_frete` (`id_frete`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_delivery_ibfk_4` FOREIGN KEY (`id_endereco`) REFERENCES `tb_endereco` (`id_endereco`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_delivery_ibfk_5` FOREIGN KEY (`id_pedido`) REFERENCES `tb_pedido` (`id_pedido`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_estabelecimento`
--
ALTER TABLE `tb_estabelecimento`
  ADD CONSTRAINT `tb_estabelecimento_ibfk_4` FOREIGN KEY (`id_endereco`) REFERENCES `tb_endereco` (`id_endereco`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_estabelecimento_ibfk_2` FOREIGN KEY (`id_matriz`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`) ON UPDATE CASCADE;

--
-- Constraints for table `tb_fidelidade_troca`
--
ALTER TABLE `tb_fidelidade_troca`
  ADD CONSTRAINT `tb_fidelidade_troca_ibfk_4` FOREIGN KEY (`id_cliente`) REFERENCES `tb_cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_fidelidade_troca_ibfk_3` FOREIGN KEY (`id_produto`) REFERENCES `tb_produto` (`id_produto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_frete`
--
ALTER TABLE `tb_frete`
  ADD CONSTRAINT `tb_frete_ibfk_1` FOREIGN KEY (`id_estabelecimento`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_funcionario`
--
ALTER TABLE `tb_funcionario`
  ADD CONSTRAINT `tb_funcionario_ibfk_5` FOREIGN KEY (`id_cargo`) REFERENCES `tb_cargo` (`id_cargo`) ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_funcionario_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_horario_funcionamento_delivery`
--
ALTER TABLE `tb_horario_funcionamento_delivery`
  ADD CONSTRAINT `tb_horario_funcionamento_delivery_ibfk_1` FOREIGN KEY (`id_horario_funcionamento`) REFERENCES `tb_horario_funcionamento` (`id_horario_funcionamento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_horario_funcionamento_delivery_ibfk_3` FOREIGN KEY (`id_estabelecimento`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`);

--
-- Constraints for table `tb_horario_funcionamento_estabelecimento`
--
ALTER TABLE `tb_horario_funcionamento_estabelecimento`
  ADD CONSTRAINT `tb_horario_funcionamento_estabelecimento_ibfk_1` FOREIGN KEY (`id_estabelecimento`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_horario_funcionamento_estabelecimento_ibfk_2` FOREIGN KEY (`id_horario_funcionamento`) REFERENCES `tb_horario_funcionamento` (`id_horario_funcionamento`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_item_pedido`
--
ALTER TABLE `tb_item_pedido`
  ADD CONSTRAINT `tb_item_pedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `tb_pedido` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_item_pedido_ibfk_2` FOREIGN KEY (`id_cardapio_produto`) REFERENCES `tb_cardapio_produto` (`idcardapio_produto`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_pedido`
--
ALTER TABLE `tb_pedido`
  ADD CONSTRAINT `tb_pedido_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `tb_cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_pedido_ibfk_2` FOREIGN KEY (`id_estabelecimento`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `tb_pedido_ibfk_3` FOREIGN KEY (`id_funcionario`) REFERENCES `tb_funcionario` (`id_funcionario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_status_pedido`
--
ALTER TABLE `tb_status_pedido`
  ADD CONSTRAINT `tb_status_pedido_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `tb_pedido` (`id_pedido`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `tb_tipo_cardapio`
--
ALTER TABLE `tb_tipo_cardapio`
  ADD CONSTRAINT `tb_tipo_cardapio_ibfk_2` FOREIGN KEY (`id_estabelecimento`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_tipo_pagamento`
--
ALTER TABLE `tb_tipo_pagamento`
  ADD CONSTRAINT `tb_tipo_pagamento_ibfk_1` FOREIGN KEY (`id_estabelecimento`) REFERENCES `tb_estabelecimento` (`id_estabelecimento`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tb_tipo_pagamento_pedido`
--
ALTER TABLE `tb_tipo_pagamento_pedido`
  ADD CONSTRAINT `tb_tipo_pagamento_pedido_ibfk_1` FOREIGN KEY (`id_tipo_pagamento`) REFERENCES `tb_tipo_pagamento` (`id_tipo_pagamento`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `tb_tipo_pagamento_pedido_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `tb_pedido` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
