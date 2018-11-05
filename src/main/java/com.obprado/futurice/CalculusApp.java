package com.obprado.futurice;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;

public class CalculusApp {

    private Server server;

    public static void main(String[] args) throws Exception {
        new CalculusApp().run();
    }

    public void run() throws Exception {
        int port = 80;
        server = new Server();
        server.addConnector(serverConnector(port, server));
        server.setHandler(calculusServletHandler());
        server.start();
    }

    public void stop() throws Exception {
        server.stop();
    }

    private static ServletContextHandler calculusServletHandler() throws IOException {
        ServletContextHandler servletHandler = new ServletContextHandler();

        servletHandler.addServlet(new ServletHolder(new CalculusServlet()), "/calculus");
        return servletHandler;
    }

    private ServerConnector serverConnector(int port, Server server) {
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        return connector;
    }
}
