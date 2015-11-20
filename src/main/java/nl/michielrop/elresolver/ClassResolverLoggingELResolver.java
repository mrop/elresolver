package nl.michielrop.elresolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.el.*;
import javax.servlet.jsp.el.ScopedAttributeELResolver;


public class ClassResolverLoggingELResolver extends ScopedAttributeELResolver {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClassResolverLoggingELResolver.class);

    @Override
    public Object getValue(ELContext context, Object base, Object property) {
        return super.getValue(new ELContextWrapper(context), base, property);
    }

    class ELContextWrapper extends StandardELContext {

        private ELContext context;

        public ELContextWrapper(ELContext context) {
            super(context);
        }

        @Override
        public ImportHandler getImportHandler() {
            return new ImportHandler() {
                @Override
                public Class<?> resolveClass(String name) {
                    LOGGER.info("Variable {} is undefined, please add a scope", name);
                    throw new ELException(String.format("Variable %s is undefined, please add a scope", name));

                }
            };
        }

        @Override
        public ELResolver getELResolver() {
            return context.getELResolver();
        }

        @Override
        public FunctionMapper getFunctionMapper() {
            return context.getFunctionMapper();
        }

        @Override
        public VariableMapper getVariableMapper() {
            return context.getVariableMapper();
        }

        @Override
        public Object getContext(Class key) {
            return super.getContext(key);
        }
    }


}
