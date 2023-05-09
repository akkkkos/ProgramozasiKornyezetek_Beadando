package hu.akosbalogh.tvshowlist.service.impl;

import hu.akosbalogh.tvshowlist.data.model.TvShow;
import hu.akosbalogh.tvshowlist.data.repository.TvShowRepositoryInterface;
import hu.akosbalogh.tvshowlist.service.TvShowServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * A Service for manipulating the stored Tv Show data.
 */
@Service
public class TvShowService implements TvShowServiceInterface {

    private final TvShowRepositoryInterface<TvShow, Long> tvShowRepository;

    @Autowired
    public TvShowService(TvShowRepositoryInterface<TvShow, Long> tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    @Override
    public TvShow createTvShow(TvShow show) {
        return tvShowRepository.save(show);
    }

    @Override
    public Optional<TvShow> retrieveTvShowById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<TvShow> retrieveAllTvShowsMatchingUserId(Long id) {
        return tvShowRepository.retrieveByUserId(id);
    }

    @Override
    public List<TvShow> retrieveAllTvShows() {
        return tvShowRepository.getAll();
    }

    @Override
    public TvShow updateTvShow(TvShow show) {
        return tvShowRepository.update(show);
    }

    @Override
    public void deleteTvShowById(Long id) {
        tvShowRepository.deleteById(id);
    }
}
