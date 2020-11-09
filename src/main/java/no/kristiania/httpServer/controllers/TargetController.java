package no.kristiania.httpServer.controllers;

import no.kristiania.httpServer.HttpMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TargetController implements HttpController {

    @Override
    public void handle(String requestMethod, HttpMessage request, Socket clientSocket, OutputStream outputStream) throws IOException {
        String requestTarget = RequestTarget.requestTarget(request);
        int questionPos = requestTarget.indexOf('?');
        String requestPath = questionPos != -1 ? requestTarget.substring(0, questionPos) : requestTarget;

        if (requestPath.equals("/")) {
            outputStream.write(("HTTP/1.1 302 Redirect\r\n" +
                    "Location: /index.html\r\n" +
                    "Connection: close\r\n" +
                    "\r\n").getBytes(StandardCharsets.UTF_8));
        }
    }
}
