package sfdc.formula.commands;

import sfdc.formula.*;
import sfdc.formula.impl.*;

/**
 * Describe your class here.
 *
 * @author djacobs
 * @since 140
 */
public class ConstantNull extends ConstantBase {
    public ConstantNull() {
        super("NULL", ConstantNull.class, new Class[0]);
    }

    @Override
    public FormulaCommand getCommand(FormulaAST node, FormulaContext context) {
        return new ConstantNullCommand(this);
    }

    @Override
    public SQLPair getSQL(FormulaAST node, FormulaContext context, String[] args, String[] guards, TableAliasRegistry registry) {
        return new SQLPair("NULL", null);
    } 
    
    @Override
    public JsValue getJavascript(FormulaAST node, FormulaContext context, JsValue[] args) throws FormulaException {
        return JsValue.NULL;
    }
}

