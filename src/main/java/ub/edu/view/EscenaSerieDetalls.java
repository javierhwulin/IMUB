package ub.edu.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.HashMap;


public class EscenaSerieDetalls extends Escena{
    private static final String TIPUS_ACTUALIZADOR = "wishList";
    public Text nomSerie_text;
    public Text data_text_caracteristiques;
    public Text data_text_nom;
    public Text data_text_dataAnyPrimeraEmissio;
    public Text data_text_durada;
    public Text data_text_descripcio;
    public Text data_text_idioma;

    public ImageView data_image_image;


    public Button valorar_btn;
    public Button wish_btn;

    private String correu_persona;
    private String nom_contingut_audiovisual;

    public UpdaterManager updater;

    public void start() throws Exception {
        this.correu_persona=this.controller.getSessionMemory().getCorreuPersona();
        this.nom_contingut_audiovisual =this.controller.getSessionMemory().getNomSerie();
        initData();
        updater = new UpdaterManager(TIPUS_ACTUALIZADOR);
    }

    public void initData() throws Exception {
        HashMap<Object,Object> serieHashMap = controller.getDetallsSerie(this.nom_contingut_audiovisual);
        String nom, idioma, imatge="", descripcio="";
        Integer anyPrimeraEmissio, durada;

        if(serieHashMap.get("imatge")!=null){
            try {
                imatge = (String) serieHashMap.get("imatge");
                Image image = new Image(imatge);
                data_image_image.setImage(image);
            } catch (Exception e){
                System.out.println("No se pudo cargar la imagen de la Serie.");
            }
        }

        if(serieHashMap.get("nom")!=null){
            nom = (String) serieHashMap.get("nom");
            nomSerie_text.setText(nom);
            data_text_nom.setText("Nom: " + nom);
        }
        if(serieHashMap.get("descripcio")!=null){
            descripcio = (String) serieHashMap.get("descripcio");
            data_text_descripcio.setText("Descripcio: " + descripcio);
        }
        if(serieHashMap.get("dataAnyPrimeraEmissio")!=null){
            anyPrimeraEmissio = (Integer) serieHashMap.get("dataAnyPrimeraEmissio");
            data_text_dataAnyPrimeraEmissio.setText("Any Primera Emissio: " + anyPrimeraEmissio);
        }
        if(serieHashMap.get("idioma")!=null){
            idioma = (String) serieHashMap.get("idioma");
            data_text_idioma.setText("Idioma: " + idioma);
        }
        if(serieHashMap.get("durada")!=null){
            durada = (Integer)  serieHashMap.get("durada");
            data_text_durada.setText("Durada: " + durada);
        }
    }

    public void onBtnWishListClick() throws IOException {
        //Nova finestra
        String result = controller.addToWishList(controller.getSessionMemory().getNomSerie(), controller.getSessionMemory().getCorreuPersona());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Èxit");
        alert.setHeaderText("Èxit");
        alert.setContentText(result);
        alert.showAndWait();
        updater.notify(TIPUS_ACTUALIZADOR);
    }

    public void onBtntemporadesViewAddClick() throws Exception {
        //Nova finestra
        Escena escena = EscenaFactory.INSTANCE.creaEscena("temporadesDetalls-view", "Temporades: "+this.nom_contingut_audiovisual);
        EscenaTemporadesDetalls escenaTemporadesDetalls = ((EscenaTemporadesDetalls)escena);
        escenaTemporadesDetalls.setController(controller);
        escenaTemporadesDetalls.start();
    }
}
