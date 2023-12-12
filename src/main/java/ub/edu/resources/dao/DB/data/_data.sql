INSERT INTO Client (correuElectronic, contrasenya, identificador, mobil, icona, nomPerfil)
VALUES ('client1@example.com', 'password1', 'client123', '+1234567890', 'client1.jpg', 'Joan Cantimpalo');
        
INSERT INTO Client (correuElectronic, contrasenya, identificador, mobil, icona, nomPerfil)
VALUES ('client2@example.com', 'password2', 'client456', '+9876543210', 'client2.jpg', 'Maria Martorell');

INSERT INTO Client (correuElectronic, contrasenya, identificador, mobil, icona, nomPerfil)
VALUES ("ajaleo@gmail.com", "password", "client-1", "+123456678", "client2.jpg", "John Doe");

INSERT INTO Client (correuElectronic, contrasenya, identificador, mobil, icona, nomPerfil)
VALUES ("dtomacal@yahoo.cat", "password", "client-2", "+123456678", "client2.jpg", "John Doe");

INSERT INTO Client (correuElectronic, contrasenya, identificador, mobil, icona, nomPerfil)
VALUES ("heisenberg@gmail.com", "password", "client-3", "+123456678", "client2.jpg", "John Doe");

INSERT INTO Comunitat (id, nomComunitat, descripcio)
VALUES (1, "Sci-Fi Lovers", "For those who love science fiction");

INSERT INTO Comunitat (id, nomComunitat, descripcio)
VALUES (2, "Action Heroes", "For fans of action-packed movies and series");

INSERT INTO Comunitat (id, nomComunitat, descripcio)
VALUES (3, "High Drama", "For those who love dramatic stories");

INSERT INTO Comunitat (id, nomComunitat, descripcio)
VALUES (4, "Thriller Seekers", "For those who love suspense and thrill");

