package edu.itplus.bibliospring.backend.Service.impl;

import edu.itplus.bibliospring.backend.Utils.PasswordEncrypter;
import edu.itplus.bibliospring.backend.model.User;
import edu.itplus.bibliospring.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


class LoginServiceImplTest {

    TestUserDao testuserDao;

    private LoginServiceImpl serviceUnderTest;

    private UserRepository testuserRepository;

    private PasswordEncrypter testpasswordEncrypter;

    @BeforeEach
    void setUp() {
        testuserDao = new TestUserDao();

        testuserRepository=mock(UserRepository.class);
        when(testuserRepository.findByUsername(testuserDao.nonDbUser.getUsername())).thenReturn(testuserDao.dbUser);

        testpasswordEncrypter=mock(PasswordEncrypter.class);
        when(testpasswordEncrypter.hashPassword(TestPasswordEncrypter.password,
                                                 TestPasswordEncrypter.salt))
                .thenReturn(TestPasswordEncrypter.hashedPassword);

        serviceUnderTest = new LoginServiceImpl();

        ReflectionTestUtils.setField(serviceUnderTest,"userRepository",testuserRepository);
        ReflectionTestUtils.setField(serviceUnderTest,"passwordEncrypter",testpasswordEncrypter);
    }

    @Test
    void login() {
        boolean loginStatus= serviceUnderTest.login(testuserDao.nonDbUser);
        assertThat(loginStatus).isTrue();
        verify(testuserRepository, times(1)).findByUsername(testuserDao.nonDbUser.getUsername());
    }

    @Test
    void register() {
        serviceUnderTest.register(testuserDao.nonDbUser);

        assertThat(testuserDao.nonDbUser.getPassword()).isEqualTo(TestPasswordEncrypter.hashedPassword);
        verify(testuserRepository,times(1)).create(testuserDao.nonDbUser);
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