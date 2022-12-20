package com.company.Jetty;

import com.company.Servlet.PlayerServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Server {

    private org.eclipse.jetty.server.Server build(){
        final org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server();
        final int port = 8080;
        final HttpConfiguration httpConfig = new HttpConfiguration();
        final HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(httpConfig);
        final ServerConnector serverConnector = new ServerConnector(server, httpConnectionFactory);
        serverConnector.setHost("localhost");
        serverConnector.setPort(port);
        server.setConnectors(new Connector[]{serverConnector});
        return server;
    }
    public void initServer() throws Exception {
        final org.eclipse.jetty.server.Server server = build();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        context.addServlet(new ServletHolder("players", new PlayerServlet()), "/players/*");
        context.addServlet(new ServletHolder("items", new PlayerServlet()), "/items/*");
        context.addServlet(new ServletHolder("currencies", new PlayerServlet()), "/currencies/*");
        context.addServlet(new ServletHolder("progresses", new PlayerServlet()), "/progresses/*");
        server.setHandler(context);
        server.start();
    }
}
