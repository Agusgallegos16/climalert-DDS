package ar.edu.utn.frba.dds.serviciomonitoreo.config;

import ar.edu.utn.frba.dds.serviciomonitoreo.domain.NotificadorPort;
import ar.edu.utn.frba.dds.serviciomonitoreo.domain.ProveedorMeteorologico;
import ar.edu.utn.frba.dds.serviciomonitoreo.service.ClimalertApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ClimalertConfig {

  @Bean
  public ClimalertApplicationService climaApplicationService(
      ProveedorMeteorologico proveedorMeteorologico,
      NotificadorPort notificadorEmail) {

    List<String> destinatarios = List.of("emergencias@clima.com", "monitoreo@clima.com");

    return new ClimalertApplicationService(proveedorMeteorologico, notificadorEmail, destinatarios);
  }
}