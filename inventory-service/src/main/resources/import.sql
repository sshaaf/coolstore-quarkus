// Add Inventory data
-- CREATE SEQUENCE todo_seq START 1;
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (1, '1', 'http://maps.google.com/?q=Raleigh', 'Raleigh', 736);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (2, '2', 'http://maps.google.com/?q=Boston', 'Boston', 512);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (3, '3', 'http://maps.google.com/?q=Seoul', 'Seoul', 256);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (4, '4', 'http://maps.google.com/?q=Singapore', 'Singapore', 54);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (5, '5', 'http://maps.google.com/?q=London', 'London', 87);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (6, '6', 'http://maps.google.com/?q=NewYork', 'New York', 443);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (7, '7', 'http://maps.google.com/?q=Paris', 'Paris', 600);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (8, '8', 'http://maps.google.com/?q=Tokyo', 'Tokyo', 230);

INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (9, '9', 'http://maps.google.com/?q=Raleigh', 'Raleigh', 736);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (10, '10', 'http://maps.google.com/?q=Boston', 'Boston', 512);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (11, '11', 'http://maps.google.com/?q=Seoul', 'Seoul', 256);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (12, '12', 'http://maps.google.com/?q=Singapore', 'Singapore', 54);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (13, '13', 'http://maps.google.com/?q=London', 'London', 87);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (14, '14', 'http://maps.google.com/?q=NewYork', 'New York', 443);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (15, '15', 'http://maps.google.com/?q=Paris', 'Paris', 600);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (16, '16', 'http://maps.google.com/?q=Tokyo', 'Tokyo', 230);

INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (17, '17', 'http://maps.google.com/?q=London', 'London', 87);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (18, '18', 'http://maps.google.com/?q=NewYork', 'New York', 443);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (19, '19', 'http://maps.google.com/?q=Paris', 'Paris', 600);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (20, '20', 'http://maps.google.com/?q=Tokyo', 'Tokyo', 230);

INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (21, '329299', 'http://maps.google.com/?q=Raleigh', 'Raleigh', 736);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (22, '329199', 'http://maps.google.com/?q=Boston', 'Boston', 512);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (23, '165613', 'http://maps.google.com/?q=Seoul', 'Seoul', 256);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (24, '165614', 'http://maps.google.com/?q=Singapore', 'Singapore', 54);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (25, '165954', 'http://maps.google.com/?q=London', 'London', 87);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (26, '444434', 'http://maps.google.com/?q=NewYork', 'New York', 443);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (27, '444435', 'http://maps.google.com/?q=Paris', 'Paris', 600);
INSERT INTO INVENTORY (id, itemId, link, location, quantity) values (28, '444437', 'http://maps.google.com/?q=Tokyo', 'Tokyo', 230);

ALTER SEQUENCE todo_seq RESTART WITH 29;