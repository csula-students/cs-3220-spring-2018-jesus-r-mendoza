CREATE TABLE Users(
	id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
	username VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL
);

CREATE TABLE Generators(
	id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL,
	description TEXT NULL,
	rate INT(11) NULL,
	base_cost INT(11) NULL,
	unlock_at INT(11) NULL,
	created_by INT(11) NULL REFERENCES USERS(ID), INDEX(created_by)
);

CREATE TABLE Events(
	id INT(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
	name VARCHAR(255) NOT NULL,
	description TEXT NULL,
	trigger_at INT(11) NULL,
	created_by INT(11) NULL REFERENCES USERS(ID), INDEX(created_by)
);

CREATE TABLE Quantities(
	generator_id INT(11) NOT NULL REFERENCES Generators(ID), INDEX(generator_id), 
	token VARCHAR(255) NULL,
	quantity INT(11) NULL DEFAULT 0
);

DESCRIBE Users;
DESCRIBE Generators;
DESCRIBE Events;
DESCRIBE Quantities;

INSERT INTO Users VALUES(0 ,"admin","cs3220password"), (0 ,"me","notapassword");
SELECT * FROM Users;

INSERT INTO Generators VALUES(0, "Grandma", "Grandma likes to make cookies", 5, 10, 10, 1), 
(0, "Factory", "Factory to produce cookies", 10, 50, 50, 1),
(0, "Mine", "Mining cookies", 20, 200, 200, 2);
SELECT * FROM Generators;

INSERT INTO Events VALUES(0, "Grandma shows up", "You always know grandma likes to make cookies", 10, 1), 
(0, "You can construct factory now!", "Factory to produce cookies", 50, 1),
(0, "We've found cookies in deep mountain ... in the mine?", "Mining cookies", 200, 2),
(0, "sample event", "This is a sample event. Please delete me", 99999, 2);
SELECT * FROM Events;

INSERT INTO Quantities VALUES(1, "c7a69d44e0b9b415b2d9956cb26b944a", 2), 
(2, "c7a69d44e0b9b415b2d9956cb26b944a", 1),
(1, "80516ce4663c3bd0c8385309a2fe226e", 20),
(2, "80516ce4663c3bd0c8385309a2fe226e", 30);
SELECT * FROM Quantities;



UPDATE generators SET unlock_at = 10, rate = 1 WHERE name = "grandma";
SELECT * FROM Generators;

SELECT q.quantity, g.name, g.description, g.rate, g.base_cost, g.unlock_at FROM Generators g JOIN Quantities q ON(g.id = q.generator_id) WHERE token = "80516ce4663c3bd0c8385309a2fe226e";

SELECT * FROM Generators WHERE unlock_at = (SELECt MAX(unlock_at) FROM Generators);

SELECT * FROM Generators ORDER BY (unlock_at);

DELETE FROM Events WHERE name = "sample event";
SELECT * FROM Events;
