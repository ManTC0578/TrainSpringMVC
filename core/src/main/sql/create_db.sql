SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

DROP SCHEMA IF EXISTS `myplatform` ;
CREATE SCHEMA IF NOT EXISTS `myplatform` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `myplatform` ;


-- -----------------------------------------------------
-- Table `myplatform`.`Country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myplatform`.`Country` ;

CREATE  TABLE IF NOT EXISTS `myplatform`.`Country` (
  `CountryID` INT NOT NULL AUTO_INCREMENT,
  `Code` VARCHAR(2) NOT NULL ,
  `Name` VARCHAR(50) NOT NULL ,
  `LanguageKey` VARCHAR(50)  NULL ,
  PRIMARY KEY (`CountryID`) ,
  UNIQUE INDEX `Code_UNIQUE` (`Code` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myplatform`.`City`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myplatform`.`City` ;

CREATE  TABLE IF NOT EXISTS `myplatform`.`City` (
  `CityID` INT NOT NULL AUTO_INCREMENT,
  `CountryID` INT NOT NULL ,
  `Code` VARCHAR(50) NULL ,
  `Name` VARCHAR(100) NOT NULL ,
  `LanguageKey` VARCHAR(50)  NULL ,
  PRIMARY KEY (`CityID`) ,
  INDEX `City_Name_INDEX` (`Name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myplatform`.`Province`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myplatform`.`Province` ;

CREATE  TABLE IF NOT EXISTS `myplatform`.`Province` (
  `ProvinceID` INT NOT NULL AUTO_INCREMENT,
  `Code` VARCHAR(50) NOT NULL ,
  `Name` VARCHAR(100) NOT NULL ,
  `CountryID` INT NOT NULL ,
  `LanguageKey` VARCHAR(50) NULL ,
  `DisplayOrder` SMALLINT NULL ,
  PRIMARY KEY (`ProvinceID`) ,
  UNIQUE INDEX `Province_Code_UQ` (`Code` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myplatform`.`UserAdmin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myplatform`.`UserAdmin` ;

CREATE  TABLE IF NOT EXISTS `myplatform`.`UserAdmin` (
  `UserAdminID` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(45) NOT NULL ,
  `Password` VARCHAR(45) NOT NULL ,
  `FullName` VARCHAR(100) NOT NULL ,
  `Email` VARCHAR(100) NOT NULL ,
  `Status` TINYINT NOT NULL ,
  `Role` VARCHAR(45) NOT NULL ,
  `CreatedDate` DATETIME NULL ,
  `ModifiedDate` DATETIME NULL ,
  PRIMARY KEY (`UserAdminID`) ,
  UNIQUE INDEX `UserAdmin_UserName_UNIQUE` (`UserName` ASC) ,
  UNIQUE INDEX `UserAdmin_Email_UNIQUE` (`Email` ASC) )
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `myplatform`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myplatform`.`User` ;

CREATE  TABLE IF NOT EXISTS `myplatform`.`User` (
  `UserID` INT NOT NULL AUTO_INCREMENT,
  `FullName` VARCHAR(100) NOT NULL ,
  `Email` VARCHAR(100) NOT NULL ,
  `Password` VARCHAR(50) NOT NULL ,
  `Status` TINYINT NOT NULL DEFAULT 2 ,
  `CreatedDate` DATETIME NULL ,
  `ModifiedDate` DATETIME NULL ,
  `ModifiedBy` VARCHAR(100) NULL ,
  PRIMARY KEY (`UserID`) ,
  UNIQUE INDEX `User_Email_UQ` (`Email` ASC) )
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
