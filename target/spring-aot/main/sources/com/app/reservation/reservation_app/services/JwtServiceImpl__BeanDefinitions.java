package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link JwtServiceImpl}.
 */
@Generated
public class JwtServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'jwtServiceImpl'.
   */
  public static BeanDefinition getJwtServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(JwtServiceImpl.class);
    InstanceSupplier<JwtServiceImpl> instanceSupplier = InstanceSupplier.using(JwtServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(JwtServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
