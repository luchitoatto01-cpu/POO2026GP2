package pe.edu.upeu.project_final.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.edu.upeu.project_final.HelloApplication;
import pe.edu.upeu.project_final.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final.modelo.Habitacion;
import pe.edu.upeu.project_final.utils.UtilAlerts;

import java.util.Locale;
import java.util.Optional;

public class HabitacionController {

    @FXML
    private TableView<Habitacion> tableHabitacion;
    @FXML
    private TableColumn<Habitacion, Number> ColOrden;
    @FXML
    private TableColumn<Habitacion, Integer> ColId;
    @FXML
    private TableColumn<Habitacion, String> ColNumero;
    @FXML
    private TableColumn<Habitacion, Integer> ColPiso;
    @FXML
    private TableColumn<Habitacion, String> ColTipo;
    @FXML
    private TableColumn<Habitacion, Integer> ColCapacidad;
    @FXML
    private TableColumn<Habitacion, Double> ColPrecio;
    @FXML
    private TableColumn<Habitacion, String> ColEstado;

    private final IHabitacionDao habitacionDao = new HabitacionDaoImpl();
    private ObservableList<Habitacion> listaHabitaciones;

    @FXML
    public void initialize() {

        ColOrden.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(getIndex() + 1));
            }
        });

        ColId.setCellValueFactory(cd -> cd.getValue().idhabitacionProperty().asObject());
        ColNumero.setCellValueFactory(cd -> cd.getValue().numeroProperty());
        ColPiso.setCellValueFactory(cd -> cd.getValue().pisoProperty().asObject());
        ColTipo.setCellValueFactory(cd -> cd.getValue().tipoProperty());
        ColCapacidad.setCellValueFactory(cd -> cd.getValue().capacidadProperty().asObject());
        ColPrecio.setCellValueFactory(cd -> cd.getValue().precioNocheProperty().asObject());
        ColEstado.setCellValueFactory(cd -> cd.getValue().estadoProperty());

        // mostrar el precio con formato S/ 0.00
        ColPrecio.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : String.format(Locale.US, "S/ %.2f", item));
            }
        });

        cargarDatos();
    }

    private void cargarDatos() {
        listaHabitaciones = FXCollections.observableArrayList(habitacionDao.listAll());
        tableHabitacion.setItems(listaHabitaciones);
    }

    @FXML
    private void handleNew() {
        showForm(null);
    }

    @FXML
    private void handleEdit() {
        Habitacion seleccionada = tableHabitacion.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            UtilAlerts.mostrarAlerts("Selección requerida",
                    "Seleccione una habitación para editar", "WARNING").showAndWait();
            return;
        }
        Habitacion completa = habitacionDao.searchById(seleccionada.getIdhabitacion());
        showForm(completa);
    }

    @FXML
    private void handleDelete() {
        Habitacion seleccionada = tableHabitacion.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            UtilAlerts.mostrarAlerts("Selección requerida",
                    "Seleccione una habitación para eliminar", "WARNING").showAndWait();
            return;
        }
        Alert confirm = UtilAlerts.mostrarAlerts("Confirmar eliminación",
                "¿Está seguro de eliminar la habitación " + seleccionada.getNumero() + "?",
                "CONFIRMATION");
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (habitacionDao.delete(seleccionada)) {
                listaHabitaciones.remove(seleccionada);
            } else {
                UtilAlerts.mostrarAlerts("Error",
                        "No se pudo eliminar la habitación (puede tener reservas asociadas)",
                        "ERROR").showAndWait();
            }
        }
    }

    private void showForm(Habitacion h) {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class
                    .getResource("/pe/edu/upeu/project_final/views/habitacion-form.fxml"));
            Parent root = loader.load();
            HabitacionFormController controller = loader.getController();
            controller.setHabitacion(h); // null = nueva

            Stage stage = new Stage();
            stage.setTitle(h == null ? "Nueva Habitación" : "Editar Habitación");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(tableHabitacion.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (controller.isSaved()) {
                Habitacion procesada = controller.getHabitacion();
                boolean ok = (h == null) ? habitacionDao.insert(procesada)
                                         : habitacionDao.update(procesada);
                if (ok) {
                    UtilAlerts.mostrarAlerts("Éxito",
                            h == null ? "Habitación registrada" : "Habitación actualizada",
                            "CONFIRMATION").showAndWait();
                } else {
                    UtilAlerts.mostrarAlerts("Error",
                            "No se pudo guardar la habitación", "ERROR").showAndWait();
                }
                cargarDatos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
