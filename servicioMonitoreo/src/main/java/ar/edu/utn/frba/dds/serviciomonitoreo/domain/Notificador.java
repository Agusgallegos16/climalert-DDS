package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import org.springframework.stereotype.Component;

@Component
public class Notificador implements NotificadorPort {

  @Override
  public Notificacion armarNotificacion(Dato detalle, String destinatario, String mensaje) {
    return new Notificacion(detalle, destinatario, mensaje);
  }

  @Override
  public void despachar(Notificacion notificacion) {
    System.out.println("=============================================");
    System.out.println("ENVIANDO EMAIL ENVIADO A: " + notificacion.destinatario());
    System.out.println("Detalles del Clima en: " + notificacion.detalle().ubicacion());
    System.out.println("Fecha y Hora: " + notificacion.detalle().fechaHora());
    System.out.println("Alerta detectada: " + notificacion.mensaje());
    System.out.println("=============================================");
  }
}
