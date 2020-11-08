package no.kristiania.httpServer.controllers;

import no.kristiania.database.StatusDao;
import no.kristiania.database.Task;
import no.kristiania.httpServer.HttpMessage;
import no.kristiania.httpServer.QueryString;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class StatusController implements HttpController {
    private StatusDao dao;
    private String body;

    public StatusController(StatusDao dao) {
        this.dao = dao;
    }

    @Override
    public void handle(String requestMethod, HttpMessage request, Socket clientSocket, OutputStream outputStream) throws IOException, SQLException {
        try {
            if (requestMethod.equals("POST")) {

            } else {
                body = getBody();
                String status = "200";

                String response = "HTTP/1.1 "+ status +" OK\r\n" +
                        "Content-Length: " + body.length() + "\r\n" +
                        "Content-Type: text/html; application/x-www-form-urlencoded\r\n" +
                        "Connection: close\r\n" +
                        "\r\n" +
                        body;

                clientSocket.getOutputStream().write(response.getBytes("UTF-8"));
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
                .map(dao -> String.format("<option name='" + dao.getId() +"' value='"+ dao.getId() +"' id='" + dao.getId() + "'>" + dao.getStatus() + "</option> "))
                .collect(Collectors.joining(""));
    }

}
