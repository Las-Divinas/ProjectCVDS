/* Create Tables */
/* Ya quedo */
CREATE TABLE usuario(documento varchar(50) primary key, correo varchar(50) unique, nombre varchar(100) NOT NULL, contraseña varchar(50) NOT NULL, rol varchar(50) NOT NULL);

CREATE TABLE novelty(
    id serial primary key,
    description varchar(200) NOT NULL,
    title varchar(30) NOT NULL,
    date DATE NOT NULL,
    user_id varchar(50) NOT NULL,
    laboratory_id INTEGER,
    element_id INTEGER,
    equipment_id INTEGER,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES usuario(documento),
    CONSTRAINT fk_noveltyloboratory FOREIGN KEY (laboratory_id) REFERENCES laboratorio(id),
    CONSTRAINT fk_noveltyequipment FOREIGN KEY (equipment_id) REFERENCES equipment(id),
    CONSTRAINT fk_noveltyelement FOREIGN KEY (element_id) REFERENCES element(id)
);
/* Ya quedo */
CREATE TABLE equipment(
    id serial primary key,
    element integer NOT NULL,
    name varchar(50) NOT NULL,
    description varchar(200) NOT NULL,
    laboratory_id integer NOT NULL,
    CONSTRAINT fk_laboratory FOREIGN KEY (laboratory_id) REFERENCES laboratory(id)
);
/* Ya quedo */
CREATE TABLE laboratorio(
    id serial primary key,
    name varchar(50) NOT NULL,
    description varchar(200) NOT NULL
);
/* Ya quedo */
create table element(
	id serial primary key,
	id_equipment integer,
	name varchar(50) not null,
	type varchar(50) not null,
	description varchar(200) not null,
	estado varchar(50) not null,
	constraint fk_equipment foreign key (id_equipment) references equipment(id)
);

