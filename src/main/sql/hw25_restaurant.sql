SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `restaurant` ;
CREATE SCHEMA IF NOT EXISTS `restaurant` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `restaurant` ;

-- -----------------------------------------------------
-- Table `restaurant`.`Menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Menu` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `cost` DECIMAL(8,3) NOT NULL,
  `cook_time` INT(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));


-- -----------------------------------------------------
-- Table `restaurant`.`Ingredients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Ingredients` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Ingredients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
 -- `cost` DECIMAL(8,3) NOT NULL,
 -- `quantity` INT(5) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `restaurant`.`Faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Orders` ;

CREATE TABLE IF NOT EXISTS `restaurant`.`Orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bill` DECIMAL(8,2) NOT NULL,
  `status` ENUM('opened', 'ready', 'delivered', 'closed') NOT NULL,
--  `tablet` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurant`.`Menu_Ingredients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Menu_Ingredients`;

CREATE TABLE IF NOT EXISTS `restaurant`.`Menu_Ingredients` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_menu` INT NOT NULL,
  `id_ingredients` INT NOT NULL,
--  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`, `id_menu`, `id_ingredients`),
  INDEX `fk_Menu_has_Ingredients_Ingredients1_idx` (`id_menu` ASC),
  INDEX `fk_Ingredients_has_Menu_Menu1_idx` (`id_ingredients` ASC),
  UNIQUE INDEX `idMenuIngredients_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_Menu_has_Ingredients_Menu1`
    FOREIGN KEY (`id_menu`)
    REFERENCES `restaurant`.`Menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Menu_has_Ingredients_Ingredients1`
    FOREIGN KEY (`id_ingredients`)
    REFERENCES `restaurant`.`Ingredients` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurant`.`Order_Menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `restaurant`.`Orders_Menu`;

CREATE TABLE IF NOT EXISTS `restaurant`.`Orders_Menu` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `id_order` INT NOT NULL,
  `id_menu` INT NOT NULL,
--  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`, `id_order`, `id_menu`),
  INDEX `fk_Order_has_Menu_Menu1_idx` (`id_order` ASC),
  INDEX `fk_Menu_has_Order_Order1_idx` (`id_menu` ASC),
  UNIQUE INDEX `idOrderMenu_UNIQUE` (`id` ASC),
  CONSTRAINT `fk_Order_has_Menu_Menu1`
    FOREIGN KEY (`id_order`)
    REFERENCES `restaurant`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Menu_has_Order_Order1`
    FOREIGN KEY (`id_menu`)
    REFERENCES `restaurant`.`Menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `restaurant`;
insert into Menu (name, cost, cook_time) values ('Borsh', 50, 30);
insert into Menu (name, cost, cook_time) values ('Pilaf', 60, 40);
insert into Menu (name, cost, cook_time) values ('Solyanka', 70, 30);
insert into Menu (name, cost, cook_time) values ('Fish', 50, 20);
insert into Menu (name, cost, cook_time) values ('Tomato juice', 10, 10);
insert into Menu (name, cost, cook_time) values ('Coffe', 15, 5);

insert into Ingredients (name, cost, quantity) values ('Potato', 0.01, 10000);
insert into Ingredients (name, cost, quantity) values ('Rice', 0.02, 8000);
insert into Ingredients (name, cost, quantity) values ('Beef', 0.15, 5000);
insert into Ingredients (name, cost, quantity) values ('Pork', 0.13, 6000);
insert into Ingredients (name, cost, quantity) values ('Сarrot', 0.011, 6000);
insert into Ingredients (name, cost, quantity) values ('Onion', 0.012, 10000);
insert into Ingredients (name, cost, quantity) values ('Tomato', 0.015, 9000);
insert into Ingredients (name, cost, quantity) values ('Beet', 0.015, 9000);
insert into Ingredients (name, cost, quantity) values ('Cabage', 0.015, 9000);
insert into Ingredients (name, cost, quantity) values ('Carp', 0.09, 4000);
insert into Ingredients (name, cost, quantity) values ('Oil', 0.03, 6000);
insert into Ingredients (name, cost, quantity) values ('Cofee', 0.5, 2000);

insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 1, 100);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 3, 100);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 5, 50);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 6, 50);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 7, 100);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 8, 50);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 9, 100);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (1, 11, 50);

insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (2, 2, 150);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (2, 4, 150);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (2, 5, 100);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (2, 6, 500);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (2, 11, 60);

insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (3, 1, 100);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (3, 3, 80);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (3, 4, 80);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (3, 5, 50);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (3, 6, 50);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (3, 7, 100);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (3, 11, 60);

insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (4, 10, 200);
insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (4, 11, 70);

insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (5, 7, 200);

insert into Menu_Ingredients (id_menu, id_ingredients, quantity) values (6, 12, 10);

insert into Orders (bill, status, tablet) values (115, 'closed', 1);
insert into Orders (bill, status, tablet) values (140, 'opened', 1);
insert into Orders (bill, status, tablet) values (140, 'delivered', 2);
insert into Orders (bill, status, tablet) values (60, 'ready', 3);

insert into Orders_Menu (id_order, id_menu, quantity) values (1, 1, 1);
insert into Orders_Menu (id_order, id_menu, quantity) values (1, 4, 1);
insert into Orders_Menu (id_order, id_menu, quantity) values (1, 6, 1);

insert into Orders_Menu (id_order, id_menu, quantity) values (2, 2, 1);
insert into Orders_Menu (id_order, id_menu, quantity) values (2, 3, 1);
insert into Orders_Menu (id_order, id_menu, quantity) values (2, 5, 1);

insert into Orders_Menu (id_order, id_menu, quantity) values (3, 2, 2);
insert into Orders_Menu (id_order, id_menu, quantity) values (3, 5, 2);

insert into Orders_Menu (id_order, id_menu, quantity) values (4, 6, 4);


