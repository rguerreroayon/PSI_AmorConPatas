package controles.controlesVistasFX;

import accesoDatos.Utilidades;
import com.jfoenix.controls.*;
import controles.controlesDatos.ControlMaster;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.input.KeyEvent;
import objetosNegocio.Animal;
import utilities.AlertBox;


import java.io.IOException;
import java.time.LocalDate;
import java.util.GregorianCalendar;

public class ControlVista_RegistroMascota {

    //ControlMaster
    ControlMaster master ;
    //Toggle
    ToggleGroup radioBotones = new ToggleGroup();

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
    JFXRadioButton botonRadioPerro;

    @FXML
    JFXRadioButton botonRadioGato;

    @FXML
    JFXRadioButton botonRadioOtro;

    @FXML
    JFXRadioButton botonRadioRaza;

    //Fecha API
    @FXML
    JFXDatePicker fecha;


    //Handler Papa
    @FXML
    public void initialize(){
        botonRadioOtro.setToggleGroup(radioBotones);
        botonRadioGato.setToggleGroup(radioBotones);
        botonRadioPerro.setToggleGroup(radioBotones);
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

    public void handlerVentanaRegistrarAdopcion(Event event) throws  IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/vista/adopcion/vistaAdopcion.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePageScene);
        app_stage.show();
    }

    public void handlerRestriccionCaracteres_campoRaza(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoRaza.getText().length() > 15 ){
            event.consume();
        }
    }

    public void handlerPaseLista(ActionEvent event) throws IOException{
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/vista/pase_lista/vistaPaseLista.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePageScene);
        app_stage.show();
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

    //Handler Botones y RadioButtons
    public void handlerRadioButton_otro(){
        campoEspecieOtro.setDisable(false);

    }

    public void handlerRadioButton_gato(){
        campoEspecieOtro.setDisable(true);
        campoEspecieOtro.setText("");

    }

    public void handlerRadioButton_perro(){
        campoEspecieOtro.setDisable(true);
        campoEspecieOtro.setText("");

    }

    public void handlerRadioButton_raza(){

        if(botonRadioRaza.isSelected()){
            campoRaza.setDisable(false);
        }else{
            campoRaza.setText("");
            campoRaza.setDisable(true);
        }
    }


    public void handlerBotonCancelar(){
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

    private void limpiarPantalla(){
        campoNombre.setText("");
        campoDescripcion.setText("");
        fecha.resetValidation();
    }

    public void handlerBotonConfirmarRegistro(){
        master = new ControlMaster();

        //Restriccion 1 - Verificar si el campo del nombre no esta vacio
        if(campoNombre.getText().isEmpty()){
            AlertBox.display("Por favor escribe un nombre para la mascota","Nombre vacio","/resources/Icons/warning.png");
            return;
        }

        //Restriccion 2 - Verificar si el campo de descripcion no esta vacio
        if(campoDescripcion.getText().isEmpty()){
            AlertBox.display("Por favor escribe una descripción de este rescate","Descripción vacía","/resources/Icons/warning.png");
            return;
        }


        //Verificar que se haya seleccionado una fecha
        String f = fecha.getValue()!= null ? fecha.getValue().toString() : "";
        if(f.equals("")){
            AlertBox.display("Por favor, selecciona una fecha","Fecha vacia","/resources/Icons/warning.png");

            return;
        }

        if(radioBotones.getSelectedToggle() == null){
            AlertBox.display("Por favor, seleccione una especie","Especie no seleccionada","/resources/Icons/warning.png");

            return;
        }

        if(radioBotones.getSelectedToggle().equals(botonRadioOtro) &&campoEspecieOtro.getText().isEmpty() ){
            System.out.println("IT WORKS");
            AlertBox.display("Por favor, especifica la especie en el campo de texto","Otra especie no seleccionada","/resources/Icons/warning.png");
            return;
        }

        String nombreAnimal = campoNombre.getText();
        String especie = "";
        String raza;
        GregorianCalendar fechaRegistro = Utilidades.convertirFecha_StringToGregorian(f);;
        int idVoluntario = 1; //Aqui tenenemos que programar
        String descripcionFinal = campoDescripcion.getText();


        if(radioBotones.getSelectedToggle().equals(botonRadioOtro)){
            especie = campoEspecieOtro.getText();
        }

        if (radioBotones.getSelectedToggle().equals(botonRadioGato)) {
            especie = "Gato";
        }

        if (radioBotones.getSelectedToggle().equals(botonRadioPerro)) {
            especie = "Perro";
        }

        if(botonRadioRaza.isSelected()){
            raza = campoRaza.getText();
        }else{
            raza = "No aplica";
        }

        Animal animal = new Animal(nombreAnimal,especie,raza,fechaRegistro,idVoluntario,descripcionFinal,-1);

       int resultadoQuery = master.getcAnimales().getAnimales().queryAgregarAnimal(animal);
        System.out.println(resultadoQuery);
        if(resultadoQuery == 1){
            AlertBox.display("Se ha realizado el registro de rescate exitosamente","Rescate exitoso","/resources/Icons/dog-house.png");
            limpiarPantalla();
            return;
        }else{
            AlertBox.display("Algo salio mal", "Rescate fallido", "resources/Icons/warning.png");
            return;
        }
    }

}
