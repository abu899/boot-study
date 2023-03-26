package study.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import study.spring.HelloConfig;

public class AppInitializerV2Spring implements AppInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitializerV2Spring.onStartup");

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 스프링 mvc dispatcher servlet 생성 및 spring container 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // 서블릿 컨텍스트에 dispatcher servlet 등록
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV2", dispatcherServlet);
        servlet.addMapping("/spring/*"); // spring/* 로 들어오면 dispatcher servlet 을 통함
    }
}
