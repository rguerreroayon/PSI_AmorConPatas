package controles.controlesVistasFX;

import com.jfoenix.controls.*;
import controles.controlesDatos.ControlMaster;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.KeyEvent;
import utilities.alertbox.AlertBox;


import java.time.LocalDate;

public class ControlVista_RegistroMascota {

    //ControlMaster
    ControlMaster master = new ControlMaster();
    //Toggle
    final ToggleGroup radioBotones = new ToggleGroup();
    final ToggleGroup toggleRaza = new ToggleGroup();

    //AnchorPane Padre


    //Botones Frame
    @FXML
    JFXButton botonConfirmarRegistro;

    @FXML
    JFXButton botonCancelar;


    //Botones Menú
    @FXML
    JFXButton botonAsistencia;

    @FXML
    JFXButton botonRegistrarAdopcion;

    @FXML
    JFXButton botonRegistrarVoluntario;

    @FXML
    JFXButton botonRegistrarAdoptante;

    @FXML
    JFXButton botonOpcionesAdministrador;


    //Campos de texto
    @FXML
    JFXTextField campoNombre;

    @FXML
    JFXTextField campoRaza;

    @FXML
    JFXTextField campoEspecieOtro;

    //TextArea
    @FXML
    JFXTextArea campoDescripcion;

    //Radio Buttons
    @FXML
    JFXRadioButton radioPerro;

    @FXML
    JFXRadioButton radioGato;

    @FXML
    JFXRadioButton radioOtro;

    @FXML
    JFXRadioButton radioRaza;

    //Fecha API
    @FXML
    JFXDatePicker fecha;

    private void setRadioGroup(){
        radioOtro.setToggleGroup(radioBotones);
        radioGato.setToggleGroup(radioBotones);
        radioPerro.setToggleGroup(radioBotones);
        radioRaza.setToggleGroup(toggleRaza);
    }

    //Handler Papa
    @FXML
    public void initialize(){
        setRadioGroup();
        campoEspecieOtro.setDisable(true);
        campoRaza.setDisable(true);
    }


    //Handlers Restricciones caracteres
    public void handlerRestriccionCaracteres_nombre(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoNombre.getText().length() > 15 ){
            event.consume();
        }

    }

    public void handlerRestriccionCaracteres_otro(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoEspecieOtro.getText().length() > 15 ){
            event.consume();
        }

    }

    public void handlerRestriccionCaracteres_raza(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoRaza.getText().length() > 15 ){
            event.consume();
        }

    }

    public void handlerRestriccionCaracteres_descripcion(KeyEvent event){
        String regularExpression = "^[a-zA-Z ]+$";

        if(!event.getCharacter().matches(regularExpression)){
            event.consume();
        }

        if(campoDescripcion.getText().length() > 50){
            event.consume();
        }
    }

    //Verificacion para anadir

    public void handlerVerificacionRegistroMascota(){
        if(campoNombre.getText().isEmpty()){
            AlertBox.display("El campo de texto esta vacío", "Registro fallido", "resources/Icons/warning.png");
            return;
        }

        System.out.println(radioBotones.getSelectedToggle().toString());



    }


    //Handler Botones y RadioButtons
    public void handlerRadioButton_otro(){
        campoEspecieOtro.setDisable(false);

    }

    public void handlerRadioButton_gato(){
        campoEspecieOtro.setDisable(true);

    }

    public void handlerRadioButton_perro(){
        campoEspecieOtro.setDisable(true);

    }

    public void handlerRadioButton_raza(){
        campoRaza.setDisable(false);

    }


    public void handlerBotonCancelar(){
        botonCancelar.setText("It works!!");
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();

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

    public void handlerKeyTypedInFecha(){

        fecha.setEditable(false);

    }
}
