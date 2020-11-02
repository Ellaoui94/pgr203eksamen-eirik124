package no.kristiania.httpServer.controllers;

import no.kristiania.database.Project;
import no.kristiania.database.ProjectDao;
import no.kristiania.httpServer.HttpMessage;
import no.kristiania.httpServer.QueryString;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class ProjectController implements HttpController {
    private ProjectDao dao;
    private String body;

    public ProjectController(ProjectDao dao) {
        this.dao = dao;
    }

    @Override
    public void handle(String requestMethod, HttpMessage request, Socket clientSocket, OutputStream outputStream) throws IOException, SQLException {
        try {
            if (requestMethod.equals("POST")) {
                QueryString requestParameter = new QueryString(request.getBody());

                Project project = new Project();
                project.setName(URLDecoder.decode(requestParameter.getParameter("project_name"), StandardCharsets.UTF_8));
                dao.insert(project);


                outputStream.write(("HTTP/1.1 302 Redirect\r\n" +
                        "Location: /newProject.html\r\n" +
                        "Connection: close\r\n" +
                        "\r\n").getBytes("UTF-8"));

            } else {
               /* for (Project project : dao.list()) {
                    body += "<option id='" + project.getId() + "'>" + project.getName() + "</option> ";
                }*/

                body = getBody();
                String status = "200";

                outputStream.write(("HTTP/1.1 " + status + " OK\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: " + body.length() + "\r\n" +
                        "Connection: close\r\n" +
                        "\r\n" +
                        body).getBytes("UTF-8"));
                outputStream.flush();
            }
        } catch (SQLException e) {
            String message = e.toString();
            outputStream.write(("HTTP/1.1 500 Internal server error\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + message.length() + "\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    message).getBytes("UTF-8"));
        }


    }


    public String getBody() throws SQLException {
        return dao.list().stream()
                .map(dao -> String.format("<option id='" + dao.getId() + "'>" + dao.getName() + "</option>"))
                .collect(Collectors.joining(""));
    }
}
