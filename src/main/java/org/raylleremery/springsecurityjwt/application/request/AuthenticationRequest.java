package org.raylleremery.springsecurityjwt.application.request;

public record AuthenticationRequest(String login, String password) {
}
