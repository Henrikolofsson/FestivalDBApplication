-- Mörtfors Rock Festival

DROP DATABASE IF EXISTS ai0026project;
CREATE DATABASE ai0026project;
\c ai0026project;

-- The festivals scenes
CREATE TABLE scenes (
    scene                   text    PRIMARY KEY,
    max_capacity            int     NOT NULL
);

-- The workers working for the festival
CREATE TABLE workers (
    person_number           text    PRIMARY KEY,
    worker_name             text    NOT NULL,
    address                 text    NOT NULL
);

-- The bands playing at the festival
CREATE TABLE bands (
    band_id                 text    NOT NULL PRIMARY KEY,
    band_name               text    NOT NULL,
    band_country_of_origin  text    NOT NULL,
    band_info               text,
    contact_person_id       text    REFERENCES workers
);

-- The festival schedule
CREATE TABLE schedule (
    day                     text    NOT NULL,
    schedule_time           time    NOT NULL,
    band_id_playing         text    NOT NULL REFERENCES bands,
    scene                   text    NOT NULL REFERENCES scenes,
    PRIMARY KEY (day, schedule_time, scene)
);

-- The bands bandmembers
CREATE TABLE bandmember (
    bandmember_id           serial    PRIMARY KEY,
    bandmember_name         text    NOT NULL,
    bandmember_info         text
);

-- Table connecting the bandmembers to bands
CREATE TABLE bandmember_association (
    bandmember_id           int     NOT NULL REFERENCES bandmember,
    band_id                 text    NOT NULL REFERENCES bands
);

-- The system administrators
CREATE TABLE system_administrators (
    username                text    NOT NULL PRIMARY KEY,
    password                text    NOT NULL
);

-- Adding an administrator to the system
INSERT INTO system_administrators VALUES('Henrik', 'Olofsson');

-- Adding scenes to the table scenes
INSERT INTO scenes VALUES('Red', 150);
INSERT INTO scenes VALUES('Blue', 500);
INSERT INTO scenes VALUES('Green', 1000);

-- Adding workers to the table workers... (Workers Person Nbr, Workers Name, Workers Address)
INSERT INTO workers VALUES('590628-0120', 'Anders Tideman', 'Gosvägen 39c');
INSERT INTO workers VALUES('910202-3394', 'Filippa Cedergren', 'Mysgatan 58e');
INSERT INTO workers VALUES('781016-5555', 'Sten Skog', 'Grässtigen 14');
INSERT INTO workers VALUES('881231-3131', 'Palle Pantare', 'Hemlös');
INSERT INTO workers VALUES('660713-0666', 'Björn Varg', 'Djurvägen 17');
INSERT INTO workers VALUES('690410-0215', 'Charlie Sheen', 'Main. st 22 avenue');

-- Adding bands to the table bands... (Bands Name, Bands country of origin, Bands contact person ID, Bands Info, Band ID)
INSERT INTO bands VALUES(0001, 'Slaughters','Denmark', 'A heavy death monster ultra metal from Copenhagen', '590628-0120');
INSERT INTO bands VALUES(0002,'Psycadelic Mushroom Eaters','Netherlands', 'Indie music combined with psycadelic trance', '881231-3131');
INSERT INTO bands VALUES(0003,'TreeHuggers','Sweden', 'The famous swedish music genre "Dansband" comes alive with this special band', '690410-0215');
INSERT INTO bands VALUES(0004,'Alien Nymphos From Uranus','France', 'Coozy chill music from france', '910202-3394');
INSERT INTO bands VALUES(0005,'The Bad Livers','USA', 'Hard rock from The USA, much like Springsteen, but with liver problems', '660713-0666');
INSERT INTO bands VALUES(0006,'Bassholes','Germany', 'We don´t even know what genre this is, but it will make you dance', '781016-5555');
INSERT INTO bands VALUES(0007,'Drunks With Guns','Finland', 'These guys end up drunk after to much sauna and vodka, it´s hard to decipher the lyrics but it is really catchy', '590628-0120');
INSERT INTO bands VALUES(0008,'Guns N´ Wankers','Denmark', 'Women from Copenhagen who is always playing in the famous Borat-suit, a wonderful sight', '660713-0666');
INSERT INTO bands VALUES(0009,'Ice Cream Headache','Belgium', 'Blues from the heart of Belgium', '910202-3394');
INSERT INTO bands VALUES(0010,'No Pants Bandits','India', 'Indian traditional music from New Delhi', '881231-3131');
INSERT INTO bands VALUES(0011,'Old Lady Driver','China', 'Wonderful chinese music from Xiang Miang', '690410-0215');
INSERT INTO bands VALUES(0012,'Rainbow Butt Monkeys','Germany', 'And we thought that Germans didn´t have a sense of humor. These guys don´t make music, but they do sound like monkeys', '660713-0666');
INSERT INTO bands VALUES(0013,'Van Gogh´s Ear','Norway', 'Indie music from Oslo, incredibly extra', '910202-3394');
INSERT INTO bands VALUES(0014,'Zombies Under Stress','Sweden', 'Rock from Sweden, they do sound like they swallowed a bunch of potatoes so we are guessing they are from Skåne', '781016-5555');
INSERT INTO bands VALUES(0015,'Two And A Half Hookers','USA', 'Great music and one of Charlie Sheens favorite bands!', '690410-0215');

