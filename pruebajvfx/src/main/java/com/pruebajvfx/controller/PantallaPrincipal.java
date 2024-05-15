package com.pruebajvfx.controller;

import java.io.IOException;

import com.pruebajvfx.App;
import com.pruebajvfx.models.EstudianteNodo;
import com.pruebajvfx.models.ListaEnlazada;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PantallaPrincipal {
    @FXML
    private Button btn_nuevo;

    @FXML 
    private TableView<EstudianteNodo> tbDatos;
    @FXML 
    private TableColumn<EstudianteNodo, Double> colPromedio; 
    @FXML 
    private TableColumn<EstudianteNodo, String> colNombre; 
    @FXML
    private TableColumn<EstudianteNodo, String> colCarrera; 
    @FXML
    private TableColumn<EstudianteNodo, String> colProyecto; 

    private ObservableList<EstudianteNodo> data;
    private ListaEnlazada listaEnlazada = new ListaEnlazada(); 



    @FXML
    private void fnBtnNuevo(ActionEvent event) throws IOException{ // se agrega IOException para el lanzamiento de la nueva pantalla 
        StringProperty informacion = new SimpleStringProperty();
        informacion.set("Nuevo");
        System.out.println("Se presiono el botón nuevo");

        FXMLLoader loader = new FXMLLoader(App.class.getResource("pantalla_formulario.fxml"));
        Parent root = loader.load();
        PantallaFormulario pantallaFormulario = loader.getController(); // Obtener el controlador de PantallaFormulario
        pantallaFormulario.mostrarInformacion("HOla desde el pPrincipal"); // Llamar al método mostrarInformacion
        pantallaFormulario.setPantallaPrincipalController(this);
        Scene scene = new Scene(root, 640, 480);
        Stage stage = new Stage(); 
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML2(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

     //Metodo para poder llamar a otra pantalla. 
    private static PantallaFormulario loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        PantallaFormulario controller = fxmlLoader.getController(); // Obtener el controlador de PantallaFormulario
        return controller;
    }



    /**
     * 
        //@FXML
    private void fnBtnNuevo(ActionEvent event) throws IOException{ // se agrega IOException para el lanzamiento de la nueva pantalla 
        StringProperty informacion = new SimpleStringProperty();
        informacion.set("Nuevo");
        System.out.println("Se presiono el botón nuevo");
        Scene scene = new Scene(loadFXML("pantalla_formulario"), 640, 480);
        Stage stage = new Stage(); 
        stage.setScene(scene);
        stage.show();
    }

     //Metodo para poder llamar a otra pantalla. 
     private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }



     */


    

    @FXML 
    private void initialize() {
        data = FXCollections.observableArrayList();
        /**
        
        EstudianteNodo estudiante = new EstudianteNodo();
        EstudianteNodo estudiante2 = new EstudianteNodo();
        estudiante.setNombre("Prueba");
        estudiante2.setNombre("Jualian Alvarez");
        data.add(estudiante);
        data.add(estudiante2);
        System.out.println("Se carga la pantalla, se ejecuta el initizlize");
        System.out.println("Datos a imprimir " + estudiante.getNombre());
         */
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedio"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colProyecto.setCellValueFactory(new PropertyValueFactory<>("proyecto"));
        //tbDatos.setItems(data);
        System.out.println("--- LLEGA ANTES DE LA FUNCION  ----");
        conversionData();
    }

   
    //Cargar datos de ListaEnlazada a data de 
    private void conversionData(){
        System.out.println("Ingresa a la función");
        EstudianteNodo estudiante = new EstudianteNodo();
        estudiante.setNombre("Juan Ramon");      
        EstudianteNodo estudiante2 = new EstudianteNodo();
        estudiante2.setNombre("Pedro Pedro");
        //estudiante.setEnlace(estudiante2);
        listaEnlazada.guardarNodo(estudiante, 1);
        listaEnlazada.guardarNodo(estudiante2, 2);
        int cantidad = listaEnlazada.getCantidad();
        System.out.println("Cantidad de nodos = " + cantidad);
        System.out.println("---Llega al for");
        for(int i = 0; i<cantidad; i++){
            System.out.println("vuelta No. " + i);
            EstudianteNodo nodo = listaEnlazada.getNodoEnPosicion(i);
            System.out.println("Valor de nombre de nodo " + nodo.getNombre());
            data.add(nodo);
        }
        tbDatos.setItems(data);
    }

    public void recibirInformacion(String recibirInfo){
        System.out.println("Se recibe la info: " + recibirInfo);
    }
}
