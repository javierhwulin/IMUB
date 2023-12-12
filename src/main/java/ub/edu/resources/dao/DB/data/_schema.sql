-- Create table for Pel·lícula inheriting from ContingutDigital
CREATE TABLE Pelicula (
    id INTEGER PRIMARY KEY,
    titol VARCHAR(255),
    descripcio TEXT,
    imatge TEXT,
    anyPrimeraEmissio INTEGER,
    idioma VARCHAR(255),
    durada TEXT,
    valoracio FLOAT
);


-- Create table for Serie inheriting from ContingutDigital
CREATE TABLE Serie (
    id INTEGER PRIMARY KEY,
    titol VARCHAR(255),
    descripcio TEXT,
    imatge TEXT,
    anyPrimeraEmissio INTEGER,
    idioma VARCHAR(255),
    durada TEXT
    -- valoracio FLOAT
);

-- Create table for Temporada with a foreign key to Serie
CREATE TABLE Temporada (
    id INTEGER PRIMARY KEY,
    serie_id INTEGER REFERENCES Serie(id),
    numTemporada INTEGER,
    anyEstrena INTEGER,
    valoracio INTEGER
);


-- Create table for Episodi with a foreign key to Temporada
CREATE TABLE Episodi (
    id INTEGER PRIMARY KEY,
    temporada_id INTEGER REFERENCES Temporada(id),
    numEpisodi INTEGER,
    nom VARCHAR(255),
    anyEstrena DATE,
    duracio TEXT,
    idioma VARCHAR(255),
    descripcio TEXT,
    url VARCHAR(255),
    valoracio FLOAT
);


-- Create table for Desig with a foreign key to Client
CREATE TABLE DesigPelicula (
    id INTEGER PRIMARY KEY,
    client_id INTEGER REFERENCES Client(id),
    pelicula_id INTEGER REFERENCES Pelicula(id),
    data DATE
);

CREATE TABLE DesigSerie (
    id INTEGER PRIMARY KEY,
    client_id INTEGER REFERENCES Client(id),
    serie_id INTEGER REFERENCES Serie(id),
    data DATE
);

-- Create table for Client
CREATE TABLE Client (
    id INTEGER PRIMARY KEY,
    correuElectronic VARCHAR(255),
    contrasenya VARCHAR(255),
    identificador VARCHAR(255),
    mobil VARCHAR(255),
    icona VARCHAR(255),
    nomPerfil VARCHAR(255)
);

-- Create table for Comunitat
CREATE TABLE Comunitat (
    id INTEGER PRIMARY KEY,
    nomComunitat VARCHAR(255),
    descripcio TEXT
);

CREATE TABLE ValoracioLikes (
    id INTEGER PRIMARY KEY,
    puntuacio INTEGER
);

CREATE TABLE ValoracioPunts (
    id INTEGER PRIMARY KEY,
    puntuacio FLOAT
);

CREATE TABLE ValoracioEstrelles (
    id INTEGER PRIMARY KEY,
    puntuacio BOOLEAN
);

-- Create table for Tematica
CREATE TABLE Tematica (
    id INTEGER PRIMARY KEY,
    nomTematica VARCHAR(255)
);

-- Relationships (1-to-many) are already defined with foreign keys in the table definitions above.

-- Many-to-many relationship between ContingutDigital and Tematica
CREATE TABLE Pelicula_Tematica (
    id INTEGER PRIMARY KEY,
    pelicula_id INTEGER REFERENCES Pelicula(id),
    tematica_id INTEGER REFERENCES Tematica(id)
);

CREATE TABLE Serie_Tematica (
    id INTEGER PRIMARY KEY,
    serie_id INTEGER REFERENCES Serie(id),
    tematica_id INTEGER REFERENCES Tematica(id)
);


-- Many-to-many relationship between Comunitat and Tematica
CREATE TABLE Comunitat_Tematica (
    id INTEGER PRIMARY KEY,
    comunitat_id INTEGER REFERENCES Comunitat(id),
    tematica_id INTEGER REFERENCES Tematica(id)
);

-- Many-to-many relationship between Client and Comunitat
CREATE TABLE Client_Comunitat (
    client_id INTEGER REFERENCES Client(id),
    comunitat_id INTEGER REFERENCES Comunitat(id),
    PRIMARY KEY (client_id, comunitat_id)
);

-- Many-to-many relationship between ContingutDigital and Productora
CREATE TABLE Pelicula_ValoracioPunts (
    pelicula_id INTEGER NOT NULL REFERENCES Pelicula(id),
    valoracio_id INTEGER NOT NULL REFERENCES ValoracioPunts(id),
    client_id INTEGER NOT NULL REFERENCES Client(id),
    PRIMARY KEY (pelicula_id, valoracio_id, client_id)
);

CREATE TABLE Pelicula_ValoracioEstrelles (
    pelicula_id INTEGER NOT NULL REFERENCES Pelicula(id),
    valoracio_id INTEGER NOT NULL REFERENCES ValoracioEstrelles(id),
    client_id INTEGER NOT NULL REFERENCES Client(id),
    PRIMARY KEY (pelicula_id, valoracio_id, client_id)
);

CREATE TABLE Pelicula_ValoracioLikes (
    pelicula_id INTEGER NOT NULL REFERENCES Pelicula(id),
    client_id INTEGER NOT NULL REFERENCES Client(id),
    valoracio_id INTEGER NOT NULL REFERENCES ValoracioLike(id),
    PRIMARY KEY (pelicula_id, valoracio_id, client_id)
);


CREATE TABLE Episodi_ValoracioPunts (
    episodi_id INTEGER NOT NULL REFERENCES Episodi(id),
    valoracio_id INTEGER NOT NULL REFERENCES ValoracioPunts(id),
    client_id INTEGER NOT NULL REFERENCES Client(id),
    PRIMARY KEY (episodi_id, valoracio_id, client_id)
);

CREATE TABLE Episodi_ValoracioEstrelles (
    episodi_id INTEGER NOT NULL REFERENCES Episodi(id),
    valoracio_id INTEGER NOT NULL REFERENCES ValoracioEstrelles(id),
    client_id INTEGER NOT NULL REFERENCES Client(id),
    PRIMARY KEY (episodi_id, valoracio_id, client_id)
);

CREATE TABLE Episodi_ValoracioLikes (
    episodi_id INTEGER NOT NULL REFERENCES Episodi(id),
    client_id INTEGER NOT NULL REFERENCES Client(id),
    valoracio_id INTEGER NOT NULL REFERENCES ValoracioLike(id),
    PRIMARY KEY (episodi_id, valoracio_id, client_id)
);
