package com.pruebajvfx.models;

public class ListaEnlazada {
    private EstudianteNodo nodoInicial, nodoFinal;
    private int cantidad;
    private int serieNodo;

    public EstudianteNodo getNodoInicial(){ return nodoInicial;}
    public int getCantidad(){return cantidad;}

    public String guardarNodo(EstudianteNodo nodo, int posicion){
        nodo.setCodigo("R-" + this.serieNodo);
        if( (posicion > 0) && (posicion <= (cantidad + 1))){

            if(posicion == 1){
                EstudianteNodo n = null;
                if(this.nodoInicial != null) n =  this.nodoInicial;
                nodo.setEnlace(n);
                this.nodoInicial = nodo;
                if(nodoFinal == null) this.nodoFinal = nodo;
                cantidad++;
                this.serieNodo++;
                return "- Dato guardado: Codigo: " + nodo.getCodigo() + ", Nombre: " + nodo.getNombre() + ", Salario: " + nodo.getPromedio();
            }if(posicion == (cantidad + 1)){
                nodoFinal.setEnlace(nodo);
                nodoFinal = nodo;
                cantidad++;
                this.serieNodo++;
                return "- Dato guardado: Codigo: " + nodo.getCodigo() + ", Nombre: " + nodo.getNombre() + ", Salario: " + nodo.getPromedio();
            }else{
                EstudianteNodo n = nodoInicial;
                for(int i = 1; i < posicion -1; i++){
                    n =  n.getEnlace();
                }
                nodo.setEnlace(n.getEnlace());
                n.setEnlace(nodo);
                cantidad++;
                this.serieNodo++;
                return "- Dato guardado: Codigo: " + nodo.getCodigo() + ", Nombre: " + nodo.getNombre() + ", Salario: " + nodo.getPromedio();
            }
        }else{
            return "El nodo a guardar excede el tamaño de la lista";
        }
    }

    public EstudianteNodo getNodoEnPosicion(int posicion) {
        EstudianteNodo nodoActual = nodoInicial;
        int contador = 0;
        while (nodoActual != null && contador < posicion) {
            nodoActual = nodoActual.getEnlace();
            contador++;
        }
        return nodoActual;
    }

    public void eliminacionIndice(int eliminar){
        if(eliminar == 1){
            EstudianteNodo n = nodoInicial;
            if(n.getEnlace() != null){
                nodoInicial = n.getEnlace();
                cantidad--;
            }else{
                nodoInicial = null;
                nodoFinal = null;
                cantidad--;
            }
        }else if( eliminar == cantidad){
            EstudianteNodo n = nodoInicial;
            for(int i = 0; i < cantidad; i++){
                if( n.getEnlace() == nodoFinal){
                    nodoFinal = n;
                    n.setEnlace(null);
                    cantidad--;
                }
                n =  n.getEnlace();
            }
        }else{
            EstudianteNodo n = nodoInicial;
            for(int i = 1; i < eliminar; i++){
                if(i == (eliminar - 1)){
                    n.setEnlace(n.getEnlace().getEnlace());
                    cantidad--;
                }
                n = n.getEnlace();
            }
        }
    }

    public String ordenAgregar(EstudianteNodo nodo){
        Double nodoPromedio = nodo.getPromedio();
        if(nodoInicial == null){
            //Validación si es el primer nodo en guardarse
            guardarNodo(nodo, 1);
        }else{
            EstudianteNodo nodoIni = nodoInicial;
            Double _promedio = nodoIni.getPromedio();
            if(_promedio < nodo.getPromedio()){
                //Si el promedio a evaluar es menor que el primer nodo registrado
                guardarNodo(nodo, 1);
                return "";
            }else if( nodoIni.getEnlace() == nodoFinal){
                //Caso donde solamente existen dos nodos registrados
                if(nodoPromedio < nodoFinal.getPromedio()){
                    //Validación si el promedio es menor que el promedio del segundo nodo
                    guardarNodo(nodo, 2);
                }else{
                    guardarNodo(nodo, 3);
                }
                return "";
            }
            //Caso si hay más de dos nodos registrados (hasta n nodos)
            int contador = 0;
            EstudianteNodo nodoActual = nodoIni;
            while(nodoIni != null && contador < cantidad){
                nodoActual = nodoActual.getEnlace();
                contador++;
                if(nodoActual.getPromedio() < nodoPromedio){
                    guardarNodo(nodo, contador); //POSIBLEMENTE SUMAR 1
                    return "";
                }
            }

            //Si no es menor a ninguno indica que debe de ir de ultimo
            guardarNodo(nodo, cantidad +1);
            return "";
        }
        


        return "";
    }
}
