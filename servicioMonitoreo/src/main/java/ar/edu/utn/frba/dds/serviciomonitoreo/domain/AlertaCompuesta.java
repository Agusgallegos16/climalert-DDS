package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import java.util.List;
import java.util.stream.Collectors;

public class AlertaCompuesta implements Alerta{
  private final List<Alerta> alertas;
  private final OperadorLogico operador;

  public AlertaCompuesta(OperadorLogico operador, Alerta... alertas) {
    this.operador = operador;
    this.alertas = List.of(alertas);
  }

  @Override
  public boolean evaluarCondiciones(Dato dato) {
    return switch (operador) {
      case AND -> alertas.stream().allMatch(a -> a.evaluarCondiciones(dato));
      case OR  -> alertas.stream().anyMatch(a -> a.evaluarCondiciones(dato));
    };
  }

  @Override
  public String obtenerMensajeDeAdvertencia(Dato dato) {
    String separador = operador == OperadorLogico.AND ? " Y " : " O ";
    return alertas.stream()
        .filter(a -> a.evaluarCondiciones(dato))
        .map(a -> a.obtenerMensajeDeAdvertencia(dato))
        .collect(Collectors.joining(separador));
  }

}
