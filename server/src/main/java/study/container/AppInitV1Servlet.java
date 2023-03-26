package study.container;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import study.servlet.HelloServlet;

/**
 * @WebServlet 대신 프로그래밍 방식을 사용하는 이유
 * WebServlet 어노테이션은 하드코딩 되어있지만 프로그래밍 방식은 다소 불편하지만 유연성을 제공
 */
public class AppInitV1Servlet implements AppInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV1Servlet.onStartup");

        // 순수 서블릿 코드 등록
        ServletRegistration.Dynamic helloServlet = servletContext.addServlet("helloServlet", new HelloServlet());
        helloServlet.addMapping("/hello-servlet");
    }
}
