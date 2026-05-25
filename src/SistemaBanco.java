import java.util.Scanner;

/**
    Clase de control principal del flujo de la aplicacion.
    Despliega la interfaz de menu interactivo y gestiona la coordinacion 
    entre el registro general, la fila de espera y la pila del historial.
 **/
public class SistemaBanco {
    public static void main(String[] args) {
        
        Lista libroGeneral = new Lista();
        Cola filaEspera = new Cola();
        Pila historialAtendidos = new Pila();
        
        Scanner entrada = new Scanner(System.in);
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Registrar elemento");
            System.out.println("2. Ver todos los elementos registrados");
            System.out.println("3. Ver elementos pendientes");
            System.out.println("4. Procesar siguiente elemento");
            System.out.println("5. Ver historial de elementos procesados");
            System.out.println("6. Buscar elemento por codigo");
            System.out.println("7. Cancelar elemento pendiente");
            System.out.println("8. Deshacer ultimo procesamiento");
            System.out.println("9. Ver cantidad de elementos");
            System.out.println("10. Salir");
            System.out.print("Por facor usuario aeleccione una opcion (1, 2, 3,... o 10): ");
            
            try {
                opcion = Integer.parseInt(entrada.nextLine());
            } catch (Exception e) {
                System.out.println("ERROR: Por favor introduzca un numero que sea valido en el menú.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el ID del turno (Ej: T-11): ");
                    String id = entrada.nextLine();
                    System.out.print("Nombre del cliente: ");
                    String cliente = entrada.nextLine();
                    System.out.print("Tipo de tramite: ");
                    String tramite = entrada.nextLine();

                    TurnoBancario nuevoTurno = new TurnoBancario(id, cliente, tramite);

                    libroGeneral.agregar(nuevoTurno);
                    filaEspera.encolar(nuevoTurno);
                    System.out.println("Turno registrado con exito.");
                    break;

                case 2:
                    System.out.println("--- REGISTRO HISTORICO GENERAL DEL LIBRO ---");
                    libroGeneral.mostrarAdelante();
                    break;

                case 3:
                    System.out.println("--- CLIENTES ESPERANDO EN FILA ---");
                    filaEspera.mostrar();
                    break;

                case 4:
                    if (filaEspera.esVacia()) {
                        System.out.println("No hay nadie en la fila para atender.");
                    } else {
                        TurnoBancario atendido = (TurnoBancario) filaEspera.desencolar();
                        atendido.setEstado("Atendido"); 
                        historialAtendidos.apilar(atendido);
                        System.out.println("Atendiendo a: " + atendido.getCliente() + " [" + atendido.getID() + "]");
                    }
                    break;

                case 5:
                    System.out.println("--- HISTORIAL DE TICKETS EN EL GANCHO ---");
                    historialAtendidos.mostrar();
                    break;

                case 6:
                    System.out.print("Ingrese el ID del turno a buscar: ");
                    String idBuscar = entrada.nextLine();
                    TurnoBancario fantasmaBusqueda = new TurnoBancario(idBuscar, "", "");
                    TurnoBancario encontrado = (TurnoBancario) libroGeneral.buscarDato(fantasmaBusqueda);
                    
                    if (encontrado != null) {
                        System.out.println("Resultado: " + encontrado);
                    } else {
                        System.out.println("El turno con ID " + idBuscar + " no existe.");
                    }
                    break;

                case 7:
                    System.out.print("Ingrese el ID del turno que desea CANCELAR de la fila: ");
                    String idCancelar = entrada.nextLine();
                    TurnoBancario fantasmaCancelar = new TurnoBancario(idCancelar, "", "");

                    if (!filaEspera.contiene(fantasmaCancelar)) {
                        System.out.println("Ese turno no esta en la fila de pendientes.");
                    } else {
                        Cola colaAuxiliar = new Cola();
                        boolean cancelado = false;

                        while (!filaEspera.esVacia()) {
                            TurnoBancario actual = (TurnoBancario) filaEspera.desencolar();
                            if (actual.equals(fantasmaCancelar)) {
                                actual.setEstado("Cancelado"); 
                                cancelado = true;
                            } else {
                                colaAuxiliar.encolar(actual); 
                            }
                        }

                        while (!colaAuxiliar.esVacia()) {
                            filaEspera.encolar(colaAuxiliar.desencolar());
                        }

                        if (cancelado) {
                            System.out.println("El turno " + idCancelar + " fue cancelado correctamente.");
                        }
                    }
                    break;

                case 8:
                    if (historialAtendidos.esVacia()) {
                        System.out.println("No hay operaciones en el historial para deshacer.");
                    } else {
                        TurnoBancario devuelto = (TurnoBancario) historialAtendidos.desapilar();
                        devuelto.setEstado("Pendiente"); 
                        filaEspera.encolar(devuelto);
                        System.out.println("Se deshizo la atencion de: " + devuelto.getCliente() + ". Volvio a la fila.");
                    }
                    break;

                case 9:
                    System.out.println("Total en el libro general: " + libroGeneral.cuentaElementos());
                    System.out.println("Esperando en fila actualmente: " + filaEspera.tamanio());
                    System.out.println("Atendidos en el historial: " + historialAtendidos.tamanio());
                    break;

                case 10:
                    System.out.println("Saliendo del sistema del banco...");
                    break;

                default:
                    System.out.println("Opcion invalida, intente de nuevo...");
            }
        } while (opcion != 10);
        
        entrada.close();
    }
}