package spittr.db.hibernate4;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import spittr.db.SpittleRepository;
import spittr.domain.Spittle;

@Repository
public class HibernateSpittleRepository implements SpittleRepository {

	private SessionFactory sessionFactory;

	@Inject
	public HibernateSpittleRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();//<co id="co_RetrieveCurrentSession"/>
	}

	public long count() {
		return findAll().size(); 
	}

	public List<Spittle> findRecent() {
		return findRecent(10);
	}

	public List<Spittle> findRecent(int count) {
		return (List<Spittle>) spittleCriteria()
				.setMaxResults(count)
				.list();
	}

	public Spittle findOne(long id) {
		return (Spittle) currentSession().get(Spittle.class, id);
	}

	public Spittle save(Spittle spittle) {
		Serializable id = currentSession().save(spittle);
		return new Spittle(
			(Long) id, 
			spittle.getSpitter(), 
			spittle.getMessage(), 
			spittle.getPostedTime());
	}

	public List<Spittle> findBySpitterId(long spitterId) {
		return spittleCriteria()
				.add(Restrictions.eq("spitter.id", spitterId))
				.list();
	}
	
	public void delete(long id) {
		currentSession().delete(findOne(id));
	}
	
	public List<Spittle> findAll() {
		return (List<Spittle>) spittleCriteria().list(); 
	}
	
	private Criteria spittleCriteria() {
		return currentSession() 
				.createCriteria(Spittle.class)
				.addOrder(Order.desc("postedTime"));
	}

}
