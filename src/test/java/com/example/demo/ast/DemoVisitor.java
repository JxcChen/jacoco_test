package com.example.demo.ast;

import org.eclipse.jdt.core.dom.*;

/**
 * @author: JJJJ
 * @date:2021/7/1 16:19
 * @Description: TODO
 */
public class DemoVisitor extends ASTVisitor {

    @Override
    public boolean visit(FieldDeclaration node) {
        for (Object obj: node.fragments()) {
            VariableDeclarationFragment v = (VariableDeclarationFragment)obj;
            System.out.println("Field:\t" + v.getName());
        }

        return true;
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        System.out.println("Method:\t" + node.getName());
        return true;
    }

    @Override
    public boolean visit(TypeDeclaration node) {
        System.out.println("Class:\t" + node.getName());
        return true;
    }
}