INSERT INTO Comunitat (id, nomComunitat, descripcio)
VALUES (5, "Fantasy Realm", "For fans of magical and fantastical worlds");
        
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (1, 'Inception', 'A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.', 'https://m.media-amazon.com/images/M/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_SX300.jpg', 2010, 'English, Japanese, French', 148, 88.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (2, 'Interstellar', 'When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.', 'https://m.media-amazon.com/images/M/MV5BZjdkOTU3MDktN2IxOS00OGEyLWFmMjktY2FiMmZkNWIyODZiXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg', 2014, 'English', 169, 87.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (3, 'The Matrix', 'When a beautiful stranger leads computer hacker Neo to a forbidding underworld, he discovers the shocking truth--the life he knows is the elaborate deception of an evil cyber-intelligence.', 'https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg', 1999, 'English', 136, 87.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (4, 'The Shawshank Redemption', 'Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.', 'https://m.media-amazon.com/images/M/MV5BNDE3ODcxYzMtY2YzZC00NmNlLWJiNDMtZDViZWM2MzIxZDYwXkEyXkFqcGdeQXVyNjAwNDUxODI@._V1_SX300.jpg', 1994, 'English', 142, 93.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (5, 'The Godfather', 'Don Vito Corleone, head of a mafia family, decides to hand over his empire to his youngest son Michael. However, his decision unintentionally puts the lives of his loved ones in grave danger.', 'https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg', 1972, 'English, Italian, Latin', 175, 92.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (6, 'The Dark Knight', 'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.', 'https://m.media-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_SX300.jpg', 2008, 'English, Mandarin', 152, 90.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (7, 'Avengers: Endgame', 'After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos'' actions and restore balance to the universe.', 'https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg', 2019, 'English, Japanese, Xhosa, German', 181, 84.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (8, 'Avatar', 'A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.', 'https://m.media-amazon.com/images/M/MV5BZDA0OGQxNTItMDZkMC00N2UyLTg3MzMtYTJmNjg3Nzk5MzRiXkEyXkFqcGdeQXVyMjUzOTY1NTc@._V1_SX300.jpg', 2009, 'English, Spanish', 162, 79.0);
    
INSERT INTO Pelicula (id, titol, descripcio, imatge, anyPrimeraEmissio, idioma, durada, valoracio)
VALUES (9, 'Parasite', 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.', 'https://m.media-amazon.com/images/M/MV5BYWZjMjk3ZTItODQ2ZC00NTY5LWE0ZDYtZTI3MjcwN2Q5NTVkXkEyXkFqcGdeQXVyODk4OTc3MTY@._V1_SX300.jpg', 2019, 'Korean, English', 132, 85.0);
    
INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (1, "Stranger Things","When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces and one strange little girl.", "URL_strangerthings", 2016, "English", 2142);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (2, "Game of Thrones", "A series about the fight for the Iron Throne", "URL_got", 2011, "English", 4161);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (3, "The Crown", "The reign of Queen Elizabeth II", "URL_crown", 2016, "English", 3480);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (4, "The Mandalorian", "A lone gunfighter makes his way through the galaxy", "URL_mandalorian", 2019, "English", 960);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (5, "The Witcher", "The tale of Geralt of Rivia, a solitary monster hunter", "URL_witcher", 2019, "English", 1500);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (6, "Breaking Bad", "A high school teacher turned methamphetamine manufacturer", "URL_breakingbad", 2008, "English", 3038);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (7, "The Office", "A mockumentary on a group of office workers", "URL_office", 2005, "English", 4136);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (8, "Friends", "Follows the lives of six friends living in Manhattan", "URL_friends", 1994, "English", 5148);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (9, "The Big Bang Theory", "A group of nerds and their interactions with a waitress", "URL_bigbang", 2007, "English", 6160);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (10, "Chernobyl", "A series about the Chernobyl nuclear disaster", "URL_chernobyl", 2019, "English", 330);

INSERT INTO Serie (id, titol , descripcio, imatge, anyPrimeraEmissio, idioma, durada)
VALUES (11, "Peaky Blinders", "A gangster family in Birmingham, England, in 1919", "URL_peaky", 2013, "English", 2160);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (1, 1, 1, 2005, 10);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (2, 1, 2, 2005, 10);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (3, 1, 3, 2005, 10);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (4, 1, 4, 2005, 10);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (5, 1, 5, 2005, 10);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (6, 2, 1, 2005, 10);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (7, 2, 2, 2005, 10);

INSERT INTO Temporada (id, serie_id, numTemporada, anyEstrena, valoracio)
VALUES (8, 3, 1, 2005, 10);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (1, 1, 1, "Chapter One: The Vanishing of Will Byers", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (2, 1, 2, "Chapter Two: The Weirdo on Maple Street", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (3, 1, 3, "Chapter Three: Holly, Jolly", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (4, 1, 4, "Chapter Four: The Body", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (5, 1, 5, "Chapter Five: The Flea and the Acrobat", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (6, 1, 6, "Chapter Six: The Monster", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (7, 1, 7, "Chapter Seven: The Bathtub", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (8, 1, 8, "Chapter Eight: The Upside Down", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (9, 6, 1, "Winter Is Coming", 75.0);

INSERT INTO Episodi (id, temporada_id, numEpisodi, nom, valoracio)
VALUES (10, 7, 1, "The North Remembers", 75.0);

INSERT INTO Tematica (id, nomTematica)
VALUES (1, "Sci-Fi");

INSERT INTO Tematica (id, nomTematica)
VALUES (2, "Drama");

INSERT INTO Tematica (id, nomTematica)
VALUES (3, "Comedy");

INSERT INTO Tematica (id, nomTematica)
VALUES (4, "Action");

INSERT INTO Tematica (id, nomTematica)
VALUES (5, "Horror");

INSERT INTO Tematica (id, nomTematica)
VALUES (6, "Thriller");

INSERT INTO Tematica (id, nomTematica)
VALUES (7, "Romance");

INSERT INTO Tematica (id, nomTematica)
VALUES (8, "Mystery");

INSERT INTO Tematica (id, nomTematica)
VALUES (9, "Crime");

INSERT INTO Tematica (id, nomTematica)
VALUES (10, "Animation");

INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (1, 1);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (2, 1);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (3, 2);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (4, 3);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (5, 4);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (6, 5);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (7, 6);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (8, 7);
        
INSERT INTO Pelicula_Tematica (pelicula_id, tematica_id)
VALUES (9, 8);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (1, 1);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (2, 2);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (3, 2);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (4, 1);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (5, 8);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (6, 6);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (7, 3);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (8, 3);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (9, 3);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (10, 2);

INSERT INTO Serie_Tematica (serie_id, tematica_id)
VALUES (11, 9);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (1, 1);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (1, 4);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (2, 4);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (3, 2);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (4, 6);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (4, 4);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (5, 10);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (5, 3);

INSERT INTO Comunitat_Tematica (comunitat_id, tematica_id)
VALUES (5, 4);

INSERT INTO Client_Comunitat (client_id, comunitat_id)
VALUES (3, 1);

INSERT INTO Client_Comunitat (client_id, comunitat_id)
VALUES (4, 2);

INSERT INTO Client_Comunitat (client_id, comunitat_id)
VALUES (5, 3);

INSERT INTO Client_Comunitat (client_id, comunitat_id)
VALUES (3, 4);

INSERT INTO Client_Comunitat (client_id, comunitat_id)
VALUES (4, 5);

INSERT INTO Client_Comunitat (client_id, comunitat_id)
VALUES (5, 1);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (1, 1.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (2, 2.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (3, 3.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (4, 4.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (5, 5.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (6, 6.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (7, 7.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (8, 8.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (9, 9.0);
        
INSERT INTO ValoracioPunts (id, puntuacio)
VALUES (10, 10.0);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (1, 1);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (2, 2);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (3, 3);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (4, 4);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (5, 5);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (6, 6);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (7, 7);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (8, 8);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (9, 9);

INSERT INTO ValoracioEstrelles (id, puntuacio)
VALUES (10, 10);

INSERT INTO ValoracioLikes (id, puntuacio)
VALUES (1, False);

INSERT INTO ValoracioLikes (id, puntuacio)
VALUES (2, True);

INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (6, 1, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (6, 2, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (6, 3, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (6, 4, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (6, 5, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (3, 6, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (3, 7, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (3, 8, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (3, 9, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (3, 10, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (9, 10, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (9, 9, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (9, 8, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (9, 7, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (9, 6, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (8, 5, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (8, 4, 2);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (8, 3, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (8, 2, 1);
        
INSERT INTO Pelicula_ValoracioPunts (pelicula_id, valoracio_id, client_id)
VALUES (8, 1, 2);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (1, 1, 1);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (2, 1, 1);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (3, 3, 2);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (4, 3, 1);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (5, 5, 2);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (6, 3, 2);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (7, 3, 1);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (8, 3, 2);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (9, 2, 2);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (10, 1, 1);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (11, 2, 2);
        
INSERT INTO Pelicula_ValoracioEstrelles (pelicula_id, valoracio_id, client_id)
VALUES (12, 4, 2);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (6, 1, 1);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (6, 2, 2);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (3, 1, 3);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (3, 2, 1);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (4, 1, 2);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (9, 1, 3);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (9, 2, 4);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (8, 1, 1);

INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (8, 2, 2);
        
INSERT INTO Pelicula_ValoracioLikes (pelicula_id, valoracio_id, client_id)
VALUES (10, 2, 3);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (6, 1, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (6, 2, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (6, 3, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (6, 4, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (6, 5, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (3, 6, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (3, 7, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (3, 8, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (3, 9, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (3, 10, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (9, 10, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (9, 9, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (9, 8, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (9, 7, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (9, 6, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (8, 5, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (8, 4, 2);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (8, 3, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (8, 2, 1);

INSERT INTO Episodi_ValoracioPunts (episodi_id, valoracio_id, client_id)
VALUES (8, 1, 2);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (1, 1, 1);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (2, 1, 1);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (3, 3, 2);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (4, 3, 1);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (5, 5, 2);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (6, 3, 2);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (7, 3, 1);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (8, 3, 2);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (9, 2, 2);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (10, 1, 1);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (4, 2, 2);

INSERT INTO Episodi_ValoracioEstrelles (episodi_id, valoracio_id, client_id)
VALUES (2, 4, 2);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (6, 1, 1);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (6, 2, 2);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (3, 1, 3);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (3, 2, 1);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (4, 1, 2);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (9, 1, 3);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (9, 2, 4);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (8, 1, 1);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (8, 2, 2);

INSERT INTO Episodi_ValoracioLikes (episodi_id, valoracio_id, client_id)
VALUES (10, 2, 3);

INSERT INTO DesigPelicula (client_id, pelicula_id, data)
VALUES (3, 1, "01-09-2023 12:00:00");

INSERT INTO DesigPelicula (client_id, pelicula_id, data)
VALUES (4, 1, "02-09-2023 12:32:00");

INSERT INTO DesigPelicula (client_id, pelicula_id, data)
VALUES (5, 7, "03-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (3, 1, "01-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (4, 2, "11-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (5, 3, "11-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (3, 4, "11-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (4, 5, "11-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (5, 6, "11-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (3, 7, "01-10-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (4, 8, "28-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (5, 9, "28-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (3, 10, "15-09-2023 12:00:00");

INSERT INTO DesigSerie (client_id, serie_id, data)
VALUES (4, 11, "15-09-2023 12:00:00");
