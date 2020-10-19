package no.kristiania.database;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;


import static org.assertj.core.api.Assertions.assertThat;

class ProjectMemberDaoTest {

    private ProjectMemberDao projectMemberDao;
    private final Random random = new Random();

    @BeforeEach
    void setUp() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(dataSource).load().migrate();
        projectMemberDao = new ProjectMemberDao(dataSource);
    }

    @Test
    void shouldListInsertedProjectMembers() throws SQLException {
        ProjectMember projectMember1 = exampleProjectMember();
        ProjectMember projectMember2 = exampleProjectMember();
        projectMemberDao.insert(projectMember1);
        projectMemberDao.insert(projectMember2);
        assertThat(projectMemberDao.list())
                .extracting(ProjectMember::getFirstName)
                .contains(projectMember1.getFirstName(), projectMember2.getFirstName());
    }

    private ProjectMember exampleProjectMember() {
        ProjectMember projectMember = new ProjectMember();
        projectMember.setFirstName(exampleProjectMemberFirstName());
        projectMember.setLastName(exampleProjectMemberLastName());
        projectMember.setEmail(exampleProjectMemberEmail());

        return projectMember;
    }

    private String exampleProjectMemberFirstName() {
        String[] options = {"Ola", "Kari", "Harry", "Henriette"};
        return options[random.nextInt(options.length)];
    }

    private String exampleProjectMemberLastName() {
        String[] options = {"Halvorsen", "Haraldsen", "Jensen", "Svendsen"};
        return options[random.nextInt(options.length)];
    }

    private String exampleProjectMemberEmail() {
        String[] options = {"minmail@mailenmin.no", "test@tester.se", "hei@hade.com", "hallo@hello.co.uk"};
        return options[random.nextInt(options.length)];
    }
}