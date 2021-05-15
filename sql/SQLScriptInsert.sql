use delivery;

INSERT INTO roles (id,name) values (0,'admin');
INSERT INTO roles (id,name) values (1,'client');
select * from roles;

INSERT INTO statuses (id,name) values (0,'OPENED');
INSERT INTO statuses (id,name) values (1,'CONFIRMED');
INSERT INTO statuses (id,name) values (2,'PAID');
INSERT INTO statuses (id,name) values (3,'CLOSED');
select * from statuses;

INSERT INTO departure (id,city_departure) values (0,'Kyiv');
INSERT INTO departure (id,city_departure) values (1,'Kharkiv');
INSERT INTO departure (id,city_departure) values (2,'Odesa');
INSERT INTO departure (id,city_departure) values (3,'Donetsk');
INSERT INTO departure (id,city_departure) values (4,'Dnipro');
INSERT INTO departure (id,city_departure) values (5,'Zaporizhia');
INSERT INTO departure (id,city_departure) values (6,'Lviv');

select * from departure;

INSERT INTO arrive (id,city_arrive) values (0,'Kyiv');
INSERT INTO arrive (id,city_arrive) values (1,'Kharkiv');
INSERT INTO arrive (id,city_arrive) values (2,'Odesa');
INSERT INTO arrive (id,city_arrive) values (3,'Donetsk');
INSERT INTO arrive (id,city_arrive) values (4,'Dnipro');
INSERT INTO arrive (id,city_arrive) values (5,'Zaporizhia');
INSERT INTO arrive (id,city_arrive) values (6,'Lviv');


select * from arrive;














