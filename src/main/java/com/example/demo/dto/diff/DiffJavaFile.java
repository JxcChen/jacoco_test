package com.example.demo.dto.diff;

import java.util.ArrayList;

/**
 * @author: JJJJ
 * @date:2021/6/30 14:34
 * @Description: TODO
 */
public class DiffJavaFile {
    // 被修改的文件包路径
    private String javaFileDir;
    // 被修改的所有行号
    private ArrayList<Integer> diffLinesNum;
    // 被修改行号对应的内容
    private ArrayList<DiffLines> diffLines;


    public String getJavaFileDir() {
        return javaFileDir;
    }

    public void setJavaFileDir(String javaFileDir) {
        this.javaFileDir = javaFileDir;
    }

    public ArrayList<Integer> getDiffLinesNum() {
        return diffLinesNum;
    }

    public void setDiffLinesNum(ArrayList<Integer> diffLinesNum) {
        this.diffLinesNum = diffLinesNum;
    }

    public ArrayList<DiffLines> getDiffLines() {
        return diffLines;
    }

    public void setDiffLines(ArrayList<DiffLines> diffLines) {
        this.diffLines = diffLines;
    }
}
