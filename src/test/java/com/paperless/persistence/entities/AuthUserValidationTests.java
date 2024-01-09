package com.paperless.persistence.entities;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;


@Slf4j
class AuthUserValidationTests {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    @DisplayName("All fields are valid")
    void allValid() {
        // Arrange
        AuthUser authUser = createValidAuthUser();

        // Act
        Set<ConstraintViolation<AuthUser>> violations = validator.validate(authUser);

        // Assert
        assertTrue(violations.isEmpty(), "Expected no violations, but found: " + violations);
    }

    @Test
    @DisplayName("Username is invalid")
    void usernameInvalid() {
        // Arrange
        AuthUser authUser = createValidAuthUser();
        authUser.setUsername("r");

        // Act
        Set<ConstraintViolation<AuthUser>> violations = validator.validate(authUser);

        // Assert
        assertFalse(violations.isEmpty(), "Expected violations for username");
        logViolations(violations);
    }

    @Test
    @DisplayName("Password is invalid")
    void passwordInvalid() {
        // Arrange
        AuthUser authUser = createValidAuthUser();
        authUser.setPassword("1234");

        // Act
        Set<ConstraintViolation<AuthUser>> violations = validator.validate(authUser);

        // Assert
        assertFalse(violations.isEmpty(), "Expected violations for password");
        logViolations(violations);
    }

    @Test
    @DisplayName("First name is invalid")
    void firstNameInvalid() {
        // Arrange
        AuthUser authUser = createValidAuthUser();
        authUser.setFirstName("m");

        // Act
        Set<ConstraintViolation<AuthUser>> violations = validator.validate(authUser);

        // Assert
        assertFalse(violations.isEmpty(), "Expected violations for first name");
        logViolations(violations);
    }

    @Test
    @DisplayName("Last name is invalid")
    void lastNameInvalid() {
        // Arrange
        AuthUser authUser = createValidAuthUser();
        authUser.setLastName("v");

        // Act
        Set<ConstraintViolation<AuthUser>> violations = validator.validate(authUser);

        // Assert
        assertFalse(violations.isEmpty(), "Expected violations for last name");
        logViolations(violations);
    }

    @Test
    @DisplayName("Email is invalid")
    void emailInvalid() {
        // Arrange
        AuthUser authUser = createValidAuthUser();
        authUser.setEmail("MaxVerrb.com");

        // Act
        Set<ConstraintViolation<AuthUser>> violations = validator.validate(authUser);

        // Assert
        assertFalse(violations.isEmpty(), "Expected violations for email");
        logViolations(violations);
    }

    private AuthUser createValidAuthUser() {
        AuthUser authUser = new AuthUser();
        authUser.setUsername("RedBull24");
        authUser.setFirstName("Max");
        authUser.setLastName("Verstappen");
        authUser.setPassword("PolePosition1+");
        authUser.setEmail("MaxVer@gmail.com");
        return authUser;
    }

    private void logViolations(Set<ConstraintViolation<AuthUser>> violations) {
        for (var violation : violations) {
            log.error(violation.getMessage());
        }
    }
}