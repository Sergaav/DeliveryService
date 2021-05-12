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
INSERT INTO departure (id,city_departure) values (7,'Luhansk');
INSERT INTO departure (id,city_departure) values (8,'Vinnitsya');
INSERT INTO departure (id,city_departure) values (9,'Lutsk');
INSERT INTO departure (id,city_departure) values (10,'Zhitomir');
INSERT INTO departure (id,city_departure) values (11,'Rivne');
INSERT INTO departure (id,city_departure) values (12,'Sumy');
INSERT INTO departure (id,city_departure) values (13,'Ternopil');
INSERT INTO departure (id,city_departure) values (14,'Uzhhorod');
INSERT INTO departure (id,city_departure) values (15,'Ivano-Frankivsk');
INSERT INTO departure (id,city_departure) values (16,'Kropyvnitskii');
INSERT INTO departure (id,city_departure) values (17,'Mykolayiv');
INSERT INTO departure (id,city_departure) values (18,'Poltava');
INSERT INTO departure (id,city_departure) values (19,'Kherson');
INSERT INTO departure (id,city_departure) values (20,'Khmelnitskii');
INSERT INTO departure (id,city_departure) values (21,'Cherkasy');
INSERT INTO departure (id,city_departure) values (22,'Chernivtsi');
INSERT INTO departure (id,city_departure) values (23,'Chernihiv');

select * from departure;

INSERT INTO arrive (id,city_arrive) values (0,'Kyiv');
INSERT INTO arrive (id,city_arrive) values (1,'Kharkiv');
INSERT INTO arrive (id,city_arrive) values (2,'Odesa');
INSERT INTO arrive (id,city_arrive) values (3,'Donetsk');
INSERT INTO arrive (id,city_arrive) values (4,'Dnipro');
INSERT INTO arrive (id,city_arrive) values (5,'Zaporizhia');
INSERT INTO arrive (id,city_arrive) values (6,'Lviv');
INSERT INTO arrive (id,city_arrive) values (7,'Luhansk');
INSERT INTO arrive (id,city_arrive) values (8,'Vinnitsya');
INSERT INTO arrive (id,city_arrive) values (9,'Lutsk');
INSERT INTO arrive (id,city_arrive) values (10,'Zhitomir');
INSERT INTO arrive (id,city_arrive) values (11,'Rivne');
INSERT INTO arrive (id,city_arrive) values (12,'Sumy');
INSERT INTO arrive (id,city_arrive) values (13,'Ternopil');
INSERT INTO arrive (id,city_arrive) values (14,'Uzhhorod');
INSERT INTO arrive (id,city_arrive) values (15,'Ivano-Frankivsk');
INSERT INTO arrive (id,city_arrive) values (16,'Kropyvnitskii');
INSERT INTO arrive (id,city_arrive) values (17,'Mykolayiv');
INSERT INTO arrive (id,city_arrive) values (18,'Poltava');
INSERT INTO arrive (id,city_arrive) values (19,'Kherson');
INSERT INTO arrive (id,city_arrive) values (20,'Khmelnitskii');
INSERT INTO arrive (id,city_arrive) values (21,'Cherkasy');
INSERT INTO arrive (id,city_arrive) values (22,'Chernivtsi');
INSERT INTO arrive (id,city_arrive) values (23,'Chernihiv');

select * from arrive;









