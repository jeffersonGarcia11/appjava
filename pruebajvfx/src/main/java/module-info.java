module com.pruebajvfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens com.pruebajvfx to javafx.fxml;
    exports com.pruebajvfx;

    //Se añadieron las siguientes paquetes para que puedan ser escuchados por la configuración principal o algo así
    exports com.pruebajvfx.controller; // Exporta el paquete que contiene tus controladores
    opens com.pruebajvfx.controller to javafx.fxml; // Abre el paquete que contiene tus controladores
    opens com.pruebajvfx.models to javafx.base;

}
