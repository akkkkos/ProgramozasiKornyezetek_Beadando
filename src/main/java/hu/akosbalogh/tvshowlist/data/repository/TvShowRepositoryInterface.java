package hu.akosbalogh.tvshowlist.data.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for TvShowRepository.
 *
 * @param <T> Type of object.
 * @param <I> Id.
 */
public interface TvShowRepositoryInterface<T, I> {

    T save(T item);

    Optional<T> getById(I id);

    List<T> retrieveByUserId(I id);

    List<T> getAll();

    T update(T item);

    void deleteById(I id);
}
