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
import java.util.HashMap;
import java.util.List;

public class MyDiffInfo {
	// 类路径
	public String class_path;
	// 类名
	public String class_name;
	// 修改的行号集合
	public List<Integer> chang_line_num = new ArrayList<>();
	// 修改的行号和对应行内容
	public  HashMap<Integer,String> chang_line = new HashMap<>();


	public String getClass_path() {
		return class_path;
	}

	public void setClass_path(String class_path) {
		this.class_path = class_path;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public List<Integer> getChang_line_num() {
		return chang_line_num;
	}

	public void setChang_line_num(List<Integer> chang_line_num) {
		this.chang_line_num = chang_line_num;
	}

	public HashMap<Integer, String> getChang_line() {
		return chang_line;
	}

	public void setChang_line(HashMap<Integer, String> chang_line) {
		this.chang_line = chang_line;
	}
}
