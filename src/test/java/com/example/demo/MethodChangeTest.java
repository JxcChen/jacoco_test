package com.example.demo;

import com.example.demo.ast.DiffMethod;
import com.example.demo.utils.DiffUtils;
import com.example.demo.utils.GitUtils;
import org.junit.jupiter.api.Test;
import org.xmlunit.diff.Diff;

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
        String diffText = GitUtils.diffMethod("4b9d76e7c0e158288465e0c6bf4ea02df12cb21a", "1029534b53b122017a50924c128fc5cce40164a5");
        DiffMethod.diffStr = DiffUtils.getDiffData(diffText);
        DiffMethod.isMethodChange("com/example/demo/controller/CalculatorController.java","getReport","(Lcom/example/demo/controller/Param;)Ljava/lang/String;\"");
    }

}
