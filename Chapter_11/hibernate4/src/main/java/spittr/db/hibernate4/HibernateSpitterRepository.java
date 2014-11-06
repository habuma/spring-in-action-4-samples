package spittr.db.hibernate4;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import spittr.db.SpitterRepository;
import spittr.domain.Spitter;

@Repository
public class HibernateSpitterRepository implements SpitterRepository {

	private SessionFactory sessionFactory;

	@Inject
	public HibernateSpitterRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;		 //<co id="co_InjectSessionFactory"/>
	}
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();//<co id="co_RetrieveCurrentSession"/>
	}
	
	public long count() {
		return findAll().size();
	}

	public Spitter save(Spitter spitter) {
		Serializable id = currentSession().save(spitter);  //<co id="co_UseCurrentSession"/>
		return new Spitter((Long) id, 
				spitter.getUsername(), 
				spitter.getPassword(), 
				spitter.getFullName(), 
				spitter.getEmail(), 
				spitter.isUpdateByEmail());
	}

	public Spitter findOne(long id) {
		return (Spitter) currentSession().get(Spitter.class, id); 
	}

	public Spitter findByUsername(String username) {		
		return (Spitter) currentSession() 
				.createCriteria(Spitter.class) 
				.add(Restrictions.eq("username", username))
				.list().get(0);
	}

	public List<Spitter> findAll() {
		return (List<Spitter>) currentSession() 
				.createCriteria(Spitter.class).list(); 
	}
	
}
