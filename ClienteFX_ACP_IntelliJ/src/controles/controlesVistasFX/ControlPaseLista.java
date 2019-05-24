package controles.controlesVistasFX;

import accesoDatos.DAOEventos;
import accesoDatos.DAOVoluntarios;
import accesoDatos.Utilidades;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import manajador_ventanas.WindowsControlador;
import objetosNegocio.Evento;
import objetosNegocio.Voluntario;
import utilities.AlertBox;
import vista.pase_lista.VoluntarioT;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;


public class ControlPaseLista implements Initializable {

    WindowsControlador wc = new WindowsControlador();
    @FXML
    JFXButton botonCancelar;

    @FXML
    Label labelBienvenida;

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
    JFXTextField campoNombreEvento;

    @FXML
    JFXTextField campoDescripcion;

    @FXML
    JFXTextField campoHoras;

    @FXML
    JFXDatePicker fecha;

    @FXML
    TableView tablaVoluntarios;

    @FXML
    TableColumn nombre;
    @FXML
    TableColumn horas;
    @FXML
    TableColumn checkboxC;


    DAOVoluntarios dv = new DAOVoluntarios();
    ObservableList<Voluntario> voluntarios;


    DAOEventos de = new DAOEventos();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXCollections.observableArrayList(dv.transformarQuerySet());
        nombre = new TableColumn<Voluntario,String>("Nombre Completo");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        nombre.setResizable(false);
        nombre.setPrefWidth(390);

        horas = new TableColumn<Voluntario,String>("Horas Acumuladas");
        horas.setCellValueFactory(new PropertyValueFactory<>("horasAcumuladas"));
        horas.setResizable(false);
        horas.setPrefWidth(114);

        checkboxC = new TableColumn<CheckBox,String>("CheckBox");
        checkboxC.setCellValueFactory(new PropertyValueFactory<>("checkbox"));
        checkboxC.setResizable(false);
        checkboxC.setPrefWidth(105);

        tablaVoluntarios.setItems(conversionV());
        tablaVoluntarios.getColumns().addAll(nombre,horas,checkboxC);



    }

    public ObservableList<VoluntarioT> conversionV(){
        ArrayList<VoluntarioT> voluntariosT = new ArrayList();

        for (Voluntario voluntario:
             voluntarios) {



            voluntariosT.add(
                    new VoluntarioT(
                    voluntario.getNombre(),
                    voluntario.getTelefono(),
                    voluntario.getDireccion(),
                    voluntario.getIdVoluntario(),
                    voluntario.getHorasAcumuladas()

                    ));


        }
        return FXCollections.observableArrayList(voluntariosT);
    }



    public void handlerRestriccion_NombreEvento(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoNombreEvento.getText().length() > 15 ){
            event.consume();
        }

    }
    public void handlerRestriccion_Descripcion(KeyEvent event){
        String regularExpression = "^[a-zA-Z ]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoDescripcion.getText().length() > 30 ){
            event.consume();
        }

    }

    public void handlerRestriccion_Hora(KeyEvent event){
        String regularExpression = "^[0-9]*$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoHoras.getText().length() > 15 ){
            event.consume();
        }

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

    public void handlerConfirmarAsistencia(){

        if(campoNombreEvento.getText().isEmpty()){
            AlertBox.display("Por favor escribe un nombre para el evento","Nombre vacio","/resources/Icons/warning.png");
            return;
        }
        if(campoDescripcion.getText().isEmpty()){
            AlertBox.display("Por favor escribe una descripción","Descripción vacía","/resources/Icons/warning.png");
            return;
        }

        if(campoHoras.getText().isEmpty()){
            AlertBox.display("Por favor escribe las horas a otorgar","Horas vacías","/resources/Icons/warning.png");
            return;
        }


        String f = fecha.getValue()!= null ? fecha.getValue().toString() : "";

        if(f.equals("")){
            AlertBox.display("Por favor, selecciona una fecha","Fecha vacia","/resources/Icons/warning.png");

            return;
        }

        ArrayList<Voluntario> asisten = new ArrayList();


        for ( Object v: tablaVoluntarios.getItems()) {
            VoluntarioT ve = (VoluntarioT) v;
            System.out.println(ve.getNombre());
            asisten.add(
                    dv.queryGetVoluntarioPorID(ve.getIdVoluntario())
            );
        }

        GregorianCalendar fechaGregorian = Utilidades.convertirFecha_StringToGregorian(f);

        dv.queryAnadirHorasVoluntarios(asisten,Integer.valueOf(campoHoras.getText()));
        de.anadirEvento(new Evento(campoNombreEvento.getText(),fechaGregorian));


        AlertBox.display("Las horas han sido añadidas!","Horas registradas","/resources/Icons/boy.png");
        actualizar();
        return;

    }

    private void actualizar(){
        FXCollections.observableArrayList(dv.transformarQuerySet());
        campoHoras.setText("");
        campoDescripcion.setText("");
        campoNombreEvento.setText("");


    }

    public void handlerFecha(){
        LocalDate today = LocalDate.now();
        System.out.println(today.toString());
        LocalDate theDay = today.minusDays(7);
        LocalDate startingPoint = LocalDate.of(theDay.getYear(),theDay.getMonth(),(theDay.getDayOfMonth()));
        LocalDate offPoint = LocalDate.of(today.getYear(),today.getMonth(),(today.getDayOfMonth()));
        fecha.setEditable(false);






        restrictDate(fecha,startingPoint,offPoint);
    }

    private static void restrictDate(JFXDatePicker date, LocalDate min, LocalDate max){
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell(){
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(min)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }else if (item.isAfter(max)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        date.setDayCellFactory(dayCellFactory);
    }

}
