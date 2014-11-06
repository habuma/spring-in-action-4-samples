package spittr;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

@Repository
public class NaiveMapBasedSpittleRepository implements SpittleRepository {

	private AtomicLong nextId = new AtomicLong(1L);
	
	private Map<Long, Spittle> spittles = new HashMap<Long, Spittle>();
	
	@Override
	public Spittle save(Spittle spittle) {
		try {
			Field idField = Spittle.class.getField("id");
			idField.setAccessible(true);
			long id = nextId.incrementAndGet();
			idField.set(spittle, id);
			spittles.put(id, spittle);
		} catch (Exception e) {
			// don't worry about it.
		}
		
		return spittle;
	}
	
	@Override
	public Spittle findOne(Long id) {
		return spittles.get(id);
	}
	
}
