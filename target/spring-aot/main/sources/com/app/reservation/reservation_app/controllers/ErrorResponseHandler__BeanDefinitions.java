package com.app.reservation.reservation_app.controllers;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ErrorResponseHandler}.
 */
@Generated
public class ErrorResponseHandler__BeanDefinitions {
  /**
   * Get the bean definition for 'errorResponseHandler'.
   */
  public static BeanDefinition getErrorResponseHandlerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ErrorResponseHandler.class);
    beanDefinition.setInstanceSupplier(ErrorResponseHandler::new);
    return beanDefinition;
  }
}
