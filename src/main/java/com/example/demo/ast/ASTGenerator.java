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
import com.example.demo.ast.structure.MyDiffMethodNode;
import org.eclipse.jdt.core.dom.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ASTGenerator {

	public List<MyDiffMethodNode> methodNodeList = new ArrayList<MyDiffMethodNode>();

	public ASTGenerator(File f) {
		// TODO Auto-generated constructor stub
//		ParseFile(f);
	}

	public List<MyDiffMethodNode> getMethodNodeList() {
		return methodNodeList;
	}

	// read file content into a string
	public String readFileToString(String filePath) throws IOException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));

		char[] buf = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			// System.out.println(numRead);
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}

		reader.close();

		return fileData.toString();
	}

	// use ASTParse to parse string
	public void parse(String str) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(str.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);

		final CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		// find the MethodDeclaration node, MethodNodeVisitor
		MethodNodeVisitor methodNodeVisitor = new MethodNodeVisitor();
		cu.accept(methodNodeVisitor);
		// traverse all child nodes, NodeVisitor
		for (MethodDeclaration m : methodNodeVisitor.getMethodDecs()) {
			MyDiffMethodNode mNode = new MyDiffMethodNode();
			mNode.methodNode = m;
			NodeVisitor nv = new NodeVisitor();
			m.accept(nv);
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
				int pHashcode = node.getParent().hashCode();
				int hashcode = node.hashCode();
				int[] link = { pHashcode, hashcode };
				mNode.mapping.add(link);
			}
			methodNodeList.add(mNode);
		}
	}

	// loop directory to get file list
	public void ParseFilesInDir() throws IOException {
		File dirs = new File(".");
		String dirPath = dirs.getCanonicalPath() + File.separator + "src" + File.separator;

		File root = new File(dirPath);
		// System.out.println(rootDir.listFiles());
		File[] files = root.listFiles();
		String filePath = null;

		for (File f : files) {
			filePath = f.getAbsolutePath();
			if (f.isFile()) {
				parse(readFileToString(filePath));
			}
		}
	}

	// loop directory to get file list
	public void ParseFile(File f) {
		String filePath = f.getAbsolutePath();
		// 判断f是否一个文件
		if (f.isFile()) {
			try {
				parse(readFileToString(filePath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
//			System.out.println(f.getAbsolutePath()+"Not a File!");
		}
	}
}
