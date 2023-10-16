package com.mastiff.storydemo.classes;

public class Image {
    private String url;
    private String authorProfileUrl;
    private String authorName;
    private String title;
    private String description;

    public Image(String url, String authorProfileUrl, String authorName, String title, String description) {
        this.url = url;
        this.authorProfileUrl = authorProfileUrl;
        this.authorName = authorName;
        this.title = title;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthorProfileUrl() {
        return authorProfileUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
