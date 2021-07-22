package com.example.demo.ast;

import org.eclipse.jdt.core.dom.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author: JJJJ
 * @date:2021/7/2 10:16
 * @Description: TODO
 */
public class AstTest {

    public static void main(String[] args) throws Exception {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setResolveBindings(true);
        parser.setBindingsRecovery(true);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        File resource = new File("src/main/java/com/example/demo/utils/DiffUtils.java");
        Path path = Paths.get(resource.toURI());
        String sourceStr = new String(Files.readAllBytes(path));
        char[] charsSource = sourceStr.toCharArray();
        parser.setSource(charsSource);
        parser.setUnitName(path.toAbsolutePath().toString());
        CompilationUnit cu =  (CompilationUnit)parser.createAST(null);
        // 获取抽象树
        AST ast = cu.getAST();
        // 获取类 如果有多个就会获取多个
        List<TypeDeclaration> types = cu.types();
        // 获取到第一个
        TypeDeclaration type = types.get(0);
        // 获取属性
//        FieldDeclaration[] fields = type.getFields();
//        for (FieldDeclaration f :
//                fields) {
//            System.out.println(f.toString());
//        }
        // 获取方法
        MethodDeclaration[] methods = type.getMethods();
        for (MethodDeclaration method:
                methods) {
            int lineNumber = cu.getLineNumber(method.getStartPosition());
            int length = method.getLength();
            System.out.println(length+"+++");
            System.out.println(lineNumber);
            System.out.println(method.toString());

        }


    }
}