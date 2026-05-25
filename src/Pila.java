/**
    Clase que implementa el comportamiento de una estructura LIFO (Last In, First Out).
    Almacena de forma apilada los elementos procesados para mantener un historial directo,
    permitiendo acceder de inmediato al ultimo turno operado para dar soporte a reversiones.
**/
public class Pila {
    
    private Nodo tope; 
    private int cantidad;

    public Pila() {
        this.tope = null;
        this.cantidad = 0;
    }

    //  Agrega un elemento en el extremo superior de la pila de historial.
    public void apilar(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (tope != null) {
            nuevoNodo.siguiente = tope;
            tope.anterior = nuevoNodo;
        }
        tope = nuevoNodo;
        cantidad++;
    }

    //  Remueve y extrae el elemento superior de la estructura.
    public Object desapilar() {
        if (esVacia()) return null;
        
        Object info = tope.contenido;
        tope = tope.siguiente;
        
        if (tope != null) {
            tope.anterior = null;
        }
        
        cantidad--;
        return info;
    }

    //  Retorna el elemento superior sin desvincularlo de la estructura
    public Object peek() {
        if (esVacia()) return null;
        return tope.contenido;
    }

    public int tamanio() {
        return cantidad;
    }

    public boolean esVacia() {
        return tope == null;
    }

    public boolean contiene(Object dato) {
        return buscar(dato) != null;
    }

    public void limpiar() {
        tope = null;
        cantidad = 0;
    }

    //  Imprime el contenido de los elementos ordenados desde el mas reciente
    public void mostrar() {
        if (esVacia()) {
            System.out.println("El historial de atencion esta vacio.");
            return;
        }
        Nodo aux = tope;
        while (aux != null) {
            System.out.println(aux.contenido);
            aux = aux.siguiente;
        }
    }

    //  Efectua un escaneo lineal sobre la pila buscando concordancia con un objeto
    public Object buscar(Object dato) {
        Nodo aux = tope;
        while (aux != null) {
            if (aux.contenido.equals(dato)) {
                return aux.contenido;
            }
            aux = aux.siguiente;
        }
        return null;
    }
}