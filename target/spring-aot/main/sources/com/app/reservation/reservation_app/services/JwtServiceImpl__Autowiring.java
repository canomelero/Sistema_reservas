package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link JwtServiceImpl}.
 */
@Generated
public class JwtServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static JwtServiceImpl apply(RegisteredBean registeredBean, JwtServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("jwtSecretKey").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("jwtExpiration").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("refreshTokenExpiration").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
