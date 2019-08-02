package com.midas.demo.mvp.model.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by midas on 2019/5/27
 * desc:分类 Bean
 */
public class CategoryBean implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String bgPicture;
    private String bgColor;
    private String headerImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBgPicture() {
        return bgPicture;
    }

    public void setBgPicture(String bgPicture) {
        this.bgPicture = bgPicture;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    @NonNull
    @Override
    public String toString() {
        return "CategoryBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", bgPicture='" + bgPicture + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", headerImage='" + headerImage + '\'' +
                '}';
    }
}
