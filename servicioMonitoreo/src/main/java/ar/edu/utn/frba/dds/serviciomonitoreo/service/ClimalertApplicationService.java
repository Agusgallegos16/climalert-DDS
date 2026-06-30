package ar.edu.utn.frba.dds.serviciomonitoreo.service;

import ar.edu.utn.frba.dds.serviciomonitoreo.domain.Alerta;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.Dato;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.Notificacion;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.NotificadorPort;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.ProveedorMeteorologico;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.SistemaClimalert;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClimalertApplicationService {
  private final ProveedorMeteorologico proveedorMeteorologico;
  private final NotificadorPort notificadorEmail;
  private final List<String> destinatariosAlerta;

  public ClimalertApplicationService(ProveedorMeteorologico proveedorMeteorologico, NotificadorPort notificadorEmail, List<String> destinatariosAlerta) {
    this.proveedorMeteorologico = proveedorMeteorologico;
    this.notificadorEmail = notificadorEmail;
    this.destinatariosAlerta = destinatariosAlerta;
  }

  public void procesarMonitoreoClimatico(SistemaClimalert sistema) {

    Dato datoMedido = proveedorMeteorologico.obtenerDato();
    List<Alerta> alertasDisparadas = sistema.registrarYEvaluarClima(datoMedido);

    if (!alertasDisparadas.isEmpty()) {
      String mensaje = alertasDisparadas.stream()
          .map(alerta -> alerta.obtenerMensajeDeAdvertencia(datoMedido))
          .collect(Collectors.joining(" / "));

      for (String email : this.destinatariosAlerta) {
        Notificacion notificacion = notificadorEmail.armarNotificacion(datoMedido, email, mensaje);
        notificadorEmail.despachar(notificacion);
      }
    }
  }
}
