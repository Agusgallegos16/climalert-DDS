package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import java.time.LocalDateTime;

public record Dato(double temperatura, double humedad, String ubicacion, LocalDateTime fechaHora) {
}
