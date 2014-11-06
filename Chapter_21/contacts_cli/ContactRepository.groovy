@Grab("h2")

import java.sql.ResultSet

class ContactRepository {

  @Autowired
  JdbcTemplate jdbc

  List<Contact> findAll() {
    jdbc.query(
        "select id, firstName, lastName, phoneNumber, emailAddress " +
        "from contacts order by lastName",
          new RowMapper<Contact>() {
            Contact mapRow(ResultSet rs, int rowNum) {
              new Contact(
                id: rs.getLong(1), 
                firstName: rs.getString(2),
                lastName: rs.getString(3), 
                phoneNumber: rs.getString(4),
                emailAddress: rs.getString(5))
            }
          })
  }

  void save(Contact contact) {
    jdbc.update(
        "insert into contacts " +
        "(firstName, lastName, phoneNumber, emailAddress) " +
        "values (?, ?, ?, ?)",
      contact.firstName, contact.lastName,
      contact.phoneNumber, contact.emailAddress)
  }

}