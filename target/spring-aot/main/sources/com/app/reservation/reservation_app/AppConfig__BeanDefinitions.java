package com.app.reservation.reservation_app;

import org.springframework.aot.generate.Generated;
import org.springframework.beans.factory.aot.BeanInstanceSupplier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.InstanceSupplier;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Bean definitions for {@link AppConfig}.
 */
@Generated
public class AppConfig__BeanDefinitions {
  /**
   * Get the bean definition for 'appConfig'.
   */
  public static BeanDefinition getAppConfigBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AppConfig.class);
    beanDefinition.setTargetType(AppConfig.class);
    ConfigurationClassUtils.initializeConfigurationClass(AppConfig.class);
    InstanceSupplier<AppConfig> instanceSupplier = InstanceSupplier.using(AppConfig$$SpringCGLIB$$0::new);
    instanceSupplier = instanceSupplier.andThen(AppConfig__Autowiring::apply);
    beanDefinition.setInstanceSupplier(instanceSupplier);
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'passwordEncoder'.
   */
  private static BeanInstanceSupplier<PasswordEncoder> getPasswordEncoderInstanceSupplier() {
    return BeanInstanceSupplier.<PasswordEncoder>forFactoryMethod(AppConfig$$SpringCGLIB$$0.class, "passwordEncoder")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("appConfig", AppConfig.class).passwordEncoder());
  }

  /**
   * Get the bean definition for 'passwordEncoder'.
   */
  public static BeanDefinition getPasswordEncoderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(PasswordEncoder.class);
    beanDefinition.setFactoryBeanName("appConfig");
    beanDefinition.setInstanceSupplier(getPasswordEncoderInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authenticationManager'.
   */
  private static BeanInstanceSupplier<AuthenticationManager> getAuthenticationManagerInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AuthenticationManager>forFactoryMethod(AppConfig$$SpringCGLIB$$0.class, "authenticationManager", AuthenticationConfiguration.class)
            .withGenerator((registeredBean, args) -> registeredBean.getBeanFactory().getBean("appConfig", AppConfig.class).authenticationManager(args.get(0)));
  }

  /**
   * Get the bean definition for 'authenticationManager'.
   */
  public static BeanDefinition getAuthenticationManagerBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthenticationManager.class);
    beanDefinition.setFactoryBeanName("appConfig");
    beanDefinition.setInstanceSupplier(getAuthenticationManagerInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'userDetailsService'.
   */
  private static BeanInstanceSupplier<UserDetailsService> getUserDetailsServiceInstanceSupplier() {
    return BeanInstanceSupplier.<UserDetailsService>forFactoryMethod(AppConfig$$SpringCGLIB$$0.class, "userDetailsService")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("appConfig", AppConfig.class).userDetailsService());
  }

  /**
   * Get the bean definition for 'userDetailsService'.
   */
  public static BeanDefinition getUserDetailsServiceBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(UserDetailsService.class);
    beanDefinition.setFactoryBeanName("appConfig");
    beanDefinition.setInstanceSupplier(getUserDetailsServiceInstanceSupplier());
    return beanDefinition;
  }

  /**
   * Get the bean instance supplier for 'authenticationProvider'.
   */
  private static BeanInstanceSupplier<AuthenticationProvider> getAuthenticationProviderInstanceSupplier(
      ) {
    return BeanInstanceSupplier.<AuthenticationProvider>forFactoryMethod(AppConfig$$SpringCGLIB$$0.class, "authenticationProvider")
            .withGenerator((registeredBean) -> registeredBean.getBeanFactory().getBean("appConfig", AppConfig.class).authenticationProvider());
  }

  /**
   * Get the bean definition for 'authenticationProvider'.
   */
  public static BeanDefinition getAuthenticationProviderBeanDefinition() {
    RootBeanDefinition beanDefinition = new RootBeanDefinition(AuthenticationProvider.class);
    beanDefinition.setFactoryBeanName("appConfig");
    beanDefinition.setInstanceSupplier(getAuthenticationProviderInstanceSupplier());
    return beanDefinition;
  }
}
