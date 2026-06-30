package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

public class AlertaHumedad implements Alerta{
  private final double umbralHumedad;

  public AlertaHumedad(double umbralHumedad) {
    this.umbralHumedad = umbralHumedad;
  }

  @Override
  public boolean evaluarCondiciones(Dato dato) {
    return dato.humedad() > this.umbralHumedad;
  }

  @Override
  public String obtenerMensajeDeAdvertencia(Dato dato) {
    return "Humedad elevada: " + dato.humedad() + "% (Umbral: " + umbralHumedad + "%)";
  }
}
