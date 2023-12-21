package ub.edu.resources;

import ub.edu.model.*;
import ub.edu.model.ED.*;
import ub.edu.model.cataleg.*;
import ub.edu.resources.service.AbstractFactoryData;
import ub.edu.resources.service.DataService;

import java.util.List;

import ub.edu.resources.service.FactoryDB;

public class ResourcesFacade {

    private final DataService dataService;         // Connexio amb les dades
    private final imUBCataleg imUBCataleg;                  // Model a tractar
    private final imUBClients imUBClients;
    private final ModelFacade modelFacade;

    public ResourcesFacade(imUBCataleg imUBCataleg, imUBClients imubClients, ModelFacade modelFacade) {
        //factory = new FactoryMOCK();

        // Origen de les dades
        AbstractFactoryData factory = new FactoryDB();
        dataService = new DataService(factory);
        this.imUBCataleg = imUBCataleg;
        this.imUBClients = imubClients;
        this.modelFacade = modelFacade;

    }


    public void populateImUB() {
        loadPelicules();
        loadSeries();
        loadTematiques();
        loadComunitats();
        relacionarPeliculesTemes();    // Suposem que les tematiques i les pel·lícules ja estan creades
        relacionarSeriesTemes();       // Suposem que les tematiques i les series ja estan creades
        relacionarComunitatsTemes();   // Suposem que les tematiques i les comunitats ja estan creades
    }
    public void populateiImUBClients() {
        initCarteraClients();
        initPerfilClients();
    }

    public boolean initCarteraClients() {
        List<Client> l = null;
        try {
            l = dataService.getAllPersones();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            imUBClients.setCarteraClients(l);
            return true;
        }else return false;
    }

    public boolean loadPelicules() {
        List<Pelicula> l = null;
        try {
            l = dataService.getAllPelicules();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            imUBCataleg.setLlistaPelicules(l);
            return true;
        }else return false;
    }

    public boolean loadSeries() {
        List<Serie> l = null;
        try {
            l = dataService.getAllSeries();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            imUBCataleg.setLlistaSeries(l);
            return true;
        }else return false;
    }

    public boolean loadTematiques() {
        List<Tematica> l = null;
        try {
            l = dataService.getAllTematiques();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            imUBCataleg.setLlistaTematiques(l);
            return true;
        }else return false;
    }
    public boolean loadComunitats() {
        List<Comunitat> l = null;
        try {
            l = dataService.getAllComunitats();
        } catch (Exception e) {
            return false;
        }
        if (l != null) {
            imUBCataleg.setLlistaComunitats(l);
            return true;
        }else return false;
    }
    private void relacionarPeliculesTemes() {
        List<Parell<String, String>> relacionsPT = dataService.getAllRelacionsPeliculesTematiques();

        for (Parell p : relacionsPT) {
            Tematica tema = imUBCataleg.findTematica(p.getElement1().toString());
            Pelicula peli = imUBCataleg.findPelicula(p.getElement2().toString());
            peli.addTematica(tema);
        }
    }

    private void relacionarSeriesTemes(){
        List<Parell<String, String>> relacionsST = dataService.getAllRelacionsSeriesTematiques();

        for (Parell p : relacionsST) {
            Tematica tema = imUBCataleg.findTematica(p.getElement1().toString());
            Serie serie = imUBCataleg.findSerie(p.getElement2().toString());
            serie.addTematica(tema);
        }
    }

    private void relacionarComunitatsTemes(){
        List<Parell<String, String>> relacionsST = dataService.getAllRelacionsTematicaComunitat();

        for (Parell p : relacionsST) {
            Tematica tema = imUBCataleg.findTematica(p.getElement1().toString());
            Comunitat comunitat = imUBCataleg.findComunitat(p.getElement2().toString());
            comunitat.addTematica(tema);
        }
    }

    private void initPerfilClients() {
        // Es considera que totes les classes amb les quals estan relacionades
        // les valoracions, els clients i els continguts valorables
        initWish();
        initValoracions();
    }

