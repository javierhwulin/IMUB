package ub.edu.model;

import java.util.HashMap;
import java.util.List;

// ValorarFacade is a Facade class for Valorar functionality that encapsulates the implementation
public class ValorFacade {
    private final imUBCataleg cataleg;
    private final RatingHashMap likesRating;
    private final RatingHashMap pointsRating;
    private final RatingHashMap starsRating;

    public ValorFacade(imUBCataleg cataleg) {
        this.cataleg = cataleg;
        this.likesRating = new LikesHashMap();
        this.pointsRating = new PointsHashMap();
        this.starsRating = new StarsHashMap();
    }

    public boolean valorarContingut(Client client, String typeValoracio, String title, float rating) {
        switch (typeValoracio) {
            case "ValorEstrelles" -> {
                if (cataleg.esPelicula(title)) {
                    starsRating.addFilmRating(cataleg, client, title, rating);
                    return true;
                } else {
                    System.out.println("Model Facade: valorarContingut -> contingut no es una pelicula");
                    return false;
                }
            }
            case "ValorPunts" -> {
                if (cataleg.esPelicula(title)) {
                    pointsRating.addFilmRating(cataleg, client, title, rating);
                    return true;
                } else {
                    System.out.println("Model Facade: valorarContingut -> contingut no es una pelicula");
                    return false;
                }
            }
            case "ValorLikes" -> {
                if (cataleg.esPelicula(title)) {
                    likesRating.addFilmRating(cataleg, client, title, rating);
                    return true;
                } else {
                    System.out.println("Model Facade: valorarContingut -> contingut no es una pelicula");
                    return false;
                }
            }
            default -> {
                System.out.println("Model Facade: valorarContingut -> tipus de valoració no es correcte");
                return false;
            }
        }
    }

    public boolean valorarContingut(Client client, String typeValoracio, String title, int numTemporada, int numEpisodi, float rating) {
        switch (typeValoracio) {
            case "StarValor" -> {
                if (!cataleg.esPelicula(title)) {
                    starsRating.addEpisodeRating(cataleg, client, title, numTemporada, numEpisodi, rating);
                    return true;
                } else {
                    System.out.println("Model Facade: valorarContingut -> contingut no es una serie");
                    return false;
                }
            }
            case "PointValor" -> {
                if (!cataleg.esPelicula(title)) {
                    pointsRating.addEpisodeRating(cataleg, client, title, numTemporada, numEpisodi, rating);
                    return true;
                } else {
                    System.out.println("Model Facade: valorarContingut -> contingut no es una serie");
                    return false;
                }
            }
            case "LikeValor" -> {
                if (!cataleg.esPelicula(title)) {
                    likesRating.addEpisodeRating(cataleg, client, title, numTemporada, numEpisodi, rating);
                    return true;
                } else {
                    System.out.println("Model Facade: valorarContingut -> contingut no es una serie");
                    return false;
                }
            }
        }
        return false;
    }

    public List<HashMap<String, String>> getAllFilmRating(String typeValoracio, String typeCalcul) {
        return switch (typeValoracio) {
            case "StarValor" -> starsRating.getAllFilmRatings(typeCalcul);
            case "PointValor" -> pointsRating.getAllFilmRatings(typeCalcul);
            case "LikeValor" -> likesRating.getAllFilmRatings(typeCalcul);
            default -> null;
        };
    }

    public List<HashMap<String, String>> getAllSerieRating(String typeValoracio, String typeCalcul) {
        return switch(typeValoracio) {
            case "StarValor" -> starsRating.getAllSerieRatings(typeCalcul);
            case "PointValor" -> pointsRating.getAllSerieRatings(typeCalcul);
            case "LikeValor" -> likesRating.getAllSerieRatings(typeCalcul);
            default -> null;
        };
    }

    //TopList sort by rating value the list of ratings by type of rating and return the top list
    private List<HashMap<String, String>> TopList(String tipusContingut, String tipusCalcul, RatingHashMap allRatings, int top) {
        switch (tipusContingut) {
            case "Serie" -> {
                List<HashMap<String, String>> list = allRatings.getAllSerieRatings(tipusCalcul);
                list.sort((contingut1, contingut2) -> {
                    float valor1 = Float.parseFloat(contingut1.get("valor"));
                    float valor2 = Float.parseFloat(contingut2.get("valor"));

                    return Float.compare(valor2, valor1);
                });
                return list;
            }
            case "Pelicula" -> {
                List<HashMap<String, String>> list = allRatings.getAllFilmRatings(tipusCalcul);
                list.sort((contingut1, contingut2) -> {
                    float valor1 = Float.parseFloat(contingut1.get("valor"));
                    float valor2 = Float.parseFloat(contingut2.get("valor"));

                    return Float.compare(valor2, valor1);
                });
                return list;
            }
            default -> {
                System.out.println("Model Facade: TopLikeList -> tipus de contingut no es correcte");
                throw new IllegalArgumentException();
            }
        }
    }
    public List<HashMap<String, String>> TopLikeList(String tipusContingut, String tipusCalcul, int top) {
        return TopList(tipusContingut, tipusCalcul, likesRating, top);
    }

    public List<HashMap<String, String>> TopPointList(String tipusContingut, String tipusCalcul, int top) {
        return TopList(tipusContingut, tipusCalcul, pointsRating, top);
    }

    public List<HashMap<String, String>> TopStarList(String tipusContingut, String tipusCalcul, int top) {
        return TopList(tipusContingut, tipusCalcul, starsRating, top);
    }
}
