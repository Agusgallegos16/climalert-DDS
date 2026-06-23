package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

public class WeatherApiAdapter implements ProveedorMeteorologico{

  @Override
  public Dato obtenerDato(){
    return new Dato();
  }
}
