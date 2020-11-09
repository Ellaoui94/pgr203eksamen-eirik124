package no.kristiania.database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDao {

    private DataSource dataSource;

    public StatusDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Status status) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO status (status) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            )) {
                statement.setString(1, status.getStatus());

                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    status.setId(generatedKeys.getLong("id"));
                }
            }
        }
    }

    public Status retrieve(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM status WHERE id = ?")) {
                statement.setLong(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return mapRowToStatus(rs);
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    public List<Status> list() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from status")) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<Status> status = new ArrayList<>();
                    while (rs.next()) {
                        status.add(mapRowToStatus(rs));
                    }
                    return status;
                }
            }
        }
    }

    private Status mapRowToStatus(ResultSet rs) throws SQLException {
        Status status = new Status();
        status.setId(rs.getLong("id"));
        status.setName(rs.getString("status"));
        return status;
    }
}
