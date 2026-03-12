package com.app.reservation.reservation_app.controllers;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResourceController}.
 */
@Generated
public class ResourceController__BeanDefinitions {
  /**
   * Get the bean definition for 'resourceController'.
   */
  public static BeanDefinition getResourceControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResourceController.class);
    InstanceSupplier<ResourceController> instanceSupplier = InstanceSupplier.using(ResourceController::new);
    instanceSupplier = instanceSupplier.andThen(ResourceController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
