CREATE TABLE IF NOT EXISTS `expenses` (
  `id` serial NOT NULL,
  `date` date NOT NULL,
  `amount` decimal(12,2) NOT NULL,
  `reason` varchar(400) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
