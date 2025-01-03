package ub.edu.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import ub.edu.model.ValorType;
import ub.edu.model.cataleg.ContingutType;

import java.util.Objects;

public class EscenaValorarObra extends Escena {
    private static final String TIPUS_ACTUALIZADOR = "top10";

    public RadioButton radioButton_Group1_Like;
    public RadioButton radioButton_Group1_Dislike;
    public TextField textField_Group2;
    public RadioButton radioButton_Group3_Text1;
    public RadioButton radioButton_Group3_Text2;
    public RadioButton radioButton_Group3_Text3;
    public RadioButton radioButton_Group3_Text4;
    public RadioButton radioButton_Group3_Text5;

    public RadioButton radioButton_G1_Text1;
    public RadioButton radioButton_G2_Text2;
    public RadioButton radioButton_G3_Text3;

    public Button button_valorar;
    public Button button_cancel;

    private String correu_persona;

    private String tipus_obra_audiovisual;
    private String nom_obra_audiovisual;
    private String nom_serie;
    private int temporada;
    private int episodi;
    public UpdaterManager updater;


    public void start(){
        this.correu_persona=this.controller.getSessionMemory().getCorreuPersona();
        this.tipus_obra_audiovisual = this.controller.getSessionMemory().getTipusObra();
        this.nom_obra_audiovisual =this.controller.getSessionMemory().getNomObra();
        if(tipus_obra_audiovisual.equals("Episodi")){
            this.nom_serie = this.controller.getSessionMemory().getNomSerie();
            this.temporada = this.controller.getSessionMemory().getNumTemporada();
            this.episodi = this.controller.getSessionMemory().getNumEpisodi();
        }

        initialize_RB();
        updater = new UpdaterManager(TIPUS_ACTUALIZADOR);
    }

    @FXML
    private void initialize_RB() {
        ToggleGroup group1 = new ToggleGroup();
        radioButton_Group1_Like.setToggleGroup(group1);
        radioButton_Group1_Dislike.setToggleGroup(group1);

        ToggleGroup group3= new ToggleGroup();
        radioButton_Group3_Text1.setToggleGroup(group3);
        radioButton_Group3_Text2.setToggleGroup(group3);
        radioButton_Group3_Text3.setToggleGroup(group3);
        radioButton_Group3_Text4.setToggleGroup(group3);
        radioButton_Group3_Text5.setToggleGroup(group3);

        ToggleGroup group= new ToggleGroup();
        radioButton_G1_Text1.setToggleGroup(group);
        radioButton_G2_Text2.setToggleGroup(group);
        radioButton_G3_Text3.setToggleGroup(group);

        initDisabled();
        initObservers();

    }

    public void initDisabled(){
        radioButton_Group1_Like.setDisable(true);
        radioButton_Group1_Dislike.setDisable(true);

        textField_Group2.setDisable(true);

        radioButton_Group3_Text1.setDisable(true);
        radioButton_Group3_Text2.setDisable(true);
        radioButton_Group3_Text3.setDisable(true);
        radioButton_Group3_Text4.setDisable(true);
        radioButton_Group3_Text5.setDisable(true);

        radioButton_G1_Text1.setDisable(false);
        radioButton_G2_Text2.setDisable(false);
        radioButton_G3_Text3.setDisable(false);

    }

    public void initObservers(){
        radioButton_G1_Text1.setOnAction(actionEvent -> {
            radioButton_Group1_Like.setDisable(false);
            radioButton_Group1_Dislike.setDisable(false);

            textField_Group2.setDisable(true);

            radioButton_Group3_Text1.setDisable(true);
            radioButton_Group3_Text2.setDisable(true);
            radioButton_Group3_Text3.setDisable(true);
            radioButton_Group3_Text4.setDisable(true);
            radioButton_Group3_Text5.setDisable(true);
        });

        radioButton_G2_Text2.setOnAction(actionEvent -> {
            radioButton_Group1_Like.setDisable(true);
            radioButton_Group1_Dislike.setDisable(true);

            textField_Group2.setDisable(false);

            radioButton_Group3_Text1.setDisable(true);
            radioButton_Group3_Text2.setDisable(true);
            radioButton_Group3_Text3.setDisable(true);
            radioButton_Group3_Text4.setDisable(true);
            radioButton_Group3_Text5.setDisable(true);
        });

        radioButton_G3_Text3.setOnAction(actionEvent -> {
            radioButton_Group1_Like.setDisable(true);
            radioButton_Group1_Dislike.setDisable(true);

            textField_Group2.setDisable(true);

            radioButton_Group3_Text1.setDisable(false);
            radioButton_Group3_Text2.setDisable(false);
            radioButton_Group3_Text3.setDisable(false);
            radioButton_Group3_Text4.setDisable(false);
            radioButton_Group3_Text5.setDisable(false);
        });
    }

