package main.patterns;

public class StreamExamples {

    public static void main(String[] args) {

        System.out.println(StreamExamples.METRICS_LABELS.GET_TENANT.getLabel());
        /*
        int[] array = {12, 33, 30, -1, 2, 9};

        //find minimum in array
        int min = IntStream.of(array).min().getAsInt();

        //find max in array
        int max = IntStream.of(array).max().getAsInt();

        System.out.println("Min: " + min + " Max: " + max);

        IntStream.of(array).min().ifPresent(value -> System.out.println("Min: " + value));

        IntStream.of(array).max().ifPresent(value -> System.out.println("Max: " + value));

        IntSummaryStatistics statistics = IntStream.of(array).summaryStatistics();
        System.out.println("Min: " + statistics.getMin());
        System.out.println("Max: " + statistics.getMax());
        System.out.println("Avg: " + statistics.getAverage());
        System.out.println("Count: " + statistics.getCount());

        IntStream.of(array).distinct().sorted().limit(3).forEach(System.out::print);
        IntStream.of(array).distinct().sorted().limit(3).forEach(item -> System.out.print(" " +item));

         */
    }


    public static enum METRICS_LABELS {
        CREATE_TENANT("Create Tenant"),
        DELETE_TENANT("Delete Tenant"),
        GET_TENANT("Get Tenant"),
        UPDATE_TENANT("Update Tenant"),
        DELETE_SM_REQUEST_STATUSES("Delete SM Request Statuses"),
        GET_SM_REQUEST_STATUSES("Get SM Request Statuses"),
        SM_PROVISIONING_GET_METRICS("sm.Provisioning.get.metric"),
        CREATE_REPLICA_TENANT("Create Replica Tenant"),
        DYN_PUBLISH("Publish Dyn URLS"),
        CREATE_TENANT_SUCCESS("Create Tenant Success"),
        CREATE_TENANT_FAILURE("Create Tenant Failure"),
        CREATE_APP_SERVICE_SUCCESS("Create AppService Success"),
        CREATE_APP_SERVICE_FAILURE("Create AppService Failure");

        private String label;

        METRICS_LABELS(String s) {
            this.label = s;
        }

        public String getLabel() {
            return label;
        }
    }
}
