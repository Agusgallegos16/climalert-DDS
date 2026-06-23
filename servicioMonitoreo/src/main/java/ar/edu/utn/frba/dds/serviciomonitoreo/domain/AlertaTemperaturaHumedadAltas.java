package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

public class AlertaTemperaturaHumedadAltas implements Alerta {

  @Override
  public boolean evaluarCondiciones(Dato dato) {
    return dato.temperatura() > 35.0 && dato.humedad() > 60.0;
  }
}
