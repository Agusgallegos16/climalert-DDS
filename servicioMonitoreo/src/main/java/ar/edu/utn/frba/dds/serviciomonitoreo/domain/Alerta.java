package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

public interface Alerta {
  public boolean evaluarCondiciones(Dato dato);
  String obtenerMensajeDeAdvertencia(Dato dato);
}
