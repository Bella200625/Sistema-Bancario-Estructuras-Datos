/**
    Clase que implementa el comportamiento de una estructura FIFO (First In, First Out).
    Administra de forma secuencial la fila de espera del banco, asegurando que 
    los clientes sean atendidos estrictamente bajo el orden de llegada.
 */
public class Cola {
    
    private Nodo primero; 
    private Nodo ultimo;  
    private int cantidad;

    public Cola() {
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    //  Inserta un elemento al final de la fila de espera.

    public void encolar(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (esVacia()) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
        } else {
            ultimo.siguiente = nuevoNodo;
            nuevoNodo.anterior = ultimo;
            ultimo = nuevoNodo;
        }
        cantidad++;
    }

    //  Extrae y devuelve el primer elemento de la fila para su procesamiento.
    public Object desencolar() {
        if (esVacia()) return null;
        
        Object info = primero.contenido;
        primero = primero.siguiente;
        
        if (primero == null) {
            ultimo = null; 
        } else {
            primero.anterior = null;
        }
        
        cantidad--;
        return info;
    }

    //  Permite examinar el proximo elemento a ser atendido sin retirarlo
    public Object peek() {
        if (esVacia()) return null;
        return primero.contenido;
    }

    public int tamanio() {
        return cantidad;
    }

    public boolean esVacia() {
        return primero == null;
    }

    //  Comprueba si un turno especifico se encuentra haciendo fila en este momento
    public boolean contiene(Object dato) {
        Nodo aux = primero;
        while (aux != null) {
            if (aux.contenido.equals(dato)) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    public void limpiar() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    //  Imprime en pantalla el estado actual de los turnos en espera
    public void mostrar() {
        if (esVacia()) {
            System.out.println("La fila de espera esta vacia.");
            return;
        }
        Nodo aux = primero;
        while (aux != null) {
            System.out.println(aux.contenido);
            aux = aux.siguiente;
        }
    }
}