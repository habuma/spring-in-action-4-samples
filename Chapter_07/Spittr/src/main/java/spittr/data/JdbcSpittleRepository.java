package spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spittr.Spittle;

@Repository
public class JdbcSpittleRepository implements SpittleRepository {

  private JdbcOperations jdbc;

  @Autowired
  public JdbcSpittleRepository(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public List<Spittle> findRecentSpittles() {
    return jdbc.query(
        "select id, message, created_at, latitude, longitude" +
        " from Spittle" +
        " order by created_at desc limit 20",
        new SpittleRowMapper());
  }

  public List<Spittle> findSpittles(long max, int count) {
    return jdbc.query(
        "select id, message, created_at, latitude, longitude" +
        " from Spittle" +
        " where id < ?" +
        " order by created_at desc limit 20",
        new SpittleRowMapper(), max);
  }

  public Spittle findOne(long id) {
    List<Spittle> spittles = jdbc.query(
        "select id, message, created_at, latitude, longitude" +
        " from Spittle" +
        " where id = ?",
        new SpittleRowMapper(), id);
    return spittles.size() > 0 ? spittles.get(0) : null;
  }

  public void save(Spittle spittle) {
    jdbc.update(
        "insert into Spittle (message, created_at, latitude, longitude)" +
        " values (?, ?, ?, ?)",
        spittle.getMessage(),
        spittle.getTime(),
        spittle.getLatitude(),
        spittle.getLongitude());
  }

  private static class SpittleRowMapper implements RowMapper<Spittle> {
    public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Spittle(
          rs.getLong("id"),
          rs.getString("message"), 
          rs.getDate("created_at"), 
          rs.getDouble("longitude"), 
          rs.getDouble("latitude"));
    }
  }
  
}
