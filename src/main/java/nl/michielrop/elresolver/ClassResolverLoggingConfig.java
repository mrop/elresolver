package nl.michielrop.elresolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;


@WebListener
public class ClassResolverLoggingConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        JspApplicationContext jspContext = JspFactory.getDefaultFactory().getJspApplicationContext(servletContext);
        jspContext.addELResolver(new ClassResolverLoggingELResolver());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