    private void initWish() {
        List<Parell<String, String>> relacionsCPW = dataService.getAllRelacionsClientPeliculaWish();

        for (Parell p : relacionsCPW) {

            Client client = imUBClients.findClientCartera(p.getElement1().toString());
            Pelicula pelicula = imUBCataleg.findPelicula(p.getElement2().toString());
            // TO DO  Pràctica 4: Cal afegir la pel·lícula a la llista de desitjos del client
            client.addToWishList(pelicula);
        }

        List<Parell<String, String>> relacionsCSW = dataService.getAllRelacionsClientSerieWish();

        for (Parell p : relacionsCSW) {
            // TO DO  Pràctica 4: Cal afegir les valoracions de les sèries a la llista de desitjos del client
            Client client = imUBClients.findClientCartera(p.getElement1().toString());
            Serie serie = imUBCataleg.findSerie(p.getElement2().toString());
            client.addToWishList(serie);
        }
    }

    private void initValoracions() {
        iniValoracionsPelicules();
        iniValoracionsEpisodis();
    }

    private void iniValoracionsPelicules(){
        List<Trio<String, String, Float>> relacionsST = dataService.getAllRelacionsClientPeliculaPunts();

        for (Trio p : relacionsST) {
            // TODO Practica 4 : afegir les valoracions de pel·lícules per punts
            // nom client es (p.getElement1().toString(),
            // nom pelicula es p.getElement2().toString(),
            // valor es p.getElement3.toString());
            String nomClient = p.getElement1().toString();
            String nomPelicula = p.getElement2().toString();
            String valor = p.getElement3().toString();

            modelFacade.valorarContingut(nomPelicula, nomClient, ValorType.VALORAR_PER_PUNTS, valor);
        }

        List<Trio<String, String, Integer>> relacionsSTI = dataService.getAllRelacionsClientPeliculaEstrelles();

        for (Trio p : relacionsSTI) {
            // TODO Practica 4 : afegir les valoracions de pel·lícules per estrelles
            // nom client es (p.getElement1().toString(),
            // nom pelicula es p.getElement2().toString(),
            // estrelles es p.getElement3.toString());

            String nomClient = p.getElement1().toString();
            String nomPelicula = p.getElement2().toString();
            String valor = p.getElement3().toString();

            Client client = imUBClients.findClientCartera(nomClient);
            modelFacade.valorarContingut(nomPelicula, nomClient, ValorType.VALORAR_PER_ESTRELLES, valor);
        }
        List<Trio<String, String, Boolean>>  relacionsSTB = dataService.getAllRelacionsClientPeliculaLikes();

        for (Trio p : relacionsSTB) {
            // TODO Practica 4 : afegir les valoracions de pel·lícules per likes
            // nom client es (p.getElement1().toString(),
            // nom pelicula es p.getElement2().toString(),
            // likes es p.getElement3.toString());
            String nomClient = p.getElement1().toString();
            String nomPelicula = p.getElement2().toString();
            String valor = p.getElement3().toString();

            if (valor.equals("true")) {
                modelFacade.valorarContingut(nomPelicula, nomClient, ValorType.VALORAR_PER_LIKES, "1");
            } else if (valor.equals("false")) {
                modelFacade.valorarContingut(nomPelicula, nomClient, ValorType.VALORAR_PER_LIKES, "0");
            }
        }
    }

