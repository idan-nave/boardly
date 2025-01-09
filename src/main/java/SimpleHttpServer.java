import com.sun.net.httpserver.HttpServer;
import handlers.DashboardHandler;
import handlers.LoginHandler;

import java.net.InetSocketAddress;

public class SimpleHttpServer {
    public static void main(String[] args) throws Exception {
        // יצירת אובייקט HttpServer
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // הגדרת ה-Handlers
//        server.createContext("/register", new RegisterHandler());
        server.createContext("/login", new LoginHandler());
        server.createContext("/dashboard", new DashboardHandler());
//        server.createContext("/process", new ProccessHandler());
//        server.createContext("/teams", new TeamsHandler());
//        server.createContext("/addWorker", new AddWorkerHandler());
//        server.createContext("/test", new TestHandler());
        // התחלת השרת
        server.start();
        System.out.println("Server started on http://localhost:8080");
    }
}

