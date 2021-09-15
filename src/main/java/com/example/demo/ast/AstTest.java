package com.example.demo.ast;


import com.example.demo.utils.GitUtils;

/**
 * Created by Administrator on 2017/8/10.
 */
public class AstTest {

    public void test() throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        DiffMethods.source_path="D:/JavaCode/cyber-range-jacoco/src/main/java/";
        DiffMethods.diff_str= GitUtils.diffMethod("1029534b53b122017a50924c128fc5cce40164a5","4b9d76e7c0e158288465e0c6bf4ea02df12cb21a");
        DiffMethods.use_diff=true;
        Boolean isChangeAdd = DiffMethods.isMethodchanged("com/cyber/range/controller/DemoController","add","(Lcom/cyber/range/controller/Param;)Ljava/lang/String;");
        Boolean isChangeAdd2 = DiffMethods.isMethodchanged("com/cyber/range/controller/DemoController","add2","(Lcom/cyber/range/controller/Param;)Ljava/lang/String;");
        System.out.println(isChangeAdd);
        System.out.println(isChangeAdd2);

    }

}
