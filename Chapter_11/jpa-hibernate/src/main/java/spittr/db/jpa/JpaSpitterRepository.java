package spittr.db.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import spittr.db.SpitterRepository;
import spittr.domain.Spitter;

@Repository
public class JpaSpitterRepository implements SpitterRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public long count() {
		return findAll().size();
	}

	public Spitter save(Spitter spitter) {
		entityManager.persist(spitter);
		return spitter;
	}

	public Spitter findOne(long id) {
		return entityManager.find(Spitter.class, id);
	}

	public Spitter findByUsername(String username) {		
		return (Spitter) entityManager.createQuery("select s from Spitter s where s.username=?").setParameter(1, username).getSingleResult();
	}

	public List<Spitter> findAll() {
		return (List<Spitter>) entityManager.createQuery("select s from Spitter s").getResultList();
	}
	
}
