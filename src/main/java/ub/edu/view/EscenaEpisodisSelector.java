package ub.edu.view;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.List;


public class EscenaEpisodisSelector extends Escena{
    private static final double ESPAI_ENTRE_BOTONS = 30;
    public AnchorPane episodi_pane;
    public Button episodi_btn;
    private String correu_persona;
    private String nom_serie;
    private Integer num_temporada;

    public void start() throws Exception {
        this.correu_persona=this.controller.getSessionMemory().getCorreuPersona();
        this.nom_serie =this.controller.getSessionMemory().getNomSerie();
        this.num_temporada = this.controller.getSessionMemory().getNumTemporada();
        popularEpisodis(nom_serie, num_temporada);
    }

    public void popularEpisodis(String id, Integer idTemp) throws Exception {
        List<HashMap<Object,Object>> hashMapList = controller.getAllEpisodis(id, idTemp);
        if(hashMapList.size()==0){
            episodi_btn.setText("Empty Episodis");
            episodi_btn.setDisable(true);
            return;
        }
        List<Node> episodisPaneChildren = episodi_pane.getChildren();

        double width = episodi_btn.getWidth();
        double height = episodi_btn.getHeight();
        double layoutX = episodi_btn.getLayoutX();
        double layoutY = episodi_btn.getLayoutY();

        String s;
        Button new_btn;
        for (int i = 0; i < hashMapList.size(); i++) {
            Integer numEpisodi = (Integer) hashMapList.get(i).get("numEpisodi");
            new_btn = createEpisodiButton(numEpisodi, "Episodi " + numEpisodi, width, height, layoutX, layoutY);
            episodisPaneChildren.add(new_btn);
            layoutY += ESPAI_ENTRE_BOTONS;
        }
        //Actualitzem la mida del pane que conté els botons perque es pugui fer scroll cap abaix si hi ha més botons dels que caben al pane
        episodi_pane.setPrefHeight(layoutY);
        //Esborrem excursio_btn, que l'utilitzavem únicament com a referència per la mida dels botons
        episodisPaneChildren.remove(episodi_btn);
    }

    private Button createEpisodiButton(Integer numEpisodi, String text, double width, double height, double layoutX, double layoutY){
        Button new_btn = new Button();
        new_btn.setPrefWidth(width);
        new_btn.setPrefHeight(height);
        new_btn.setText(text);
        new_btn.setLayoutX(layoutX);
        new_btn.setLayoutY(layoutY);
        new_btn.setAlignment(Pos.BASELINE_LEFT);
        new_btn.setOnMouseClicked(event ->
        {
            if (event.getButton() == MouseButton.PRIMARY)
            {
                try {
                    mostrarEpisodiDetalls(numEpisodi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return new_btn;
    }

    private void mostrarEpisodiDetalls(Integer num_episodi) throws Exception {
        Escena escena = EscenaFactory.INSTANCE.creaEscena("episodiDetalls-view", "Episodi "+String.valueOf(num_episodi) + "; Temporada " + this.num_temporada + "; Serie " + this.nom_serie);
        EscenaEpisodiDetalls escenaEpisodiDetalls = ((EscenaEpisodiDetalls) escena);
        escena.setController(controller);
        this.controller.getSessionMemory().setNumEpisodi(num_episodi);
        escenaEpisodiDetalls.start();
    }

}


