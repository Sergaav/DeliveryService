-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE =
        'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema delivery
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema delivery
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `delivery` DEFAULT CHARACTER SET utf8;
USE `delivery`;

-- -----------------------------------------------------
-- Table `delivery`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`roles`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`users`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `login`      VARCHAR(45) NOT NULL,
    `password`   VARCHAR(45) NOT NULL,
    `first_name` VARCHAR(10) NULL,
    `last_name`  VARCHAR(15) NULL,
    `roles_id`   INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
    INDEX `fk_users_roles_idx` (`roles_id` ASC) VISIBLE,
    CONSTRAINT `fk_users_roles`
        FOREIGN KEY (`roles_id`)
            REFERENCES `delivery`.`roles` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`statuses`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`statuses`
(
    `id`   INT         NOT NULL,
    `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`zones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`zones`
(
    `id`        INT         NOT NULL,
    `zone_name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `zone_name_UNIQUE` (`zone_name` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`destination`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`destination`
(
    `id`       INT         NOT NULL,
    `name`     VARCHAR(45) NOT NULL,
    `zones_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
    INDEX `fk_destination_zones1_idx` (`zones_id` ASC) VISIBLE,
    CONSTRAINT `fk_destination_zones1`
        FOREIGN KEY (`zones_id`)
            REFERENCES `delivery`.`zones` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`rates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`rates`
(
    `id`     INT    NOT NULL AUTO_INCREMENT,
    `weight` INT    NOT NULL,
    `rate`   DOUBLE NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `weight_UNIQUE` (`weight` ASC) VISIBLE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`parsels`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`parsels`
(
    `id`          INT         NOT NULL,
    `description` VARCHAR(60) NOT NULL,
    `length`      INT         NOT NULL,
    `width`       INT         NOT NULL,
    `height`      INT         NOT NULL,
    `weight`      INT         NOT NULL,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `delivery`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `delivery`.`orders`
(
    `id`             INT         NOT NULL AUTO_INCREMENT,
    `address`        VARCHAR(45) NOT NULL,
    `date_creation`  DATE        NOT NULL,
    `statuses_id`    INT         NOT NULL,
    `destination_id` INT         NOT NULL,
    `rates_id`       INT         NOT NULL,
    `parsels_id`     INT         NOT NULL,
    `users_id`       INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_orders_statuses1_idx` (`statuses_id` ASC) VISIBLE,
    INDEX `fk_orders_destination1_idx` (`destination_id` ASC) VISIBLE,
    INDEX `fk_orders_rates1_idx` (`rates_id` ASC) VISIBLE,
    INDEX `fk_orders_parsels1_idx` (`parsels_id` ASC) VISIBLE,
    INDEX `fk_orders_users1_idx` (`users_id` ASC) VISIBLE,
    CONSTRAINT `fk_orders_statuses1`
        FOREIGN KEY (`statuses_id`)
            REFERENCES `delivery`.`statuses` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_orders_destination1`
        FOREIGN KEY (`destination_id`)
            REFERENCES `delivery`.`destination` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_orders_rates1`
        FOREIGN KEY (`rates_id`)
            REFERENCES `delivery`.`rates` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_orders_parsels1`
        FOREIGN KEY (`parsels_id`)
            REFERENCES `delivery`.`parsels` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_orders_users1`
        FOREIGN KEY (`users_id`)
            REFERENCES `delivery`.`users` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
