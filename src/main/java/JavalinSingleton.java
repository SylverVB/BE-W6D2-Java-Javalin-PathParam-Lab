import io.javalin.Javalin;

public class JavalinSingleton {

    /**
     * This method configures a Javalin app instance and sets up endpoints.
     * In this case, we create three HTTP GET endpoints:
     * - /hello: returns the string "Hello World"
     * - /client-side-err: returns a 400 status code with a message "Client Side Error"
     * - /firstname/{first}: retrieves the variable "first" from the path parameter and sends it in the response body.
     *
     * The app.start() method will not be called here. The main method in App.java
     * will start the server. This method is only responsible for setting up and returning the configured Javalin server.
     *
     * @return The configured Javalin server instance.
     */
    public static Javalin getInstance() {
        // Create a new instance of Javalin server
        Javalin app = Javalin.create();

        // Define an HTTP GET endpoint at "/hello"
        app.get("/hello", ctx -> ctx.result("Hello World"));

        // Define an HTTP GET endpoint at "/client-side-err"
        // When a GET request is made to "/client-side-err", the server responds with status 400 and message "Client Side Error"
        app.get("/client-side-err", ctx -> {
            ctx.status(400);
            ctx.result("Client Side Error");
        });

        /**
         * Assignment: retrieve the variable "first" from the path parameter and send it in the response body. Produce the response using:
         * ctx.result(StringVariable);
         *
         * Please refer to the "PathParameters.MD" file.
         */
        // Define an HTTP GET endpoint at "/firstname/{first}"
        app.get("/firstname/{first}", ctx -> {
            // Retrieve the 'first' path parameter
            String firstName = ctx.pathParam("first");
            // Send the path parameter as the response body
            ctx.result(firstName);
        });

        // Return the configured Javalin app instance
        return app;
    }
}