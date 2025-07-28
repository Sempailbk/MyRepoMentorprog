package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.Utils.PasswordEncrypter;
import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static edu.itplus.bibliospring.backend.Service.impl.TestUserDao.dbUser;
import static edu.itplus.bibliospring.backend.Service.impl.TestUserDao.nonDbUser;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


class LoginServiceImplTest {
    private LoginServiceImpl serviceUnderTest;

    private UserRepository testuserRepository;

    private PasswordEncrypter testpasswordEncrypter;

    @BeforeEach
    void setUp() {
        nonDbUser = new User();
        nonDbUser.setUsername("Helo");
        nonDbUser.setPassword(TestPasswordEncrypter.password);
        nonDbUser.setUUID(TestPasswordEncrypter.salt);
        nonDbUser.setId(1L);

        dbUser = new User();
        dbUser.setUsername("Heloka");
        dbUser.setPassword(TestPasswordEncrypter.hashedPassword);
        dbUser.setUUID(TestPasswordEncrypter.salt);
        dbUser.setId(1L);

        testuserRepository=mock(UserRepository.class);
        when(testuserRepository.findByUsername(nonDbUser.getUsername())).thenReturn(dbUser);

        testpasswordEncrypter=mock(PasswordEncrypter.class);
        when(testpasswordEncrypter.hashPassword(nonDbUser.getPassword(), nonDbUser.getUUID())).thenReturn(dbUser.getPassword());

        serviceUnderTest = new LoginServiceImpl();

        ReflectionTestUtils.setField(serviceUnderTest,"userRepository",testuserRepository);
        ReflectionTestUtils.setField(serviceUnderTest,"passwordEncrypter",testpasswordEncrypter);
    }

    @Test
    void login() {
        boolean loginStatus= serviceUnderTest.login(nonDbUser);
        assertThat(loginStatus).isTrue();
        verify(testuserRepository, times(1)).findByUsername(nonDbUser.getUsername());
    }

    @Test
    void register() {
        serviceUnderTest.register(nonDbUser);

        assertThat(nonDbUser.getPassword()).isEqualTo(TestPasswordEncrypter.hashedPassword);
        verify(testuserRepository,times(1)).create(nonDbUser);
    }
    @Test
    void login_shouldFailForUnknownUser() {
        User unknownUser = new User();
        unknownUser.setUsername("ghost");
        unknownUser.setPassword("irrelevant");
        unknownUser.setUUID("ghostsalt");

        when(testuserRepository.findByUsername("ghost")).thenReturn(null);

        boolean result = serviceUnderTest.login(unknownUser);

        assertThat(result).isFalse();
    }
}