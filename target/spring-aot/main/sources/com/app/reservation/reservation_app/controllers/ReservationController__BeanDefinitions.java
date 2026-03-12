package com.app.reservation.reservation_app.controllers;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * Bean definitions for {@link ReservationController}.
 */
@Generated
public class ReservationController__BeanDefinitions {
  /**
   * Get the bean definition for 'reservationController'.
   */
  public static BeanDefinition getReservationControllerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(ReservationController.class);
    InstanceSupplier<ReservationController> instanceSupplier = InstanceSupplier.using(ReservationController::new);
    instanceSupplier = instanceSupplier.andThen(ReservationController__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }
}
