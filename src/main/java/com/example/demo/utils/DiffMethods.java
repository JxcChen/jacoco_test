/*******************************************************************************
 * Copyright (c) 2009, 2018 Mountainminds GmbH & Co. KG and Contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *
 *******************************************************************************/
package com.example.demo.utils;



import com.example.demo.dto.ast.MyDiffMethodNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class DiffMethods {
    public static String sourcePath;
    public static String diffStr;
    public static Boolean useDiff;

    public static boolean isMethodChanged(String classname, String methodName, String desc,String diffStr) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        boolean is_changed = false;
        String filePath = sourcePath+classname + ".java";
        //实例化一个ast_diffGenerator，并传入源码文件及diff_json
        JdtAstGenerator jdtAstGenerator = new JdtAstGenerator(filePath, diffStr);
        //获取到包含是否变化信息的列表
        //diffMethodNodesList存放了方法的行范围及是否改变
        List<MyDiffMethodNode> diffMethodNodesList = jdtAstGenerator.getMethodNodeList();
        //jacoco传来的方法的参数列表
        List<String> para_list = getParamList(desc);
        for (MyDiffMethodNode diffMethodNode : diffMethodNodesList) {
            //ast解析源码后获取的方法参数列表
            List diff_para_list01 = diffMethodNode.methodNode.parameters();
            List<String> diff_para_list = toStringList(diff_para_list01);
            //布尔值，用来判断jacoco传来的方法是否跟ast解析出来的方法是否相等
            boolean is_paraequals = para_list.containsAll(diff_para_list) && diff_para_list.containsAll(para_list);
            if (diffMethodNode.methodNode.getName().toString().equals(methodName)) {
                //取出该方法是否被改变的布尔值以供返回
                is_changed = diffMethodNode.isChange;
            }
        }
        return is_changed;
    }

    /**
     * 根据jacoco传过来的参数跟进行解析
     * @param desc
     * @return
     */
    public static List<String> getParamList(String desc) {
        String[] temp = desc.trim().split("\\)");

        List<String> list = new ArrayList<String>();
        if (temp.length > 1) {
            String param[] = temp[0].split(";");
            String ret[] = temp[1].split("/");
//            String return_velue = ret[ret.length - 1];
//            return_velue = return_velue.replace(";", "");
            for (int i = 0; i < param.length; i++) {
                String pa[] = param[i].split("/");
                String para = pa[pa.length - 1];
                list.add(para);
            }
        }
        return list;
    }

    /**
     * 装换成string集合
     * @param list List<Objetc>
     */
    public static List<String> toStringList(List<SingleVariableDeclaration> list) {
        List<String> strList = new ArrayList<String>();
        for (SingleVariableDeclaration obj : list) {
            strList.add(obj.getType().toString());
        }
        return strList;
    }

}
