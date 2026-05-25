/**
    Clase que funciona como el contenedor o eslabon fundamental.
    Su proposito es almacenar el objeto de datos y mantener las referencias basicas
    de los elementos contiguos para permitir la construccion de estructuras enlazadas.
 */
public class Nodo {
    
    public Object contenido; 
    public Nodo siguiente; 
    public Nodo anterior;  

    /**
        Constructor del nodo.
        Recibe el objeto que va a guardar y deja los punteros de navegacion 
        inicializados en nulo hasta que se conecte a una estructura.
     */
    public Nodo(Object contenido) {
        this.contenido = contenido;
        this.siguiente = null;
        this.anterior = null;
    }
}