package hu.akosbalogh.tvshowlist.service.impl;

import hu.akosbalogh.tvshowlist.data.model.Status;
import hu.akosbalogh.tvshowlist.data.model.TvShow;
import hu.akosbalogh.tvshowlist.data.repository.TvShowRepositoryInterface;
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
 * Unit tests for TvShowService
 */
public class TvShowServiceTest {

    private static final Long DUMMY_USER_ID = 1L;
    private static final Long DUMMY_TVSHOW_ID = 1L;

    //Long id, Long userId, String title, Status status, int episodeCount, int watchedEpisodeCount
    private static final TvShow DUMMY_TVSHOW = new TvShow(DUMMY_TVSHOW_ID, DUMMY_USER_ID, "testTitle", Status.WATCHING, 10, 5);

    @Mock
    private TvShowRepositoryInterface<TvShow, Long> tvShowRepository;

    private TvShowService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new TvShowService(tvShowRepository);
    }

    @Test
    void createTvShowShouldSendTvShowDataToRepositoryAndReturnSavedTvShow() {
        // Given
        given(tvShowRepository.save(DUMMY_TVSHOW)).willReturn(DUMMY_TVSHOW);

        // When
        final TvShow result = underTest.createTvShow(DUMMY_TVSHOW);

        // Then
        assertThat(result, equalTo(DUMMY_TVSHOW));
        verify(tvShowRepository).save(DUMMY_TVSHOW);
        verifyNoMoreInteractions(tvShowRepository);
    }

    @Test
    void retrieveTvShowByIdShouldReturnWithTheUserIfItExistsWithGivenId() {
        // Given
        given(tvShowRepository.save(DUMMY_TVSHOW)).willReturn(DUMMY_TVSHOW);
        given(tvShowRepository.getById(DUMMY_USER_ID)).willReturn(Optional.of(DUMMY_TVSHOW));

        // When
        underTest.createTvShow(DUMMY_TVSHOW);
        final TvShow result = underTest.retrieveTvShowById(DUMMY_TVSHOW_ID).get();

        // Then
        assertThat(result, equalTo(DUMMY_TVSHOW));
        verify(tvShowRepository).save(DUMMY_TVSHOW);
        verify(tvShowRepository).getById(DUMMY_TVSHOW_ID);
        verifyNoMoreInteractions(tvShowRepository);
    }

    @Test
    void retrieveAllTvShowsMatchingUserIdShouldReturnWithTheTvShowsWithGivenUserId() {
        // Given
        given(tvShowRepository.save(DUMMY_TVSHOW)).willReturn(DUMMY_TVSHOW);
        given(tvShowRepository.retrieveByUserId(DUMMY_USER_ID)).willReturn(List.of(DUMMY_TVSHOW));

        // When
        underTest.createTvShow(DUMMY_TVSHOW);
        final List<TvShow> result = underTest.retrieveAllTvShowsMatchingUserId(DUMMY_USER_ID);

        // Then
        assertThat(result, equalTo(List.of(DUMMY_TVSHOW)));
        verify(tvShowRepository).save(DUMMY_TVSHOW);
        verify(tvShowRepository).retrieveByUserId(DUMMY_USER_ID);
        verifyNoMoreInteractions(tvShowRepository);
    }

    @Test
    void retrieveAllTvShowsShouldReturnWithListOfAllTvShows() {
        // Given
        given(tvShowRepository.getAll()).willReturn(List.of(DUMMY_TVSHOW));

        // When
        final List<TvShow> result = underTest.retrieveAllTvShows();

        // Then
        assertThat(result, equalTo(List.of(DUMMY_TVSHOW)));
        verify(tvShowRepository).getAll();
        verifyNoMoreInteractions(tvShowRepository);
    }

    @Test
    void deleteTvShowByIdShouldDeleteTvShowFromRepositoryWithGivenId() {
        // Given

        // When
        underTest.deleteTvShowById(DUMMY_TVSHOW_ID);

        // Then
        verify(tvShowRepository).deleteById(DUMMY_TVSHOW_ID);
        verifyNoMoreInteractions(tvShowRepository);
    }
}
