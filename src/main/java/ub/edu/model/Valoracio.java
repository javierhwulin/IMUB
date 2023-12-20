package ub.edu.model;
import ub.edu.model.ED.Quartet;
import ub.edu.model.cataleg.ContingutDigital;
import java.util.HashMap;

public interface Valoracio {
    float getValoracio();
    void addValoracio(Client client, ContingutDigital contingutDigital, HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions);
    void addValoracio(Client client, ContingutDigital contingutDigital, int numTemporada, int numEpisodi, HashMap<Quartet<Client, ContingutDigital, Integer, Integer>, Valoracio> valoracions);
}
