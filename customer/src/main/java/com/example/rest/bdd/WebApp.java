package com.example.rest.bdd;

import com.example.rest.bdd.config.WebConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public final class WebApp {

    public static void main(String[] args) throws Exception {
        new WebApp().start(WebConfig.class);
    }

    public WebApplicationContext start(Class<?>... configurations) throws Exception {
        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(configurations);

        final Server server = new Server(webPort());
        server.setHandler(contextHandlerFor(applicationContext));
        server.start();

        return applicationContext;
    }

    private int webPort() {
        String webPort = System.getenv("PORT");
        if ((webPort == null) || webPort.isEmpty()) {
            webPort = "7171";
        }
        return Integer.valueOf(webPort);
    }

    private ServletContextHandler contextHandlerFor(AnnotationConfigWebApplicationContext applicationContext) {
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new DispatcherServlet(applicationContext)), "/*");
        return context;
    }
}