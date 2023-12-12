package ub.edu.resources.dao.MOCK.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.resources.dao.relationships.DAORelacioTematicaPelicula;

public class DAORelacioTematicaPeliculaMOCK extends DAORelacioMOCK<Parell<String, String>> implements DAORelacioTematicaPelicula{
    public DAORelacioTematicaPeliculaMOCK() {
        addTematicaPelicula("Drama", "The Godfather");
        addTematicaPelicula("Drama", "Shawshank Redemption");
        addTematicaPelicula("Sci-Fi", "The Matrix");
        addTematicaPelicula("Thriller", "The Dark Knight");
        addTematicaPelicula("Sci-Fi", "Avatar");
        addTematicaPelicula("Sci-Fi", "Inception");
        addTematicaPelicula("Sci-Fi", "Interstellar");
        addTematicaPelicula("Fantasy", "Avengers: Endgame");
        addTematicaPelicula("Drama", "Parasite");
    }

    private boolean addTematicaPelicula(String nomTematica, String nomPelicula) {
        return relacions.add(new Parell<>(nomTematica, nomPelicula));
    }
}
