package hu.akosbalogh.tvshowlist.data.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import hu.akosbalogh.tvshowlist.data.model.TvShow;
import hu.akosbalogh.tvshowlist.data.repository.TvShowRepositoryInterface;
import org.springframework.stereotype.Repository;

/**
 * An in memory repository for storing Tv Show data.
 */
@Repository
public class TvShowRepository implements TvShowRepositoryInterface<TvShow, Long> {

    private static final Map<Long, TvShow> STORAGE = new HashMap<>();


    @Override
    public TvShow save(TvShow show) {
        Long id = STORAGE.size() + 1L;
        show.setId(id);
        STORAGE.put(id, show);
        return STORAGE.get(id);
    }

    @Override
    public Optional<TvShow> getById(Long id) {
        return Optional.ofNullable(STORAGE.get(id));
    }

    @Override
    public List<TvShow> retrieveByUserId(Long id) {
        return STORAGE.values()
                .stream()
                .filter(show -> show.getUserId().equals(id)).toList();
    }

    @Override
    public List<TvShow> getAll() {
        return STORAGE.values().stream().toList();
    }

    @Override
    public TvShow update(TvShow show) {
        Long id = show.getId();
        STORAGE.put(id, show);
        return STORAGE.get(id);
    }

    //TODO fix deletion (deleting show may create new show with already existing id)
    @Override
    public void deleteById(Long id) {
        STORAGE.remove(id);

    }
}
