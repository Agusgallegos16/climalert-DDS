package ar.edu.utn.frba.dds.serviciomonitoreo.scheduler;

import ar.edu.utn.frba.dds.serviciomonitoreo.service.ClimalertApplicationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ClimalertScheduler {

  private final ClimalertApplicationService climalertApplicationService;

  public ClimalertScheduler(ClimalertApplicationService climalertApplicationService) {
    this.climalertApplicationService = climalertApplicationService;
  }

  @Scheduled(fixedRate = 300000) // cada 5 minutos
  public void obtenerDatosClimaticos() {
    climalertApplicationService.obtenerYRegistrarClima();
  }

  @Scheduled(fixedRate = 60000) // cada 1 minuto
  public void analizarUltimaInformacion() {
    climalertApplicationService.analizarYNotificar();
  }
}
