package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ReservationServiceImpl}.
 */
@Generated
public class ReservationServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ReservationServiceImpl apply(RegisteredBean registeredBean,
      ReservationServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("reservationRep").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("userRep").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("resourceRep").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
