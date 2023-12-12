package ub.edu.model.cataleg;

import java.util.ArrayList;
import java.util.List;


public class Comunitat {

    private List<Tematica> tematiques;

    private List<ContingutDigital> communitylist;
    private String nomComunitat;
    private String descripcioComunitat;

    public Comunitat(String nomComunitat, String descripcioComunitat) {
        this.nomComunitat = nomComunitat;
        this.descripcioComunitat = descripcioComunitat;
        tematiques = new ArrayList<Tematica>();

        communitylist = new ArrayList<ContingutDigital>();
    }

    public String getNom() {
        return nomComunitat;
    }
    public void setNom(String nomComunitat){
        this.nomComunitat = nomComunitat;
    }
    public String getDescripcioComunitat() {
        return descripcioComunitat;
    }

    public List<ContingutDigital> getCommunitylist() {return communitylist;}
    public void setCommunitylist(List<ContingutDigital> communitylist) {
        this.communitylist = communitylist;
    }
    public void addTematica(Tematica t) {
        if (tematiques.size() <= 3) {
            tematiques.add(t);
        }
    }

    public void addContingutDigital(ContingutDigital cd) {
        communitylist.add(cd);
    }

    public String getDescripcio() {
        return descripcioComunitat;
    }
}
