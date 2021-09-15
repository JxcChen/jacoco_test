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
        String diffStr = GitUtils.diffMethod("62fbbd505e032c462b9439e85483a84fb6979bf7","e3ade2643cce533bff3920ce25d682f530d97cc0");
        DiffMethods.diffStr = DiffUtils.getDiffData(diffStr);
        System.out.println(DiffUtils.getDiffData(diffStr));
        DiffMethods.useDiff =true;
        Boolean isChangeAdd = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController","getReport","(Lcom/example/demo/controller/Param;)V");
        Boolean isChangeAdd2 = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController","getReport2","(Lcom/example/demo/controller/Param;)V");
        System.out.println(isChangeAdd);
        System.out.println(isChangeAdd2);
    }
}
