package com.example.demo.ast;

import com.example.demo.dto.ast.MyDiffMethodNode;
import com.example.demo.utils.DiffMethods;
import com.example.demo.utils.GitUtils;
import com.example.demo.utils.JdtAstGenerator;
import org.eclipse.jdt.core.dom.*;
import org.junit.jupiter.api.Test;

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

    @Test
    void test01(){
        JdtAstGenerator jdtAstGenerator = new JdtAstGenerator("src/main/java/com/example/demo/controller/CalculatorController.java",
                GitUtils.diffMethod("44ef696caed38fbbb0dd151128f1181c63041b85", "2990d78b0b285aaa5ff902e0db48b3e38e783a65"));
        List<MyDiffMethodNode> methodNodeList = jdtAstGenerator.getMethodNodeList();
        System.out.println("debug");
    }

    @Test
    void test02() throws Exception {
        DiffMethods.sourcePath = "/Users/chnjx/IdeaProjects/jacoco_test/src/main/java/";
        boolean is_change_add = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController",
                "getReport",
                "(Lcom/cyber/range/controller/Param;)Ljava/lang/String;\"",
                GitUtils.diffMethod("44ef696caed38fbbb0dd151128f1181c63041b85", "2990d78b0b285aaa5ff902e0db48b3e38e783a65")
        );
        boolean is_change_add2 = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController",
                "getReport2",
                "(Lcom/cyber/range/controller/Param;)Ljava/lang/String;\"",
                GitUtils.diffMethod("44ef696caed38fbbb0dd151128f1181c63041b85", "2990d78b0b285aaa5ff902e0db48b3e38e783a65")
        );
        System.out.println("debug");
    }

    @Test
    void test03(){
        System.out.println(System.getProperty("user.dir") + "/src/main/java/");
    }

}