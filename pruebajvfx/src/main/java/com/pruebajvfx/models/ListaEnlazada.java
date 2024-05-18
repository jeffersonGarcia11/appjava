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
        Double nodoPromedioNuevo = nodo.getPromedio();
        if(this.nodoInicial == null){
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
                if(nodoPromedioNuevo < nodoFinal.getPromedio()){
                    //Validación si el promedio es menor que el promedio del segundo nodo
                    guardarNodo(nodo, 2);
                }else{ //Guardado de nodo al final si solamente existen 2 nodos registrados previamente
                    guardarNodo(nodo, 3);
                }
                return "";
            }else{
                /*
                for(int i = 0; i<cantidad; i++){
                    System.out.println("vuelta No. " + i);
                    EstudianteNodo nodoEstuEvaluar = getNodoEnPosicion(i);
                    Double promedioEvaluar = nodoEstuEvaluar.getPromedio();
                    if(nodoPromedioNuevo < promedioEvaluar){
                        guardarNodo(nodo, i++);
                    }else if(nodoPromedioNuevo == promedioEvaluar){
                        guardarNodo(nodo, i++);
                    }
                    System.out.println("Valor de nombre de nodo " + nodoEstuEvaluar.getPromedio());
                    
                }
                */
                boolean guardado = false;
                int i = 0; 
                while(guardado == false){

                    EstudianteNodo nodoEstuEvaluar = getNodoEnPosicion(i);
                    Double promedioEvaluar = nodoEstuEvaluar.getPromedio();
                    if(nodoPromedioNuevo < promedioEvaluar){
                        guardarNodo(nodo, i++);
                        guardado = true;
                    }else if(nodoPromedioNuevo == promedioEvaluar){
                        guardarNodo(nodo, i++);
                        guardado = true;
                    }
                    System.out.println("Valor de nombre de nodo " + nodoEstuEvaluar.getPromedio());
                    i++;
                }
                if(guardado == false){
                    //Guardará en ultima poscision el nodo si no encuentra 
                    guardarNodo(nodo, cantidad++);
                }
            }
            //Caso si hay más de dos nodos registrados (hasta n nodos)
            //Si no es menor a ninguno indica que debe de ir de ultimo
            guardarNodo(nodo, cantidad +1);
            return "";
        }
        return "";
    }

    // Para editar, evaluar primero hay que comparar si se cambió el valor del promedio, si no, solamente pedir la posición y editar el nodo
    // de lo contrario eliminar por la posición y luego agregar el nuevo. 
    //Para manipular data, la info se encuentra en el otro proyecto
}
