package ub.edu.model;

import ub.edu.model.cataleg.ContingutDigital;

import java.util.List;

public class Client {
    private String pwd;
    private String nom;
    private String nompropi;
    private String cognoms;
    private String dni;
    private WishList wishList;

    public Client(String nom, String pwd) {
        this.pwd = pwd;
        this.nom = nom;
        this.wishList = new WishList();
    }

    public Client(String correu, String nom, String cognoms, String dni, String password) {
        this.nom = correu;
        this.nompropi = nom;
        this.cognoms = cognoms;
        this.dni = dni;
        this.pwd = password;
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

    public List<ContingutDigital> getWishList() {
        return wishList.getElements();
    }
    public void addToWishList(ContingutDigital contingutDigital) {
        wishList.add(contingutDigital);
    }
}
