package com.example.demo.ast;

import com.example.demo.ast.structure.MyDiffMethodNode;

import java.io.File;
import java.util.List;

/**
 * @author JJJJ
 * @Title:
 * @date 2021/9/9下午8:42
 * @Description:
 */
public class DiffMethod {

    public static String sourcePath;
    public static boolean isChange = false;
    public static String diffStr;

    public static boolean isMethodChange(String className, String methodName, String methodDesc){
        // 先将类拼装上绝对路劲
        String filePath = sourcePath + className + ".java";
        // 创建file对象
        File classFile = new File(filePath);
        //diffMethodNodesList存放了方法的行范围及是否改变
        List<MyDiffMethodNode> diffMethodNodesList;
        //实例化一个ast_diffGenerator，并传入源码文件及diff_json


        return isChange;
    }
}
