package ub.edu.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import ub.edu.model.StatusType;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EscenaRegister extends Escena {

    public Button registre_btn;
    public Button cancellar_btn;
    public TextField registre_correu;
    public PasswordField registre_pwd;
    public PasswordField registre_pwd_repeat;
    public TextField registre_nom;
    public TextField registre_cognoms;
    public TextField registre_dni;
    public ComboBox<ComboBoxItemWrap<HashMap<Object, Object>>> comboBox_register_comunitat;

    public void start() {
        populateComunitats();
    }

    public void populateComunitats(){

        List<HashMap<Object, Object>> comunitats = controller.getAllComunitatsPerNom();
        if (comunitats == null) {
            return;
        }

        ObservableList<ComboBoxItemWrap<HashMap<Object, Object>>> options = FXCollections.observableArrayList();
        for (HashMap<Object, Object> comunitat : comunitats) {
            Object label = comunitat.get("nom");
            if (label != null) {
                options.add(new ComboBoxItemWrap<>(comunitat, false)); // false para que inicialmente no estén seleccionados
            }
        }

        comboBox_register_comunitat.setItems(options);
        comboBox_register_comunitat.setCellFactory(c -> {
            ListCell<ComboBoxItemWrap<HashMap<Object, Object>>> cell = new ListCell<>() {
                @Override
                protected void updateItem(ComboBoxItemWrap<HashMap<Object, Object>> item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty && item != null) {
                        final CheckBox cb = new CheckBox((String) item.getItem().get("nom"));
                        cb.selectedProperty().bindBidirectional(item.checkProperty());
                        setGraphic(cb);
                    } else {
                        setGraphic(null);
                    }
                }
            };

            cell.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
                cell.getItem().checkProperty().set(!cell.getItem().checkProperty().get());
                StringBuilder sb = new StringBuilder();
                comboBox_register_comunitat.getItems().filtered(f-> f!=null).filtered(f-> f.getCheck()).forEach(p -> {
                    sb.append("; "+p.getItem().get("nom"));
                });
                final String string = sb.toString();
                comboBox_register_comunitat.setPromptText(string.substring(Integer.min(2, string.length())));
            });

            return cell;
        });

    }


    @FXML
    public void onRegistreButtonClick() throws Exception {
        String correu = registre_correu.getText();
        String nom = registre_nom.getText();
        String cognoms = registre_cognoms.getText();
        String dni = registre_dni.getText();
        String pwd = registre_pwd.getText();
        String pwd_repeat = registre_pwd_repeat.getText();

        if (correu==null || correu.equals("") || pwd==null || pwd.equals("") || !pwd.equals(pwd_repeat)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error en el registre");
            alert.setContentText("El correu y la contraseña son obligatories. Les contrasenyes deben coincidir");
            alert.showAndWait();
            return;
        }

        List<HashMap<Object, Object>> selectedComunities = comboBox_register_comunitat.getItems().stream()
                .filter(ComboBoxItemWrap::getCheck)
                .map(ComboBoxItemWrap::getItem)
                .collect(Collectors.toList());

        // Comprobar el número de comunidades seleccionadas
        if (selectedComunities.size() < 1 || selectedComunities.size() > 5) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Selección de Comunidad Incorrecta");
            alert.setHeaderText("Error en la selección de comunidades");
            alert.setContentText("Debe seleccionar al menos una comunidad y no más de cinco.");
            alert.showAndWait();
            return;
        }

        //PAS2- Crear una nueva Persona
        //NEW StatusType resultat = controller.addNewClient(correu, nom, cognoms, dni, pwd, selectedComunities);

        String resultat = controller.addNewPersona(correu, nom, cognoms, dni, pwd);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        if (resultat.equals(StatusType.PERSONA_DUPLICADA.toString()) ||
                resultat.equals(StatusType.FORMAT_INCORRECTE.toString()) ||
                resultat.equals(StatusType.FORMAT_INCORRECTE_CORREU_PWD.toString())){
            //Problema en el registre:
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error en el registre");
            alert.setContentText(resultat.toString());
        } else {
            //Registre amb èxit:
            //PAS3- crear relacio comunitats amb el client
            for(HashMap<Object, Object> comunity : selectedComunities){
                controller.addRelacioComunitatClient(correu,(String) comunity.get("nom"));
            }

            alert.setTitle("Registre exitòs");
            alert.setHeaderText("Registre exitòs");
            alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                @Override
                public void handle(DialogEvent dialogEvent) {
                    //System.out.println(alert.getResult());
                    String resu_ButtonData = String.valueOf(alert.getResult().getButtonData());
                    if(resu_ButtonData.equals("OK_DONE")){
                        //boton aceptar
                        event_goLogin(correu);

                    }else{
                        //boton cancelar
                        //no hacer nada, nos quedamos donde ya estamos
                    }
                }
            });
        }
        alert.showAndWait();
    }

    private void event_goLogin (String correu){
        try {
            Escena login = EscenaFactory.INSTANCE.creaEscena("login-view", "imUB Login View");
            EscenaLogin escenaLogin = ((EscenaLogin) login);
            login.setController(controller);
            this.controller.getSessionMemory().setCorreuPersona(correu);
            escenaLogin.start();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTornarLoginButtonClick(){
        event_goLogin(null);
    }

}