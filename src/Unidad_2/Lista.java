/**
    Clase que implementa una estructura de lista doblemente enlazada.
    Funciona como el registro historico general donde se almacenan absolutamente 
    todos los turnos que interactuan con el banco, sin importar su estado final.
 */
public class Lista {
    
    private Nodo cabeza; 
    private Nodo cola;   
    private int contador; 

    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.contador = 0;
    }

    //  Inserta un nuevo elemento al final de la estructura enlazada.
    public void agregar (Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.siguiente = nuevoNodo;
            nuevoNodo.anterior = cola;
            cola = nuevoNodo;
        }
        contador++;
    }

    //  Inserta un nuevo elemento en la posicion inicial de la lista.
    public void agregarAlInicio(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            nuevoNodo.siguiente = cabeza;
            cabeza.anterior = nuevoNodo;
            cabeza = nuevoNodo;
        }
        contador++;
    }

    //  Inserta un elemento en un indice especifico mediante recorrido secuencial.
    public void agregarEnPosicion(int posicion, Object dato) {
        if (posicion < 0 || posicion > contador) return; 
        
        if (posicion == 0) {
            agregarAlInicio(dato);
        } else if (posicion == contador) {
            agregar(dato);
        } else {
            Nodo nuevoNodo = new Nodo(dato);
            Nodo aux = cabeza;
            for (int i = 0; i < posicion; i++) {
                aux = aux.siguiente;
            }
            nuevoNodo.siguiente = aux;
            nuevoNodo.anterior = aux.anterior;
            aux.anterior.siguiente = nuevoNodo;
            aux.anterior = nuevoNodo;
            contador++;
        }
    }

    //  Remueve el primer nodo reconfigurando la referencia de la cabeza.
    public void eliminarPrimero() {
        if (cabeza == null) return;
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
        }
        contador--;
    }

    //  Remueve el ultimo nodo reconfigurando la referencia de la cola.
    public void eliminarUltimo() {
        if (cola == null) return;
        if (cabeza == cola) {
            cabeza = null;
            cola = null;
        } else {
            cola = cola.anterior;
            cola.siguiente = null;
        }
        contador--;
    }

    //  Localiza y remueve un elemento en base a un indice numérico.
    public void eliminarEnPosicion(int posicion) {
        if (posicion < 0 || posicion >= contador) return;
        
        if (posicion == 0) {
            eliminarPrimero();
        } else if (posicion == contador - 1) {
            eliminarUltimo();
        } else {
            Nodo aux = cabeza;
            for (int i = 0; i < posicion; i++) {
                aux = aux.siguiente;
            }
            aux.anterior.siguiente = aux.siguiente;
            aux.siguiente.anterior = aux.anterior;
            contador--;
        }
    }

    //  Retorna el contenido de un nodo basandose en su posicion fisica.
    public Object buscarDato(int posicion) {
        if (posicion < 0 || posicion >= contador) return null;
        Nodo aux = cabeza;
        for (int i = 0; i < posicion; i++) {
            aux = aux.siguiente;
        }
        return aux.contenido;
    }

    //  Busca un objeto por valor comparando identidades con el metodo equals.
    public Object buscarDato(Object dato) {
        Nodo aux = cabeza;
        while (aux != null) {
            if (aux.contenido.equals(dato)) {
                return aux.contenido;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    
    //  Verifica si el objeto existe dentro del recorrido de la lista
    public boolean contiene(Object dato) {
        return buscarDato(dato) != null;
    }

    
    //  Devuelve el tamaño actual de elementos registrados.
    public int cuentaElementos() {
        return contador;
    }

    //  Restablece las referencias de control vaciando la estructura.
    public void limpiar() {
        cabeza = null;
        cola = null;
        contador = 0;
    }

    //  Recorre la lista de forma ascendente desde la cabeza hasta la cola.
    public void mostrarAdelante() {
        if (cabeza == null) {
            System.out.println("No hay registros en la lista.");
            return;
        }
        Nodo aux = cabeza;
        while (aux != null) {
            System.out.println(aux.contenido);
            aux = aux.siguiente;
        }
    }

    //  Recorre la lista de forma descendente desde la cola hasta la cabeza.
    public void mostrarAtras() {
        if (cola == null) {
            System.out.println("No hay registros en la lista.");
            return;
        }
        Nodo aux = cola;
        while (aux != null) {
            System.out.println(aux.contenido);
            aux = aux.anterior;
        }
    }
}