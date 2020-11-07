package no.kristiania.database;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberToProjectDao {

    private DataSource dataSource;

    public MemberToProjectDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(MemberToProject memberToProject) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("INSERT INTO projectmember_to_project (project_name, projectmember_name, task_name, status, description) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            )) {

                statement.setInt(1, memberToProject.getProjectId());
                statement.setInt(2, memberToProject.getMemberNameId());
                statement.setInt(3, memberToProject.getTaskId());
                statement.setString(4, memberToProject.getStatus());
                statement.setString(5, memberToProject.getDescription());
                statement.executeUpdate();

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    generatedKeys.next();
                    memberToProject.setId(generatedKeys.getLong("id"));
                }
            }
        }
    }

    public MemberToProject retrieve(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM projectmember_to_project WHERE id = ?")) {
                statement.setLong(1, id);
                try (ResultSet rs = statement.executeQuery()) {
                    if (rs.next()) {
                        return mapRowToProjectMemberToProject(rs);
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    public void updateStatus(String status, long id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE projectmember_to_project SET status = ? WHERE id=?")) {
                statement.setString(1,status);
                statement.setLong(2,id);
                statement.executeUpdate();
            }
        }
    }

    private MemberToProject mapRowToProjectMemberToProject(ResultSet rs) throws SQLException {
        MemberToProject memberToProject = new MemberToProject();
        memberToProject.setId(rs.getLong("id"));
        memberToProject.setProjectId(rs.getInt("project_name"));
        memberToProject.setProjectName(rs.getString("p_name"));
        memberToProject.setProjectMemberFirstName(rs.getString("first_name"));
        memberToProject.setProjectMemberLastName(rs.getString("last_name"));
        memberToProject.setNameId(rs.getInt("projectmember_name"));
        memberToProject.setTaskId(rs.getInt("task_name"));
        memberToProject.setTaskName(rs.getString("name"));
        memberToProject.setStatus(rs.getString("status"));
        memberToProject.setDescription(rs.getString("description"));
        return memberToProject;
    }

    public List<MemberToProject> list() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT task.id, task.name, project.p_name, task_name, project_name, projectmember_name, status, description, projectmembers.first_name, projectmembers.last_name FROM task INNER JOIN projectmember_to_project ON  projectmember_to_project.task_name = task.id INNER JOIN project ON projectmember_to_project.project_name = project.id INNER JOIN projectmembers ON projectmember_to_project.projectmember_name = projectmembers.id")) {
                try (ResultSet rs = statement.executeQuery()) {
                    List<MemberToProject> memberToProjects = new ArrayList<>();
                    while (rs.next()) {
                        memberToProjects.add(mapRowToProjectMemberToProject(rs));
                    }
                    return memberToProjects;
                }
            }
        }
    }
}
