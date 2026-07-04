package ar.edu.utn.frba.dds.serviciomonitoreo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WeatherResponseDto(
    Location location,
    Current current
) {
  
  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Location(
      String name
  ) {}

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Current(
      @JsonProperty("temp_c")
      double tempC,
      int humidity,
      @JsonProperty("pressure_mb")
      double pressureMb,
      @JsonProperty("wind_kph")
      double windKph,
      @JsonProperty("wind_dir")
      String windDir,
      @JsonProperty("precip_mm")
      double precipMm,
      int cloud,
      @JsonProperty("feelslike_c")
      double feelslikeC,
      double uv,
      Condition condition
  ) {}

  @JsonIgnoreProperties(ignoreUnknown = true)
  public record Condition(
      String text
  ) {}
}