-- Adding bandmembers and bandmember association to connect bands with their bandmembers... bandmember: (band member ID, Band member name, Band member info)
--                                                                                          bandmember_association: (band member ID, band ID)
INSERT INTO bandmember VALUES(00001, 'Bjørn Dansksson', 'A funny guy with a great beard');
INSERT INTO bandmember_association VALUES(00001, 0001);

INSERT INTO bandmember VALUES(00002, 'Lelle Luffare', 'Wonderful guy with a big heart, but not very understandable');
INSERT INTO bandmember_association VALUES(00002, 0002);

INSERT INTO bandmember VALUES(00003, 'Leif GW', 'Funny guy, some says he is a lot into criminologies? He is very charismatic');
INSERT INTO bandmember_association VALUES(00003, 0003);

INSERT INTO bandmember VALUES(00004, 'Pierre Francais', 'A charmer and a joker, he loves magic and.. baguettes');
INSERT INTO bandmember_association VALUES(00004, 0004);

INSERT INTO bandmember VALUES(00005, 'Bonnie McAlcoholic', 'Wonderful guy with a great voice, the smell of his breath however is mostly cognac');
INSERT INTO bandmember_association VALUES(00005, 0005);

INSERT INTO bandmember VALUES(00006, 'Anton Hyde', 'Cool guy with a cool style. He loves base, and so do we!');
INSERT INTO bandmember_association VALUES(00006, 0006);

INSERT INTO bandmember VALUES(00007, 'Aino Pikkeli', 'He got no sense of humor, but he is very caring. When he is not drunk, which he is.. all the time');
INSERT INTO bandmember_association VALUES(00007, 0007);

INSERT INTO bandmember VALUES(00008, 'Mia Piotrsson', 'A delightful woman with an amazing voice. Her voice is as wonderful as her beauty');
INSERT INTO bandmember_association VALUES(00008, 0008);

INSERT INTO bandmember VALUES(00009, 'Simon Bern', 'A fun guy with a great sense of humor, his blues is touching your soul');
INSERT INTO bandmember_association VALUES(00009, 0009);

INSERT INTO bandmember VALUES(00010, 'Pika pika', 'One of the indian musicians, her instrumental talent is beyond amazing');
INSERT INTO bandmember_association VALUES(00010, 0010);

INSERT INTO bandmember VALUES(00011, 'Xiang Miang', 'She looks like a goddess, she sings like a goddess, is she a goddess?');
INSERT INTO bandmember_association VALUES(00011, 0011);

INSERT INTO bandmember VALUES(00012, 'Michael Green', 'He does not make music, but he makes great animal noices');
INSERT INTO bandmember_association VALUES(00012, 0012);

INSERT INTO bandmember VALUES(00013, 'Tobias Meier', 'He looks like a hippie straight from the 70´s');
INSERT INTO bandmember_association VALUES(00013, 0013);

INSERT INTO bandmember VALUES(00014, 'Erik Svensson', 'Loves zombies. And beer.');
INSERT INTO bandmember_association VALUES(00014, 0014);

INSERT INTO bandmember VALUES(00015, 'Paulina DiGrovski', 'One of the 2 fully grown woman in this band');
INSERT INTO bandmember_association VALUES(00015, 0015);

INSERT INTO bandmember VALUES(00016, 'Maria DiGrovski', 'The second of the 2 fully grown woman in this band');
INSERT INTO bandmember_association VALUES(00016, 0015);

INSERT INTO bandmember VALUES(00017, 'Candy Mayweather', 'The short one that makes the half woman in this band');
INSERT INTO bandmember_association VALUES(00017, 0015);