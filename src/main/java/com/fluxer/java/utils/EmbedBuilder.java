package com.fluxer.java.utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class EmbedBuilder {
    private String title;
    private String description;
    private String url;
    private Integer color;
    private String footerText;
    private String footerIcon;
    private String authorName;
    private String authorIcon;
    private String authorUrl;
    private String thumbnail;
    private String image;
    private final List<Field> fields = new ArrayList<>();

    public EmbedBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public EmbedBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public EmbedBuilder setColor(Color color) {
        this.color = color.getRGB() & 0xFFFFFF;
        return this;
    }

    public EmbedBuilder addField(String name, String value, boolean inline) {
        this.fields.add(new Field(name, value, inline));
        return this;
    }

    public EmbedBuilder setFooter(String text, String iconUrl) {
        this.footerText = text;
        this.footerIcon = iconUrl;
        return this;
    }

    public EmbedBuilder setAuthor(String name, String iconUrl, String url) {
        this.authorName = name;
        this.authorIcon = iconUrl;
        this.authorUrl = url;
        return this;
    }

    public EmbedBuilder setThumbnail(String url) {
        this.thumbnail = url;
        return this;
    }

    public EmbedBuilder setImage(String url) {
        this.image = url;
        return this;
    }

    public static class Field {
        public String name;
        public String value;
        public boolean inline;

        public Field(String name, String value, boolean inline) {
            this.name = name;
            this.value = value;
            this.inline = inline;
        }
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getColor() { return color; }
    public List<Field> getFields() { return fields; }
}
