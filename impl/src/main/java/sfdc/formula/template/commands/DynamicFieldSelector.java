/*
 * Copyright, 2010, salesforce.com All Rights Reserved Company Confidential
 */
package sfdc.formula.template.commands;

import java.util.Deque;

import sfdc.formula.*;
import sfdc.formula.FormulaCommandType.AllowedContext;
import sfdc.formula.FormulaCommandType.SelectorSection;
import sfdc.formula.commands.*;
import sfdc.formula.impl.*;
/**
 * Formula Command Info/Command for a dynamic reference field element; i.e. in an expression like a[b].c, the .c part.   
 * @author aballard
 * @since 168
 */
@AllowedContext(section=SelectorSection.ADVANCED,isJavascript=false)
public class DynamicFieldSelector extends FormulaCommandInfoImpl {

    public final static String DYNAMIC_REF_IDENT = FormulaCommandInfoRegistry.DYNAMIC_REF_IDENT; 
    
    public DynamicFieldSelector() {
        super(DYNAMIC_REF_IDENT, String.class, new Class[]{});
    }

    @Override
    public FormulaCommand getCommand(FormulaAST node, FormulaContext context) {
        return new DynamicFieldSelectorCommand(this, node.getText());
    }

    @Override
    public SQLPair getSQL(FormulaAST node, FormulaContext context, String[] args, String[] guards, TableAliasRegistry registry)
        throws FormulaException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public JsValue getJavascript(FormulaAST node, FormulaContext context, JsValue[] args) throws FormulaException {
        throw new UnsupportedOperationException();
    }
}

class DynamicFieldSelectorCommand extends AbstractFormulaCommand {
    private static final long serialVersionUID = 1L;
    DynamicFieldSelectorCommand(FormulaCommandInfo info, String fieldName) {
        super(info);
        this.fieldName = fieldName;
    }

    // The name is the value.
    @Override
    public void execute(FormulaRuntimeContext context, Deque<Object> stack) throws Exception {
        stack.push(fieldName);
    }

    private final String fieldName;

}
