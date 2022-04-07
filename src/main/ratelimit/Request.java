package main.ratelimit;


public class Request {

    String username;
    String timestamp;

    public Request(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    String getRequestor() {
        return username;
    }

    String getTimeStamp() {
        return timestamp;
    }
}
