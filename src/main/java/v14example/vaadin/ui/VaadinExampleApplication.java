package v14example.vaadin.ui;

import com.vaadin.flow.server.VaadinServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// https://www.google.com/search?client=safari&rls=en&q=run+spring+boot+application+as+windows+service&ie=UTF-8&oe=UTF-8

/**
 * The entry point of the Spring Boot application.
 */
@Configuration
@SpringBootApplication
public class VaadinExampleApplication extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(VaadinExampleApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean frontendServletBean()
    {
        ServletRegistrationBean bean = new ServletRegistrationBean<>(new VaadinServlet() {
            @Override
            protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
            {
                if (!serveStaticOrWebJarRequest(req, resp))
                {
                    resp.sendError(404);
                }
            }
        }, "/frontend/*");
        bean.setLoadOnStartup(1);
        return bean;
    }
}
