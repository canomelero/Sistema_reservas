package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link AuthServiceImpl}.
 */
@Generated
public class AuthServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static AuthServiceImpl apply(RegisteredBean registeredBean, AuthServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("tokenRep").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("encoder").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("userRep").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("jwtServ").resolveAndSet(registeredBean, instance);
    AutowiredFieldValueResolver.forRequiredField("authManager").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
