-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema devsu
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema devsu
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `devsu` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `devsu` ;

-- -----------------------------------------------------
-- Table `devsu`.`people`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsu`.`people` (
  `age` INT NOT NULL,
  `gender` CHAR(1) NOT NULL,
  `address` VARCHAR(255) NULL DEFAULT NULL,
  `id_person` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_person`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `devsu`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsu`.`accounts` (
  `id` BIGINT NOT NULL,
  `account_type` TINYINT NULL DEFAULT NULL,
  `balance` DOUBLE NULL DEFAULT NULL,
  `id_person` VARCHAR(255) NULL DEFAULT NULL,
  `status` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `personfor_idx` (`id_person` ASC) VISIBLE,
  CONSTRAINT `idPerson`
    FOREIGN KEY (`id_person`)
    REFERENCES `devsu`.`people` (`id_person`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `devsu`.`clients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsu`.`clients` (
  `status` BIT(1) NOT NULL,
  `id_client` VARCHAR(255) NOT NULL,
  `id_person` VARCHAR(255) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE INDEX `UK_3smr0ndp3m0lcu7s2lhmpny5i` (`id_person` ASC) VISIBLE,
  CONSTRAINT `FKpahk4cj3nuimgr9y3n5qqn2d`
    FOREIGN KEY (`id_person`)
    REFERENCES `devsu`.`people` (`id_person`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `devsu`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `devsu`.`transactions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `id_account` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idAccount_idx` (`id_account` ASC) VISIBLE,
  CONSTRAINT `idAccount`
    FOREIGN KEY (`id_account`)
    REFERENCES `devsu`.`accounts` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 17
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
