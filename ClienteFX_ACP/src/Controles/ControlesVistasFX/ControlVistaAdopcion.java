/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles.ControlesVistasFX;

import Controles.ControlesDatosFX.ControlMaster;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

/**
 *
 * @author rob
 */
public class ControlVistaAdopcion {

    //ControlMaster
    ControlMaster controlMaster;

    //Buttons
    JFXButton botonAsistencia;
    JFXButton botonRegistrarAdopcion;
    JFXButton botonRegistrarVoluntario;
    JFXButton botonRegistrarAdoptante;
    JFXButton botonOpcionesAdministrador;
    JFXButton botonConfirmarAdopcion;
    JFXButton botonCancelar;
    //Text Fields
    JFXTextField campoNombreNuevo;
    JFXTextField campoBusquedaAnimal;
    JFXTextField campoNombreAdoptante;
    //Text Area
    JFXTextArea campoDescripcion;

    //ComboBox
    JFXComboBox comboBoxAdoptantes;
    JFXComboBox comboBoxAnimales;
    //Fecha
    JFXDatePicker fecha;

    //Handlers 
    
    public void handlerBotonConfirmarAdopcion(){
        
    }
    
    public void handlerBotonCancelar(){
        
    }
    
    
    public void handlerCampoBusquedaAnimal(){
        controlMaster = new ControlMaster();
        
        
        comboBoxAnimales.getItems().addAll(
                controlMaster.getcAnimales().getAnimales().queryGetAnimalAdoptadosPorNombre(
                        campoBusquedaAnimal.getText()
                )
        );
        
        
    }
    
    public void handlerCampoBusquedaAdoptante(){
        controlMaster = new ControlMaster();
        
        
        comboBoxAdoptantes.getItems().addAll(
                controlMaster.getcAdoptantes().getAdoptantes().queryGetAdoptantesPorNombre(
                        campoBusquedaAnimal.getText()
                )
        );
    }
    
}
