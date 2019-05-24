package controles.controlesVistasFX;

import accesoDatos.DAOVoluntarios;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import objetosNegocio.Voluntario;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControlPaseLista implements Initializable {

    @FXML
    JFXButton botonCancelar;

    @FXML
    JFXButton botonAsistencia;
    @FXML
    JFXButton botonRegistrarAdopcion;
    @FXML
    JFXButton botonRegistrarVoluntario;
    @FXML
    JFXButton botonRegistrarAdoptante;
    @FXML
    JFXButton botonRegistrarRescate;
    @FXML
    JFXButton botonConfirmarPaseLista;



    @FXML
    JFXListView listaVoluntarios;
    DAOVoluntarios dv = new DAOVoluntarios();
    ObservableList<Voluntario> voluntarios = FXCollections.observableArrayList(dv.transformarQuerySet());










    class Cell extends ListCell<Voluntario> {

        HBox hbox = new HBox();
        CheckBox checkBox = new CheckBox();
        Label label = new Label();
        Pane pane = new Pane();

        public Cell() {
            super();
            hbox.getChildren().addAll(label, pane, checkBox);
            hbox.setHgrow(pane, Priority.ALWAYS);

        }


        public void updateItem(Voluntario voluntario, boolean empty) {
            super.updateItem(voluntario, empty);
            setText(null);
            setGraphic(null);
            if (voluntario!= null && !empty) {
                label.setText(voluntario.getNombre());
                setGraphic(hbox);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaVoluntarios.setItems(voluntarios);
        listaVoluntarios.setCellFactory(param -> new Cell());



    }

    public void handlerVentanaRegistrarRescate(Event event) throws IOException {

        Parent homePageParent = FXMLLoader.load(getClass().getResource("/vista/registro_mascota/vistaRegistroMascota.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePageScene);
        app_stage.show();


    }

    public void handlerVentanaRegistrarAdopcion(Event event) throws IOException {

        Parent homePageParent = FXMLLoader.load(getClass().getResource("/vista/adopcion/vistaAdopcion.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePageScene);
        app_stage.show();


    }

    public void handlerBotonCancelar(){
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();

    }
}
