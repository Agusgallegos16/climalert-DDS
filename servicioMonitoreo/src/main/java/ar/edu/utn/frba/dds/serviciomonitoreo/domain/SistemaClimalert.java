package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
public class SistemaClimalert {
  @Getter
  @Builder.Default
  private List<Dato> historialClimas = new ArrayList<>();
  @Builder.Default
  private List<Alerta> alertasProgramadas = new ArrayList<>();

  public void registrarAlerta(Alerta alerta) {
    this.alertasProgramadas.add(alerta);
  }

  public void registrarClima(Dato nuevoDato){
    this.historialClimas.add(nuevoDato);
  }

  public List<Alerta> evaluarAlertas(Dato dato) {
    return alertasProgramadas.stream()
        .filter(alerta -> alerta.evaluarCondiciones(dato))
        .toList();
  }

}
