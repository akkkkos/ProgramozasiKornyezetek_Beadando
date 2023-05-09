package hu.akosbalogh.tvshowlist.service;

import hu.akosbalogh.tvshowlist.data.model.TvShow;

import java.util.List;
import java.util.Optional;

/**
 * Interface for TvShowService.
 */
public interface TvShowServiceInterface {

    TvShow createTvShow(TvShow show);

    Optional<TvShow> retrieveTvShowById(Long id);

    List<TvShow> retrieveAllTvShowsMatchingUserId(Long id);

    List<TvShow> retrieveAllTvShows();

    TvShow updateTvShow(TvShow show);

    void deleteTvShowById(Long id);
}
