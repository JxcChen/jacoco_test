package com.example.demo.dto.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JJJJ
 * @Title: node_visitor
 * @date 2022/7/248:26 上午
 * @Description: 节点访问器
 */
public class MyNodeVisitor extends ASTVisitor {
    List<ASTNode> nodeList = new ArrayList<>();

    @Override
    public void preVisit(ASTNode node){
        nodeList.add(node);
    }

    public MyNodeVisitor(List<ASTNode> nodeList) {
        this.nodeList = nodeList;
    }
}
