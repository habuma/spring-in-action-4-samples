package spittr.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spittr.Spitter;

@Repository
public class JdbcSpitterRepository implements SpitterRepository {
  
  private JdbcOperations jdbc;

  @Autowired
  public JdbcSpitterRepository(JdbcOperations jdbc) {
    this.jdbc = jdbc;
  }

  public Spitter save(Spitter spitter) {
    jdbc.update(
        "insert into Spitter (username, password, first_name, last_name, email)" +
        " values (?, ?, ?, ?, ?)",
        spitter.getUsername(),
        spitter.getPassword(),
        spitter.getFirstName(),
        spitter.getLastName(),
        spitter.getEmail());
    return spitter; // TODO: Determine value for id
  }

  public Spitter findByUsername(String username) {
    return jdbc.queryForObject(
        "select id, username, null, first_name, last_name, email from Spitter where username=?", 
        new SpitterRowMapper(), 
        username);
  }
  
  private static class SpitterRowMapper implements RowMapper<Spitter> {
    public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
      return new Spitter(
          rs.getLong("id"),
          rs.getString("username"),
          null,
          rs.getString("first_name"),
          rs.getString("last_name"),
          rs.getString("email"));
    }
  }

}
