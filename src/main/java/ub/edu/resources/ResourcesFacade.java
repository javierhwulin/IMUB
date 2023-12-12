package ub.edu.resources;

import ub.edu.model.StatusType;
import ub.edu.model.Seguretat;
import ub.edu.model.*;
import ub.edu.model.ED.*;
import ub.edu.model.cataleg.*;
import ub.edu.resources.service.AbstractFactoryData;
import ub.edu.resources.service.DataService;

import java.util.List;

import ub.edu.resources.service.FactoryDB;
import ub.edu.resources.service.FactoryMOCK;

public class ResourcesFacade {

    private AbstractFactoryData factory;      // Origen de les dades
    private DataService dataService;         // Connexio amb les dades
    private imUBCataleg imUBCataleg;                  // Model a tractar
    private imUBClients imUBClients;

    private ModelFacade modelFacade;

    public ResourcesFacade(imUBCataleg imUBCataleg, imUBClients imubClients, ModelFacade modelFacade) {
        //factory = new FactoryMOCK();

        factory = new FactoryDB();
        dataService = new DataService(factory);
        this.imUBCataleg = imUBCataleg;
        this.imUBClients = imubClients;
        this.modelFacade = modelFacade;

    }


    public void populateImUB() {
        try {
            loadPelicules();
            loadSeries();
            loadTematiques();
            loadComunitats();
            relacionarPeliculesTemes();    // Suposem que les tematiques i les pel·lícules ja estan creades
            relacionarSeriesTemes();       // Suposem que les tematiques i les series ja estan creades
            relacionarComunitatsTemes();   // Suposem que les tematiques i les comunitats ja estan creades
        } catch (Exception e) {
            System.out.println("Exception: --> " + e.getMessage());
        }
    }
    public void populateiImUBClients() {
        try {
            initCarteraClients();
            initPerfilClients();

        } catch (Exception e) {
            System.out.println("Exception: --> " + e.getMessage());
        }
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

    private void initPerfilClients() throws Exception {
        // Es considera que totes les classes amb les quals estan relacionades
        // les valoracions, els clients i els continguts valorables
        initWish();
        initValoracions();
    }

    private void initWish() throws Exception  {
        List<Parell<String, String>> relacionsCPW = dataService.getAllRelacionsClientPeliculaWish();

        for (Parell p : relacionsCPW) {

            Client client = imUBClients.findClientCartera(p.getElement1().toString());
            Pelicula pelicula = imUBCataleg.findPelicula(p.getElement2().toString());
            // TO DO  Pràctica 4: Cal afegir la pel·lícula a la llista de desitjos del client
        }

        List<Parell<String, String>> relacionsCSW = dataService.getAllRelacionsClientSerieWish();

        for (Parell p : relacionsCSW) {
            // TO DO  Pràctica 4: Cal afegir les valoracions de les sèries a la llista de desitjos del client
        }

    }

    public void initValoracions() throws Exception {

        iniValoracionsPelicules();
        iniValoracionsEpisodis();
    }

    private void iniValoracionsPelicules() {
        List<Trio<String, String, Float>> relacionsST = dataService.getAllRelacionsClientPeliculaPunts();

        for (Trio p : relacionsST) {
            // TO DO Practica 4 : afegir les valoracions de pel·lícules per punts
            // nom client es (p.getElement1().toString(),
            // nom pelicula es p.getElement2().toString(),
            // valor es p.getElement3.toString());
        }

        List<Trio<String, String, Integer>> relacionsSTI = dataService.getAllRelacionsClientPeliculaEstrelles();

        for (Trio p : relacionsSTI) {
            // TO DO Practica 4 : afegir les valoracions de pel·lícules per estrelles
            // nom client es (p.getElement1().toString(),
            // nom pelicula es p.getElement2().toString(),
            // estrelles es p.getElement3.toString());
        }
        List<Trio<String, String, Boolean>>  relacionsSTB = dataService.getAllRelacionsClientPeliculaLikes();

        for (Trio p : relacionsSTB) {
            // TO DO Practica 4 : afegir les valoracions de pel·lícules per likes
            // nom client es (p.getElement1().toString(),
            // nom pelicula es p.getElement2().toString(),
            // likes es p.getElement3.toString());
        }
    }

    private void iniValoracionsEpisodis() {
        List<Quintet<String, String, Integer, Integer, Float>> relacionsST = dataService.getAllRelacionsClientEpisodiPunts();

        for (Quintet p : relacionsST) {
            // TO DO Practica 4 : afegir les valoracions de episodis per punts
            // nom client es (p.getElement1().toString(),
            // nom serie es p.getElement2().toString(),
            // num temporada es p.getElement3.toString(),
            // num episodi es p.getElement4.toString(),
            // punts es p.getElement5.toString());

        }

        List<Quintet<String, String, Integer, Integer, Integer>> relacionsSTI = dataService.getAllRelacionsClientEpisodiEstrelles();

        for (Quintet p : relacionsSTI) {
            // TO DO Practica 4 : afegir les valoracions de episodis per estrelles
            // nom client es (p.getElement1().toString(),
            // nom serie es p.getElement2().toString(),
            // num temporada es p.getElement3.toString(),
            // num episodi es p.getElement4.toString(),
            // estrelles es p.getElement5.toString());
        }
        List<Quintet<String, String, Integer, Integer, Boolean>> relacionsSTB = dataService.getAllRelacionsClientEpisodiLikes();

        for (Quintet p : relacionsSTB) {
            // TO DO Practica 4 : afegir les valoracions de episodis per likes
            // nom client es (p.getElement1().toString(),
            // nom serie es p.getElement2().toString(),
            // num temporada es p.getElement3.toString(),
            // num episodi es p.getElement4.toString(),
            // likes es p.getElement5.toString());
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
