package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

public class AlertaTemperatura implements Alerta {
  private final double umbralTemperatura;

  public AlertaTemperatura(double umbralTemperatura) {
    this.umbralTemperatura = umbralTemperatura;
  }

  @Override
  public boolean evaluarCondiciones(Dato dato) {
    return dato.temperatura() > this.umbralTemperatura;
  }

  @Override
  public String obtenerMensajeDeAdvertencia(Dato dato) {
    return "Temperatura elevada: " + dato.temperatura() + "°C (Umbral establecido: " + umbralTemperatura + "°C)";
  }

}
