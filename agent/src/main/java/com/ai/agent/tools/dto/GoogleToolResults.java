package com.ai.agent.tools.dto;

public class GoogleToolResults {
    private String title;
    private String snippet;
    private String link;


    public GoogleToolResults(String title, String snippet, String link) {
        this.title = title;
        this.snippet = snippet;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