    private void iniValoracionsEpisodis() {
        List<Quintet<String, String, Integer, Integer, Float>> relacionsST = dataService.getAllRelacionsClientEpisodiPunts();

        for (Quintet p : relacionsST) {
            // TODO Practica 4 : afegir les valoracions de episodis per punts
            // nom client es (p.getElement1().toString(),
            // nom serie es p.getElement2().toString(),
            // num temporada es p.getElement3.toString(),
            // num episodi es p.getElement4.toString(),
            // punts es p.getElement5.toString());
            String nomClient = p.getElement1().toString();
            String nomSerie = p.getElement2().toString();
            String numTemporada = p.getElement3().toString();
            String numEpisodi = p.getElement4().toString();
            String valor = p.getElement5().toString();

            modelFacade.valorarContingut(nomSerie, Integer.parseInt(numTemporada), Integer.parseInt(numEpisodi), nomClient, ValorType.VALORAR_PER_PUNTS, valor);
        }

        List<Quintet<String, String, Integer, Integer, Integer>> relacionsSTI = dataService.getAllRelacionsClientEpisodiEstrelles();

        for (Quintet p : relacionsSTI) {
            // TODO Practica 4 : afegir les valoracions de episodis per estrelles
            // nom client es (p.getElement1().toString(),
            // nom serie es p.getElement2().toString(),
            // num temporada es p.getElement3.toString(),
            // num episodi es p.getElement4.toString(),
            // estrelles es p.getElement5.toString());
            String nomClient = p.getElement1().toString();
            String nomSerie = p.getElement2().toString();
            String numTemporada = p.getElement3().toString();
            String numEpisodi = p.getElement4().toString();
            String valor = p.getElement5().toString();

            modelFacade.valorarContingut(nomSerie, Integer.parseInt(numTemporada), Integer.parseInt(numEpisodi), nomClient, ValorType.VALORAR_PER_ESTRELLES, valor);
        }
        List<Quintet<String, String, Integer, Integer, Boolean>> relacionsSTB = dataService.getAllRelacionsClientEpisodiLikes();

        for (Quintet p : relacionsSTB) {
            // TODO Practica 4 : afegir les valoracions de episodis per likes
            // nom client es (p.getElement1().toString(),
            // nom serie es p.getElement2().toString(),
            // num temporada es p.getElement3.toString(),
            // num episodi es p.getElement4.toString(),
            // likes es p.getElement5.toString());
            String nomClient = p.getElement1().toString();
            String nomSerie = p.getElement2().toString();
            String numTemporada = p.getElement3().toString();
            String numEpisodi = p.getElement4().toString();
            String valor = p.getElement5().toString();

            Client client = imUBClients.findClientCartera(nomClient);
            if(client == null) System.out.println("Client no trobat");
            else {
                if (valor.equals("true")) {
                    modelFacade.valorarContingut(nomSerie, Integer.parseInt(numTemporada), Integer.parseInt(numEpisodi), nomClient, ValorType.VALORAR_PER_LIKES, "1");
                } else if (valor.equals("false")) {
                    modelFacade.valorarContingut(nomSerie, Integer.parseInt(numTemporada), Integer.parseInt(numEpisodi), nomClient, ValorType.VALORAR_PER_LIKES, "0");
                }
            }
        }
    }


    public StatusType addNewPersona(String correu, String nom, String cognoms, String dni, String password) throws Exception {
        if  (modelFacade.findPersonaByCorreu(correu) != null)
            return StatusType.PERSONA_DUPLICADA;
        else if (Seguretat.isMail(correu) && Seguretat.isPasswordSegur(password)){
            Client persona = new Client(correu, nom, cognoms, dni, password);
            if(this.dataService.addClient(persona)){
                //actualizar lista perfil de clients
                imUBClients.addClient(persona);
            }
            return StatusType.REGISTRE_VALID;
        }
        else return StatusType.FORMAT_INCORRECTE_CORREU_PWD;
    }


    public StatusType addRelacioClientComunitat(String correuPersona, String nomComunitat) throws Exception {
        Parell<String,String> gfp = new Parell<>(correuPersona,nomComunitat);
        modelFacade.addComunitatClient(correuPersona, nomComunitat);
        if ( dataService.addRelacioClientComunitat(gfp))
            return StatusType.REGISTRE_VALID;
        else return StatusType.COMUNITAT_INEXISTENT;
    }


}
