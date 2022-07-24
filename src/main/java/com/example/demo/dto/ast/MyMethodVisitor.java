package com.example.demo.dto.ast;


import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JJJJ
 * @Title:
 * @date 2022/7/248:22 上午
 * @Description: 方法访问器
 */
public class MyMethodVisitor extends ASTVisitor {
    List<MethodDeclaration> methodList = new ArrayList<>();

    @Override
    public boolean visit(MethodDeclaration node){
        methodList.add(node);
        return true;
    }

    public List<MethodDeclaration> getMethodList() {
        return methodList;
    }
}
