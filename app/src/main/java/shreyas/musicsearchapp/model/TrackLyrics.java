package shreyas.musicsearchapp.model;

import org.simpleframework.xml.Element;

/**
 * Created by shreyasmp on 9/7/17.
 *
 *  Track lyrics model class.
 *  Considered the api output in xml format for better results
 */

public class TrackLyrics {


    @Element(name = "artist")
    private String artist;

    @Element(name = "song")
    private String song;

    @Element(name = "lyrics")
    private String lyrics;

    @Element(name = "url")
    private String url;

    @Element (name = "page_namespace")
    private String page_namespace;

    @Element (name = "page_id")
    private int page_id;

    @Element(name = "isOnTakedownList")
    private int isOnTakedownList;

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPage_namespace() {
        return page_namespace;
    }

    public void setPage_namespace(String page_namespace) {
        this.page_namespace = page_namespace;
    }

    public int getPage_id() {
        return page_id;
    }

    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }

    public int getIsOnTakedownList() {
        return isOnTakedownList;
    }

    public void setIsOnTakedownList(int isOnTakedownList) {
        this.isOnTakedownList = isOnTakedownList;
    }


}
