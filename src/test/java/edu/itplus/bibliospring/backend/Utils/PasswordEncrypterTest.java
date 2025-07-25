package edu.itplus.bibliospring.backend.Utils;

import org.junit.jupiter.api.Test;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;



class PasswordEncrypterTest {

    @Test
    void hashPassword() {
        //Arrange
        PasswordEncrypter passwordEncrypter = new PasswordEncrypter();
        String password = "password";
        String salt= "fae9f897-4abf-4b90-97ee-c748abfc1c9b";
        //Act
        String hash=passwordEncrypter.hashPassword(password,salt);
        //Assert
        String expectedHash="45E7218DD81F68973B2170CD7DF95EA375CB1BB932A219DBD84BA4920A44BAF0";
        assertThat(hash).isEqualTo(expectedHash);



    }
}