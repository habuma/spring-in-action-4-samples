package spittr.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import spittr.db.SpittleRepository;
import spittr.domain.Spittle;

@Repository
public class JpaSpittleRepository implements SpittleRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public long count() {
    return findAll().size();
  }

  public List<Spittle> findRecent() {
    return findRecent(10);
  }

  public List<Spittle> findRecent(int count) {
    return (List<Spittle>) entityManager.createQuery("select s from Spittle s order by s.postedTime desc")
        .setMaxResults(count)
        .getResultList();
  }

  public Spittle findOne(long id) {
    return entityManager.find(Spittle.class, id);
  }

  public Spittle save(Spittle spittle) {
    entityManager.persist(spittle);
    return spittle;
  }

  public List<Spittle> findBySpitterId(long spitterId) {
    return (List<Spittle>) entityManager.createQuery("select s from Spittle s, Spitter sp where s.spitter = sp and sp.id=? order by s.postedTime desc")
        .setParameter(1, spitterId)
        .getResultList();
  }

  public void delete(long id) {
    entityManager.remove(findOne(id));
  }

  public List<Spittle> findAll() {
    return (List<Spittle>) entityManager.createQuery("select s from Spittle s").getResultList();
  }

}
