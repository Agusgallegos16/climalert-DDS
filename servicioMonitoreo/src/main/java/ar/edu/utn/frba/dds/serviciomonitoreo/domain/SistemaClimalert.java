package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
public class SistemaClimalert {
  @Getter
  private List<Dato> historialClimas = new ArrayList<>();
  private List<Alerta> alertasProgramadas = new ArrayList<>();

  public void registrarAlerta(Alerta alerta) {
    this.alertasProgramadas.add(alerta);
  }

  public List<Alerta> registrarYEvaluarClima(Dato nuevoDato) {
    this.historialClimas.add(nuevoDato);

    return alertasProgramadas.stream()
        .filter(alerta -> alerta.evaluarCondiciones(nuevoDato))
        .toList();
  }

}
