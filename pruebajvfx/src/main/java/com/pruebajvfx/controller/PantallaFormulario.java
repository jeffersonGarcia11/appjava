package com.pruebajvfx.controller;

import java.io.IOException;

import com.pruebajvfx.models.EstudianteNodo;

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
    private Button btnEliminar; 
    @FXML
    private TextField txtNombre, txtCarrera, txtProyecto, txtPromedio;
    @FXML
    private Text lbTitle;

    @FXML
    private Text txtAlerta;
    private String nombre, carrera, proyecto;
    private double promedio;
    private boolean esEdicion = false;
    private int posicion;

    private PantallaPrincipal pantallaPrincipalController;


    private StringProperty informacion = new SimpleStringProperty();

    public void setPantallaPrincipalController(PantallaPrincipal controller) {
        this.pantallaPrincipalController = controller;
    }

    @FXML
    private void btnEliminarOnClick() throws IOException{
        System.out.println("Se presiono el botón de eliminar");
        EstudianteNodo nodo = new EstudianteNodo();
                nodo.setCarrera(txtCarrera.getText());
                nodo.setNombre(txtNombre.getText());
                nodo.setPromedio( Double.parseDouble(txtPromedio.getText()));
                nodo.setProyecto(txtProyecto.getText());
                pantallaPrincipalController.eliminarRegistro(posicion);;
                Stage stage = (Stage) btnAccion.getScene().getWindow();
                stage.close();
        //valoresCaptura();        
        
        
    }
    

    @FXML
    private void btnGuardarOnClick() throws IOException{

        if(esEdicion){
            //SI ES EDICION
            if(casillasLlenas()){
                if(valoresCaptura()){
                    EstudianteNodo nodo = new EstudianteNodo();
                    nodo.setCarrera(txtCarrera.getText());
                    nodo.setNombre(txtNombre.getText());
                    nodo.setPromedio( Double.parseDouble(txtPromedio.getText()));
                    nodo.setProyecto(txtProyecto.getText());
                    pantallaPrincipalController.edicionRegistro(nodo, posicion);
                    Stage stage = (Stage) btnAccion.getScene().getWindow();
                    stage.close();
                }            
            }
        }else{ // NO ES EDICION
            System.out.println("Se presiono el botón de guardar");
            devolverDatos();
        }
    }

    public void devolverDatos(){
        if(casillasLlenas()){
            if(valoresCaptura()){
                EstudianteNodo nodo = new EstudianteNodo();
                nodo.setCarrera(txtCarrera.getText());
                nodo.setNombre(txtNombre.getText());
                nodo.setPromedio( Double.parseDouble(txtPromedio.getText()));
                nodo.setProyecto(txtProyecto.getText());
                pantallaPrincipalController.recibirInformacion(nodo);
                Stage stage = (Stage) btnAccion.getScene().getWindow();
                stage.close();
            }
        }
    }

    private boolean casillasLlenas(){
        if(txtCarrera.getText() == "" ||txtCarrera.getText().isEmpty() || txtNombre.getText().isEmpty() || txtNombre.getText().isEmpty()|| txtPromedio.getText().isEmpty() ){
                txtAlerta.setText("Llene todos los campos");
                return false;
            }
        
        return true;
    }

    @FXML
    private void initialize(){
        System.out.println("Se ejecuta al iniciar");
        String infoCaptura = informacion.get();
        System.out.println("Informacion capturada " + infoCaptura);
    }

    //Se capturan los valores de los inputs que se presentan
    private boolean valoresCaptura(){
        nombre = txtNombre.getText();
        carrera = txtCarrera.getText();
        proyecto = txtProyecto.getText();
        try{
            promedio = Double.parseDouble(txtPromedio.getText());
            return true;
        }catch(NumberFormatException ex){
            txtAlerta.setText("Valide valor Promedio (Double)");   
            return false;
        }
    }

    public void mostrarInformacion(String informacion) {
        lbTitle.setText("NUEVO");
        btnAccion.setText("Guardar");
        btnEliminar.setVisible(false);
        //btnEliminar.setManaged(false);
    }

    public void mostrarInformacionEdicion(EstudianteNodo nodo, int posicion) {
        this.posicion = posicion;
        esEdicion = true;
        lbTitle.setText("EDICION");
        btnAccion.setText("EDITAR");
        txtNombre.setText(nodo.getNombre());
        txtCarrera.setText(nodo.getCarrera());
        txtProyecto.setText(nodo.getProyecto());
        txtPromedio.setText(String.valueOf(nodo.getPromedio()) );
        
    }

    
}
