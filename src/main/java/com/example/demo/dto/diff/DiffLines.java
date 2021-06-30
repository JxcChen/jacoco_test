package com.example.demo.dto.diff;

/**
 * @author: JJJJ
 * @date:2021/6/30 14:23
 * @Description: TODO
 */
public class DiffLines {
    // 差异数据行号
    private int diffLineIndex;
    // 差异数据内容
    private String diffLineData;

    public int getDiffLineIndex() {
        return diffLineIndex;
    }

    public void setDiffLineIndex(int diffLineIndex) {
        this.diffLineIndex = diffLineIndex;
    }

    public String getDiffLineData() {
        return diffLineData;
    }

    public void setDiffLineData(String diffLineData) {
        this.diffLineData = diffLineData;
    }

    @Override
    public String toString() {
        return "DiffLines{" +
                "diffLineIndex=" + diffLineIndex +
                ", diffLineData='" + diffLineData + '\'' +
                '}';
    }
}
