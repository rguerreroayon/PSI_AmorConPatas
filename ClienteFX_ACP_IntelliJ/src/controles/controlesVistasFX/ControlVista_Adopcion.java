/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controles.controlesVistasFX;

import accesoDatos.Utilidades;
import controles.controlesDatos.ControlMaster;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import objetosNegocio.*;
import utilities.AlertBox;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author rob
 */
public class ControlVista_Adopcion {

    //ControlMaster
    ControlMaster controlMaster;

    //Buttons
    @FXML
    JFXButton botonAsistencia;

    @FXML
    JFXButton botonBuscarMascota;

    @FXML
    JFXButton botonBuscarAdoptante;

    @FXML
    JFXButton botonRegistrarAdopcion;

    @FXML
    JFXButton botonRegistrarVoluntario;

    @FXML
    JFXButton botonRegistrarAdoptante;

    @FXML
    JFXButton botonRegistrarRescate;

    @FXML
    JFXButton botonConfirmarAdopcion;

    @FXML
    JFXButton botonCancelar;


    //Text Fields
    @FXML
    JFXTextField campoNombreNuevo;

    @FXML
    JFXTextField campoBusquedaAnimal;

    @FXML
    JFXTextField campoNombreAdoptante;
    //Text Area

    @FXML
    JFXTextArea campoDescripcion;

    //ComboBox
    @FXML
    JFXComboBox comboBoxAdoptantes;

    @FXML
    JFXComboBox comboBoxAnimales;
    //Fecha
    @FXML
    JFXDatePicker fecha;

    //Handlers 

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


    public void handlerBotonConfirmarAdopcion(){
        controlMaster = new ControlMaster();


        if(campoNombreNuevo.getText().isEmpty()){

            AlertBox.display("Por favor escribe un nombre para la mascota","Nombre vacio","/resources/Icons/warning.png");
           return;
        }

        if(comboBoxAnimales.getSelectionModel().getSelectedItem() == null || comboBoxAdoptantes.getSelectionModel().getSelectedItem() == null){
            AlertBox.display("Por favor, selecciona un elemento","Elemento no seleccionado","/resources/Icons/warning.png");

            return;
        }

        if(campoDescripcion.getText().isEmpty()){
            AlertBox.display("Por favor, escribe una descripción","Descripción vacia","/resources/Icons/warning.png");

            return;
        }

        String f = fecha.getValue()!= null ? fecha.getValue().toString() : "";

        if(f.equals("")){
            AlertBox.display("Por favor, selecciona una fecha","Fecha vacia","/resources/Icons/warning.png");

            return;
        }


        String regularExpression = "^[a-zA-Z ]+$";



        if(campoBusquedaAnimal.getText().length() > 15 || campoNombreNuevo.getText().length() > 15 || campoDescripcion.getText().length() > 50 || campoNombreAdoptante.getText().length() > 30){
            AlertBox.display("Excediste la cantidad de caracteres","Exceso de caracteres", "/resources/Icons/warning.png");

        }





        GregorianCalendar fechaGregorian = Utilidades.convertirFecha_StringToGregorian(f);



        String nombreMascota = campoNombreNuevo.getText();
        String descripcion = campoDescripcion.getText();
        Animal animalAdoptar = (Animal) comboBoxAnimales.getSelectionModel().getSelectedItem();
        Adoptante adoptante = (Adoptante) comboBoxAdoptantes.getSelectionModel().getSelectedItem();

        Animal animal = controlMaster.getcAnimales().getAnimales().queryGetAnimalPorID(animalAdoptar.getIdAnimal());

        if(animal.getIsAdoptado() == 1){
            return;
        }
        ArrayList<Adopcion> adopciones = controlMaster.getcAdopciones().getAdopciones().transformarQuerySet();

        for (Adopcion ad: adopciones
             ) {
            if(ad.getIdAnimal() == animalAdoptar.getIdAnimal()){
                AlertBox.display("Este animal ya esta adoptado en la base de datos", "Adopción Fallida", "resources/Icons/warning.png");

            }

        }

        Adopcion adopcion = new Adopcion(animalAdoptar.getIdAnimal(),adoptante.getIdAdoptante(),1,fechaGregorian,descripcion);

        int resultadoQuery = controlMaster.getcAdopciones().getAdopciones().queryAnadirAdopcion(adopcion,nombreMascota);



        if(resultadoQuery == 2){
            AlertBox.display("Se ha realizado la adopción exitosamente","Adopción exitosa","/resources/Icons/dog-house.png");
            limpiarPantalla();
            return;
        }else{
            AlertBox.display("Algo salio mal", "Adopción Fallida", "resources/Icons/warning.png");
            comboBoxAnimales.getItems().clear();
            return;
        }









    }
    
