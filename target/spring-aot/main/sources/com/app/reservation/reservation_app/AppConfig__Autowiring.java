package com.app.reservation.reservation_app;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link AppConfig}.
 */
@Generated
public class AppConfig__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static AppConfig apply(RegisteredBean registeredBean, AppConfig instance) {
    AutowiredFieldValueResolver.forRequiredField("userRep").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
