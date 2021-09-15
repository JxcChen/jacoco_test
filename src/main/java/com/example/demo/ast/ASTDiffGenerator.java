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


import com.example.demo.ast.structure.MyASTNode;
import com.example.demo.ast.structure.MyDiffInfo;
import com.example.demo.ast.structure.MyDiffMethodNode;
import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ASTDiffGenerator extends ASTGenerator{

	public List<MyDiffMethodNode> diffMethodNodeList = new ArrayList<MyDiffMethodNode>();
	public DiffInfoGenerator diffinfo_generator = null;
	private String class_name;
	private String pakage_name;
	public String class_path_name;
	public ASTDiffGenerator(File f, String diff_str) {
		super(f);
		//传入diff_json中的数据：每个类及类中改变的行数
		diffinfo_generator = new DiffInfoGenerator(diff_str);
		// 通过AST把类源码实例化成对象，并通过拿到的类变化行数，标记每个方法的is_change成员变量
		ParseFile(f);
	}

	public List<MyDiffMethodNode> getMethodNodeList() {
		return methodNodeList;
	}

	// read file content into a string
	// use ASTParse to parse string
	public void parse(String str) {
		// 固定写法
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		// find the MethodDeclaration node, MethodNodeVisitor
		// TODO: 具体用法查询
		MethodNodeVisitor methodNodeVisitor = new MethodNodeVisitor();
		cu.accept(methodNodeVisitor);

		// 获取包名
		this.pakage_name=cu.getPackage().getName().toString();
		TypeDeclaration type=null;
		if(cu.types().size()>0) {
			// 获取类中信息 可以获取到类中任何信息
			type = (TypeDeclaration) cu.types().get(0);
			// 获取类名
			this.class_name=type.getName().toString();
		}else{
			this.class_name="Donthaveclass";
		}
		// 将 . 替换成 /
		this.class_path_name=this.pakage_name.replace(".","/")+"/"+class_name+".java";
		// 获取到差异信息列表 方法名 + 差异行号
		List<MyDiffInfo> diffinfolist = diffinfo_generator.getDiffinfolist();
		// traverse all child nodes, NodeVisitor
		for (MethodDeclaration m : methodNodeVisitor.getMethodDecs()) {
			MyDiffMethodNode mNode = new MyDiffMethodNode();
			mNode.methodNode = m;
			NodeVisitor nv = new NodeVisitor();
			m.accept(nv);
			// ASTNode 能够获取类中全部节点
			List<ASTNode> astnodes = nv.getASTNodes();
			for (ASTNode node : astnodes) {
				MyASTNode myNode = new MyASTNode();
				myNode.astNode = node;
				myNode.lineNum = cu.getLineNumber(node.getStartPosition());
				// add to nodeList
				mNode.nodeList.add(myNode);
				// add to mapping
				// in case, I need to exclude root node
				if (node.equals(m)) {
					continue;
				}
//				int pHashcode = node.getParent().hashCode();
//				int hashcode = node.hashCode();
//				int[] link = { pHashcode, hashcode };
//				mNode.mapping.add(link);

			}
			mNode.start_line= mNode.nodeList.get(0).getLineNum();
			mNode.end_line= mNode.nodeList.get(mNode.nodeList.size()-1).getLineNum();
			String class_name=diffinfolist.get(0).class_name;
			String method_name = mNode.methodNode.getName().toString();
			//判断各个类节点中的方法是否改变
			for (MyDiffInfo diffinfo : diffinfolist){
				if(this.class_path_name.equals(diffinfo.class_name) ){
					for(int i : diffinfo.getChang_line_num()){
						if(i>=mNode.start_line && i<=mNode.end_line){
							mNode.is_changed = true;
						}
					}
				}
			}
			methodNodeList.add(mNode);
		}
	}
}