    public void handlerBotonCancelar(){
        botonCancelar.setText("It works!!");
        Stage stage = (Stage) botonCancelar.getScene().getWindow();
        stage.close();

    }

    public void handlerKeyTypedInFecha(){

        fecha.setEditable(false);

    }

    public void limpiarPantalla(){
        comboBoxAnimales.getItems().clear();
        comboBoxAdoptantes.getItems().clear();
        campoDescripcion.setText("");
        campoNombreNuevo.setText("");
        campoBusquedaAnimal.setText("");
        campoNombreAdoptante.setText("");
    }

    public void handlerRestriccionCaracteres_Animal(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoBusquedaAnimal.getText().length() > 15 ){
            event.consume();
        }



    }

    public void handlerRestriccionCaracteres_AnimalNuevo(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression )){
            event.consume();
        }

        if(campoNombreNuevo.getText().length() > 15){
            event.consume();
        }



    }

    public void handlerRestriccionCaracteres_Adoptante(KeyEvent event){
        String regularExpression = "^[a-zA-Z]+$";

        if(!event.getCharacter().matches(regularExpression)){
            event.consume();
        }

        if(campoNombreAdoptante.getText().length() > 30){
            event.consume();
        }


    }

    public void handlerRestriccionCaracteres_Descripcion(KeyEvent event){
        String regularExpression = "^[a-zA-Z ]+$";

        if(!event.getCharacter().matches(regularExpression)){
            event.consume();
        }

        if(campoDescripcion.getText().length() > 50){
            event.consume();
        }


    }

    public void handleBuscarMascota(){
        controlMaster = new ControlMaster();


        String animal = campoBusquedaAnimal.getText();
        if(animal.isEmpty()){
            AlertBox.display("El campo de texto esta vacío", "Busqueda fallida", "resources/Icons/warning.png");
            return;
        }

        ArrayList <Animal> listaAnimales = controlMaster.getcAnimales().getAnimales().queryGetAnimalesRescatadosPorNombreAnimal(animal);

        if(listaAnimales.isEmpty()){
            AlertBox.display("No existen mascotas con ese nombre", "Busqueda fallida", "resources/Icons/warning.png");
            comboBoxAnimales.getItems().clear();
            return;
        }


        comboBoxAnimales.getItems().clear();
        comboBoxAnimales.getItems().addAll(listaAnimales);

        if(!listaAnimales.isEmpty()){
            AlertBox.display("Las mascotas han sido actualizadas!","Mascotas actualizadas","resources/Icons/dog_actualizado.png");
        }

    }
    
    public void handleBuscarAdoptante(){
        controlMaster = new ControlMaster();
        String adoptante = campoNombreAdoptante.getText();
        if(adoptante.isEmpty()){
            AlertBox.display("El campo de texto esta vacío", "Busqueda fallida", "resources/Icons/warning.png");
            return;
        }
        ArrayList <Adoptante> listaAdoptantes = controlMaster.getcAdoptantes().getAdoptantes().queryGetAdoptantesPorNombre(adoptante);

        if(listaAdoptantes.isEmpty()){
            AlertBox.display("No existen adoptantes con ese nombre", "Busqueda fallida", "resources/Icons/warning.png");
            comboBoxAnimales.getItems().clear();
            return;
        }

        comboBoxAdoptantes.getItems().clear();
        comboBoxAdoptantes.getItems().addAll(listaAdoptantes);

        if(!listaAdoptantes.isEmpty()){
            AlertBox.display("Los adoptantes han sido actualizados!","Adoptantes actualizadas","resources/Icons/boy_actualizado.png");
        }

    }

    public void handlerVentanaRegistrarRescate(Event event) throws IOException {

        Parent homePageParent = FXMLLoader.load(getClass().getResource("/vista/registro_mascota/vistaRegistroMascota.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePageScene);
        app_stage.show();


    }

    public void handlerPaseLista(Event event) throws IOException{
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/vista/pase_lista/vistaPaseLista.fxml"));
        Scene homePageScene = new Scene(homePageParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homePageScene);
        app_stage.show();
    }



    
}
