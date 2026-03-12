package com.app.reservation.reservation_app.repositories;

import com.app.reservation.reservation_app.models.Token;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.Long;
import java.lang.String;
import java.util.List;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link TokenRepository}.
 */
@Generated
public class TokenRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public TokenRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link TokenRepository#findAllById(java.lang.Long)}.
   */
  public List<Token> findAllById(Long id) {
    String queryString = "SELECT t FROM Token t WHERE t.id = :id";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("id", id);

    return (List<Token>) query.getResultList();
  }
}
