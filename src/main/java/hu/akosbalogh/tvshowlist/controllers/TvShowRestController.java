package hu.akosbalogh.tvshowlist.controllers;

import hu.akosbalogh.tvshowlist.data.model.TvShow;
import hu.akosbalogh.tvshowlist.service.impl.TvShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

/**
 * Web Controller for taking care of Tv Show REST actions.
 */

@RestController
@RequestMapping("/api/tvshow")
public class TvShowRestController {
    private final TvShowService tvShowService;

    @Autowired
    public TvShowRestController(TvShowService tvShowService) {
        this.tvShowService = tvShowService;
    }

    /**
     * Get show by Id.
     *
     * @param id Id.
     * @return Returns the Tv Show with the given Id if found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TvShow> getTvShowById(@PathVariable Long id) {
        Optional<TvShow> show = tvShowService.retrieveTvShowById(id);
        return show.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byuserid/{userId}")
    public List<TvShow> getAllTvShowsMatchingUserId(@PathVariable Long userId) {
        return tvShowService.retrieveAllTvShowsMatchingUserId(userId);
    }

    @GetMapping()
    public List<TvShow> getAllTvShows() {
        return tvShowService.retrieveAllTvShows();
    }

    @PostMapping
    public TvShow createTvShow(@RequestBody TvShow show) {
        return tvShowService.createTvShow(show);
    }

    @PutMapping
    public TvShow updateTvShow(@RequestBody TvShow show) {
        return tvShowService.updateTvShow(show);
    }

    @DeleteMapping("/{id}")
    public void deleteTvShowById(@PathVariable Long id) {
        tvShowService.deleteTvShowById(id);
    }
}
