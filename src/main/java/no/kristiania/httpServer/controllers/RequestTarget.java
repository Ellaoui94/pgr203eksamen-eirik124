package no.kristiania.httpServer.controllers;

import no.kristiania.httpServer.HttpMessage;

public class RequestTarget {
    public static String requestTarget(HttpMessage request) {
        String requestLine = request.getStartLine();
        return requestLine.split(" ")[1];
    }
}
