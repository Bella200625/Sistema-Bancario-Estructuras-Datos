package Unidad3_SDK;


import java.util.Scanner;

/*
 Esta clase representa el punto de entrada del programa.

 Aquí se muestra el menú interactivo en consola
 y se capturan los datos ingresados por el usuario
 para enviarlos al GestorBanco...
*/
public class Main {

	public static void main(String[] args) {

		/*
		 Scanner permite leer datos desde la consola

		 Lo usamos para capturar opciones,
		 nombres, números de turno y demás datos
		*/
		Scanner teclado = new Scanner(System.in);

		// Creamos el gestor principal del sistema
		GestorBanco gestor = new GestorBanco();

		// Esta variable guarda la opción seleccionada en el menú
		int opcion = 0;

		/*
		 El ciclo do while mantiene el menú activo
		 hasta que el usuario decida salir.

		 La condición se repite mientras la opción
		 sea diferente de 15
		*/
		do {

			System.out.println("\n//======//=======//=======//=======//");
			System.out.println("         SISTEMA BANCARIO");
			System.out.println("//======//=======//=======//=======//");
			System.out.println("1. Registrar turno");
			System.out.println("2. Ver turnos registrados");
			System.out.println("3. Ver cola de pendientes");
			System.out.println("4. Atender siguiente turno");
			System.out.println("5. Ver historial");
			System.out.println("6. Buscar turno por número");
			System.out.println("7. Buscar cliente por nombre");
			System.out.println("8. Filtrar por estado");
			System.out.println("9. Ordenar por nombre");
			System.out.println("10. Ver estadísticas");
			System.out.println("11. Ver agrupados por trámite");
			System.out.println("12. Cancelar turno");
			System.out.println("13. Deshacer última atención");
			System.out.println("14. Ver tamaños de colecciones");
			System.out.println("15. Salir");
			System.out.println(" ");

			System.out.print("Usuario por favor Seleccione una opción: ");

			try {

				/*
				 nextLine() captura texto completo.
				 Luego Integer.parseInt convierte
				 ese texto en un número entero
				*/
				opcion = Integer.parseInt(teclado.nextLine());

				switch (opcion) {

					case 1:

						System.out.print("Número del turno: ");
						String numero = teclado.nextLine();

						System.out.print("Nombre del cliente: ");
						String nombre = teclado.nextLine();

						System.out.print("Tipo de trámite: ");
						String tramite = teclado.nextLine();

						System.out.print("Hora de llegada: ");
						String hora = teclado.nextLine();

						/*
						 Aquí creamos un nuevo objeto TurnoBancario
						 usando los datos ingresados en consola
						*/
						TurnoBancario nuevoTurno =
								new TurnoBancario(numero, nombre, tramite, hora);

						// El gestor registra el turno en las colecciones
						gestor.registrarTurno(nuevoTurno);

						break;

					case 2:

						gestor.mostrarTodosLosTurnos();

						break;

					case 3:

						gestor.mostrarPendientes();

						break;

					case 4:

						gestor.procesarSiguienteTurno();

						break;

					case 5:

						gestor.mostrarHistorial();

						break;

					case 6:

						System.out.print("Ingrese el número del turno: ");

						String numBuscar = teclado.nextLine();

						/*
						 El resultado puede devolver un objeto
						 o también null si no existe
						*/
						TurnoBancario encontradoMap =
								gestor.buscarPorNumero(numBuscar);

						if (encontradoMap != null) {

							System.out.println("Turno encontrado:");
							System.out.println(encontradoMap);
						}

						break;

					case 7:

						System.out.print("Nombre del cliente a buscar: ");

						String nomBuscar = teclado.nextLine();

						gestor.buscarPorNombreCliente(nomBuscar);

						break;

					case 8:

						System.out.print("Estado a filtrar: ");

						String estFiltrar = teclado.nextLine();

						gestor.filtrarPorEstado(estFiltrar);

						break;

					case 9:

						gestor.ordenarPorNombre();

						break;

					case 10:

						gestor.mostrarEstadisticas();

						break;

					case 11:

						gestor.mostrarAgrupadosPorTramite();

						break;

					case 12:

						System.out.print("Número del turno a cancelar: ");

						String numCancelar = teclado.nextLine();

						gestor.cancelarTurno(numCancelar);

						break;

					case 13:

						gestor.deshacerUltimoProcesamiento();

						break;

					case 14:

						gestor.verTamañoColecciones();

						break;

					case 15:

						System.out.println("Cerrando sistema...");

						break;

					default:

						System.out.println("Seleccione una opción válida.");
				}

			} catch (NumberFormatException e) {

				/*
				 Este error ocurre si el usuario
				 escribe letras en vez de números
				*/
				System.out.println("Por favor ingrese un número válido.");

			} catch (IllegalArgumentException | IllegalStateException e) {

				/*
				 Captura errores relacionados
				 con reglas del sistema
				*/
				System.out.println("Error: " + e.getMessage());
			}

		} while (opcion != 15);

		// Cerramos el Scanner para liberar los recursos
		teclado.close();
	}
}