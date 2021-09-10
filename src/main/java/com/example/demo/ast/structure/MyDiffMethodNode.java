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
package com.example.demo.ast.structure;

import java.util.ArrayList;
import java.util.List;

public class MyDiffMethodNode extends MyMethodNode{
	public int start_line;
	public int end_line;
	public boolean is_changed=false;
	public String requestmapping;
	public String request_method;
	public List<MyParam> paramlist = new ArrayList<>();
	public MyDiffMethodNode() {
			super();
	}

}
