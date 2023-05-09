package hu.akosbalogh.tvshowlist.data.model;

/**
 * Model Class for TvShows
 */
public class TvShow {
    private Long id;
    private Long userId;
    private String title;
    private Status status;
    private int episodeCount;
    private int watchedEpisodeCount;

    public TvShow() {
    }

    public TvShow(Long id, Long userId, String title, Status status, int episodeCount, int watchedEpisodeCount) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.status = status;
        this.episodeCount = episodeCount;
        this.watchedEpisodeCount = watchedEpisodeCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getWatchedEpisodeCount() {
        return watchedEpisodeCount;
    }

    public void setWatchedEpisodeCount(int watchedEpisodeCount) {
        this.watchedEpisodeCount = watchedEpisodeCount;
    }

    @Override
    public String toString() {
        return "TvShow{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", episodeCount=" + episodeCount +
                ", watchedEpisodeCount=" + watchedEpisodeCount +
                '}';
    }
}

