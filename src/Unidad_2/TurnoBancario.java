/**
    Clase que representa la entidad principal del sistema.
    Modela el ticket de turno que se asigna a cada cliente al ingresar al banco.
    Controla los datos basicos del usuario, el tramite solicitado y el estado actual en el flujo.
 */
public class TurnoBancario {
    
    private String ID; 
    private String cliente;  
    private String tramite;    
    private String estado; 

    /**
        Constructor para inicializar el turno con sus datos esenciales.
        Al empezar, el estado se asigna automaticamente como "Pendiente" 
        lo que quiere decir que el cliente siempre inicia esperando en la fila.
     */
    public TurnoBancario(String ID, String cliente, String tramite) {
        this.ID = ID;
        this.cliente = cliente;
        this.tramite = tramite;
        this.estado = "Pendiente"; 
    }

    /**
        Metodos Getters y Setters.
        Sirven para acceder y modificar de forma segura los atributos privados de la clase
        respetando el principio de encapsulamiento de la programacion orientada a objetos.
     */
    public String getID() { return ID; }
    public void setID(String ID) { this.ID = ID; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getTramite() { return tramite; }
    public void setTramite(String tramite) { this.tramite = tramite; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    /**
        Metodo para la representacion en texto del objeto.
        Permite que al imprimir el turno se visualicen sus datos legibles en la consola.
     */
    @Override
    public String toString() {
        return "Turno: " + ID + " | Cliente: " + cliente + " | Tramite: " + tramite + " | Estado: " + estado;
    }

    /**
        Metodo para evaluar la igualdad entre dos objetos.
        Sobrescribe el metodo original para establecer que dos turnos son iguales 
        si y solo si comparten el mismo ID o identificador principal.
     */
    @Override
    public boolean equals(Object objeto) {
        if (objeto == null) return false;
        if (!(objeto instanceof TurnoBancario)) return false;
        TurnoBancario otroTurno = (TurnoBancario) objeto;
        return this.ID.equalsIgnoreCase(otroTurno.ID);
    }
}