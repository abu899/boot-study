package study.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import study.spring.HelloConfig;

/**
 * SpringServletContainerInitializer 가 spring-web 내 META_INF 의 services 에  등록되어 있으며
 * 내부로 들어가보면 WebApplicationInitializer 가 HandleTypes 로 등록되어있는 것을 볼 수 있음
 */
public class AppInitializerV3SpringMvc implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("AppInitializerV3SpringMvc.onStartup");

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        // 스프링 mvc dispatcher servlet 생성 및 spring container 연결
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appContext);

        // 서블릿 컨텍스트에 dispatcher servlet 등록
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV3", dispatcherServlet);
        servlet.addMapping("/"); // spring/* 로 들어오면 dispatcher servlet 을 통함
    }
}
