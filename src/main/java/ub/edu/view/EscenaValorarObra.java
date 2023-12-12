package ub.edu.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import ub.edu.controller.SessionMemory;

public class EscenaValorarObra extends Escena {

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
    private String nom_obra_audiovisual;


    public void start(){
        this.correu_persona=this.controller.getSessionMemory().getCorreuPersona();
        this.nom_obra_audiovisual =this.controller.getSessionMemory().getNomObra();

        initialize_RB();
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
        radioButton_G1_Text1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                radioButton_Group1_Like.setDisable(false);
                radioButton_Group1_Dislike.setDisable(false);

                textField_Group2.setDisable(true);

                radioButton_Group3_Text1.setDisable(true);
                radioButton_Group3_Text2.setDisable(true);
                radioButton_Group3_Text3.setDisable(true);
                radioButton_Group3_Text4.setDisable(true);
                radioButton_Group3_Text5.setDisable(true);
            }
        });

        radioButton_G2_Text2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                radioButton_Group1_Like.setDisable(true);
                radioButton_Group1_Dislike.setDisable(true);

                textField_Group2.setDisable(false);

                radioButton_Group3_Text1.setDisable(true);
                radioButton_Group3_Text2.setDisable(true);
                radioButton_Group3_Text3.setDisable(true);
                radioButton_Group3_Text4.setDisable(true);
                radioButton_Group3_Text5.setDisable(true);
            }
        });

        radioButton_G3_Text3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                radioButton_Group1_Like.setDisable(true);
                radioButton_Group1_Dislike.setDisable(true);

                textField_Group2.setDisable(true);

                radioButton_Group3_Text1.setDisable(false);
                radioButton_Group3_Text2.setDisable(false);
                radioButton_Group3_Text3.setDisable(false);
                radioButton_Group3_Text4.setDisable(false);
                radioButton_Group3_Text5.setDisable(false);
            }
        });
    }

    public void onButtonValorarClick(){
        //enviar la valoracion
        //TODO:
        // La idea es: guardar la valoracion en el modelo y actualizar la vista en caso necesario
        // Para ello:
        // 1-Recoger los datos seleccionados de la vista (cómo se recogen? ver codigo ejemplo de más abajo)
        // 2-Conectar con el controller pasandole los parametros necesarios para que
        // el controller el modelo (y opcionalmente se ejecute el metodo add de los respectivos DAO_TipusVot_DB + DAO_Vot_DB)
        // 3- Refrescar la vista si es necesario

        // Aqui tienes un código de ejemplo para ver cómo recoger el valor de un radio button
        String typeValorar = "";
        String valor = "";

        if (radioButton_G1_Text1.isSelected()) {
            typeValorar = "ValorLikes";
            if (radioButton_Group1_Like.isSelected()) {
                valor = radioButton_Group1_Like.getText();
            } else if (radioButton_Group1_Dislike.isSelected()) {
                valor = radioButton_Group1_Dislike.getText();
            }
        } else if (radioButton_G2_Text2.isSelected()) {
            typeValorar = "ValorPunts";
            valor = textField_Group2.getText();
        } else if (radioButton_G3_Text3.isSelected()) {
            typeValorar = "ValorEstrelles";
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

        System.out.println("EscenaValorarObra:onButtonValorarClick ->  Valoració de tipus: "+ typeValorar+ " és: "+ valor);
        //TODO Pràctica 4: Afegir comprobacions als valors

        //TODO Pràctica 4: Fer efectiva la valoració d'una pel.licula via crida al controlador

        controller.valorarContingut(nom_obra_audiovisual, correu_persona, typeValorar, valor);

        stage.close();
    }

    public void onButtonCancelarClick(){
        //enviar la valoracion
        System.out.println("Entro en cancelar una valoracion");
        stage.close();
    }


}