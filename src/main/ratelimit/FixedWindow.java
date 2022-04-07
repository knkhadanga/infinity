package main.ratelimit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FixedWindow {

    private final int REQUEST_INTERVAL = 5; //5 seconds
    private final int MAX_REQUEST_PER_USER = 3;

    private SimpleDateFormat time_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Map<String, RequestCount> cache = new HashMap<>();

    boolean isAllowed(Request request) throws ParseException {

        if (!cache.containsKey(request.getRequestor())) {
            String current_time = time_format.format(new Date());
            RequestCount count = new RequestCount(current_time);
            cache.put(request.getRequestor(), count);
            return true;
        }

        RequestCount request_count = cache.get(request.getRequestor());
        String last_time_stamp = request_count.getLastRequestStamp();

        Date last_time = time_format.parse(last_time_stamp);
        String time = time_format.format(new Date());
        Date current_time = time_format.parse(time);

        if ((current_time.getTime() - last_time.getTime()) / 1000 >= REQUEST_INTERVAL) {
            request_count.resetCount();
            request_count.increaseCount();
            request_count.updateTimestamp(time);
            return true;
        } else {
            if (request_count.getRequestCount() >= MAX_REQUEST_PER_USER) {
                return false;
            } else {
                request_count.increaseCount();
                return true;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat time_f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        FixedWindow limiter = new FixedWindow();

        Request request1 = new Request("krushna", time_f.format(new Date()));
        Request request2 = new Request("krushna", time_f.format(new Date()));
        Request request3 = new Request("krushna", time_f.format(new Date()));
        Request request4 = new Request("krushna", time_f.format(new Date()));

        System.out.println(limiter.isAllowed(request1));
        System.out.println(limiter.isAllowed(request2));
        System.out.println(limiter.isAllowed(request3));
        System.out.println(limiter.isAllowed(request4));

        Thread.sleep(5000);

        request1 = new Request("krushna", time_f.format(new Date()));
        request2 = new Request("krushna", time_f.format(new Date()));
        request3 = new Request("krushna", time_f.format(new Date()));
        request4 = new Request("krushna", time_f.format(new Date()));

        System.out.println(limiter.isAllowed(request1));
        System.out.println(limiter.isAllowed(request2));
        System.out.println(limiter.isAllowed(request3));
        System.out.println(limiter.isAllowed(request4));
    }
}
