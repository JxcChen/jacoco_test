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
package com.example.demo.ast;


import com.example.demo.ast.structure.MyDiffMethodNode;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kuohai on 2018/3/18.
 */
public class DiffMethods {
    public static String source_path;
    public static String diff_str;
    public static Boolean use_diff;

    public static boolean isMethodchanged(String classname, String methodname, String desc) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        boolean is_changed = false;
        String filePath = source_path + classname + ".java";
        System.out.println(filePath);
        File f = new File(filePath);
        //diffMethodNodesList存放了方法的行范围及是否改变
        List<MyDiffMethodNode> diffMethodNodesList;
        //实例化一个ast_diffGenerator，并传入源码文件及diff_json
        ASTGenerator ast_diffGenerator = new ASTDiffGenerator(f, diff_str);
        //获取到包含是否变化信息的列表
        diffMethodNodesList = ast_diffGenerator.getMethodNodeList();
        //jacoco传来的方法的参数列表
        List<String> para_list = get_paramlist(desc);
        for (MyDiffMethodNode diff_methodnode : diffMethodNodesList) {
            //ast解析源码后获取的方法参数列表
            List diff_para_list01 = diff_methodnode.methodNode.parameters();
            List<String> diff_para_list = toStringlist(diff_para_list01);
            //布尔值，用来判断jacoco传来的方法是否跟ast解析出来的方法是否相等
            boolean is_paraequals = para_list.containsAll(diff_para_list) && diff_para_list.containsAll(para_list);
            if (diff_methodnode.methodNode.getName().toString().equals(methodname) && is_paraequals) {
                //取出该方法是否被改变的布尔值以供返回
                is_changed = diff_methodnode.is_changed;
            }
        }
        return is_changed;
    }

    public static List<String> get_paramlist(String desc) {
        String temp[];
        temp = desc.trim().split("\\)");

        List<String> list = new ArrayList<String>();
        if (temp.length > 1) {
            String param[] = temp[0].split(";");
            String ret[] = temp[1].split("/");
            String return_velue = ret[ret.length - 1];
            return_velue = return_velue.replace(";", "");
            for (int i = 0; i < param.length; i++) {
                String pa[] = param[i].split("/");
                String para = pa[pa.length - 1];
                list.add(para);
            }
        }
        return list;
    }

    public static List<String> toStringlist(List<SingleVariableDeclaration> list) {
        List<String> strlist = new ArrayList<String>();
        for (SingleVariableDeclaration obj : list) {
            strlist.add(obj.getType().toString());
        }
        return strlist;
    }

}
