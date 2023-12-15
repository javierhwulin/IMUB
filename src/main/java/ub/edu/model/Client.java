package ub.edu.model;

import ub.edu.model.cataleg.ContingutDigital;

import java.util.HashMap;
import java.util.List;

public class Client {
    private String pwd;
    private String nom;
    private String nompropi;
    private String cognoms;
    private String dni;
    private final StarsHashMap valoracionsPerEstrelles;
    private final PointsHashMap valoracionsPerPunts;
    private final LikesHashMap valoracionsPerLikes;
    private WishList wishList;

    public Client(String nom, String pwd) {
        this.pwd = pwd;
        this.nom = nom;
        this.valoracionsPerLikes = new LikesHashMap();
        this.valoracionsPerPunts = new PointsHashMap();
        this.valoracionsPerEstrelles = new StarsHashMap();
        this.wishList = new WishList();
    }

    public Client(String correu, String nom, String cognoms, String dni, String password) {
        this.nom = correu;
        this.nompropi = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.pwd = password;
        this.valoracionsPerLikes = new LikesHashMap();
        this.valoracionsPerPunts = new PointsHashMap();
        this.valoracionsPerEstrelles = new StarsHashMap();
    }

    public String getPwd() {
        return pwd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public HashMap<String, Float> getAllValoracionsPelisPerLikes() {
        return valoracionsPerLikes.getAllFilmRatings();
    }
    public HashMap<String, Float> getAllValoracionsPelisPerPunts() {
        return valoracionsPerPunts.getAllFilmRatings();
    }
    public HashMap<String, Float> getAllValoracionsPelisPerEstrelles() {
        return valoracionsPerEstrelles.getAllFilmRatings();
    }
    public HashMap<String, Float> getAllValoracionsEpisodisPerLikes() {
        return valoracionsPerLikes.getAllEpisodeRatings();
    }
    public HashMap<String, Float> getAllValoracionsEpisodisPerPunts() {
        return valoracionsPerPunts.getAllEpisodeRatings();
    }
    public HashMap<String, Float> getAllValoracionsEpisodisPerEstrelles() {
        return valoracionsPerEstrelles.getAllEpisodeRatings();
    }
    public void addLike(imUBCataleg cataleg, String titol, float valoracio) {
        valoracionsPerLikes.addFilmRating(cataleg, titol, valoracio);
    }
    public void addLike(imUBCataleg cataleg, String titol, int numTemporada, int numEpisodi, float valoracio) {
        valoracionsPerLikes.addEpisodeRating(cataleg, titol, numTemporada, numEpisodi, valoracio);
    }
    public void addPunts(imUBCataleg cataleg, String titol, float valoracio) {
        valoracionsPerPunts.addFilmRating(cataleg, titol, valoracio);
    }
    public void addPunts(imUBCataleg cataleg, String titol, int numTemporada, int numEpisodi, float valoracio) {
        valoracionsPerPunts.addEpisodeRating(cataleg, titol, numTemporada, numEpisodi, valoracio);
    }
    public void addEstrelles(imUBCataleg imUBCataleg, String titol, float valoracio) {
        valoracionsPerEstrelles.addFilmRating(imUBCataleg, titol, valoracio);
    }
    public void addEstrelles(imUBCataleg imUBCataleg, String titol, int numTemporada, int numEpisodi, float valoracio) {
        valoracionsPerEstrelles.addEpisodeRating(imUBCataleg, titol, numTemporada, numEpisodi, valoracio);
    }
    public List<ContingutDigital> getWishList() {
        return wishList.getElements();
    }
    public void addToWishList(ContingutDigital contingutDigital) {
        wishList.add(contingutDigital);
    }
}
