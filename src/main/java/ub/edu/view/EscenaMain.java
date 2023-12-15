package ub.edu.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class EscenaMain extends Escena  {
    private static final double ESPAI_ENTRE_BOTONS = 30;

    public Button obra_audiovisual_btn;
    public Button button_izq_resetFiltres_main;
    //public Button button_der_resetFiltres_main;
    public AnchorPane contingut_audiovisual_pane;
    public TableView tableTop10Valorades;
    public TableColumn nomColumn;
    public TableColumn valueColumn;
    public TableView tableWishList;
    public TableColumn nomColumnWishList;
    public Text textPrincipal;
    public ComboBox comboBox_main_comunitat;
    public ImageView image_main;

    public CheckBox checkBoxPunts;
    public ComboBox comboBox_TipusTop10;

    public Button button_punts_main;
    public Button button_estrelles_main;
    public Button button_likes_main;
    @FXML
    public ComboBox comboBox_main_tipus;
    public ComboBox comboBox_main_tematica;


    public void start() throws Exception {
        String correu = controller.getSessionMemory().getCorreuPersona();
        initViewMemory();
        initStartMain();
        assignarTextPrincipal_Correu_i_Comunitat(correu);
        asignarimagen();
        popularComboBoxTipus(); //success
        popularComboBoxTematiques(); //success
        popularObresAudiovisualsPerNom();
        // TODO: Repensar on es cridaran aquests mètodes
        popularTopDeuValorades();
        popularWishList();
    }

    public void reload() {
        popularTopDeuValorades();
        popularWishList();
    }

    public void initStartMain(){
        comboBox_TipusTop10.setValue("Pelicula");
        checkBoxPunts.setSelected(true);
    }

    public void initViewMemory() {
        controller.getViewMemory().setMainScene(this);
        controller.getViewMemory().setFilterTypeValue("ValorPunts");
        controller.getViewMemory().setFilterStrategyValue("ValoracioStrategyPromig");
        controller.getViewMemory().setFilterTypeObraAudioVisual("ALL");
        controller.getViewMemory().setFilterTypeTop("Pelicula");
    }

    public void assignarTextPrincipal_Correu_i_Comunitat(String correuPersona) throws Exception {
        //Paso1. Teniendo el correo de la persona, buscar el id del grupo.
        List<HashMap<Object,Object>> resuListGrupHashMap= controller.getAllComunitatsPerPersona(correuPersona);

        //Paso2. Recoger el grupo por su id.
        String firstnameComunitat=null;

        comboBox_main_comunitat.getItems().add(0,"Selecciona la Comunitat");
        if(!resuListGrupHashMap.isEmpty()){
            for (HashMap<Object,Object> grup:resuListGrupHashMap){
                comboBox_main_comunitat.getItems().add(grup.get("nom"));
            }
            firstnameComunitat="Selecciona la Comunitat";
        }else{
            firstnameComunitat="Comunitats Buida";
            comboBox_main_comunitat.setDisable(true);
        }

        controller.getSessionMemory().setNomComunitat(firstnameComunitat);
        comboBox_main_comunitat.setPromptText(firstnameComunitat);
        textPrincipal.setText("IMUB: "+correuPersona);
        this.observable_comboBox_main_comunitat();
    }

    public void asignarimagen() throws FileNotFoundException {
        FileInputStream input = new FileInputStream("./src/main/view-resources/ub/edu/static-resources/logo8.png");
        Image image = new Image(input);
        image_main.setImage(image);
    }


    public static class DataWish {
        //Cal deixar aquests atributs com finals per poder popular la taula
        private final SimpleStringProperty nom;
        DataWish(String nom_param){
            this.nom = new SimpleStringProperty(nom_param);
        }
        //métodos getters
        public String getNom() {
            return nom.get();
        }
    }

    private void popularWishList() {
        nomColumnWishList.setCellValueFactory(new PropertyValueFactory<DataWish, String>("nom"));

        List<HashMap<Object, Object>> listaObres = controller.getWishList(this.controller.getSessionMemory().getCorreuPersona());

        // Borrar todos los elementos existentes en la TableView
        tableWishList.getItems().clear();

        // Agregar los elementos de la lista de nombres a la TableView
        for (HashMap<Object, Object> listaObre : listaObres) {
            // Obtener el nombre (u otro valor) de la obra de la lista de HashMaps
            String nom = (String) listaObre.get("nom");

            // Agregar la cadena directamente a la TableView
            tableWishList.getItems().add(new DataWish(nom));
        }
    }

    public static class DataTop {
        //Cal deixar aquests atributs com finals per poder popular la taula quan el mètode  la cridi
        private final SimpleStringProperty nom;
        private final SimpleStringProperty value;
        DataTop(String nom_param, Integer value_param){
            this.nom = new SimpleStringProperty(nom_param);
            this.value = new SimpleStringProperty(value_param.toString());
        }
        //métodos getters
        public String getNom() {
            return nom.get();
        }
        public String getValue() {
            return value.get();
        }
    }
    private void popularTopDeuValorades() {
        //Borrar todos los elementos existentes en la TableView antes de popularla de nuevo con los nuevos datos.
        tableTop10Valorades.getItems().clear();

        //La clase interna DataTop está justo arriba de esta función.
        nomColumn.setCellValueFactory(new PropertyValueFactory<DataTop, String>("nom"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<DataTop, String>("value"));


        // TODO Pràctica 4 - inicialment s'omplen els top10 per valoració mitjana punts i pel·licules
        String tipusContingut = controller.getViewMemory().getFilterTypeTop();
        String FilterType = controller.getViewMemory().getFilterTypeValue();
        String FilterStrategy = controller.getViewMemory().getFilterStrategyValue();

        if(FilterType.equals("ValorPunts")) {
            button_punts_main.setStyle("-fx-background-color: #ff0000");
            button_estrelles_main.setStyle("-fx-background-color: #ffffff");
            button_likes_main.setStyle("-fx-background-color: #ffffff");
        }else if(FilterType.equals("ValorEstrelles")) {
            button_punts_main.setStyle("-fx-background-color: #ffffff");
            button_estrelles_main.setStyle("-fx-background-color: #3bb143");
            button_likes_main.setStyle("-fx-background-color: #ffffff");
        }else{
            button_punts_main.setStyle("-fx-background-color: #ffffff");
            button_estrelles_main.setStyle("-fx-background-color: #ffffff");
            button_likes_main.setStyle("-fx-background-color: #006fb9");
        }
        List<HashMap<String, String>> listaObres;
        if(FilterType.equals("ValorPunts")) {
            listaObres = controller.top10(tipusContingut, FilterType, FilterStrategy);
        }else{
            listaObres = controller.top10(tipusContingut, FilterType, "ValoracioStrategyAbsolut");
        }


        for (HashMap<String, String> obra : listaObres) {
            String nom = obra.get("nom");
            float valor = Float.parseFloat(obra.get("valor"));
            tableTop10Valorades.getItems().add(
                    new DataTop(nom, (int) valor));
        }

        // TODO opcional Pràctica 4: obrir la possibilitat de poder valorar per estrategia de ponderacio i per altres tipus de valoracions
        // fixa't en el valors dels buttons de la part dreta de la pantalla
    }

    private void popularObresAudiovisualsPerNom(){
        //Esborrem tots el continguts que hi havia al pane abans de popularlo
        contingut_audiovisual_pane.getChildren().clear();

        List<HashMap<Object,Object>> listaObres;
        Object str_default_ComboBox_tematica = comboBox_main_tematica.getPromptText();
        comboBox_main_tematica.setValue(str_default_ComboBox_tematica);
        if(controller.getViewMemory().getFilterTypeObraAudioVisual() == null){
            listaObres = controller.getAllContingutsDigitalsPerNom();
        }else if(controller.getViewMemory().getFilterTypeObraAudioVisual().equals("Pelicula")){
            listaObres = controller.getAllPelicules();
        }else if(controller.getViewMemory().getFilterTypeObraAudioVisual().equals("Serie")){
           listaObres = controller.getAllSeries();
        }else{
            listaObres = controller.getAllContingutsDigitalsPerNom();
        }


        System.out.println(listaObres.toString());
        if(listaObres == null || listaObres.isEmpty()){
            return;
        }
        List<Node> obresPaneChildren = contingut_audiovisual_pane.getChildren();

        double width = obra_audiovisual_btn.getWidth();
        double height = obra_audiovisual_btn.getHeight();
        double layoutX = obra_audiovisual_btn.getLayoutX();
        double layoutY = obra_audiovisual_btn.getLayoutY();
        //Instanciem un botó per cada obra audiovisual

        String text;
        Button new_btn;
        String nom;
        for (HashMap<Object,Object> obra:listaObres) {
            nom = (String) obra.get("nom");
            text = nom;
            new_btn = createObraAudiovisualButton(nom, text, width, height, layoutX, layoutY);
            obresPaneChildren.add(new_btn);
            layoutY += ESPAI_ENTRE_BOTONS;
        }
        //Actualitzem la mida del pane que conté els botons perque es pugui fer scroll cap abaix si hi ha més botons dels que caben al pane
        contingut_audiovisual_pane.setPrefHeight(layoutY);
        //Esborrem obra_audiovisual_btn, que l'utilitzavem únicament com a referència per la mida dels botons
        obresPaneChildren.remove(obra_audiovisual_btn);
    }

    /*
    * Crea un botó de dimensions width x height, colocat a la posició (layoutX, layoutY)
    * */
    private Button createObraAudiovisualButton(String nom_obra_audiovisual, String text, double width, double height, double layoutX, double layoutY){
        Button new_btn = new Button();
        new_btn.setPrefWidth(width);
        new_btn.setPrefHeight(height);
        new_btn.setText(text);
        new_btn.setLayoutX(layoutX);
        new_btn.setLayoutY(layoutY);
        new_btn.setAlignment(Pos.BASELINE_LEFT);
        //asignamos el evento del click que ejecutará mostrar la ventana con detalles de la obra audiovisual
        new_btn.setOnMouseClicked(event ->
        {
            if (event.getButton() == MouseButton.PRIMARY)
            {
                try {
                    mostrarFinestraObraAudiovisual(nom_obra_audiovisual);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return new_btn;
    }

    private void mostrarFinestraObraAudiovisual(String nom) throws Exception {
        if (this.controller.esPelicula(nom)){
            Escena escena = EscenaFactory.INSTANCE.creaEscena("pelliculaDetalls-view", "Detalls pellicula "+nom);

            EscenaPelliculaDetalls escenaPelliculaDetalls = ((EscenaPelliculaDetalls)escena);
            escenaPelliculaDetalls.setController(controller);
            this.controller.getSessionMemory().setNomPelicula(nom);
            escenaPelliculaDetalls.start();
        } else {
            Escena escena = EscenaFactory.INSTANCE.creaEscena("serieDetalls-view", "Detalls serie "+nom);
            EscenaSerieDetalls escenaSerieDetalls = ((EscenaSerieDetalls)escena);
            escenaSerieDetalls.setController(controller);
            this.controller.getSessionMemory().setNomSerie(nom);
            escenaSerieDetalls.start();
        }

    }

    public void observable_comboBox_main_comunitat(){
        comboBox_main_comunitat.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue classObject, String oldValue, String newValue) {
                controller.getSessionMemory().setNomComunitat(newValue);
            }
        });
    }

    public void popularComboBoxTipus() throws Exception {

        comboBox_main_tipus.getItems().add(0,"Selecciona el tipus");
        comboBox_main_tipus.getItems().add(1,"Pelicula");
        comboBox_main_tipus.getItems().add(2,"Serie");

        //añadir el listener del combobox
        comboBox_main_tipus.valueProperty().addListener(new ChangeListener<String>() {
            //OPCIÓN-1 -> asignar listener para que se ejecute cuando detecte el cambio
            @Override public void changed(ObservableValue classObject, String oldValue, String newValue) {
                System.out.println("Filtrar Per Tipus: "+newValue);
                //TODO: extensión de popular la lista de Peliculas
                switch (newValue) {
                    case "Pelicula":
                        controller.getViewMemory().setFilterTypeObraAudioVisual("Pelicula");
                        popularObresAudiovisualsPerNom();
                        break;
                    case "Serie":
                        controller.getViewMemory().setFilterTypeObraAudioVisual("Serie");
                        popularObresAudiovisualsPerNom();
                        break;
                    default:
                        controller.getViewMemory().setFilterTypeObraAudioVisual("ALL");
                        popularObresAudiovisualsPerNom();
                }
            }
        });

        comboBox_TipusTop10.getItems().add(0,"Selecciona el tipus");
        comboBox_TipusTop10.getItems().add(1,"Pelicula");
        comboBox_TipusTop10.getItems().add(2,"Serie");
        comboBox_TipusTop10.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty || item.isEmpty()) {
                    setText(null);
                } else {
                    setText(item);
                    setDisable(item.equals("Selecciona el tipus"));
                }
            }
        });
        comboBox_TipusTop10.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue classObject, String oldValue, String newValue) {
                System.out.println("Filtrar Per Tipus: "+newValue);
                //TODO: extensión de popular el Top10 por Top10 de Peliculas segons el tipus Pel·lícules o Sèries
                tableTop10Valorades.getItems().clear();

                String filterType = controller.getViewMemory().getFilterTypeValue();
                String filterStrategy = controller.getViewMemory().getFilterStrategyValue();
                String tipusContingut;
                if(newValue.equals("Pelicula")){
                    tipusContingut = "Pelicula";
                    controller.getViewMemory().setFilterTypeTop("Pelicula");
                }else if(newValue.equals("Serie")){
                    tipusContingut = "Serie";
                    controller.getViewMemory().setFilterTypeTop("Serie");
                }else{
                    tipusContingut = "Pelicula";
                    controller.getViewMemory().setFilterTypeTop("Pelicula");
                }
                List<HashMap<String, String>> listaObres;
                if(filterType.equals("ValorPunts")) {
                    listaObres = controller.top10(tipusContingut, filterType, filterStrategy);
                }else{
                    listaObres = controller.top10(tipusContingut, filterType, "ValoracioStrategyAbsolut");
                }

                for (HashMap<String, String> obra : listaObres) {
                    String nom = obra.get("nom");
                    float valor = Float.parseFloat(obra.get("valor"));
                    tableTop10Valorades.getItems().add(
                            new DataTop(nom, (int) valor));
                }
            }
        });
    }

    public void popularComboBoxTematiques() throws Exception {
        List<HashMap<Object,Object>> tematiques = controller.getAllTematiques();
        System.out.println("Tematiques: "+tematiques);
        String s = comboBox_main_tematica.getPromptText();
        comboBox_main_tematica.getItems().add(s);

        String nom=null;
        for (HashMap<Object,Object> tematica: tematiques) {
            if(tematica.get("nom")!=null){nom=(String) tematica.get("nom");}
            comboBox_main_tematica.getItems().add(nom);
        }

        //añadir el listener del combobox
        comboBox_main_tematica.valueProperty().addListener(new ChangeListener<String>() {
            //OPCIÓN-1 -> asignar listener para que se ejecute cuando detecte el cambio
            @Override public void changed(ObservableValue classObject, String oldValue, String newValue) {
                System.out.println("TODO: Filtrar continguts por temàtica: "+newValue);
                //TODO: extensión de popular la lista de continguts per temàtica
                //Esborrem tots el continguts que hi havia al pane
                contingut_audiovisual_pane.getChildren().clear();

                List<HashMap<Object,Object>> listaObres;
                if(newValue.equals("Filtrar per Temàtica")){
                    if(controller.getViewMemory().getFilterTypeObraAudioVisual().equals("Pelicula")) {
                        listaObres = controller.getAllPelicules();
                    }else if(controller.getViewMemory().getFilterTypeObraAudioVisual().equals("Serie")) {
                        listaObres = controller.getAllSeries();
                    }else {
                        listaObres = controller.getAllContingutsDigitalsPerNom();
                    }
                }else{
                    if(controller.getViewMemory().getFilterTypeObraAudioVisual().equals("Pelicula")) {
                        listaObres = controller.getAllPeliculesPerTematica(newValue);
                    }else if(controller.getViewMemory().getFilterTypeObraAudioVisual().equals("Serie")) {
                        listaObres = controller.getAllSeriesPerTematica(newValue);
                    }else{
                        listaObres = controller.getAllContingutsDigitalsPerTematica(newValue);
                    }
                }
                if(listaObres == null || listaObres.isEmpty()){
                    return;
                }
                List<Node> obresPaneChildren = contingut_audiovisual_pane.getChildren();

                double width = obra_audiovisual_btn.getWidth();
                double height = obra_audiovisual_btn.getHeight();
                double layoutX = obra_audiovisual_btn.getLayoutX();
                double layoutY = obra_audiovisual_btn.getLayoutY();
                //Instanciem un botó per cada obra audiovisual

                String text;
                Button new_btn;
                String nom;
                for (HashMap<Object,Object> obra:listaObres) {
                    nom = (String) obra.get("nom");
                    text = nom;
                    new_btn = createObraAudiovisualButton(nom, text, width, height, layoutX, layoutY);
                    obresPaneChildren.add(new_btn);
                    layoutY += ESPAI_ENTRE_BOTONS;
                }
                //Actualitzem la mida del pane que conté els botons perque es pugui fer scroll cap abaix si hi ha més botons dels que caben al pane
                contingut_audiovisual_pane.setPrefHeight(layoutY);
                //Esborrem excursio_btn, que l'utilitzavem únicament com a referència per la mida dels botons
                obresPaneChildren.remove(obra_audiovisual_btn);
            }
        });
    }
    public void onButtonIzqResetFiltresClick(){
        this.popularObresAudiovisualsPerNom();

        Object str_default_ComboBox_tipus = comboBox_main_tipus.getPromptText();
        comboBox_main_tipus.setValue(str_default_ComboBox_tipus);

        Object str_default_ComboBox_tematica = comboBox_main_tematica.getPromptText();
        comboBox_main_tematica.setValue(str_default_ComboBox_tematica);
    }

    public void onButtonPuntsClick(){
        controller.getSessionMemory().setTipusValoracio("ValorPunts");
        controller.getViewMemory().setFilterTypeValue("ValorPunts");
        checkBoxPunts.setDisable(false);

        popularTopDeuValorades();
    }
    public void onButtonEstrellesClick(){
        controller.getSessionMemory().setTipusValoracio("ValorEstrelles");
        controller.getViewMemory().setFilterTypeValue("ValorEstrelles");
        checkBoxPunts.setDisable(true);
        popularTopDeuValorades();
    }
    public void onButtonLikesClick() {
        controller.getSessionMemory().setTipusValoracio("ValorLikes");
        controller.getViewMemory().setFilterTypeValue("ValorLikes");
        checkBoxPunts.setDisable(true);
        popularTopDeuValorades();
    }
    public void onPonderadaCheckbox() {
        if (checkBoxPunts.isSelected()) {
            controller.getSessionMemory().setTipusStrategy("ValoracioStrategyPromig");
            controller.getViewMemory().setFilterStrategyValue("ValoracioStrategyPromig");
        } else {
            controller.getSessionMemory().setTipusStrategy("ValoracioStrategyAbsolut");
            controller.getViewMemory().setFilterStrategyValue("ValoracioStrategyAbsolut");
        }
        System.out.println("Strategy selected: " + controller.getSessionMemory().getTipusStrategy());
        popularTopDeuValorades();
    }
}
