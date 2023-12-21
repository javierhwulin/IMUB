package ub.edu;

import javafx.application.Application;
import javafx.stage.Stage;
import ub.edu.controller.Controller;
import ub.edu.view.Vista;
import ub.edu.view.UpdaterManager;

import java.io.IOException;


public class AppMain extends Application{
    UpdaterManager updaterManager;
    @Override
    public void start(Stage stage) throws IOException {
        // Creacio de la vista, el model i el controlador
        //TODO: Fer els canvis que es creguin necessaris per a fer la creació
        // de controlador, model i vista: Qui crea a qui?
        //EL controlador crea el model i la vista. La vista i el model no crea res.
        Controller controller = Controller.getInstance();
        Vista vista = new Vista(stage, controller);
        updaterManager = new UpdaterManager("wishList", "top10");

    }
    public static void main(String[] args) {
        launch();
    }
}