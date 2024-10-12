package ru.netology.servlet;

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

public class MainServlet extends HttpServlet {
    private PostController controller;
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";
    private static final String path = "/api/posts";
    private static final String pathWithContinuation = "/api/posts/\\\\d+";
    private static final String slash = "/";



    @Override
    public void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext("ru.netology");
        controller = (PostController) context.getBean("postController");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            final var path = req.getRequestURI();
            final var method = req.getMethod();
            // primitive routing

            if (method.equals(GET) && path.equals(path)) {
                controller.all(resp);
                return;
            }
            if (method.equals(GET) && path.matches(pathWithContinuation)) {
                System.out.println("Запущен");
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf(slash)));
                System.out.println("id = " + id);
                controller.getById(id, resp);
                return;
            }
            if (method.equals(POST) && path.equals(path)) {
                controller.  save(req.getReader(), resp);
                return;
            }
            if (method.equals(DELETE) && path.matches(pathWithContinuation)) {
                // easy way
                final var id = Long.parseLong(path.substring(path.lastIndexOf(slash)));
                controller.removeById(id, resp);
                return;
            }
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}

