package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

public interface NotificadorPort {
  Notificacion armarNotificacion(Dato detalle, String destinatario, String mensaje);
  void despachar(Notificacion notificacion);
}
