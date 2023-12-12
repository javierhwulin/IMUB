package ub.edu.resources.dao.MOCK.entities;

import ub.edu.model.cataleg.Serie;
import ub.edu.resources.dao.entities.DAOSerie;

import java.util.*;

public class DAOSerieMOCK implements DAOSerie {

    private Map<String, Serie> series = new HashMap<>();

    public DAOSerieMOCK(){
        addSerie("Stranger Things","When a young boy vanishes, a small town uncovers a mystery involving secret experiments, terrifying supernatural forces and one strange little girl.", "URL_strangerthings", 2016, "English", 2142);
        addSerie("Game of Thrones", "A series about the fight for the Iron Throne", "URL_got", 2011, "English", 4161);
        addSerie("The Crown", "The reign of Queen Elizabeth II", "URL_crown", 2016, "English", 3480);
        addSerie("The Mandalorian", "A lone gunfighter makes his way through the galaxy", "URL_mandalorian", 2019, "English", 960);
        addSerie("The Witcher", "The tale of Geralt of Rivia, a solitary monster hunter", "URL_witcher", 2019, "English", 1500);
        addSerie("Breaking Bad", "A high school teacher turned methamphetamine manufacturer", "URL_breakingbad", 2008, "English", 3038);
        addSerie("The Office", "A mockumentary on a group of office workers", "URL_office", 2005, "English", 4136);
        addSerie("Friends", "Follows the lives of six friends living in Manhattan", "URL_friends", 1994, "English", 5148);
        addSerie("The Big Bang Theory", "A group of nerds and their interactions with a waitress", "URL_bigbang", 2007, "English", 6160);
        addSerie("Chernobyl", "A series about the Chernobyl nuclear disaster", "URL_chernobyl", 2019, "English", 330);
        addSerie("Peaky Blinders", "A gangster family in Birmingham, England, in 1919", "URL_peaky", 2013, "English", 2160);
  }

    private void addSerie(String nomSerie, String descripcio, String url, int anyEstrena, String idioma, int durada){
        series.put(nomSerie, new Serie(nomSerie, descripcio, url, anyEstrena, idioma, durada));
        
    }

    @Override
    public Optional<Serie> getById(String[] id) throws Exception {
        return Optional.ofNullable(series.get(Objects.requireNonNull(id[0], "Serie name cannot be null")));
    }

    @Override
    public List<Serie> getAll() throws Exception {
        return new ArrayList<>(series.values());
    }

    @Override
    public boolean add(Serie serie) throws Exception {
        if (getById(new String[]{serie.getNom()}).isPresent()) {
            return false;
        }
        series.put(serie.getNom(), serie);
        return true;
    }

    @Override
    public boolean update(Serie serie, String[] params) throws Exception {
        serie.setTitol(Objects.requireNonNull(
                params[0], "Pelicula name cannot be null"));
        return series.replace(serie.getNom(), serie) != null;
    }

    @Override
    public boolean delete(Serie serie) throws Exception {
        return series.remove(serie.getNom()) != null;
    }
}
