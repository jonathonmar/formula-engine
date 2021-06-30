package sfdc.formula.commands;

import java.util.Deque;

import sfdc.formula.*;
import sfdc.formula.impl.*;

/**
 * Describe your class here.
 *
 * @author djacobs
 * @since 140
 */
public class ConstantFalse extends ConstantBase {
    public ConstantFalse() {
        super("FALSE", Boolean.class, new Class[0]);
    }

    @Override
    public FormulaCommand getCommand(FormulaAST node, FormulaContext context) {
        return new ConstantFalseCommand(this);
    }

    @Override
    public SQLPair getSQL(FormulaAST node, FormulaContext context, String[] args, String[] guards, TableAliasRegistry registry) {
        return new SQLPair("(0=1)", null);
    }

    @Override
    public JsValue getJavascript(FormulaAST node, FormulaContext context, JsValue[] args) throws FormulaException {
        return new JsValue("false", null, false);
    }
}

class ConstantFalseCommand extends AbstractFormulaCommand {
    private static final long serialVersionUID = 1L;

	public ConstantFalseCommand(FormulaCommandInfo formulaCommandInfo) {
        super(formulaCommandInfo);
    }

    @Override
    public void execute(FormulaRuntimeContext context, Deque<Object> stack) throws Exception {
        stack.push(Boolean.FALSE);
    }
}
