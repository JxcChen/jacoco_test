package com.example.demo.ast;

import com.example.demo.ast.structure.MyASTNode;
import com.example.demo.ast.structure.MyDiffMethodNode;
import org.eclipse.jdt.core.dom.*;


import java.io.File;
import java.util.List;

/**
 * @author: JJJJ
 * @date:2021/9/10 10:25
 * @Description: TODO
 */
public class ASTGenerator {
    public static void parseDiff(File classFile, String diffStr) {
        // 先判断classFile是否为文件
        if(!classFile.isFile())
            return;

        // 利用ast 判断所在方法是否被修改
        // 设置版本
        ASTParser astParser = ASTParser.newParser(AST.JLS8);
        // 传入文件路径
        astParser.setSource(classFile.getAbsolutePath().toCharArray());
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
        // 获取CompilationUnit对象

        final CompilationUnit cu = (CompilationUnit) astParser.createAST(null);
        // 获取MethodNodeVisitor对象
        MethodNodeVisitor methodNodeVisitor = new MethodNodeVisitor();
        cu.accept(methodNodeVisitor);
        // 遍历节点
       for (MethodDeclaration m : methodNodeVisitor.getMethodDecs()){
           MyDiffMethodNode mNode = new MyDiffMethodNode();
           mNode.methodNode = m;
           NodeVisitor nodeVisitor = new NodeVisitor();
           m.accept(nodeVisitor);
           // 获取节点列表
           List<ASTNode> astNodes = nodeVisitor.getASTNodes();
           // 遍历节点列表
           for (ASTNode node: astNodes
                ) {
               MyASTNode myNode = new MyASTNode();
               myNode.astNode = node;
               // 获取方法起始行号
               myNode.lineNum = cu.getLineNumber(node.getStartPosition());
               // 将myNode储存
               mNode.nodeList.add(myNode);
           }
       }
    }
}
