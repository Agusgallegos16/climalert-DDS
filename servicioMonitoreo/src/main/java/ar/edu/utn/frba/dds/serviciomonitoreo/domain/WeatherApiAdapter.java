package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class WeatherApiAdapter implements ProveedorMeteorologico {

  @Override
  public Dato obtenerDato() {
    // Retornamos un dato dummy para simular el comportamiento de la API externa
    return new Dato(36.0, 85.0, "Buenos Aires", LocalDateTime.now());
  }
}
