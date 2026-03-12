package com.app.reservation.reservation_app.controllers;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.AutowiredFieldValueResolver;
import org.springframework.beans.factory.support.RegisteredBean;

/**
 * Autowiring for {@link ResourceController}.
 */
@Generated
public class ResourceController__Autowiring {
  /**
   * Apply the autowiring.
   */
  public static ResourceController apply(RegisteredBean registeredBean,
      ResourceController instance) {
    AutowiredFieldValueResolver.forRequiredField("resourceServ").resolveAndSet(registeredBean, instance);
    return instance;
  }
}
