package com.example.demo.dto.diff;

import java.util.ArrayList;

/**
 * @author: JJJJ
 * @date:2021/9/15 15:21
 * @Description: TODO
 */
public class DiffResult {
    ArrayList<DiffJavaFile> diffJavaFileList = new ArrayList<>();

    public ArrayList<DiffJavaFile> getDiffJavaFileList() {
        return diffJavaFileList;
    }

    public void setDiffJavaFileList(ArrayList<DiffJavaFile> diffJavaFileList) {
        this.diffJavaFileList = diffJavaFileList;
    }
}
