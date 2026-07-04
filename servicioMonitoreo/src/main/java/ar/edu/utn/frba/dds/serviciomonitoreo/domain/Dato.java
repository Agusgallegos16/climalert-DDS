package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import java.time.LocalDateTime;

public record Dato(
    double temperatura,
    double humedad,
    double presion,
    double vientoVelocidad,
    String vientoDireccion,
    double precipitacion,
    int nubes,
    double sensacionTermica,
    double uv,
    String condicionTexto,
    String ubicacion,
    LocalDateTime fechaHora
) {
}
