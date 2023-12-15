package ub.edu.model;

public class ValorarPerLikes implements ValorarStrategy{
    @Override
    public boolean executeValoracio(imUBClients imubClients, imUBCataleg cataleg, String nomContingut, String correu, int valoracio) {
        Client client = imubClients.findClientCartera(correu);
        if(client == null){
            System.out.println("Model Facade: valorarContingut -> client no trobat");
            return false;
        }
        client.addLike(cataleg, nomContingut, valoracio);
        return true;
    }
}
