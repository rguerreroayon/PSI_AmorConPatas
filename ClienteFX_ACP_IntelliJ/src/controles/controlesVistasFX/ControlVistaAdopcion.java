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
import javafx.fxml.FXML;
import objetosNegocio.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author rob
 */
public class ControlVistaAdopcion {

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
    JFXButton botonOpcionesAdministrador;

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
    
    public void handlerBotonConfirmarAdopcion(){
        controlMaster = new ControlMaster();

        if(campoNombreNuevo.getText().isEmpty()){
           return;
        }

        if(comboBoxAnimales.getSelectionModel().getSelectedItem() == null || comboBoxAdoptantes.getSelectionModel().getSelectedItem() == null){
            return;
        }

        if(campoDescripcion.getText().isEmpty()){
            return;
        }

        String f = fecha.getValue()!= null ? fecha.getValue().toString() : "";

        if(f.equals("")){
            return;
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
        Adopcion adopcion = new Adopcion(animalAdoptar.getIdAnimal(),adoptante.getIdAdoptante(),1,fechaGregorian,descripcion);
        controlMaster.getcAdopciones().getAdopciones().queryAnadirAdopcion(adopcion,nombreMascota);

        








    }
    
    public void handlerBotonCancelar(){
        botonCancelar.setText("It works!!");
    }
    
    
    public void handleBuscarMascota(){
        controlMaster = new ControlMaster();
        String animal = campoBusquedaAnimal.getText();
        ArrayList <Animal> listaAnimales = controlMaster.getcAnimales().getAnimales().queryGetAnimalesRescatadosPorNombreAnimal(animal);


        for (Animal a: listaAnimales){
            if(!comboBoxAnimales.getItems().contains(a)){
                comboBoxAnimales.getItems().add(a);
            }
        }



    }
    
    public void handleBuscarAdoptante(){
        controlMaster = new ControlMaster();

        String adoptante = campoNombreAdoptante.getText();


            ArrayList <Adoptante> listaAdoptantes = controlMaster.getcAdoptantes().getAdoptantes().queryGetAdoptantesPorNombre(adoptante);




            comboBoxAdoptantes.getItems().addAll(listaAdoptantes);



        }

    
}
