package study.container;

import jakarta.servlet.ServletContext;

public interface AppInitializer {
    void onStartup(ServletContext servletContext);
}
