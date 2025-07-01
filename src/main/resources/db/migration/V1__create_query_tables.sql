CREATE TABLE IF NOT EXISTS `code_of_account` (
  `code` int NOT NULL,
  `level` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `element` enum('Assets','Equity','Expenses','Liabilities','Other','Revenue') NOT NULL,
  `type` enum('Credit','Debit') NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `UKja91koyfnd79e9mrkvnlolfxw` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `entries` (
  `amount` double NOT NULL,
  `coa` int NOT NULL,
  `type` tinyint NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `id` binary(16) NOT NULL,
  `transaction_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `entries_chk_1` CHECK ((`type` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE  IF NOT EXISTS `transactions` (
  `date` date NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  `id` binary(16) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `entries` ADD FOREIGN KEY (`coa`) REFERENCES `code_of_account` (`code`);
ALTER TABLE `entries` ADD FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`id`);