/*******************************************************************************
 * Copyright (c) 2009, 2018 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *
 *******************************************************************************/
package com.example.demo.ast;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;


public class MethodNodeVisitor extends ASTVisitor {

	List<MethodDeclaration> methodNodeList = new ArrayList<>();

	public List<MethodDeclaration> getMethodDecs() {
		return methodNodeList;
	}

	@Override
	public boolean visit(MethodDeclaration node) {
		methodNodeList.add(node);
		return true;
	}

}
