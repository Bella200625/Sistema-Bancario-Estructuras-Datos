package Unidad3_SDK;

import java.util.Objects;

/*
 Esta clase representa cada turno registrado dentro del sistema.
 Guarda la información principal del cliente,
 el trámite solicitado y el estado actual del turno.
*/
public class TurnoBancario {

	private String numeroTurno;
	private String nombreCliente;
	private String tipoTramite;
	private String horaLlegada;
	private String estado;

	/*
	 El constructor inicializa los datos básicos del turno
	 y automáticamente deja el estado como PENDIENTE.
	*/
	public TurnoBancario(String numeroTurno, String nombreCliente, String tipoTramite, String horaLlegada) {

		this.numeroTurno = numeroTurno;
		this.nombreCliente = nombreCliente;
		this.tipoTramite = tipoTramite;
		this.horaLlegada = horaLlegada;
		this.estado = "PENDIENTE";
	}

	public String getNumeroTurno() {
		return numeroTurno;
	}

	public void setNumeroTurno(String numeroTurno) {
		this.numeroTurno = numeroTurno;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getTipoTramite() {
		return tipoTramite;
	}

	public void setTipoTramite(String tipoTramite) {
		this.tipoTramite = tipoTramite;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	public void setHoraLlegada(String horaLlegada) {
		this.horaLlegada = horaLlegada;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// Organiza la información para mostrarla en la consola
	@Override
	public String toString() {

		return "Turno [" + numeroTurno + "] | Cliente: " + nombreCliente + " | Trámite: " + tipoTramite + " | Hora: " + horaLlegada + " | Estado: " + estado;
	}

	/*
	 Compara dos turnos usando únicamente
	 el número identificador del turno.
	*/
	@Override
	public boolean equals(Object o) {

		if (this == o) {
			return true;
		}

		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TurnoBancario that = (TurnoBancario) o;

		return Objects.equals(numeroTurno, that.numeroTurno);
	}

	/*
	 Genera el hash utilizando el número del turno
	 para mantener compatibilidad con HashMap.
	*/
	@Override
	public int hashCode() {
		return Objects.hash(numeroTurno);
	}
}