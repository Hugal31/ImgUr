package com.github.Hugal31.imgur;

import java.util.Date;

/**
 * Representation of an album or a image
 */
public abstract class ImgurItem {

    private String id;

    private String title;

    private String description;

    private Date datetime;

    private boolean favorite = false;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public boolean isFavorite() {
        return favorite;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{"
                + "id=" + getId()
                + ", title=" + getTitle()
                + ", description=" + getDescription()
                + ", date=" + getDatetime() + '}';
    }
}
