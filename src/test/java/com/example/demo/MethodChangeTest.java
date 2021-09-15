package com.example.demo;

import com.example.demo.utils.DiffUtils;
import com.example.demo.utils.GitUtils;
import org.junit.jupiter.api.Test;

/**
 * @author JJJJ
 * @Title:
 * @date 2021/9/9下午8:40
 * @Description:
 */
public class MethodChangeTest {

    @Test
    void test(){
        DiffMethod.sourcePath = "/Users/chnjx/IdeaProjects/cyber-range-jacoco/src/main/java/";
        String diffText = GitUtils.diffMethod("b9368070afe0e0b011477c80a5489806b182a1f8", "176dbb6908359e5c532b702927a2e5a6a0f05558");
        DiffMethod.diffStr = DiffUtils.getDiffData(diffText);
        DiffMethod.isMethodChange("com/example/demo/controller/CalculatorController.java","getReport","(Lcom/example/demo/controller/Param;)Ljava/lang/String;\"");
    }

}
