package org.onehippo.undefinedvariableReporter;

import javax.el.*;
import javax.servlet.jsp.el.ScopedAttributeELResolver;


public abstract class ClassResolverLoggingELResolver extends ScopedAttributeELResolver {



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
                public Class<?> resolveStatic(final String name) {
                    reportUndefinedVariable(name);
                    return null;
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

    public abstract void reportUndefinedVariable(final String name);


}
