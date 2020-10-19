package no.kristiania.database;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProjectMemberDao {

    private DataSource dataSource;

    public ProjectMemberDao(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public static void main(String[] args) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/projectmembers");
        dataSource.setUser("projectmembers");
        dataSource.setPassword("3F9YG1oYUo71in*yG");

        ProjectMemberDao projectMemberDao = new ProjectMemberDao(dataSource);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter project member's first name");
        String projectMemberFirstName = scanner.nextLine();

        ProjectMember projectMember = new ProjectMember();
        projectMember.setFirstName(projectMemberFirstName);
        projectMemberDao.insert(projectMember);
        for (ProjectMember p : projectMemberDao.list()) {
            System.out.println(p);
        }


    }

    public void insert(ProjectMember projectMember) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO projectmembers (first_name) VALUES (?)")) {
                statement.setString(1, projectMember.getFirstName());
                statement.executeUpdate();
            }
        }
    }

    public List<ProjectMember> list() throws SQLException {
        List<ProjectMember> projectMembers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from projectmembers")) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        ProjectMember projectMember = new ProjectMember();
                        projectMember.setFirstName(rs.getString("first_name"));
                        projectMembers.add(projectMember);
                    }
                }
            }
        }
        return projectMembers;


    }
}
