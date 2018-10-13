-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cellulant_banks_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cellulant_banks_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cellulant_banks_db` ;
USE `cellulant_banks_db` ;

-- -----------------------------------------------------
-- Table `cellulant_banks_db`.`bank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cellulant_banks_db`.`bank` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `bic_number` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `card_value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
