package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link AuthServiceImpl}.
 */
@Generated
public class AuthServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'authServiceImpl'.
   */
  public static BeanDefinition getAuthServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthServiceImpl.class);
    InstanceSupplier<AuthServiceImpl> instanceSupplier = InstanceSupplier.using(AuthServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(AuthServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
