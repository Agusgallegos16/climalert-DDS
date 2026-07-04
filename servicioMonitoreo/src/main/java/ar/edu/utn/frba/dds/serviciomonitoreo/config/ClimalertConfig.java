package ar.edu.utn.frba.dds.serviciomonitoreo.config;

import ar.edu.utn.frba.dds.serviciomonitoreo.domain.*;
import ar.edu.utn.frba.dds.serviciomonitoreo.service.ClimalertApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClimalertConfig {

  @Bean
  public SistemaClimalert sistemaClimalert() {
    SistemaClimalert sistema = SistemaClimalert.builder().build();

    // Registrar alertas programadas
    sistema.registrarAlerta(new AlertaCompuesta(
        OperadorLogico.AND,
        new AlertaTemperatura(35),
        new AlertaHumedad(60)
    ));

    return sistema;
  }

  @Bean
  public ClimalertApplicationService climaApplicationService(
      ProveedorMeteorologico proveedorMeteorologico,
      NotificadorPort notificador,
      SistemaClimalert sistemaClimalert) {

    List<String> destinatarios = List.of("admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com");

    return new ClimalertApplicationService(proveedorMeteorologico, notificador, destinatarios, sistemaClimalert);
  }
}