package ub.edu.resources.dao.MOCK.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.resources.dao.relationships.DAORelacioTematicaComunitat;

public class DAORelacioTematicaComunitatMOCK extends DAORelacioMOCK<Parell<String, String>> implements DAORelacioTematicaComunitat {

    public DAORelacioTematicaComunitatMOCK(){
        addTematicaComunitat("Sci-Fi Lovers", "Sci-Fi");
        addTematicaComunitat("Sci-Fi Lovers", "Action");
        addTematicaComunitat("Action Heroes", "Action");
        addTematicaComunitat("High Drama", "Drama");
        addTematicaComunitat("Thriller Seekers", "Thriller");
        addTematicaComunitat("Thriller Seekers", "Action");
        addTematicaComunitat("Fantasy Realm", "Fantasy");
        addTematicaComunitat("Fantasy Realm", "Comedy");
        addTematicaComunitat("Fantasy Realm", "Action");
    }

    private void addTematicaComunitat(String nomTematica, String nomComunitat) {
        relacions.add(new Parell<>(nomTematica, nomComunitat));
    }
}
