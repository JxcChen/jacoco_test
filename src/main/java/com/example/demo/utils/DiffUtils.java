package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.diff.*;

import java.util.ArrayList;

public class DiffUtils {


    public static String getDiffData(String diffText){
        if (diffText==null || diffText.equals("")){
            System.out.println("差异数据不能为空");
            return null;
        }
        // 所有java文件差异信息集合
        DiffResult diffResult = new DiffResult();
        // 差异行号集合
        ArrayList<DiffLines> diffLinesList = null;
        // 差异行号和行内容集合
        ArrayList<Integer> diffLineNum = null;

        //  将字符串根据换行符分割成数组

        String filePath = "";
        int diffLine = 0;
        int starLine;
        // 先删减头部diff
        diffText = diffText.replaceFirst("diff --git","");
        // 依据diff来切分字符
        String[] diffStrArr = diffText.split("diff --git");

        for (String diffStr: diffStrArr
        ) {
            // 初始化集合
            diffLineNum = new ArrayList<>();
            diffLinesList = new ArrayList<>();
            // 在根据换行符将字符串分割成行数据
            String[] diffData = diffStr.split("\n");
            DiffJavaFile diffJavaFile = new DiffJavaFile();
            // 遍历行数据获取需要的差异信息
            for (String data : diffData
            ) {
                String replaceStr = data.replaceAll(" ", "");
                if (data.startsWith("---") && !data.endsWith(".java")){
                    diffLineNum.clear();
                    break;
                }
                else if ((data.startsWith("+++") || data.startsWith("---")) && data.endsWith(".java") ){

                    filePath = data.substring(data.indexOf("com/"));
                }
                else if (data.startsWith("@@")){
                    starLine = Integer.parseInt(data.substring(data.indexOf("-")+1,data.indexOf(",")));
                    diffLine = starLine;
                    continue;
                }
                else if (replaceStr.startsWith("+") &&
                        !(replaceStr.startsWith("+*")
                                || replaceStr.startsWith("+package")
                                || replaceStr.startsWith("+import")
                                || replaceStr.startsWith("+/")
                                || replaceStr.endsWith("+"))){
                    diffLineNum.add(diffLine);
                    DiffLines diffLines = new DiffLines();
                    diffLines.setDiffLineIndex(diffLine);
                    diffLines.setDiffLineData(data.replace("+",""));
                    diffLinesList.add(diffLines);
                }
                else if (data.startsWith("-") && !((data.startsWith("-*")
                        || data.startsWith("-package")
                        || replaceStr.startsWith("-/")
                        || replaceStr.endsWith("-")))){
                    diffLineNum.add(diffLine);
                    DiffLines diffLines = new DiffLines();
                    diffLines.setDiffLineIndex(diffLine);
                    diffLines.setDiffLineData("删减数据: "+data.replace("-",""));
                    diffLinesList.add(diffLines);
                }
                diffLine++;
            }
            if (diffLineNum.size() == 0)
                continue;
            diffJavaFile.setJavaFileDir(filePath);
            diffJavaFile.setDiffLines(diffLinesList);
            diffJavaFile.setDiffLinesNum(diffLineNum);
            diffResult.getDiffJavaFileList().add(diffJavaFile);
        }

        String jsonString = JSONObject.toJSONString(diffResult);
        return jsonString;
    }

}
