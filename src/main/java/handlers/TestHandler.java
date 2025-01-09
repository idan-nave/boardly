import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class TestHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // הוספת כותרת CORS כדי לאפשר גישה מהדומיינים המתאימים
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        // הדפסת "hey" לטרמינל כאשר מתקבלת בקשת POST
        System.out.println("hey");  // הודעה שתודפס בטרמינל

        // הודעת "hello" שתשלח בתגובה ללקוח
        String response = "hello";

        // קביעת קוד הסטטוס והודעת התגובה
        exchange.sendResponseHeaders(200, response.getBytes().length);

        // שליחת התגובה
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
