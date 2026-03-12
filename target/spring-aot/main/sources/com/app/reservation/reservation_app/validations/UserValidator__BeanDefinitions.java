package com.app.reservation.reservation_app.validations;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link UserValidator}.
 */
@Generated
public class UserValidator__BeanDefinitions {
  /**
   * Get the bean definition for 'userValidator'.
   */
  public static BeanDefinition getUserValidatorBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UserValidator.class);
    beanDefinition.setInstanceSupplier(UserValidator::new);
    return beanDefinition;
  }
}
