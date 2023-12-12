package ub.edu.resources.dao.MOCK.entities;

import ub.edu.model.cataleg.Comunitat;
import ub.edu.resources.dao.entities.DAOComunitat;

import java.util.*;

public class DAOComunitatMOCK implements DAOComunitat {
    private Map<String, Comunitat> comunitats = new HashMap<>();

    public DAOComunitatMOCK() {
        addComunitat("Sci-Fi Lovers", "For those who love science fiction");
        addComunitat("Action Heroes", "For fans of action-packed movies and series");
        addComunitat("High Drama", "For those who love dramatic stories");
        addComunitat("Thriller Seekers", "For those who love suspense and thrill");
        addComunitat("Fantasy Realm", "For fans of magical and fantastical worlds");
    }

    private void addComunitat(String nom, String descripcio) {
        comunitats.put(nom, new Comunitat(nom, descripcio));
    }

    @Override
    public List<Comunitat> getAll() throws Exception {
        return new ArrayList<>(comunitats.values());
    }

    @Override
    public boolean add(Comunitat comunitat) throws Exception {
        if (getById(new String[]{comunitat.getNom()}).isPresent()) {
            return false;
        }
        comunitats.put(comunitat.getNom(), comunitat);
        return true;
    }

    @Override
    public boolean delete(Comunitat comunitat) throws Exception {
        return comunitats.remove(comunitat.getNom()) != null;
    }

    @Override
    public Optional<Comunitat> getById(String[] id) throws Exception {
        return Optional.ofNullable(comunitats.get(Objects.requireNonNull(id[0], "Comunitat name cannot be null")));
    }

    @Override
    public boolean update(Comunitat comunitat, String[] params) throws Exception {
        comunitat.setNom(Objects.requireNonNull(
                params[0], "Nom cannot be null"));
        return comunitats.replace(comunitat.getNom(), comunitat) != null;
    }
}
