package com.app.reservation.reservation_app;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ReservationAppApplication}.
 */
@Generated
public class ReservationAppApplication__BeanDefinitions {
  /**
   * Get the bean definition for 'reservationAppApplication'.
   */
  public static BeanDefinition getReservationAppApplicationBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReservationAppApplication.class);
    beanDefinition.setInstanceSupplier(ReservationAppApplication::new);
    return beanDefinition;
  }
}
