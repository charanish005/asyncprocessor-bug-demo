package coen448.computablefuture.test;

public class StandaloneTestRunner {
    public static void main(String[] args) {
        Microservice mockService1 = new Microservice() {
            @Override
            public java.util.concurrent.CompletableFuture<String> retrieveAsync(String input) {
                return java.util.concurrent.CompletableFuture.completedFuture("Hello");
            }
        };
        Microservice mockService2 = new Microservice() {
            @Override
            public java.util.concurrent.CompletableFuture<String> retrieveAsync(String input) {
                return java.util.concurrent.CompletableFuture.completedFuture("World");
            }
        };

        AsyncProcessor processor = new AsyncProcessor();
        java.util.concurrent.CompletableFuture<String> resultFuture = processor.processAsync(java.util.List.of(mockService1, mockService2));
        try {
            String result = resultFuture.get();
            System.out.println("Result: " + result);
            if ("Hello World".equals(result)) {
                System.out.println("TEST PASSED");
                System.exit(0);
            } else {
                System.out.println("TEST FAILED: expected 'Hello World'");
                System.exit(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(3);
        }
    }
}
