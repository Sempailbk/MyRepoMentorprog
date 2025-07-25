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
        String salt= UUID.randomUUID().toString();
        //Act
        String hash=passwordEncrypter.hashPassword(password,salt);
        //Assert
        String expectedHash="123456";
        assertThat(hash).isEqualTo(expectedHash);



    }
}