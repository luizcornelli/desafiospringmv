CREATE DATABASE dbdesafiomvspring
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

CREATE TABLE estado (
    id bigint NOT NULL,
    nome character varying(255),
    PRIMARY KEY (id)
);

CREATE TABLE cidade (
    id bigint NOT NULL,
    nome character varying(255),
    estado_id bigint,
    PRIMARY KEY (id), 
    FOREIGN KEY (estado_id) REFERENCES estado (id)
);

CREATE TABLE cliente (
    id bigint NOT NULL,
    data_nascimento timestamp without time zone,
    idade integer,
    nome character varying(255),
    sexo character(1),
    cidade_id bigint,
	PRIMARY KEY (id), 
    FOREIGN KEY (cidade_id) REFERENCES cidade (id)
);