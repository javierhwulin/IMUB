package ub.edu.model.cataleg;

import java.util.ArrayList;
import java.util.List;

public class Temporada {
    private final int numTemporada;
    private String nomSerie;
    private final List<Episodi> llistaEpisodis;

    public Temporada(String nomSerie, int i) {
        this.nomSerie = nomSerie;
        this.numTemporada = i;
        llistaEpisodis = new ArrayList<>();
    }

    public String getNomSerie() {
        return nomSerie;
    }

    public void setNomSerie(String nomSerie) {
        this.nomSerie = nomSerie;
    }
    
    public void addEpisodi(Episodi episodi){
        llistaEpisodis.add(episodi);
    }

    public int getNumTemporada() {
        return numTemporada;
    }

    public List<Episodi> getEpisodis() {
        return llistaEpisodis;
    }

    public Episodi findEpisodi(int numEpisodi) {
        if (numEpisodi<=llistaEpisodis.size()  )
        {
            boolean trobat = false;
            int i = 0;
            while (!trobat && i < llistaEpisodis.size()) {
                if (llistaEpisodis.get(i).getNumEpisodi() == numEpisodi) {
                    trobat = true;
                } else i++;
            }
            return llistaEpisodis.get(i);
        } else return null;
    }
}
