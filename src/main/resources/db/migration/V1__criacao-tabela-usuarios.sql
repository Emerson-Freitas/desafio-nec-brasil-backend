CREATE TABLE usuarios(

id INTEGER NOT NULL auto_increment,
usuario VARCHAR(255) NOT NULL unique,
senha VARCHAR(255) NOT NULL,

PRIMARY KEY(id)
);