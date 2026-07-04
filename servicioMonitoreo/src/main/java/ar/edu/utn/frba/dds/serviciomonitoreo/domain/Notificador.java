package ar.edu.utn.frba.dds.serviciomonitoreo.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Notificador implements NotificadorPort {

  private final JavaMailSender mailSender;

  @Value("${spring.mail.username}")
  private String remitente;

  public Notificador(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  @Override
  public Notificacion armarNotificacion(Dato detalle, String destinatario, String mensaje) {
    return new Notificacion(detalle, destinatario, mensaje);
  }

  @Override
  public void despachar(Notificacion notificacion) {
    try {
      SimpleMailMessage mail = new SimpleMailMessage();
      mail.setFrom(remitente);
      mail.setTo(notificacion.destinatario());
      mail.setSubject("Climalert - Alerta Climática - " + notificacion.detalle().ubicacion());
      mail.setText(
          "Se detectaron condiciones climáticas críticas:\n\n"
          + "Detalle de alertas:\n" + notificacion.mensaje() + "\n\n"
          + "--- Datos Climáticos Completos ---\n"
          + "Ubicación: " + notificacion.detalle().ubicacion() + "\n"
          + "Fecha y Hora: " + notificacion.detalle().fechaHora() + "\n"
          + "Temperatura: " + notificacion.detalle().temperatura() + " °C\n"
          + "Sensación Térmica: " + notificacion.detalle().sensacionTermica() + " °C\n"
          + "Humedad: " + notificacion.detalle().humedad() + " %\n"
          + "Estado del Clima: " + notificacion.detalle().condicionTexto() + "\n"
          + "Presión Atmosférica: " + notificacion.detalle().presion() + " hPa\n"
          + "Velocidad del Viento: " + notificacion.detalle().vientoVelocidad() + " km/h\n"
          + "Dirección del Viento: " + notificacion.detalle().vientoDireccion() + "\n"
          + "Precipitaciones: " + notificacion.detalle().precipitacion() + " mm\n"
          + "Nubosidad: " + notificacion.detalle().nubes() + " %\n"
          + "Índice UV: " + notificacion.detalle().uv()
      );

      mailSender.send(mail);
      System.out.println("Email enviado exitosamente a: " + notificacion.destinatario());
    } catch (Exception e) {
      System.err.println("Error al enviar email a " + notificacion.destinatario() + ": " + e.getMessage());
    }
  }
}
