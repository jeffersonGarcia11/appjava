package com.pruebajvfx.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.pruebajvfx.App;
import com.pruebajvfx.models.EstudianteNodo;
import com.pruebajvfx.models.ListaEnlazada;
import java.io.File;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PantallaPrincipal {
    @FXML
    private Button btn_nuevo;

    @FXML
    private Button btnReporte;
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

    @FXML
    private Text txtMensaje;
    private ObservableList<EstudianteNodo> data;
    private ListaEnlazada listaEnlazada = new ListaEnlazada(); 

    @FXML
    private void btnReporteOnClick(ActionEvent event) throws IOException{
        System.out.println("Se presionó el botón de reporte");
        generateReport();
    }

    @FXML
    private void fnCargaCSV(){
        System.out.println("Se presiono el btn de Carga manual");
        prevalidacionCargaCSV();
    }

    @FXML
    private void generateReport(){
        Stage primaryStage = (Stage) btnReporte.getScene().getWindow(); // Obtener el escenario actual desde la aplicación
        primaryStage.setTitle("CSV File Writer");
        
        // Seleccionar la ubicación y el nombre del archivo donde guardar los datos
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(primaryStage);

        if (selectedFile != null) {
            try {
                // Crear un BufferedWriter para escribir en el archivo seleccionado
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

                // Escribir los datos en el archivo CSV

                for(int i = 0; i< listaEnlazada.getCantidad(); i++){
                    EstudianteNodo nodo = listaEnlazada.getNodoEnPosicion(i);
                    writer.write(nodo.getPromedio() + ", " + nodo.getNombre() + ", " + nodo.getCarrera() + ", " + nodo.getProyecto());
                    writer.newLine();
                }
                // Cerrar el BufferedWriter después de escribir en el archivo
                writer.close();

                System.out.println("Archivo CSV guardado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo para guardar.");
        }
    }



    @FXML
    private void fnEditarRegistro(EstudianteNodo nodo, int posicion) throws IOException{ // se agrega IOException para el lanzamiento de la nueva pantalla 
        StringProperty informacion = new SimpleStringProperty();
        informacion.set("Edicion");
        System.out.println("Se presiono el botón nuevo");

        FXMLLoader loader = new FXMLLoader(App.class.getResource("pantalla_formulario.fxml"));
        Parent root = loader.load();
        PantallaFormulario pantallaFormulario = loader.getController(); // Obtener el controlador de PantallaFormulario
        pantallaFormulario.mostrarInformacionEdicion(nodo, posicion); // Llamar al método mostrarInformacion
        pantallaFormulario.setPantallaPrincipalController(this);
        Scene scene = new Scene(root, 640, 480);
        Stage stage = new Stage(); 
        stage.setScene(scene);
        stage.show();
    }

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

    @FXML 
    private void initialize() {
        data = FXCollections.observableArrayList();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedio"));
        colCarrera.setCellValueFactory(new PropertyValueFactory<>("carrera"));
        colProyecto.setCellValueFactory(new PropertyValueFactory<>("proyecto"));
        //tbDatos.setItems(data);
        System.out.println("--- LLEGA ANTES DE LA FUNCION  ----");
        conversionData();


        tbDatos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int seleccionado = tbDatos.getSelectionModel().getSelectedIndex();
                try{
                    fnEditarRegistro(newValue, seleccionado);
                }catch(Exception e){

                }
                
            }
        });
    }

   
    //Cargar datos de ListaEnlazada a data de 
    private void conversionData(){
        data.clear();
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


    public void edicionRegistro(EstudianteNodo nodo, int posicion){
        posicion++;
        listaEnlazada.eliminacionIndice(posicion);
        listaEnlazada.guardadoOrdenado(nodo);
        conversionData();
    }
    public void eliminarRegistro(int posicion){
        System.out.println("Se debe eliminar el registro " + posicion);
        posicion++;
        listaEnlazada.eliminacionIndice(posicion);
        conversionData();
    }
    public void recibirInformacion(EstudianteNodo nodo){
        listaEnlazada.guardadoOrdenado(nodo);
        conversionData();
    }

    @FXML
    public void generarReporteCSV(){
        Stage primaryStage = (Stage) btn_nuevo.getScene().getWindow(); // Obtener el escenario actual desde la aplicación

        primaryStage.setTitle("CSV File Writer");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv"));
        File selectedFile = fileChooser.showSaveDialog(primaryStage);

        if (selectedFile != null) {
            try {
                // Crear un BufferedWriter para escribir en el archivo seleccionado
                BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));

                // Escribir los datos en el archivo CSV
                int cantidad = listaEnlazada.getCantidad();
                for(int i = 0; i<cantidad; i++){
                    
                    EstudianteNodo nodo = listaEnlazada.getNodoEnPosicion(i);
                    writer.write(nodo.getPromedio() + ", " + nodo.getNombre() + ", " + nodo.getCarrera() + ", " + nodo.getProyecto());
                    writer.newLine();
                    //System.out.println("Valor de nombre de nodo " + nodo.getNombre());
                    
                }
                // Cerrar el BufferedWriter después de escribir en el archivo
                writer.close();

                System.out.println("Archivo CSV guardado correctamente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se ha seleccionado ningún archivo para guardar.");
        }
    }
    
    
    @FXML
    private void prevalidacionCargaCSV(){
        //FORMATO DE CARGA 
        // PROMEDIO, NOMBRE, CARRERA, PROYECTO;
        Stage primaryStage = (Stage) btnReporte.getScene().getWindow(); // Obtener el escenario actual desde la aplicación
         primaryStage.setTitle("CSV File Reader");

        // Seleccionar un archivo CSV
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        boolean archivoValidado = true;
        if (selectedFile != null) {
            try {
                // Crear un BufferedReader para leer el archivo
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;
                
                // Leer cada línea del archivo
                while ((line = reader.readLine()) != null && archivoValidado) {
                    // Procesar la línea como desees
                    //System.out.println("Línea leída: " + line);
                    archivoValidado =  lecturaLinea(line);
                    System.out.println("Linea "+ line );
                    System.out.println("Retorno valor " + archivoValidado);
                }
                if(archivoValidado == false ){
                    txtMensaje.setText("VALIDE EL ARCHIVO CARGADO");
                }else{
                    BufferedReader reader2 = new BufferedReader(new FileReader(selectedFile));
                    String line2;
                    while ((line2 = reader2.readLine()) != null) {
                        // Procesar la línea como desees
                        //System.out.println("Línea leída: " + line);
                        //archivoValidado =  lecturaLinea(line);
                        EstudianteNodo nodoReturn = guardadoCSV(line2);
                        listaEnlazada.guardadoOrdenado(nodoReturn);
                    }
                    conversionData();
                    //impresionOuput();
                    txtMensaje.setText("Archivo exitosamente cargado");
                }
                // Cerrar el BufferedReader después de terminar de leer el archivo
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            
            txtMensaje.setText("No se seleccionó ningún archivo CSV.");
        }
    }

    private boolean conversionDouble (String numero){
        if(numero == null || numero.isEmpty()){
            return false;
        }
        try{
            Double.parseDouble(numero);
            return true;
        }catch (NumberFormatException nerror){
            return false;
        }
    }

    private boolean lecturaLinea(String line){
        StringBuilder currentField = new StringBuilder();
        boolean insideQuote = false;
        boolean todoCorrecto = true;
        int fieldCount = 0;
        int i = 0;

        // Primero contar los campos para validar
        int actualFieldCount = 0;
        boolean insideQuoteTemp = false;
        for (int j = 0; j < line.length(); j++) {
            char currentChar = line.charAt(j);

            if (currentChar == '"') {
                insideQuoteTemp = !insideQuoteTemp; // Toggle the insideQuote flag
            } else if (currentChar == ',' && !insideQuoteTemp) {
                actualFieldCount++;
            }
        }
        actualFieldCount++; // Adding the last field after the last comma

        if (actualFieldCount < 4) {
            System.out.println("Datos incompletos en la línea: " + line);
            return false;
        }

        // Procesar solo los primeros cuatro campos
        while (i < line.length() && fieldCount < 4) {
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                insideQuote = !insideQuote; // Toggle the insideQuote flag
            } else if (currentChar == ',' && !insideQuote) {
                // Encontramos una coma fuera de comillas, entonces este es el final de un campo
                if (fieldCount == 0) {
                    // Llamar a la función de validación para el primer campo
                    boolean datoNumerico = conversionDouble(currentField.toString());
                    if(datoNumerico == false){
                        todoCorrecto = false;
                    }
                }
                System.out.println("Campo: " + currentField.toString());
                currentField.setLength(0); // Limpiar el StringBuilder para el próximo campo
                fieldCount++;
            } else {
                currentField.append(currentChar); // Añadir el carácter al campo actual
            }

            i++;
        }

        // Agregar el último campo si es el cuarto
        if (fieldCount < 4) {
            if (fieldCount == 0) {
                // Llamar a la función de validación para el primer campo
                boolean datoNumerico = conversionDouble(currentField.toString());
                    if(datoNumerico == false){
                        todoCorrecto = false;
                    }
            }
            System.out.println("Campo: " + currentField.toString());
        }
        return todoCorrecto;
    }

    private EstudianteNodo guardadoCSV(String line){
        StringBuilder currentField = new StringBuilder();
        boolean insideQuote = false;
        int fieldCount = 0;
        int i = 0;

        EstudianteNodo nodoSave = new EstudianteNodo();
        // Primero contar los campos para validar
        int actualFieldCount = 0;
        boolean insideQuoteTemp = false;
        for (int j = 0; j < line.length(); j++) {
            char currentChar = line.charAt(j);

            if (currentChar == '"') {
                insideQuoteTemp = !insideQuoteTemp; // Toggle the insideQuote flag
            } else if (currentChar == ',' && !insideQuoteTemp) {
                actualFieldCount++;
            }
        }
        actualFieldCount++; // Adding the last field after the last comma


        // Procesar solo los primeros cuatro campos
        while (i < line.length() && fieldCount < 4) {
            char currentChar = line.charAt(i);

            if (currentChar == '"') {
                insideQuote = !insideQuote; // Toggle the insideQuote flag
            } else if (currentChar == ',' && !insideQuote) {
                // Encontramos una coma fuera de comillas, entonces este es el final de un campo
                switch (fieldCount) {
                    case 0:
                        nodoSave.setPromedio( Double.parseDouble(currentField.toString()));
                        
                        break;
                    case 1:
                        nodoSave.setNombre(currentField.toString());

                        break;
                    case 2:
                        nodoSave.setCarrera(currentField.toString());
                        break;
                    case 3:
                    
                        nodoSave.setProyecto(currentField.toString());
                        break;
                }
                //System.out.println("Campo: " + currentField.toString());
                currentField.setLength(0); // Limpiar el StringBuilder para el próximo campo
                fieldCount++;
            } else {
                currentField.append(currentChar); // Añadir el carácter al campo actual
            }

            i++;
        }

        // Agregar el último campo si es el cuarto
        if (fieldCount < 4) {
            switch (fieldCount) {
                case 0:
                nodoSave.setPromedio( Double.parseDouble(currentField.toString()));
                
                break;
                case 1:
                    nodoSave.setNombre(currentField.toString());

                    break;
                case 2:
                    nodoSave.setCarrera(currentField.toString());
                    break;
                case 3:
                
                    nodoSave.setProyecto(currentField.toString());
                    break;
            }
            //System.out.println("Campo segundo proceso: " + currentField.toString());
        }
        return nodoSave;
    }    
}
