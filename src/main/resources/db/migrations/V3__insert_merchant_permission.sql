INSERT INTO permission (id, category, color, description, name)
VALUES ("f7d3b8d0-0bd7-425f-bca6-48cc875e2338", 0, '#00a67d', 'Create the merchant', 'saveMerchant'),
       ("4c78ec77-8a2a-4d49-b0e6-6768a65b0846", 3, '#00a67d', 'Get the merchant by ID', 'getMerchantDetail'),
       ("947e16c4-92e0-4200-bb5d-4d84305566a9", 1, '#00a67d', 'Update the name, fullName, address, nationalId, businessSector, businessType of merchant by Id', 'updateMerchant'),
       ("b1e837eb-e391-43ce-bd39-269d5558fac3", 2, '#00a67d', 'Update the status of the merchant by ID with the value false', 'deleteMerchant'),
       ("00be8a60-d50d-4953-8428-5766a90d4d89", 3, '#00a67d', 'Get list of merchant have been deleted', 'getListDeleteMerchant'),
       ("cb07c43d-782e-490f-a2d9-fb940b2de901", 1, '#00a67d', 'Update the status of the merchant by ID with the value true', 'recoverMerchant'),
       ("7f45fdc2-9928-4c11-9220-326b85d838b3", 4, '#00a67d', 'Export the merchant to Excel file', 'exportMerchant'),
       ("65de83e7-6238-4117-8b16-20ba6f07bad7", 7, '#00a67d', 'Transfer the data of merchant to another', 'merchantDataTransfer');