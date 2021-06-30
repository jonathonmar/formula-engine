/*
 * Created on Dec 10, 2004
 *
 */
package sfdc.formula.commands;

import java.io.Serializable;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import sfdc.formula.*;
import sfdc.formula.impl.FormulaCommandVisitor;
import sfdc.formula.impl.ITableAliasRegistry;

/**
 * Command pattern interface for formula operations
 *
 * @author dchasman
 * @since 140
 */
public interface FormulaCommand extends Serializable {

    String getName();
    void execute(FormulaRuntimeContext context, Deque<Object> stack) throws Exception;
    boolean isDeterministic(FormulaContext formulaContext);
    boolean isStale(FormulaContext formulaContext);
    default boolean hasAIPredictionFieldReference(FormulaContext formulaContext) {
        return false;
    }

    List<FormulaFieldReferenceInfo> getDirectReference(FormulaContext formulaContext, ITableAliasRegistry registry,
                boolean zeroExcluded, boolean allowDateValue, AtomicBoolean caseSafeIdUsed, FormulaDataType formulaResultDataType) throws UnsupportedTypeException, InvalidFieldReferenceException;

    // Hooks for core.
    default boolean isCustomIndexable(FormulaContext formulaContext) {
    	return isDeterministic(formulaContext);
    }
    default boolean isFlexIndexable(FormulaContext formulaContext) {
    	return isCustomIndexable(formulaContext);
    }
    default boolean isPostSaveIndexUpdated(FormulaContext formulaContext, FormulaDmlType dmlType) {
    	return false;
    }

    /**
     * Use this hook to perform bulk processing before the formulas are actually executed.  Useful
     * for formula functions like vlookup that perform a query for all contexts and cache
     * the value for runtime evaluation.
     *
     * @param contexts
     * @throws Exception
     */
    void preExecuteInBulk(List<FormulaRuntimeContext> contexts) throws Exception;

    /**
     * Check that all merge field referenced directly (in the formula itself) or indirectly (via
     * a formula field reference inside of this formula) are available for this FormulaType.
     *
     * If a merge field is invalid, return the FormulaException
     */
    FormulaException validateMergeFieldsForFormulaType(FormulaContext formulaContext);

    void visit(FormulaCommandVisitor visitor);
    
    /**
     * @return true if evaluating this command yields a field reference
     * Used only by FieldReferenceCommand for now...
     */
    default boolean isFieldReference() {
        return false;
    }
}
