-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `useremail` VARCHAR(45) NULL,
  `token` VARCHAR(100) NULL,
  PRIMARY KEY (`uid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`post` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `picture` VARCHAR(45) NULL,
  `content` VARCHAR(45) NULL,
  `user_uid` INT NOT NULL,
  PRIMARY KEY (`pid`, `user_uid`),
  INDEX `fk_post_user_idx` (`user_uid` ASC) VISIBLE,
  CONSTRAINT `fk_post_user`
    FOREIGN KEY (`user_uid`)
    REFERENCES `mydb`.`user` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`culture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`culture` (
  `cid` INT NOT NULL,
  `culture_name` VARCHAR(45) NULL,
  PRIMARY KEY (`cid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`culture_rawdata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`culture_rawdata` (
  `c_raw_id` INT NOT NULL AUTO_INCREMENT,
  `date` TIMESTAMP(6) NULL,
  `image_url` VARCHAR(45) NULL,
  `place` VARCHAR(45) NULL,
  `title` VARCHAR(45) NULL,
  `culture_cid` INT NOT NULL,
  PRIMARY KEY (`c_raw_id`, `culture_cid`),
  INDEX `fk_culture_rawdata_culture1_idx` (`culture_cid` ASC) VISIBLE,
  CONSTRAINT `fk_culture_rawdata_culture1`
    FOREIGN KEY (`culture_cid`)
    REFERENCES `mydb`.`culture` (`cid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
