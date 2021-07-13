//package com.example.demo.utils;
//import java.io.BufferedInputStream;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
//import org.eclipse.jdt.core.dom.AST;
//import org.eclipse.jdt.core.dom.ASTParser;
//import org.eclipse.jdt.core.dom.CompilationUnit;
//
///**
// * @author: JJJJ
// * @date:2021/7/1 16:23
// * @Description: TODO
// */
//
//
//public class JdtAstUtils {
//    /**
//     * get compilation unit of source code
//     * @param javaFilePath
//     * @return CompilationUnit
//     */
//    public static CompilationUnit getCompilationUnit(String javaFilePath){
//        byte[] input = null;
//        try {
//            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
//            input = new byte[bufferedInputStream.available()];
//            bufferedInputStream.read(input);
//            bufferedInputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        ASTParser astParser = ASTParser.newParser(AST.JLS3);
//        astParser.setSource(new String(input).toCharArray());
//        astParser.setKind(ASTParser.K_COMPILATION_UNIT);
//
//        CompilationUnit result = (CompilationUnit) (astParser.createAST(null));
//
//        return result;
//    }
//}
