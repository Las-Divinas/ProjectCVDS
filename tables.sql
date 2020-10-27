CREATE TABLE user(
    'document' bigint primary key,
    'email' varchar(50) unique,
    'name' varchar(100) NOT NULL,
    'password' varchar(50) NOT NULL,
    'role' varchar(50) NOT NULL
);

CREATE TABLE novelty(
    'id' serial primary key,
    'user_id' bigint references user(document),
    'novelty_type' varchar(50) NOT NULL,
    'description' varchar(200) NOT NULL,
    'date' DATE NOT NULL
);

CREATE TABLE equipment(
    'id' serial primary key,
    'novelty_id' integer references novelty(id),
    'name' varchar(50) NOT NULL,
    'description' varchar(200) NOT NULL
);

CREATE TABLE laboratory(
    'id' serial primary key,
    'equipment_id' integer references equipment(id),
    'novelty_id' integer references novelty(id),
    'name' varchar(50) NOT NULL
);

CREATE TABLE element(
    'id' serial primary key,
    'novelty_id' integer references novelty(id),
    'name' varchar(50) NOT NULL,
    'description' varchar(200) NOT NULL
);
