package spittr.db.hibernate4;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import spittr.db.SpittleRepository;
import spittr.domain.Spitter;
import spittr.domain.Spittle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=RepositoryTestConfig.class)
public class SpittleRepositoryTest {
	
	@Autowired
	SpittleRepository spittleRepository;

	@Test
	@Transactional
	public void count() {
		assertEquals(15, spittleRepository.count());
	}

	@Test
	@Transactional
	public void findRecent() {
		// default case
		{
			List<Spittle> recent = spittleRepository.findRecent();
			assertRecent(recent, 10);
		}
		
		// specific count case
		{
			List<Spittle> recent = spittleRepository.findRecent(5);
			assertRecent(recent, 5);
		}
	}

	@Test
	@Transactional
	public void findOne() {
		Spittle thirteen = spittleRepository.findOne(13);
		assertEquals(13, thirteen.getId().longValue());
		assertEquals("Bonjour from Art!", thirteen.getMessage());
		assertEquals(1332682500000L, thirteen.getPostedTime().getTime());
		assertEquals(4, thirteen.getSpitter().getId().longValue());
		assertEquals("artnames", thirteen.getSpitter().getUsername());
		assertEquals("password", thirteen.getSpitter().getPassword());
		assertEquals("Art Names", thirteen.getSpitter().getFullName());
		assertEquals("art@habuma.com", thirteen.getSpitter().getEmail());
		assertTrue(thirteen.getSpitter().isUpdateByEmail());
	}

	@Test
	@Transactional
	public void findBySpitter() {
		List<Spittle> spittles = spittleRepository.findBySpitterId(4L);
		assertEquals(11, spittles.size());
		for (int i = 0; i < 11; i++) {
			assertEquals(15-i, spittles.get(i).getId().longValue());
		}
	}
	
	@Test
	@Transactional
	public void save() {
		assertEquals(15, spittleRepository.count());
		Spitter spitter = spittleRepository.findOne(13).getSpitter();
		Spittle spittle = new Spittle(null, spitter, "Un Nuevo Spittle from Art", new Date());
		Spittle saved = spittleRepository.save(spittle);
		assertEquals(16, spittleRepository.count());
		assertNewSpittle(saved);
		assertNewSpittle(spittleRepository.findOne(16L));
	}

	@Test
	@Transactional
	public void delete() {
		assertEquals(15, spittleRepository.count());
		assertNotNull(spittleRepository.findOne(13));
		spittleRepository.delete(13L);
		assertEquals(14, spittleRepository.count());
		assertNull(spittleRepository.findOne(13));
	}
	
	private void assertRecent(List<Spittle> recent, int count) {
		long[] recentIds = new long[] {3,2,1,15,14,13,12,11,10,9};
		assertEquals(count, recent.size());
		for (int i = 0; i < count; i++) {
			assertEquals(recentIds[i], recent.get(i).getId().longValue());
		}
	}
	
	private void assertNewSpittle(Spittle spittle) {
		assertEquals(16, spittle.getId().longValue());
	}
	
}
