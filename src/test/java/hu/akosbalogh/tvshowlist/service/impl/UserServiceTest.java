package hu.akosbalogh.tvshowlist.service.impl;

import hu.akosbalogh.tvshowlist.data.model.User;
import hu.akosbalogh.tvshowlist.data.repository.UserRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Unit tests for UserService
 */
class UserServiceTest {

    private static final Long DUMMY_USER_ID = 1L;
    private static final String DUMMY_USER_USERNAME = "testUsername";
    private static final User DUMMY_USER = new User(DUMMY_USER_ID,DUMMY_USER_USERNAME,"testFullname");

    @Mock
    private UserRepositoryInterface<User, Long> userRepository;

    private UserService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new UserService(userRepository);
    }

    @Test
    void createUserShouldSendUserDataToRepositoryAndReturnSavedUser() {
        // Given
        given(userRepository.save(DUMMY_USER)).willReturn(DUMMY_USER);

        // When
        final User result = underTest.createUser(DUMMY_USER);

        // Then
        assertThat(result, equalTo(DUMMY_USER));
        verify(userRepository).save(DUMMY_USER);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void retrieveUserByIdShouldReturnWithTheUserIfItExistsWithGivenId() {
        // Given
        given(userRepository.save(DUMMY_USER)).willReturn(DUMMY_USER);
        given(userRepository.getById(DUMMY_USER_ID)).willReturn(Optional.of(DUMMY_USER));

        // When
        underTest.createUser(DUMMY_USER);
        final User result = underTest.retrieveUserById(DUMMY_USER_ID).get();

        // Then
        assertThat(result, equalTo(DUMMY_USER));
        verify(userRepository).save(DUMMY_USER);
        verify(userRepository).getById(DUMMY_USER_ID);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void retrieveUserByUserNameShouldReturnWithTheUserIfItExistsWithGivenUserName() {
        // Given
        given(userRepository.save(DUMMY_USER)).willReturn(DUMMY_USER);
        given(userRepository.getByUserName(DUMMY_USER_USERNAME)).willReturn(Optional.of(DUMMY_USER));

        // When
        underTest.createUser(DUMMY_USER);
        final User result = underTest.retrieveUserByUserName(DUMMY_USER_USERNAME).get();

        // Then
        assertThat(result, equalTo(DUMMY_USER));
        verify(userRepository).save(DUMMY_USER);
        verify(userRepository).getByUserName(DUMMY_USER_USERNAME);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void retrieveAllUsersShouldReturnWithListOfAllUsers() {
        // Given
        given(userRepository.getAll()).willReturn(List.of(DUMMY_USER));

        // When
        final List<User> result = underTest.retrieveAllUsers();

        // Then
        assertThat(result, equalTo(List.of(DUMMY_USER)));
        verify(userRepository).getAll();
        verifyNoMoreInteractions(userRepository);
    }


    @Test
    void deleteUserByIdShouldDeleteUserDataFromRepositoryWithGivenId() {
        // Given

        // When
        underTest.deleteUserById(DUMMY_USER_ID);

        // Then
        verify(userRepository).deleteById(DUMMY_USER_ID);
        verifyNoMoreInteractions(userRepository);
    }
}
