package org.onehippo.undefinedvariableReporter;

/**
 * Created by mrop on 11/12/15.
 */
public abstract class AbstractUndefinedVariableReporter implements UndefinedVariableReporter {

    private UndefinedVariableReporter undefinedVariableReporter;

    public AbstractUndefinedVariableReporter(final UndefinedVariableReporter undefinedVariableReporter) {
        this.undefinedVariableReporter = undefinedVariableReporter;
    }

    public AbstractUndefinedVariableReporter() {
        this(null);
    }

    @Override
    public void report(final String name){
        if (undefinedVariableReporter!=null){
            undefinedVariableReporter.report(name);
        }
    }
}
