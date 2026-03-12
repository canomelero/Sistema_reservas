package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ResourceServiceImpl}.
 */
@Generated
public class ResourceServiceImpl__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ResourceServiceImpl apply(RegisteredBean registeredBean,
      ResourceServiceImpl instance) {
    AutowiredFieldValueResolver.forRequiredField("resourceRep").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
