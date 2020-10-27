/* Create Tables */
CREATE TABLE user(
    'document' varchar(50) primary key,
    'email' varchar(50) unique,
    'name' varchar(100) NOT NULL,
    'password' varchar(50) NOT NULL,
    'role' varchar(50) NOT NULL
);

CREATE TABLE novelty(
    'id' serial primary key,
    'description' varchar(200) NOT NULL,
    'date' DATE NOT NULL,
    'user_id' varchar(50) NOT NULL,
    'equipment_id' integer NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user(document),
    CONSTRAINT fk_noveltyequipment FOREIGN KEY (equipment_id) REFERENCES equipment(id)
);

CREATE TABLE equipment(
    'id' serial primary key,
    'element' integer NOT NULL,
    'name' varchar(50) NOT NULL,
    'description' varchar(200) NOT NULL,
    'laboratory_id' integer NOT NULL,
    CONSTRAINT fk_laboratory FOREIGN KEY (laboratory_id) REFERENCES laboratory(id)
);

CREATE TABLE laboratory(
    'id' serial primary key,
    'name' varchar(50) NOT NULL,
    'description' varchar(200) NOT NULL
);

CREATE TABLE element(
    'id' serial primary key,
    'name' varchar(50) NOT NULL,
    'description' varchar(200) NOT NULL,
    'id_equipment' integer NOT NULL,
    CONSTRAINT fk_equipment FOREIGN KEY (id_equipment) REFERENCES equipment(id)
);

/* Create Role */

CREATE ROLE admin;

CREATE ROLE user;