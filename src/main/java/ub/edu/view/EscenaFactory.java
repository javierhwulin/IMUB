package ub.edu.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ub.edu.AppMain;

import java.io.IOException;

public enum EscenaFactory {
    INSTANCE;

    Escena creaEscena (String sceneName, String titol) throws IOException {
        Stage newStage = new Stage();
        //Posem titol a la finestra
        newStage.setTitle(titol);
        newStage.getIcons().add(new Image("/ub/edu/static-resources/main.jpg"));
        //Carreguem escena
        FXMLLoader fxmlLoader = new FXMLLoader(AppMain.class.getResource("/ub/edu/view/" + sceneName + ".fxml"));
        //Carreguem l'escena
        Scene newScene = new Scene(fxmlLoader.load());
        // getController retorna una instància del controlador de la finestra que
        // s'està carregant. NO es el controlador del Model-Vista-Controlador.
        Escena escena = fxmlLoader.getController();
        //Li enviem la finestra (stage) a la nova escena
        escena.setStage(newStage);
        //Enviem el css de l'escena només en cas que existeixi
        java.net.URL resource = AppMain.class.getResource("/ub/edu/view/" + sceneName + ".css");
        if (resource != null) newScene.getStylesheets().add(resource.toExternalForm());

        //Setegem l'escena en el stage
        newStage.setScene(newScene);

        //Quan tanquem la finestra main, es tanca l'aplicació.
        newStage.setOnCloseRequest(event -> {
            event.consume();

            // Mostrar una advertencia antes de cerrar
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sortir de l'aplicació");
            alert.setHeaderText("Estas segur que vols sortir de l'aplicació?");
            alert.setContentText("Pots perdre dades si no has guardat!");
            alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/ub/edu/static-resources/WARNing.jpg"));

            // Mostrar la alerta y manejar la respuesta del usuario
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.YES) {
                    Platform.exit();
                }
            });
        });
        //Mostrem la finestra
        newStage.show();
        return escena;
    }
}
