package ub.edu.resources.dao.MOCK.relationships;

import ub.edu.model.ED.Parell;
import ub.edu.resources.dao.relationships.DAORelacioClientComunitat;

public class DAORelacioClientComunitatMOCK extends DAORelacioMOCK<Parell<String, String>> implements DAORelacioClientComunitat {
    public DAORelacioClientComunitatMOCK() {
        addPersonaComunitat("ajaleo@gmail.com", "Sci-Fi Lovers");
        addPersonaComunitat("dtomacal@yahoo.cat", "Action Heroes");
        addPersonaComunitat("heisenberg@gmail.com", "High Drama");
        addPersonaComunitat("ajaleo@gmail.com", "Thriller Seekers");
        addPersonaComunitat("dtomacal@yahoo.cat", "Fantasy Realm");
        addPersonaComunitat("heisenberg@gmail.com", "Sci-Fi Lovers");
        addPersonaComunitat("ajaleo@gmail.com", "Sci-Fi Lovers");
        addPersonaComunitat("dtomacal@yahoo.cat", "Action Heroes");
        addPersonaComunitat("heisenberg@gmail.com", "High Drama");
        ;

    }

    private boolean addPersonaComunitat(String nomPersona, String nomComunitat){
        return relacions.add(new Parell<>(nomPersona, nomComunitat));
    }
}
