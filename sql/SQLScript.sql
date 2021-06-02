-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema delivery
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema delivery
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `delivery` DEFAULT CHARACTER SET utf8 ;
USE `delivery` ;

-- -----------------------------------------------------
-- Table `delivery`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `first_name` VARCHAR(10) NULL,
  `last_name` VARCHAR(15) NULL,
  `balance` DOUBLE ZEROFILL NULL DEFAULT 0.00,
  `locale` VARCHAR(45) NOT NULL,
  `role` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`weight_rate`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`weight_rate` (
  `id` INT NOT NULL,
  `rate_name` VARCHAR(15) NOT NULL,
  `weight` INT NOT NULL,
  `rate` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`parsels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`parsels` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(60) NOT NULL,
  `length` INT NOT NULL,
  `width` INT NOT NULL,
  `height` INT NOT NULL,
  `weight` INT NOT NULL,
  `weight_rate_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_parsels_weight_rate1_idx` (`weight_rate_id` ASC) VISIBLE,
  CONSTRAINT `fk_parsels_weight_rate1`
    FOREIGN KEY (`weight_rate_id`)
    REFERENCES `delivery`.`weight_rate` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`departure`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`departure` (
  `id` INT NOT NULL,
  `city_departure` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`arrive`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`arrive` (
  `id` INT NOT NULL,
  `city_arrive` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `status` ENUM("OPENED", "CONFIRMED", "PAID", "CLOSED") NOT NULL,
  `date_creation` DATE NOT NULL,
  `parsels_id` INT NOT NULL,
  `users_id` INT NOT NULL,
  `departure_id` INT NOT NULL,
  `arrive_id` INT NOT NULL,
  `date_departure` DATE NOT NULL,
  `recipient_name` VARCHAR(45) NOT NULL,
  `bill` DECIMAL NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_parsels1_idx` (`parsels_id` ASC) VISIBLE,
  INDEX `fk_orders_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_orders_departure1_idx` (`departure_id` ASC) VISIBLE,
  INDEX `fk_orders_arrive1_idx` (`arrive_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_parsels1`
    FOREIGN KEY (`parsels_id`)
    REFERENCES `delivery`.`parsels` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `delivery`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_departure1`
    FOREIGN KEY (`departure_id`)
    REFERENCES `delivery`.`departure` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_arrive1`
    FOREIGN KEY (`arrive_id`)
    REFERENCES `delivery`.`arrive` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`departure_has_arrive`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`departure_has_arrive` (
  `departure_id` INT NOT NULL,
  `arrive_id` INT NOT NULL,
  `rate` DOUBLE NOT NULL,
  PRIMARY KEY (`departure_id`, `arrive_id`),
  INDEX `fk_departure_has_arrive_arrive1_idx` (`arrive_id` ASC) VISIBLE,
  INDEX `fk_departure_has_arrive_departure1_idx` (`departure_id` ASC) VISIBLE,
  CONSTRAINT `fk_departure_has_arrive_departure1`
    FOREIGN KEY (`departure_id`)
    REFERENCES `delivery`.`departure` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_departure_has_arrive_arrive1`
    FOREIGN KEY (`arrive_id`)
    REFERENCES `delivery`.`arrive` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
