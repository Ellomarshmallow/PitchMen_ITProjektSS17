-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pitchmen_itprojekt_schema
-- -----------------------------------------------------
-- This database contains all tables to realize our Business-Logic. 
DROP SCHEMA IF EXISTS `pitchmen_itprojekt_schema` ;

-- -----------------------------------------------------
-- Schema pitchmen_itprojekt_schema
--
-- This database contains all tables to realize our Business-Logic. 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pitchmen_itprojekt_schema` DEFAULT CHARACTER SET utf8 ;
USE `pitchmen_itprojekt_schema` ;

-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`company` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`company` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`team` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`team` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`person`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`person` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`person` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `firstName` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`partnerProfile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`partnerProfile` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`partnerProfile` (
  `id` INT NOT NULL,
  `dateCreated` DATE NULL,
  `dateChanged` DATE NULL,
  `company_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_partnerProfile_company_idx` (`company_id` ASC),
  INDEX `fk_partnerProfile_team1_idx` (`team_id` ASC),
  INDEX `fk_partnerProfile_person1_idx` (`person_id` ASC),
  CONSTRAINT `fk_partnerProfile_company`
    FOREIGN KEY (`company_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partnerProfile_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_partnerProfile_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`marketplace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`marketplace` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`marketplace` (
  `id` INT NOT NULL,
  `description` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `person_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_marketplace_person1_idx` (`person_id` ASC),
  INDEX `fk_marketplace_team1_idx` (`team_id` ASC),
  INDEX `fk_marketplace_company1_idx` (`company_id` ASC),
  CONSTRAINT `fk_marketplace_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_marketplace_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_marketplace_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`project` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`project` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `dateOpened` DATE NULL,
  `dateClosed` DATE NULL,
  `marketplace_id` INT NOT NULL,
  `person_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_project_marketplace1_idx` (`marketplace_id` ASC),
  INDEX `fk_project_person1_idx` (`person_id` ASC),
  CONSTRAINT `fk_project_marketplace1`
    FOREIGN KEY (`marketplace_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`marketplace` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_project_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`jobPosting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`jobPosting` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`jobPosting` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `text` VARCHAR(255) NULL,
  `deadLine` DATE NULL,
  `partnerProfile_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_jobPosting_partnerProfile1_idx` (`partnerProfile_id` ASC),
  INDEX `fk_jobPosting_project1_idx` (`project_id` ASC),
  CONSTRAINT `fk_jobPosting_partnerProfile1`
    FOREIGN KEY (`partnerProfile_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`partnerProfile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_jobPosting_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`trait`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`trait` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`trait` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `value` VARCHAR(45) NULL,
  `partnerProfile_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trait_partnerProfile1_idx` (`partnerProfile_id` ASC),
  CONSTRAINT `fk_trait_partnerProfile1`
    FOREIGN KEY (`partnerProfile_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`partnerProfile` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`application` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`application` (
  `id` INT NOT NULL,
  `text` VARCHAR(255) NULL,
  `dateCreated` DATE NULL,
  `jobPosting_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_application_jobPosting1_idx` (`jobPosting_id` ASC),
  CONSTRAINT `fk_application_jobPosting1`
    FOREIGN KEY (`jobPosting_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`jobPosting` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`rating` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`rating` (
  `id` INT NOT NULL,
  `statement` VARCHAR(45) NULL,
  `score` FLOAT NULL,
  `application_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rating_application1_idx` (`application_id` ASC),
  CONSTRAINT `fk_rating_application1`
    FOREIGN KEY (`application_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`application` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`participation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`participation` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`participation` (
  `id` INT NOT NULL,
  `workload` FLOAT NULL,
  `dateClosed` DATE NULL,
  `dateOpened` DATE NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`person_has_participation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`person_has_participation` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`person_has_participation` (
  `person_id` INT NOT NULL,
  `participation_id` INT NOT NULL,
  INDEX `fk_person_has_participation_participation1_idx` (`participation_id` ASC),
  INDEX `fk_person_has_participation_person1_idx` (`person_id` ASC),
  CONSTRAINT `fk_person_has_participation_person1`
    FOREIGN KEY (`person_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`person` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_person_has_participation_participation1`
    FOREIGN KEY (`participation_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`participation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`company_has_participation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`company_has_participation` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`company_has_participation` (
  `company_id` INT NOT NULL,
  `participation_id` INT NOT NULL,
  INDEX `fk_company_has_participation_participation1_idx` (`participation_id` ASC),
  INDEX `fk_company_has_participation_company1_idx` (`company_id` ASC),
  CONSTRAINT `fk_company_has_participation_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_company_has_participation_participation1`
    FOREIGN KEY (`participation_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`participation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`participation_has_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`participation_has_team` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`participation_has_team` (
  `participation_id` INT NOT NULL,
  `team_id` INT NOT NULL,
  INDEX `fk_participation_has_team_team1_idx` (`team_id` ASC),
  INDEX `fk_participation_has_team_participation1_idx` (`participation_id` ASC),
  CONSTRAINT `fk_participation_has_team_participation1`
    FOREIGN KEY (`participation_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`participation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participation_has_team_team1`
    FOREIGN KEY (`team_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`team` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pitchmen_itprojekt_schema`.`participation_has_project`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pitchmen_itprojekt_schema`.`participation_has_project` ;

CREATE TABLE IF NOT EXISTS `pitchmen_itprojekt_schema`.`participation_has_project` (
  `participation_id` INT NOT NULL,
  `project_id` INT NOT NULL,
  PRIMARY KEY (`participation_id`, `project_id`),
  INDEX `fk_participation_has_project_project1_idx` (`project_id` ASC),
  INDEX `fk_participation_has_project_participation1_idx` (`participation_id` ASC),
  CONSTRAINT `fk_participation_has_project_participation1`
    FOREIGN KEY (`participation_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`participation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_participation_has_project_project1`
    FOREIGN KEY (`project_id`)
    REFERENCES `pitchmen_itprojekt_schema`.`project` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
