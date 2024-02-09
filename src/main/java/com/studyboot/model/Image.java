package com.studyboot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Image {

    String webformatURL;

    public Image() {
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    @Override
    public String toString() {
        return "Image{" +
                "webformatURL='" + webformatURL + '\'' +
                '}';
    }
}
