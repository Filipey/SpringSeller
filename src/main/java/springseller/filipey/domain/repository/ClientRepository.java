package springseller.filipey.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import springseller.filipey.domain.entity.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientRepository {

    private static String INSERT = "INSERT INTO CLIENT (NAME) VALUES(?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENT";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Client save(Client client) {
        jdbcTemplate.update(INSERT, new Object[]{client.getName()});
        return client;
    }

    public List<Client> list() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Client>() {
            @Override
            public Client mapRow(ResultSet resultSet, int i) throws SQLException {
                String name = resultSet.getString("name");
                Long id = resultSet.getLong("id");
                return new Client(id, name);
            }
        });
    }
}
