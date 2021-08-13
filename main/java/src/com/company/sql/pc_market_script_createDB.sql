CREATE DaTABASE pc_market;

USE pc_market;

CREATE TABLE product (
maker VARCHAR(10) NOT NULL,
model VARCHAR(50) NOT NULL,
type VARCHAR(50) NOT NULL,
CONSTRAINT pk_product PRIMARY KEY (model),
CONSTRAINT chk_type CHECK (type IN('PC', 'Laptop', 'Printer'))
);

CREATE TABLE pc (
code INT NOT NULL PRIMARY KEY,
  model VARCHAR(50) NOT NULL,
  speed SMALLINT NOT NULL,
  ram SMALLINT NOT NULL,
  hd REAL NOT NULL,
  cd VARCHAR(10),
  price DECIMAL(15,2),
  CONSTRAINT fk_pc FOREIGN KEY (model)
    REFERENCES product (model)
);

CREATE TABLE laptop (
code INT NOT NULL PRIMARY KEY,
  model VARCHAR(50) NOT NULL,
  speed SMALLINT NOT NULL,
  ram SMALLINT NOT NULL,
  hd REAL NOT NULL,
  screen TINYINT NOT NULL,
  price DECIMAL(15,2),
  CONSTRAINT fk_laptop FOREIGN KEY (model)
    REFERENCES product (model)
);

CREATE TABLE printer (
code INT NOT NULL PRIMARY KEY,
  model VARCHAR(50) NOT NULL,
  color char(1),
  type VARCHAR(50) NOT NULL,
  price DECIMAL(15,2),
  CONSTRAINT fk_printer FOREIGN KEY (model)
    REFERENCES product (model)
);