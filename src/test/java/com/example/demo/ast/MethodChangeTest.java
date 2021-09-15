package com.example.demo.ast;

import com.example.demo.utils.DiffUtils;
import com.example.demo.utils.GitUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: JJJJ
 * @date:2021/9/15 14:36
 * @Description: TODO
 */
public class MethodChangeTest {

    @Test
    void test() throws Exception {
        DiffMethods.sourcePath = "G:/gemdale/demo/src/main/java/";
        DiffMethods.sourcePath = "/Users/chnjx/IdeaProjects/jacoco_test/src/main/java/";
        String diffStr = GitUtils.diffMethod("3b411c5a1de398b457c0d1f767a3c096462a1e70","89677c12097739ff429775e52b1e5be9203a460e");
        DiffMethods.diffStr = DiffUtils.getDiffData(diffStr);
        System.out.println(DiffUtils.getDiffData(diffStr));
        DiffMethods.useDiff =true;
        Boolean isChangeAdd = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController","getReport","(Lcom/example/demo/controller/Param;)V");
        Boolean isChangeAdd2 = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController","getReport2","(Lcom/example/demo/controller/Param;)V");
        System.out.println(isChangeAdd);
        System.out.println(isChangeAdd2);
    }
}
