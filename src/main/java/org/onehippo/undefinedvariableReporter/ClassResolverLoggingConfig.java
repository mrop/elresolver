package org.onehippo.undefinedvariablereporter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;

import org.onehippo.undefinedvariablereporter.reporter.LoggingUndefinedVariableReporter;


@WebListener
public class ClassResolverLoggingConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        JspApplicationContext jspContext = JspFactory.getDefaultFactory().getJspApplicationContext(servletContext);
        jspContext.addELResolver(new LoggingUndefinedVariableReporter());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
