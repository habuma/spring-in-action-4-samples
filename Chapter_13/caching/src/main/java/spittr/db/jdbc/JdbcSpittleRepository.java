package spittr.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import spittr.db.SpittleRepository;
import spittr.domain.Spitter;
import spittr.domain.Spittle;

@Component
public class JdbcSpittleRepository implements SpittleRepository {

	private static final String SELECT_SPITTLE = "select sp.id, s.id as spitterId, s.username, s.password, s.fullname, s.email, s.updateByEmail, sp.message, sp.postedTime from Spittle sp, Spitter s where sp.spitter = s.id";
	private static final String SELECT_SPITTLE_BY_ID = SELECT_SPITTLE + " and sp.id=?";
	private static final String SELECT_SPITTLES_BY_SPITTER_ID = SELECT_SPITTLE + " and s.id=? order by sp.postedTime desc";
	private static final String SELECT_RECENT_SPITTLES = SELECT_SPITTLE + " order by sp.postedTime desc limit ?";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JdbcSpittleRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long count() {
		return jdbcTemplate.queryForLong("select count(id) from Spittle");
	}

	public List<Spittle> findRecent() {
		return findRecent(10);
	}

	public List<Spittle> findRecent(int count) {
		return jdbcTemplate.query(SELECT_RECENT_SPITTLES, new SpittleRowMapper(), count);
	}

	public Spittle findOne(long id) {
		try {
			return jdbcTemplate.queryForObject(SELECT_SPITTLE_BY_ID, new SpittleRowMapper(), id);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<Spittle> findBySpitterId(long spitterId) {
		return jdbcTemplate.query(SELECT_SPITTLES_BY_SPITTER_ID, new SpittleRowMapper(), spitterId);
	}

	public Spittle save(Spittle spittle) {
		long spittleId = insertSpittleAndReturnId(spittle);
		return new Spittle(spittleId, spittle.getSpitter(), spittle.getMessage(), spittle.getPostedTime());
	}

	private long insertSpittleAndReturnId(Spittle spittle) {
			SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("Spittle");
			jdbcInsert.setGeneratedKeyName("id");
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("spitter", spittle.getSpitter().getId());
			args.put("message", spittle.getMessage());
			args.put("postedTime", spittle.getPostedTime());
			long spittleId = jdbcInsert.executeAndReturnKey(args).longValue();
			return spittleId;
	}

	public void delete(long id) {
		jdbcTemplate.update("delete from Spittle where id=?", id);
	}
	
	private static final class SpittleRowMapper implements RowMapper<Spittle> {
		public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
			long id = rs.getLong("id");
			String message = rs.getString("message");
			Date postedTime = rs.getTimestamp("postedTime");
			long spitterId = rs.getLong("spitterId");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String fullName = rs.getString("fullname");
			String email = rs.getString("email");
			boolean updateByEmail = rs.getBoolean("updateByEmail");
			Spitter spitter = new Spitter(spitterId, username, password, fullName, email, updateByEmail);
			return new Spittle(id, spitter, message, postedTime);
		}
	}

}
