CREATE TABLE account (
	id BIGINT auto_increment NOT NULL,
	name varchar(60) NOT NULL,	
	balance DECIMAL(10,2) NOT NULL,
	CONSTRAINT Cozinha_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;