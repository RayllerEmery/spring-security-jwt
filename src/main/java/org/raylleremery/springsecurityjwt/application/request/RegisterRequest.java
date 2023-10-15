package org.raylleremery.springsecurityjwt.application.request;

import org.raylleremery.springsecurityjwt.domain.UserRole;

public record RegisterRequest(String login, String password, UserRole role) {
}
