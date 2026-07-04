package ar.edu.utn.frba.dds.serviciomonitoreo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ServicioMonitoreoApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServicioMonitoreoApplication.class, args);
  }

}
