package edu.itplus.bibliospring.backend.Utils;

import edu.itplus.bibliospring.backend.Utils.Impl.PasswordEncrypterSha256;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.UUID;
import static org.assertj.core.api.Assertions.*;



class PasswordEncrypterTest {


    @ParameterizedTest
    @CsvSource({
            // password,                            salt,                                    expectedHash
            "password","f65a4e3b-27a4-4e7e-9ebc-b6c4df4f80fc","e354576cfddcdb0d1b9fad75e993d14e590c124f75d527785a296ceead9eda50"
    })
    void hashPasswordParamater(String password, String salt,String expectedHash) {
        PasswordEncrypterSha256 encrypter = new PasswordEncrypterSha256();
        String actualHash = encrypter.hashPassword(password, salt);
        assertThat(actualHash).isEqualTo(expectedHash);

    }
}