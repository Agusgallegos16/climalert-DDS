package ar.edu.utn.frba.dds.serviciomonitoreo.service;

import ar.edu.utn.frba.dds.serviciomonitoreo.domain.Alerta;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.Dato;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.Notificacion;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.NotificadorPort;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.ProveedorMeteorologico;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.SistemaClimalert;

import java.util.List;
import java.util.stream.Collectors;

public class ClimalertApplicationService {
  private final ProveedorMeteorologico proveedorMeteorologico;
  private final NotificadorPort notificador;
  private final List<String> destinatariosAlerta;
  private final SistemaClimalert sistema;

  public ClimalertApplicationService(ProveedorMeteorologico proveedorMeteorologico, NotificadorPort notificador, List<String> destinatariosAlerta, SistemaClimalert sistema) {
    this.proveedorMeteorologico = proveedorMeteorologico;
    this.notificador = notificador;
    this.destinatariosAlerta = destinatariosAlerta;
    this.sistema = sistema;
  }

  // Cada 5 minutos, obtener datos y almacenarlos en el historial
  public void obtenerYRegistrarClima() {
    System.out.println(">> Obteniendo datos del clima...");
    Dato datoClima = proveedorMeteorologico.obtenerDato();
    if (datoClima == null) {
      throw new RuntimeException("Error al obtener datos climáticos: el proveedor devuelvió NULL. Verificá tu API Key y conexión.");
    }
    sistema.registrarClima(datoClima);
    System.out.println(">> Clima registrado con éxito");
  }

  // Cada 1 minuto, analizar el último dato y notificar si hay alertas
  public void analizarYNotificar() {
    System.out.println(">> Analizando alertas...");
    List<Dato> historial = sistema.getHistorialClimas();

    if (historial.isEmpty()) {
      System.out.println(">> No hay datos para analizar todavía.");
      return;
    }

    Dato ultimoDato = historial.getLast();
    List<Alerta> alertasDisparadas = sistema.evaluarAlertas(ultimoDato);
    System.out.println(">> Clima analizado en " + ultimoDato.ubicacion() + ". Alertas disparadas: " + alertasDisparadas.size());

    if (!alertasDisparadas.isEmpty()) {
      String mensaje = alertasDisparadas.stream()
          .map(alerta -> alerta.obtenerMensajeDeAdvertencia(ultimoDato))
          .collect(Collectors.joining(" / "));

      for (String email : this.destinatariosAlerta) {
        Notificacion notificacion = notificador.armarNotificacion(ultimoDato, email, mensaje);
        notificador.despachar(notificacion);
      }
    }
  }
}

