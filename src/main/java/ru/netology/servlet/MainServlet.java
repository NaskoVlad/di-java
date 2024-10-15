package ru.netology.servlet;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.AnnotatedArrayType;
import java.nio.file.Files;

public class MainServlet extends HttpServlet {
    public static void main(String[] args) throws LifecycleException, IOException {
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory("tomcat");
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(9999);
        tomcat.setConnector(connector);

        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");

        tomcat.start();
        tomcat.getServer().await();
    }
}

