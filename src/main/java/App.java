import io.javalin.Javalin;

public class App {

    public static void main(String[] args) {
        // Get the Javalin app instance from JavalinSingleton
        Javalin app = JavalinSingleton.getInstance();

        // Start the server
        app.start(9000);
    }
}