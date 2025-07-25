package edu.itplus.bibliospring.backend.Service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import static org.assertj.core.api.Assertions.*;

class LoginServiceImplTest {
    private LoginServiceImpl serviceUnderTest;
    @BeforeEach
    void setUp() {
        serviceUnderTest=new LoginServiceImpl();
        ReflectionTestUtils.setField(serviceUnderTest,"userDao",new TestUserDao());
        ReflectionTestUtils.setField(serviceUnderTest,"password",new TestPasswordEncrypter());
    }

    @Test
    void login() {
        boolean loginStatus= serviceUnderTest.login(TestUserDao.nonDbUser);
        assertThat(loginStatus).isTrue();
    }

    @Test
    void register() {

    }
}