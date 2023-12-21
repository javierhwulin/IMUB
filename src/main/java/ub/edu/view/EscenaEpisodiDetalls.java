package ub.edu.view;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;


public class EscenaEpisodiDetalls extends Escena{
    private static final String TIPUS_ACTUALIZADOR = "top10";
    public Text nomEpisodi_text;
    public Text data_nom_Serie;
    public Text data_temporada;
    public Text data_numEpisodi;

    public Text data_anyEstrena;
    public Text data_durada;
    public Text data_descripcio;
    public Text data_valoracio;

    public javafx.scene.image.ImageView data_image_image;

    public Button valorar_btn;
    private String correu_persona;
    private String id_contingut_audiovisual;

    private Integer numTemporada;
    private Integer numEpisodi;

    public void start() throws Exception {
        this.correu_persona=this.controller.getSessionMemory().getCorreuPersona();
        this.id_contingut_audiovisual =this.controller.getSessionMemory().getNomSerie();
        this.numTemporada = this.controller.getSessionMemory().getNumTemporada();
        this.numEpisodi = this.controller.getSessionMemory().getNumEpisodi();
        initData();
    }

    public void initData() throws Exception {
        HashMap<Object,Object> episodiHashMap = controller.getEpisodiDetalls(this.id_contingut_audiovisual, this.numTemporada, this.numEpisodi);
        String nom, dataCreacio="", descripcio, nSerie;
        Integer ntemp, nepisodi, durada;
        String  imatge="";
        Float valor;

        if(episodiHashMap.get("url")!=null){
            try {
                imatge = (String) episodiHashMap.get("url");
                Image image = new Image(imatge);
                data_image_image.setImage(image);
            } catch (Exception e){
                System.out.println("No se pudo cargar la imagen de l'Episodi.");
            }

        }

        if(episodiHashMap.get("nomSerie")!=null){
            nSerie = (String) episodiHashMap.get("nomSerie");
            data_nom_Serie.setText("Serie: " + nSerie);
        }
        if(episodiHashMap.get("numTemporada")!=null){
            ntemp =  (Integer) episodiHashMap.get("numTemporada");
            data_temporada.setText("Temporada: " + ntemp);
        }
        if(episodiHashMap.get("numEpisodi")!=null){
            nepisodi = (Integer) episodiHashMap.get("numEpisodi");
            data_numEpisodi.setText("Num. Episodi: " + nepisodi);
        }
        if(episodiHashMap.get("nom")!=null){
            nom = (String) episodiHashMap.get("nom");
            nomEpisodi_text.setText(nom);
            nomEpisodi_text.setText("Nom: " + nom);
        }
        if(episodiHashMap.get("anyEstrena")!=null){
            Object OBJ_dataCreacio =  episodiHashMap.get("anyEstrena");
            if(OBJ_dataCreacio!=null){
                dataCreacio=OBJ_dataCreacio.toString();
            }
            data_anyEstrena.setText("Data d'Estrena: " + dataCreacio);
        }
        if(episodiHashMap.get("durada")!=null){
            durada =  (Integer) episodiHashMap.get("durada");
            data_durada.setText("Durada: " + durada);
        }
        if(episodiHashMap.get("descripcio")!=null){
            descripcio = (String) episodiHashMap.get("descripcio");
            data_descripcio.setText("Descripcio: " + descripcio);
        }
        if(episodiHashMap.get("valoracio")!=null){
            valor = (Float) episodiHashMap.get("valoracio");
            data_valoracio.setText("Valoració Inicial: " + valor.toString());
        }

    }

    public void onBtnValorarClick() throws IOException {
        controller.getSessionMemory().setTipusObra("Episodi");
        controller.getSessionMemory().setTitolEpisodi(nomEpisodi_text.getText());
        controller.getSessionMemory().setNomObra(nomEpisodi_text.getText());
        //Nova finestra
        Escena escena = EscenaFactory.INSTANCE.creaEscena("valorarObra-view", "Valorar Contingut Audiovisual: "+this.id_contingut_audiovisual);
        EscenaValorarObra escenaValorarObra = ((EscenaValorarObra)escena);
        escenaValorarObra.setController(controller);
        escenaValorarObra.start();
        escenaValorarObra.updater.subscribe(TIPUS_ACTUALIZADOR, (EscenaMain)controller.getViewMemory().getMainScene());
    }
}
