-- Mörtfors Rock Festival

DROP DATABASE IF EXISTS ai0026project;
CREATE DATABASE ai0026project;
\c ai0026project;

-- The festivals scenes
CREATE TABLE scenes (
    scene_name              text    PRIMARY KEY,
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
    day                     date    NOT NULL,
    schedule_time           time    NOT NULL,
    band_playing            text    NOT NULL REFERENCES bands,
    scene                   text    NOT NULL REFERENCES scenes,
    PRIMARY KEY (day, schedule_time, scene)
);

-- The bands bandmembers
CREATE TABLE bandmember (
    bandmember_id           serial  PRIMARY KEY,
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

-- Schedule for what worker is responsible for security at a certain stage at a certain time.
CREATE TABLE security_schedule (
    day                     date    NOT NULL,
    security_schedule_time  time    NOT NULL,
    scene                   text    NOT NULL REFERENCES scenes,
    responsible_worker      text    NOT NULL REFERENCES workers,
    PRIMARY KEY (day, security_schedule_time, scene)
);

INSERT INTO system_administrators VALUES('Henrik', 'Olofsson');
