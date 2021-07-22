CREATE TABLE `groovy_script` (
`id` BIGINT ( 20 ) NOT NULL AUTO_INCREMENT,
`script_name` VARCHAR ( 64 ) NOT NULL COMMENT 'script name',
`script_content` text NOT NULL COMMENT 'script content',
`status` VARCHAR ( 16 ) NOT NULL DEFAULT 'ENABLE' COMMENT 'ENABLE/DISENABLE',
`extend_info` VARCHAR ( 4096 ) DEFAULT NULL,
`created_time` TIMESTAMP ( 6 ) NOT NULL DEFAULT CURRENT_TIMESTAMP ( 6 ),
`modified_time` TIMESTAMP ( 6 ) NOT NULL DEFAULT CURRENT_TIMESTAMP ( 6 ) ON UPDATE CURRENT_TIMESTAMP ( 6 ),
PRIMARY KEY ( `id` )
) ENGINE = INNODB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT = 'groovy script';


INSERT INTO `gane-platform`.`groovy_script`
(`id`, `script_name`, `script_content`, `status`, `extend_info`, `created_time`, `modified_time`)
VALUES
(1, 'helloService',
 'package com.maple.resource.groovy\r\n\r\nimport com.maple.database.groovy.HelloService\r\n\r\npublic class HelloServiceImpl implements HelloService {\r\n\r\n  @Override\r\n  String sayHello(String name) {\r\n    return \"Hello \"+name+\". Welcome to database in Groovy.\";\r\n  }\r\n}'
 , 'ENABLE', NULL, '2020-09-26 17:16:36.477818', '2020-09-27 08:23:10.790553');