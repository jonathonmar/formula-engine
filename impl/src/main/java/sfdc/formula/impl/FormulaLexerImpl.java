/*
 * Copyright, 2006, SALESFORCE.com All Rights Reserved Company Confidential
 */

package sfdc.formula.impl;

import java.io.StringReader;

import sfdc.formula.parser.gen.FormulaLexer;

/**
 * @author dchasman
 * @since 140
 */
public class FormulaLexerImpl extends FormulaLexer {

    public FormulaLexerImpl(StringReader reader, boolean template) {
        super(reader);

        setInFormula(!template);
        setTabSize(1);
    }

}
