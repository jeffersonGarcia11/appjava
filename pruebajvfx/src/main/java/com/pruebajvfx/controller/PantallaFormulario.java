package com.pruebajvfx.controller;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PantallaFormulario {
    @FXML
    private Button btnAccion; 
    @FXML
    private TextField txtNombre, txtCarrera, txtProyecto, txtPromedio;
    @FXML
    private Text lbTitle;

    private String nombre, carrera, proyecto;
    private double promedio;

    private PantallaPrincipal pantallaPrincipalController;


    private StringProperty informacion = new SimpleStringProperty();

    public void setPantallaPrincipalController(PantallaPrincipal controller) {
        this.pantallaPrincipalController = controller;
    }

    @FXML
    private void btnGuardarOnClick() throws IOException{
        System.out.println("Se presiono el botón de guardar");

        //valoresCaptura();        
        
        devolverDatos();
        Stage stage = (Stage) btnAccion.getScene().getWindow();
        stage.close();
    }

    public void devolverDatos(){
        String info = "Info proveniente de PFormulario";
        pantallaPrincipalController.recibirInformacion(info);
    }

    @FXML
    private void initialize(){
        System.out.println("Se ejecuta al iniciar");
        String infoCaptura = informacion.get();
        System.out.println("Informacion capturada " + infoCaptura);
    }

    //Se capturan los valores de los inputs que se presentan
    private void valoresCaptura() throws IOException{
        nombre = txtNombre.getText();
        carrera = txtCarrera.getText();
        proyecto = txtProyecto.getText();
        try{
            promedio = Double.parseDouble(txtPromedio.getText());
        }catch(NumberFormatException ex){
            System.err.println(" Error en el ingreso de datos");
        }
        lbTitle.setText(nombre + " " + carrera + " " +proyecto + " " + promedio);
        System.out.println("Nombre " + nombre);
        System.out.println(" Carrera" +  carrera);
        System.out.println(" Proyecto " +  proyecto);
        System.out.println(" Promedio " + promedio);
    }

    public void mostrarInformacion(String informacion) {
        System.out.println("Información recibida...  " + informacion);
        this.informacion.set(informacion);
    }

    
}
