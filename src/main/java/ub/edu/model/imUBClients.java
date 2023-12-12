package ub.edu.model;

import java.util.*;

public class imUBClients {

    private CarteraClients cartera;

    public imUBClients(){

        cartera = new CarteraClients();

    }
    public void setCarteraClients (List<Client> llistaC) {
        cartera = new CarteraClients(llistaC);
    }


    public Client findClientCartera(String string) {
        return cartera.find(string);
    }


    public void addClient(Client persona) {
        cartera.add(persona);
    }

    public CarteraClients getCarteraClients() {
        return cartera;
    }


}
