package study;

import org.apache.catalina.LifecycleException;
import study.boot.MySpringApplication;
import study.boot.MySpringBootApplication;

@MySpringBootApplication
public class MySpringBootMain {
    public static void main(String[] args) throws LifecycleException {
        System.out.println("MySpringBootMain.main");
        MySpringApplication.run(MySpringBootMain.class, args);
    }
}
