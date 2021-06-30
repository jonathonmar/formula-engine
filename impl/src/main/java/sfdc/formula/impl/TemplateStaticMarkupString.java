/*
 * Copyright, 2006, salesforce.com
 */
package sfdc.formula.impl;

import java.io.Serializable;

import sfdc.formula.commands.ConstantString;


/**
 * Marker object to indicate this was static markup from a template
 *
 * @author dchasman
 * @since 144
 */
public class TemplateStaticMarkupString extends ConstantString.StringWrapper implements Serializable {

    private static final long serialVersionUID = 1L;

	public TemplateStaticMarkupString(String value) {
        super(value);
    }

}
