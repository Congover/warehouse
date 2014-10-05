ALTER TABLE `warehouse`.`tPACKING` 
ADD COLUMN `PACKED_PRODUCT_ID` BIGINT(20) NULL AFTER `CREATE_DATE`,
ADD INDEX `IDX_FK_PACKED_PRODUCT` (`PACKED_PRODUCT_ID` ASC);
ALTER TABLE `warehouse`.`tPACKING` 
ADD CONSTRAINT `FK_PACKED_PRODUCT`
  FOREIGN KEY (`PACKED_PRODUCT_ID`)
  REFERENCES `warehouse`.`tproduct` (`PRODUCT_ID`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
DROP table `warehouse`.`tproduct_quantity`;
ALTER TABLE `warehouse`.`tpacking` DROP COLUMN `BAG_RESIDUE`;

update `warehouse`.`tpacking` set packed_product_id = product_id where packed_product_id is null;