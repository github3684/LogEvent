package com.event.trackevent.domain;

public class EventObjFromFile {
    private String id;
    private String state;
    private String type;
    private String host;
    private Long timestamp;

    public void setId(String id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public Long getTimestamp() {
        return timestamp;
    }
}
