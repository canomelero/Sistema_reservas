package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link UserServiceImpl}.
 */
@Generated
public class UserServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static UserServiceImpl apply(RegisteredBean registeredBean, UserServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("userRep").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
