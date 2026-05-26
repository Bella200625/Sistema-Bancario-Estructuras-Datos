package Unidad3_SDK;

import java.util.*;
import java.util.stream.Collectors;

/*
 Esta clase funciona como la parte lógica principal del sistema bancario.

 Aquí se coordinan todas las operaciones relacionadas con los turnos:
 registrar clientes, atenderlos, buscarlos, filtrarlos,
 organizarlos y guardar historial.

 Para lograr eso utilizamos varias colecciones reales del SDK de Java
 porque cada una resuelve una necesidad distinta dentro del sistema.
*/
public class GestorBanco {

	/*
	 La lista general guarda absolutamente todos los turnos registrados.
	 Usamos ArrayList porque permite almacenar elementos en orden
	 y recorrerlos fácilmente cuando queremos mostrar información.
	*/
	private List<TurnoBancario> listaGeneral = new ArrayList<>();

	/*
	 La cola de pendientes representa la fila real de espera del banco.

	 Utilizamos Queue junto con LinkedList porque sigue el principio FIFO:
	 el primero en entrar es el primero en ser atendido.
	*/
	private Queue<TurnoBancario> colaPendientes = new LinkedList<>();

	/*
	 El historial funciona como una pila de elementos procesados.

	 Usamos Deque con ArrayDeque porque nos permite trabajar
	 con operaciones tipo Stack usando push y pop.

	 Aquí el último cliente atendido queda primero en el historial.
	*/
	private Deque<TurnoBancario> historialProcesados = new ArrayDeque<>();

	/*
	 Este mapa sirve para encontrar turnos rápidamente usando su número.

	 La clave del mapa es el número del turno (String)
	 y el valor es el objeto completo TurnoBancario.

	 Utilizamos HashMap porque permite búsquedas rápidas
	 sin necesidad de recorrer toda la lista manualmente.
	*/
	private Map<String, TurnoBancario> mapaBusquedaRapida = new HashMap<>();

	/*
	 Este método registra un nuevo turno dentro del sistema.

	 Primero usamos containsKey del Map para revisar
	 si el número del turno ya existe previamente.

	 Si existee, entonces lanzamos una excepción para evitar duplicados.

	 Si no existe, entonces:
	 - Se agrega a la lista general
	 - Se agrega a la cola de pendientes
	 - Se guarda también dentro del mapa
	*/
	public void registrarTurno(TurnoBancario turno) {

		if (mapaBusquedaRapida.containsKey(turno.getNumeroTurno())) {
			throw new IllegalArgumentException("Ese número de turno ya existe.");
		}

		listaGeneral.add(turno);

		colaPendientes.offer(turno);

		mapaBusquedaRapida.put(turno.getNumeroTurno(), turno);

		System.out.println("Turno registrado correctamente.");
	}

	/*
	 Este método recorre la lista principal
	 para mostrar todos los turnos almacenados.

	 Usamos forEach porque permite recorrer la colección
	 de una manera más limpia
	*/
	public void mostrarTodosLosTurnos() {

		if (listaGeneral.isEmpty()) {
			System.out.println("Todavía no hay turnos registrados.");
			return;
		}

		System.out.println("\n=== TURNOS REGISTRADOS ===");

		listaGeneral.forEach(System.out::println);
	}

	/*
	 Aquí mostramos la cola de clientes pendientes

	 Utilizamos:
	 - size() para saber cuántos clientes siguen esperando
	 - peek() para ver quién sigue en la fila
	   sin eliminarlo de la cola
	 - forEach() para recorrer todos los turnos pendientes
	*/
	public void mostrarPendientes() {

		if (colaPendientes.isEmpty()) {
			System.out.println("No hay turnos pendientes.");
			return;
		}

		System.out.println("\nPendientes actuales: " + colaPendientes.size());

		System.out.println("Siguiente turno: " + colaPendientes.peek());

		System.out.println("\n--- Cola de espera ---");

		colaPendientes.forEach(System.out::println);
	}

	/*
	 Este método atiende al siguiente cliente de la fila.

	 Utilizamos poll() porque:
	 - obtiene el primer elemento de la cola
	 - y además lo elimina automáticamente

	 Después cambiamos el estado del turno
	 y usamos push() para enviarlo al historial.
	*/
	public void procesarSiguienteTurno() {

		TurnoBancario siguiente = colaPendientes.poll();

		if (siguiente == null) {
			throw new IllegalStateException("No hay clientes pendientes.");
		}

		siguiente.setEstado("PROCESADO");

		historialProcesados.push(siguiente);

		System.out.println("Atendiendo ahora a: " + siguiente);
	}

	/*
	 Este método enseña todos los turnos ya atendidos.

	 Como el historial funciona como pila,
	 el último cliente atendido aparece primero.

	 Usamos peek() para consultar el último elemento
	 sin eliminarlo de la estructura.
	*/
	public void mostrarHistorial() {

		if (historialProcesados.isEmpty()) {
			System.out.println("El historial todavía está vacío.");
			return;
		}

		System.out.println("\nÚltimo atendido: " + historialProcesados.peek());

		System.out.println("Cantidad atendidos: " + historialProcesados.size());

		System.out.println("\n--- Historial ---");

		historialProcesados.forEach(System.out::println);
	}

	/*
	 Este método busca un turno usando directamente el mapa.

	 Gracias al HashMap podemos encontrar el turno
	 usando únicamente el número identificador
	 sin recorrer todas las colecciones.
	*/
	public TurnoBancario buscarPorNumero(String numeroTurno) {

		if (!mapaBusquedaRapida.containsKey(numeroTurno)) {

			System.out.println("No existe ningún turno con ese número.");

			return null;
		}

		return mapaBusquedaRapida.get(numeroTurno);
	}

