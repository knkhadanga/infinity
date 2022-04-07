package main.ratelimit;

public class RequestCount {
    int requestCount;
    String timeStamp;

    public RequestCount(String timeStamp) {
        requestCount++;
        this.timeStamp = timeStamp;
    }

    void increaseCount(){
        requestCount++;
    }

    void updateTimestamp(String timeStamp){
        this.timeStamp = timeStamp;
    }

    void resetCount(){
        requestCount = 0;
    }

    String getLastRequestStamp(){
        return timeStamp;
    }

    int getRequestCount(){
        return requestCount;
    }
}
