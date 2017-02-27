package com.github.Hugal31.imgur;

import java.util.Date;

public class Account {

    private int id;

    private String name;

    private String bio;

    private double reputation;

    private Date created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public double getReputation() {
        return reputation;
    }

    public void setReputation(double reputation) {
        this.reputation = reputation;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Account{"
                + "id=" + id
                + ", name=" + name
                + ", bio=" + bio
                + ", reputation=" + reputation
                + ", created=" + created + '}';
    }
}
