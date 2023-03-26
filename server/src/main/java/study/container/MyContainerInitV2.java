
package study.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

/**
 * HandleTypes(*.class) 괄호 안의 인터페이스의 구현체를 Set<Class<?>> c 에 넘겨준다
 * 초기화 과정
 * 1. HandleTypes 어노테이션에 어플리케이션 초기화 인터페이스 지정
 * 2. 서블릿 컨테이너 초기화(ServletContainerInitializer)에서 파라미터로 넘어오는 c 에
 * 어플리케이션 초기화 인터페이스 구현체를 찾아 클래스 정보를 전달
 * 3. 리플렉션 사용해서 객체 생성 ->  appInitClass.getDeclaredConstructor().newInstance()
 * 4. appInitClass.OnStartup(ctx) -> 어플리케이션 초기화
 * 결국 순서는 서블릿 컨테이너 초기화(+ HandleTypes 읽음) -> 어플리케이션 초기화(+ HandleTypes 로 읽은 클래스 초기화)
 */
@HandlesTypes(AppInitializer.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + c);
        System.out.println("MyContainerInitV2 ctx = " + ctx);


        for (Class<?> appInitClass : c) {
            try {
                // new AppInitV1Servlet()
                AppInitializer appInitializer = (AppInitializer) appInitClass.getDeclaredConstructor().newInstance();
                appInitializer.onStartup(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
