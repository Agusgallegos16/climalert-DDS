package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SistemaClimalert {
  @Getter
  private Dato datoClimaActual;
  private List<Dato> historialClimas;
  private ProveedorMeteorologico proveedor;
  private List<Alerta> alertasProgramadas;

  public SistemaClimalert(ProveedorMeteorologico proveedor) {
    this.proveedor = proveedor;
    this.historialClimas = new ArrayList<>();
    this.alertasProgramadas = new ArrayList<>();
  }

  public void registrarAlerta(Alerta alerta) {
    this.alertasProgramadas.add(alerta);
  }

  public Dato obtenerDato() {
    Dato nuevoDato = proveedor.obtenerDato();
    this.datoClimaActual = nuevoDato;
    this.historialClimas.add(nuevoDato);
    return nuevoDato;
  }

}
