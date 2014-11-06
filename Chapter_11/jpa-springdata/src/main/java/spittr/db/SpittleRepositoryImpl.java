package spittr.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import spittr.domain.Spittle;

public class SpittleRepositoryImpl implements SpittleRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Spittle> findRecent() {
    return findRecent(10);
  }

  public List<Spittle> findRecent(int count) {
    return (List<Spittle>) entityManager.createQuery("select s from Spittle s order by s.postedTime desc")
        .setMaxResults(count)
        .getResultList();
  }
  
}
