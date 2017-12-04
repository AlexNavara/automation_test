USE mydbtest;

DROP TABLE IF EXISTS Equation;

CREATE TABLE Equation(
  id INT(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
  expression VARCHAR(255),
  result INT(11)
);