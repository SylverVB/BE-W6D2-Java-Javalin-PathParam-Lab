import io.javalin.Javalin;
import static io.javalin.testtools.JavalinTest.test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavalinSingletonTest {

    @Test
    public void testHelloEndpoint() {
        test(Javalin.create().get("/hello", ctx -> ctx.result("Hello World")), 
        (server, client) -> {
            var response = client.get("/hello").body().string();
            assertEquals("Hello World", response);
        });
    }

    @Test
    public void testClientSideErrorEndpoint() {
        test(Javalin.create().get("/client-side-err", ctx -> {
            ctx.status(400);
            ctx.result("Client Side Error");
        }), (server, client) -> {
            var response = client.get("/client-side-err");
            assertEquals(400, response.code());
            assertEquals("Client Side Error", response.body().string());
        });
    }

    @Test
    public void testFirstnamePathParamEndpoint() {
        test(Javalin.create().get("/firstname/{name}", ctx -> 
            ctx.result(ctx.pathParam("name"))), 
        (server, client) -> {
            var response = client.get("/firstname/John").body().string();
            assertEquals("John", response);
        });
    }
}

/*
In Java, `var` is a feature introduced in Java 10 as a local variable type inference. When you use `var`, the Java compiler automatically infers the type of the variable based on the expression assigned to it. This means you don’t have to explicitly declare the type of the variable.

In our example:

```java
var response = client.get("/hello").body().string();
```

- The `client.get("/hello")` call returns a `Response` object (from the HTTP client).
- The `body()` method returns a `ResponseBody` object, and `string()` converts the body into a `String`.

So, the compiler automatically infers that `response` is of type `String` because the final result of `client.get("/hello").body().string()` is a `String`.

Using `var` here is just a shorthand, reducing the need to explicitly write out the type (`String`) and making the code cleaner and easier to maintain.

If you were to explicitly declare the type, it would look like:

```java
String response = client.get("/hello").body().string();
``` 

// Both approaches are functionally equivalent, but using `var` simplifies the syntax.
*/