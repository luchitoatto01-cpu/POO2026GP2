package pe.edu.upeu.project_final.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize(){
        showHome();
    }
    @FXML
    private  void showHome(){
        loadView("home-view.fxml");
    }
    @FXML
    private void showClient(){
        loadView("cliente-view.fxml");
    }
    @FXML
    private void showHabitacion(){
        loadView("habitacion-view.fxml");
    }

    //metodo que llama y busca a las vistas
    private void loadView(String fxml){
        try {
            FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("/pe/edu/upeu/project_final/views/"+fxml));
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            System.err.println("Error de carga de vista"+e.getMessage());
        }
    }

}
