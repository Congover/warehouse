ALTER TABLE `warehouse`.`tPACKING` 
ADD COLUMN `STORE_ID` BIGINT(20) NULL,
ADD INDEX `IDX_FK_PACKING_STORE` (`STORE_ID` ASC);
ALTER TABLE `warehouse`.`tPACKING` 
ADD CONSTRAINT `FK_PACKING_STORE`
  FOREIGN KEY (`STORE_ID`)
  REFERENCES `warehouse`.`tstore` (`STORE_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
update `warehouse`.`tPACKING` set STORE_ID = (select max(store_id) from `warehouse`.`tstore`) where store_id is null;
