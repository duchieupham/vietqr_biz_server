INSERT INTO permission (id, category, color, description, name)
VALUES
("6c31c106-d8fd-4910-8148-aaafde33bbec", 0, '#00a67d', 'Create the terminal', 'insertTerminal'),
("84ecfbc7-be43-46f8-bc51-123f44cf8714", 0, '#00a67d', 'Get a list of terminals for the merchant', 'getTerminalsByMid'),
("6e261c66-c001-4cd6-8691-4fb8aeb7b61d", 0, '#00a67d', 'Get the terminal by ID', 'getTerminalById'),
("04daf42f-da84-4aa7-a114-a18cc8c181eb", 0, '#00a67d', 'Search the terminal by name, code, address, bank ID and more', 'searchTerminals'),
("b23dbf56-2aa8-4bc0-b35b-83767e4e5b65", 0, '#00a67d', 'Update the name, address, code and bank ID of the terminal by id', 'updateTerminalById'),
("73aeadda-f909-4c8f-ac72-1aefcc20a2f4", 0, '#00a67d', 'Update the status of the terminal by ID with the value false', 'deleteTerminalById'),
("a1546626-0307-4aff-bc66-5acc430c1306", 0, '#00a67d', 'Get a list of terminals for the merchant that have been deleted', 'getTerminalsDeletedByMid'),
("ecc19b19-3e8c-4468-bb00-bfd69d7bcc42", 0, '#00a67d', 'Update the status of the terminal by ID with the value true', 'recoverTerminalById'),
("acaf0764-dbe0-4877-a9e6-2903a80704e9", 0, '#00a67d', 'Export the terminal to an Excel file', 'exportTerminalById'),
("1919c254-b51c-4e50-bc30-89c059116d63", 0, '#00a67d', 'Export the terminal of the merchant to an Excel file', 'exportTerminalsByMid'),
("03c88cd4-79c6-45f4-a1ef-a7cc6317dc06", 0, '#00a67d', 'Import the terminals from an Excel file', 'importTerminals'),
("ec4a923b-33d0-4cb7-b089-f018046a5f6d", 0, '#00a67d', 'Transfer the data of terminal to another', 'transferTerminals');
