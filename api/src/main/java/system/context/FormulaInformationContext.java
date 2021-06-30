/*
 * Copyright, 1999-2018, salesforce.com All Rights Reserved Company Confidential
 */
package system.context;

import java.util.Map;

import sfdc.formula.FormulaContext;

/**
 * FormulaInformationContext is a context used by the formula engine for debugging information. Callers of the formula
 * engine should call SfdcCtx.formula().push(context, keysAndValues) when invoking the formula engine with any
 * additional information that would help with debugging formulas.
 *
 * @author a.rich
 * @since 214
 */
public interface FormulaInformationContext /* implements Context */ {

    /**
     * Additional information which may be set by the caller of the formula engine
     */
    public Map<String, String> getAdditionalInfo();

    /**
     * Each formula context can override {@link FormulaContext#getMetaInformationMap()} to provide the formula engine
     * useful debugging information relevant to that specific context.
     */
    public Map<String, String> getContextInfo();

    /**
     * @return The name of the FormulaContext that this context was created with
     */
    public String getContextName();

    /**
     * @param stackLevel
     *            what level is this in the context stack?
     * @return a message suitable for appending to gack messages
     */
    public String toGackMessage(int stackLevel);

    /**
     * A FormulaInformationContext Provider
     */
    public interface Provider /* extends StackableContextProvider<FormulaInformationContext> */ {
        /**
         * The new context of a process
         * @param context the context to push onto the stack
         */
    	FormulaInformationContext push(FormulaContext context, String... keysAndValues);

        /**
         * @param assertContext the expected context which should get popped.
         * @return the last context in the stack and remove it from the stack
         * @throws IllegalStateException if you are trying to pop the "last" value
         */
        FormulaInformationContext pop(FormulaInformationContext assertContext);

        String peekStackInfo();
    }

}
