package hu.akosbalogh.tvshowlist.data.repository;

import java.util.List;
import java.util.Optional;

/**
 * Interface for UserRepository.
 *
 * @param <T> Type of object.
 * @param <I> Id.
 */
public interface UserRepositoryInterface<T, I> {

    T save(T item);

    Optional<T> getById(I id);

    Optional<T> getByUserName(String userName);

    List<T> getAll();

    T update(T item);

    void deleteById(I id);
}
