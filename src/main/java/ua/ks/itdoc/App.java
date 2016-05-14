package ua.ks.itdoc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ua.ks.itdoc.config.WebConfig;
import ua.ks.itdoc.service.impl.CalculatorService;

@Configuration
@ComponentScan({"ua.ks.itdoc"})

public class App {

    public static void main(String[] args) {
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
        new WebConfig(ctx.getBean(CalculatorService.class));
        ctx.registerShutdownHook();

    }
}
