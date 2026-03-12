package com.app.reservation.reservation_app.controllers;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ReservationController}.
 */
@Generated
public class ReservationController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ReservationController apply(RegisteredBean registeredBean,
      ReservationController instance) {
    AutowiredFieldValueResolver.forRequiredField("reservationServ").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
