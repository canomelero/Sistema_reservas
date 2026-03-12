package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ResourceServiceImpl}.
 */
@Generated
public class ResourceServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'resourceServiceImpl'.
   */
  public static BeanDefinition getResourceServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ResourceServiceImpl.class);
    InstanceSupplier<ResourceServiceImpl> instanceSupplier = InstanceSupplier.using(ResourceServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(ResourceServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
