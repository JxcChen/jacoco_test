package com.example.demo.ast;


import com.example.demo.utils.DiffUtils;
import com.example.demo.utils.GitUtils;
import org.testng.annotations.Test;


public class AstTest {
    @Test
    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        DiffMethods.sourcePath ="D:/JavaCode/cyber-range-jacoco/src/main/java/";
        String diffStr = GitUtils.diffMethod("62fbbd505e032c462b9439e85483a84fb6979bf7","e3ade2643cce533bff3920ce25d682f530d97cc0");
        DiffUtils.getDiffData(diffStr);
        DiffMethods.useDiff =true;
        Boolean isChangeAdd = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController","getReport","(Lcom/example/demo/controller/Param;)");
        Boolean isChangeAdd2 = DiffMethods.isMethodChanged("com/example/demo/controller/CalculatorController","getReport2","(Lcom/example/demo/controller/Param;)");
        System.out.println(isChangeAdd);
        System.out.println(isChangeAdd2);
    }

}
