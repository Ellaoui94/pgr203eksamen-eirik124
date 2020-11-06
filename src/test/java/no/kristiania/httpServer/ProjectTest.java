package no.kristiania.httpServer;

import no.kristiania.database.Project;
import no.kristiania.database.ProjectDao;
import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectTest {
    private JdbcDataSource dataSource = TestDatabaseSource.dataSource();


    @Test
    void shouldLocateSavedProjects() throws SQLException {
        ProjectDao projectDao = new ProjectDao(dataSource);
        Project project = projectSample();
        projectDao.insert(project);
        assertThat(projectDao.list().contains(project));
    }


    private Project projectSample() {
        Project project = new Project();
        String projectName = "test-projsket";
        project.setName(projectName);
        return project;
    }
}
