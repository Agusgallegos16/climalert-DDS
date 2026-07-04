package ar.edu.utn.frba.dds.serviciomonitoreo.adapter;

import ar.edu.utn.frba.dds.serviciomonitoreo.domain.Dato;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.ProveedorMeteorologico;
import ar.edu.utn.frba.dds.serviciomonitoreo.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Component
public class WeatherApiAdapter implements ProveedorMeteorologico {

  private final RestClient restClient;

  @Value("${weatherapi.key}")
  private String apiKey;

  @Value("${weatherapi.location}")
  private String location;

  public WeatherApiAdapter(@Value("${weatherapi.base-url}") String baseUrl) {
    this.restClient = RestClient.builder().baseUrl(baseUrl).build();
  }

  @Override
  public Dato obtenerDato() {
    try {
      WeatherResponseDto response = restClient.get()
          .uri(uriBuilder -> uriBuilder
              .path("/current.json")
              .queryParam("key", apiKey)
              .queryParam("q", location)
              .build())
          .retrieve()
          .body(WeatherResponseDto.class);

      if (response != null && response.current() != null && response.location() != null) {
        return new Dato(
            response.current().tempC(),
            response.current().humidity(),
            response.current().pressureMb(),
            response.current().windKph(),
            response.current().windDir(),
            response.current().precipMm(),
            response.current().cloud(),
            response.current().feelslikeC(),
            response.current().uv(),
            response.current().condition() != null ? response.current().condition().text() : "Desconocido",
            response.location().name(),
            LocalDateTime.now()
        );
      }
    } catch (Exception e) {
      System.err.println("Error al consultar WeatherAPI: " + e.getMessage());
    }
    return null;
  }

}

