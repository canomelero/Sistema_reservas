package com.app.reservation.reservation_app.services;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ReservationServiceImpl}.
 */
@Generated
public class ReservationServiceImpl__BeanDefinitions {
  /**
   * Get the bean definition for 'reservationServiceImpl'.
   */
  public static BeanDefinition getReservationServiceImplBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReservationServiceImpl.class);
    InstanceSupplier<ReservationServiceImpl> instanceSupplier = InstanceSupplier.using(ReservationServiceImpl::new);
    instanceSupplier = instanceSupplier.andThen(ReservationServiceImpl__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
