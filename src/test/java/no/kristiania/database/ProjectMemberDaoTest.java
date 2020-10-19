package no.kristiania.database;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;


import static org.assertj.core.api.Assertions.assertThat;

class ProjectMemberDaoTest {

    @Test
    void shouldListInsertedProjectMemberFirstNames() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdatabase;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(dataSource).load().migrate();

        ProjectMemberDao projectMemberDao = new ProjectMemberDao(dataSource);
        ProjectMember projectMember = exampleProjectMember();
        projectMemberDao.insert(projectMember);
        assertThat(projectMemberDao.list())
                .extracting(ProjectMember::getFirstName)
                .contains(projectMember.getFirstName());
    }

    private ProjectMember exampleProjectMember() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setFirstName(exampleProjectMemberFirstName());
        return projectMember;
    }

    private String exampleProjectMemberFirstName() {
        String[] options = {"Ola", "Kari", "Harry", "Henriette"};
        Random random = new Random();
        return options[random.nextInt(options.length)];
    }
}