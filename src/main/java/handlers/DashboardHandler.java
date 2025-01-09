package handlers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import services.TeamService;
import services.ProcessService;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class DashboardHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // הדפסת הודעה לטרמינל כאשר בקשה מתקבלת
        System.out.println("Received request: " + exchange.getRequestMethod() + " " + exchange.getRequestURI());

        // הגדרות CORS
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        // בדיקת סוג הבקשה
        if ("GET".equals(exchange.getRequestMethod())) {
            try {
                // יצירת אובייקטים של השירותים
                TeamService teamService = new TeamService();
                ProcessService processService = new ProcessService();

                // קבלת הנתונים דרך המתודות בשירותים
                List<String> teamNames = teamService.getAllTeamNames();
                List<String> processNames = processService.getAllProcessNames();

                // הדפסת הנתונים לטרמינל
                System.out.println("Teams: " + teamNames);
                System.out.println("Processes: " + processNames);

                // יצירת JSON
                StringBuilder responseBuilder = new StringBuilder();
                responseBuilder.append("{\n");
                responseBuilder.append("\"teams\": ").append(teamNames).append(",\n");
                responseBuilder.append("\"processes\": ").append(processNames).append("\n");
                responseBuilder.append("}");

                String response = responseBuilder.toString();

                // הגדרת סוג התוכן ושליחת התגובה
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            } catch (Exception e) {
                // טיפול בשגיאות בזמן ריצה
                String errorResponse = "Error processing request: " + e.getMessage();
                System.err.println("Error handling request: " + e.getMessage());
                exchange.sendResponseHeaders(500, errorResponse.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(errorResponse.getBytes());
                }
            }
        } else {
            // אם הבקשה אינה מסוג GET, החזר שגיאה
            String errorResponse = "Only GET method is supported.";
            exchange.sendResponseHeaders(405, errorResponse.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(errorResponse.getBytes());
            }
        }
    }
}