    public void onButtonValorarClick() {
        //enviar la valoracion
        //TODO:
        // La idea es: guardar la valoracion en el modelo y actualizar la vista en caso necesario
        // Para ello:
        // 1-Recoger los datos seleccionados de la vista (cómo se recogen? ver codigo ejemplo de más abajo)
        // 2-Conectar con el controller pasandole los parametros necesarios para que
        // el controller el modelo (y opcionalmente se ejecute el metodo add de los respectivos DAO_TipusVot_DB + DAO_Vot_DB)
        // 3- Refrescar la vista si es necesario

        // Aqui tienes un código de ejemplo para ver cómo recoger el valor de un radio button
        ValorType typeValorar = null;
        String valor = "";

        if (radioButton_G1_Text1.isSelected()) {
            typeValorar = ValorType.VALORAR_PER_LIKES;
            if (radioButton_Group1_Like.isSelected()) {
                valor = radioButton_Group1_Like.getText();
            } else if (radioButton_Group1_Dislike.isSelected()) {
                valor = radioButton_Group1_Dislike.getText();
            }
        } else if (radioButton_G2_Text2.isSelected()) {
            typeValorar = ValorType.VALORAR_PER_PUNTS;
            valor = textField_Group2.getText();
        } else if (radioButton_G3_Text3.isSelected()) {
            typeValorar = ValorType.VALORAR_PER_ESTRELLES;
            if (radioButton_Group3_Text1.isSelected()) {
                valor = radioButton_Group3_Text1.getText();
            } else if (radioButton_Group3_Text2.isSelected()) {
                valor = radioButton_Group3_Text2.getText();
            } else if (radioButton_Group3_Text3.isSelected()) {
                valor = radioButton_Group3_Text3.getText();
            } else if (radioButton_Group3_Text4.isSelected()) {
                valor = radioButton_Group3_Text4.getText();
            } else if (radioButton_Group3_Text5.isSelected()) {
                valor = radioButton_Group3_Text5.getText();
            }
        }

        //TODO Pràctica 4: Afegir comprobacions als valors
        switch (Objects.requireNonNull(typeValorar)) {
            case VALORAR_PER_PUNTS -> {
                if (valor.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Valoració no vàlida");
                    alert.setContentText("El camp de valoració no pot estar buit");
                    alert.showAndWait();
                    return;
                }
                try {
                    int valoracio = Integer.parseInt(valor);
                    if (valoracio < 1 || valoracio > 10) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Valoració no vàlida");
                        alert.setContentText("El camp de valoració ha de ser un valor entre 1 i 10");
                        alert.showAndWait();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Valoració no vàlida");
                    alert.setContentText("El camp de valoració ha de ser un valor numèric enter");
                    alert.showAndWait();
                    return;
                }
            }
            case VALORAR_PER_ESTRELLES, VALORAR_PER_LIKES -> {
                if (valor.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Valoració no vàlida");
                    alert.setContentText("El camp de valoració no pot estar buit");
                    alert.showAndWait();
                    return;
                }
            }
        }
        //TODO Pràctica 4: Fer efectiva la valoració d'una pel.licula via crida al controlador
        if(tipus_obra_audiovisual.equals("Episodi")){
            if(controller.valorarContingut(nom_serie, temporada, episodi, correu_persona, typeValorar, valor)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Valoració");
                alert.setHeaderText("Valoració realitzada");
                alert.setContentText("La valoració s'ha realitzat correctament");
                alert.showAndWait();
                stage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Valoració no realitzada");
                alert.setContentText("La valoració no s'ha realitzat correctament");
                alert.showAndWait();
            }
        }else if(tipus_obra_audiovisual.equals("Pelicula")){
            if(controller.valorarContingut(nom_obra_audiovisual, correu_persona, typeValorar, valor)){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Valoració");
                alert.setHeaderText("Valoració realitzada");
                alert.setContentText("La valoració s'ha realitzat correctament");
                alert.showAndWait();
                stage.close();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Valoració no realitzada");
                alert.setContentText("La valoració no s'ha realitzat correctament");
                alert.showAndWait();
            }
        }

        controller.top10(ContingutType.Pelicula, ValorType.VALORAR_PER_PUNTS, ValorType.VALORAR_PER_PROMIG);
        updater.notify(TIPUS_ACTUALIZADOR);
    }
    public void onButtonCancelarClick(){
        //enviar la valoracion
        System.out.println("Entro en cancelar una valoracion");
        stage.close();
    }
}