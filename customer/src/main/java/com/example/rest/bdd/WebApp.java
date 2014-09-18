package com.example.rest.bdd;

import com.example.rest.bdd.config.WebConfig;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public final class WebApp {

    public static void main(String[] args) throws Exception {
        new WebApp().start(WebConfig.class);
    }

    public AbstractApplicationContext start(Class<?>... configurations) throws Exception {
        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(configurations);

        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(servletHolder, "/*");

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        final Server server = new Server(Integer.valueOf(webPort));
        server.setHandler(context);
        server.start();

        return applicationContext;
    }
}