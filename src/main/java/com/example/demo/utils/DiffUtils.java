package com.example.demo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.diff.DiffJavaFile;
import com.example.demo.dto.diff.DiffLines;

import java.util.ArrayList;

public class DiffUtils {
    public static void main(String[] args) {
        getDiffData();

    }
    public static void getDiffData(){

        // 所有java文件差异信息集合
        ArrayList<DiffJavaFile> diffJavaFileList = new ArrayList<>();
        // 差异行号集合
        ArrayList<DiffLines> diffLinesList = new ArrayList<>();
        // 差异行号和行内容集合
        ArrayList<Integer> diffLineNum = new ArrayList<>();
        String str = "diff --git a/src/main/java/com/example/demo/dto/diff/DiffJavaFile.java b/src/main/java/com/example/demo/dto/diff/DiffJavaFile.java\n" +
                "index 6383653..997cbc7 100644\n" +
                "--- a/src/main/java/com/example/demo/dto/diff/DiffJavaFile.java\n" +
                "+++ b/src/main/java/com/example/demo/dto/diff/DiffJavaFile.java\n" +
                "@@ -1,7 +1,42 @@\n" +
                "-package com.example.demo.dto.diff;/**\n" +
                "-* \n" +
                "-* @author: JJJJ\n" +
                "-* @date:2021/6/30 14:34\n" +
                "-* @Description: TODO\n" +
                "-*/public class DIffJavaFile {\n" +
                "+package com.example.demo.dto.diff;\n" +
                "+\n" +
                "+import java.util.ArrayList;\n" +
                "+\n" +
                "+/**\n" +
                "+ * @author: JJJJ\n" +
                "+ * @date:2021/6/30 14:34\n" +
                "+ * @Description: TODO\n" +
                "+ */\n" +
                "+public class DiffJavaFile {\n" +
                "+    // 被修改的文件包路径\n" +
                "+    private String javaFileDir;\n" +
                "+    // 被修改的所有行号\n" +
                "+    private ArrayList<Integer> diffLinesNum;\n" +
                "+    // 被修改行号对应的内容\n" +
                "+    private ArrayList<DiffLines> diffLines;\n" +
                "+\n" +
                "+\n" +
                "+    public String getJavaFileDir() {\n" +
                "+        return javaFileDir;\n" +
                "+    }\n" +
                "+\n" +
                "+    public void setJavaFileDir(String javaFileDir) {\n" +
                "+        this.javaFileDir = javaFileDir;\n" +
                "+    }\n" +
                "+\n" +
                "+    public ArrayList<Integer> getDiffLinesNum() {\n" +
                "+        return diffLinesNum;\n" +
                "+    }\n" +
                "+\n" +
                "+    public void setDiffLinesNum(ArrayList<Integer> diffLinesNum) {\n" +
                "+        this.diffLinesNum = diffLinesNum;\n" +
                "+    }\n" +
                "+\n" +
                "+    public ArrayList<DiffLines> getDiffLines() {\n" +
                "+        return diffLines;\n" +
                "+    }\n" +
                "+\n" +
                "+    public void setDiffLines(ArrayList<DiffLines> diffLines) {\n" +
                "+        this.diffLines = diffLines;\n" +
                "+    }\n" +
                " }\n" +
                "diff --git a/src/main/java/com/example/demo/dto/diff/DiffLines.java b/src/main/java/com/example/demo/dto/diff/DiffLines.java\n" +
                "index bb4cb33..388a46e 100644\n" +
                "--- a/src/main/java/com/example/demo/dto/diff/DiffLines.java\n" +
                "+++ b/src/main/java/com/example/demo/dto/diff/DiffLines.java\n" +
                "@@ -1,7 +1,29 @@\n" +
                "-package com.example.demo.dto.diff;/**\n" +
                "-* \n" +
                "-* @author: JJJJ\n" +
                "-* @date:2021/6/30 14:23\n" +
                "-* @Description: TODO\n" +
                "-*/public class DiffLines {\n" +
                "+package com.example.demo.dto.diff;\n" +
                "+\n" +
                "+/**\n" +
                "+ * @author: JJJJ\n" +
                "+ * @date:2021/6/30 14:23\n" +
                "+ * @Description: TODO\n" +
                "+ */\n" +
                "+public class DiffLines {\n" +
                "+    // 差异数据行号\n" +
                "+    private int diffLineIndex;\n" +
                "+    // 差异数据内容\n" +
                "+    private String diffLineData;\n" +
                "+\n" +
                "+    public int getDiffLineIndex() {\n" +
                "+        return diffLineIndex;\n" +
                "+    }\n" +
                "+\n" +
                "+    public void setDiffLineIndex(int diffLineIndex) {\n" +
                "+        this.diffLineIndex = diffLineIndex;\n" +
                "+    }\n" +
                "+\n" +
                "+    public String getDiffLineData() {\n" +
                "+        return diffLineData;\n" +
                "+    }\n" +
                "+\n" +
                "+    public void setDiffLineData(String diffLineData) {\n" +
                "+        this.diffLineData = diffLineData;\n" +
                "+    }\n" +
                " }\n";
        //  将字符串根据换行符分割成数组

        String filePath = "";
        int diffLine = 0;
        int starLine;
        // 先删减头部diff
        str = str.replaceFirst("diff --git","");
        // 一句diff来切分字符
        String[] diffStrArr = str.split("diff --git");

        for (String diffStr: diffStrArr
             ) {
            // 清空数据
            System.out.println(diffStr);
            diffLineNum.clear();
            diffLinesList.clear();
            // 在根据换行符将字符串分割成行数据
            String[] diffData = diffStr.split("\n");
            DiffJavaFile diffJavaFile = new DiffJavaFile();
            // 遍历行数据获取需要的差异信息
            for (String data : diffData
                    ) {
                String replaceStr = data.replaceAll(" ", "");

                if ((data.startsWith("+++") || data.startsWith("---")) && data.endsWith(".java") ){
                    System.out.println(data.substring(data.indexOf("com/")));
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
                else if (data.startsWith("-") && !((data.startsWith("-*") || data.startsWith("-package")))){
                    diffLineNum.add(diffLine);
                    DiffLines diffLines = new DiffLines();
                    diffLines.setDiffLineIndex(diffLine);
                    diffLines.setDiffLineData("删减数据: "+data.replace("-",""));
                    diffLinesList.add(diffLines);
                }
                diffLine++;
            }

            diffJavaFile.setJavaFileDir(filePath);
            diffJavaFile.setDiffLines(diffLinesList);
            diffJavaFile.setDiffLinesNum(diffLineNum);
            diffJavaFileList.add(diffJavaFile);
        }

        String s = JSONObject.toJSONString(diffJavaFileList);
        System.out.println(s);
    }

}
