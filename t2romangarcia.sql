DROP DATABASE IF EXISTS t2romangarcia;
CREATE DATABASE t2romangarcia;
USE t2romangarcia;
CREATE TABLE artista (
  dni VARCHAR(9),
  nombre VARCHAR(128),
  apellidos VARCHAR(128),
  edad integer NOT NULL,
  actuacion VARCHAR(128),
  tipo VARCHAR(128),
  PRIMARY KEY (dni)
);