	/*
	 Aquí usamos Streams para buscar clientes por nombre.

	 stream() abre un flujo de datos sobre la lista.
	 filter() filtra solamente los nombres que coincidan.
	 findFirst() devuelve la primera coincidencia encontrada.

	 equalsIgnoreCase() evita problemas con mayúsculas.
	*/
	public void buscarPorNombreCliente(String nombre) {

		Optional<TurnoBancario> resultado = listaGeneral.stream()

				.filter(t -> t.getNombreCliente().equalsIgnoreCase(nombre))

				.findFirst();

		if (resultado.isPresent()) {

			System.out.println("Turno encontrado:");

			System.out.println(resultado.get());

		} else {

			System.out.println("No encontré clientes con ese nombre.");
		}
	}

	/*
	 Este método filtra turnos según el estado solicitado.

	 Utilizamos Streams porque permite trabajar
	 las colecciones de manera más moderna y eficiente.

	 filter() deja pasar únicamente los elementos
	 cuyo estado coincida con el solicitado.
	*/
	public void filtrarPorEstado(String estado) {

		List<TurnoBancario> filtrados = listaGeneral.stream()

				.filter(t -> t.getEstado().equalsIgnoreCase(estado))

				.toList();

		if (filtrados.isEmpty()) {

			System.out.println("No hay turnos con estado: " + estado);

		} else {

			System.out.println("\nTurnos encontrados:");

			filtrados.forEach(System.out::println);
		}
	}

	/*
	 Este método ordena los turnos alfabéticamente.

	 sorted() organiza los elementos.
	 Comparator.comparing() indica que el criterio
	 será el nombre del cliente.
	*/
	public void ordenarPorNombre() {

		List<TurnoBancario> ordenados = listaGeneral.stream()

				.sorted(Comparator.comparing(TurnoBancario::getNombreCliente))

				.toList();

		System.out.println("\n=== TURNOS ORDENADOS ===");

		ordenados.forEach(System.out::println);
	}

	/*
	 Aquí generamos estadísticas generales del sistema.

	 groupingBy() agrupa los turnos según el estado.
	 counting() cuenta cuántos elementos hay en cada grupo.

	 El resultado final queda almacenado
	 en un Map<String, Long>:
	 - String = nombre del estado
	 - Long = cantidad de turnos
	*/
	public void mostrarEstadisticas() {

		System.out.println("\n=== ESTADÍSTICAS DEL BANCO ===");

		System.out.println("Total de turnos: " + listaGeneral.size());

		Map<String, Long> conteoPorEstado = listaGeneral.stream()

				.collect(Collectors.groupingBy(

						TurnoBancario::getEstado,

						Collectors.counting()
				));

		System.out.println("Resumen de estados: " + conteoPorEstado);
	}

	/*
	 Este método agrupa los turnos según el tipo de trámite.

	 groupingBy() crea automáticamente grupos
	 dependiendo del valor retornado por getTipoTramite().

	 El resultado es:
	 - String = tipo de trámite
	 - List<TurnoBancario> = lista de turnos asociados
	*/
	public void mostrarAgrupadosPorTramite() {

		Map<String, List<TurnoBancario>> agrupados = listaGeneral.stream()

				.collect(Collectors.groupingBy(TurnoBancario::getTipoTramite));

		agrupados.forEach((tramite, lista) -> {

			System.out.println("\nTrámite: " + tramite);

			System.out.println("Cantidad: " + lista.size());

			lista.forEach(t -> System.out.println("-> " + t));
		});
	}

	/*
	 Este método cancela un turno pendiente.

	 Primero buscamos el turno en el mapa.
	 Luego verificamos que siga pendiente.

	 removeIf() recorre la cola y elimina
	 el turno cuyo número coincida.
	*/
	public void cancelarTurno(String numeroTurno) {

		TurnoBancario turno = mapaBusquedaRapida.get(numeroTurno);

		if (turno == null) {
			throw new IllegalArgumentException("Ese turno no existe.");
		}

		if (!turno.getEstado().equalsIgnoreCase("PENDIENTE")) {
			throw new IllegalStateException("Solo se pueden cancelar turnos pendientes.");
		}

		turno.setEstado("CANCELADO");

		colaPendientes.removeIf(

				t -> t.getNumeroTurno().equalsIgnoreCase(numeroTurno)
		);

		System.out.println("Turno cancelado correctamente.");
	}

	/*
	 Este método deshace la última atención realizada.

	 pop() extrae el último elemento del historial.
	 Luego el estado vuelve a PENDIENTE
	 y offer() lo regresa nuevamente a la cola.
	*/
	public void deshacerUltimoProcesamiento() {

		if (historialProcesados.isEmpty()) {
			throw new IllegalStateException("No hay procesos para deshacer.");
		}

		TurnoBancario ultimo = historialProcesados.pop();

		ultimo.setEstado("PENDIENTE");

		colaPendientes.offer(ultimo);

		System.out.println("El turno volvió nuevamente a pendientes.");
	}

	/*
	 Este método muestra el tamaño actual
	 de todas las colecciones del sistema.

	 Sirve para comprobar que los datos
	 estén sincronizados correctamente.
	*/
	public void verTamañoColecciones() {

		System.out.println("\n=== TAMAÑO DE COLECCIONES ===");

		System.out.println("Lista General: " + listaGeneral.size());

		System.out.println("Cola Pendientes: " + colaPendientes.size());

		System.out.println("Historial: " + historialProcesados.size());

		System.out.println("Mapa de búsqueda: " + mapaBusquedaRapida.size());
	}
}