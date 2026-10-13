package pe.edu.upeu.project_final.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pe.edu.upeu.project_final.modelo.Cliente;
import pe.edu.upeu.project_final.utils.UtilAlerts;


public class ClienteFormController {
    public static UtilAlerts utilAlerts = new UtilAlerts();

    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtNombre,txtApellidos,txtDni,txtEmail;
    @FXML
    private ComboBox<String> cbGenero;
    private Cliente cliente;
    private boolean saved = false;

    @FXML
    public void initialize(){
        cbGenero.getItems().addAll("M","F");
    }

    public boolean isSaved(){
        return saved;
    }
    public Cliente getCliente(){
        return cliente;
    }
    private boolean validate(){
        if (txtNombre.getText().isEmpty() && txtDni.getText().isEmpty()){
            Alert alert = utilAlerts.mostrarAlerts("",
                    "Nombre y DNI son obligatorios","ERROR");
            alert.show();
            return false;
        }
        return true;
    }
    private void cerrar(){
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }
    //actions of the views
    @FXML
    private void handleCancel(){
        cerrar();
    }
    @FXML
    private void handleSave(){
        if (validate()){
            if (cliente == null)
                cliente = new Cliente();
            cliente.setNombre(txtNombre.getText());
            cliente.setApellidos(txtApellidos.getText());
            cliente.setDni(txtDni.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setGenero(cbGenero.getValue());
            saved =true;
            cerrar();
        }
    }
    //metodo para enviar los datos al formulario y editar
    public void setCliente(Cliente c){
        this.cliente = c;
        if (c!=null){
            lblTitulo.setText("Editar Cliente");
            txtNombre.setText(c.getNombre());
            txtApellidos.setText(c.getApellidos());
            txtDni.setText(c.getDni());
            txtEmail.setText(c.getEmail());
            cbGenero.setValue(c.getGenero());
        }
    }


}
