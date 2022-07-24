package com.example.demo.dto.ast;

/**
 * @author JJJJ
 * @Title: my_method_node
 * @date 2022/7/249:55 上午
 * @Description: 方法差异内容存储
 */
public class MyDiffMethodNode extends MyMethodNode {
    /**
     * 起始行
     */
    public int startLine;

    /**
     * 结束行
     */
    public int endLine;

    /**
     * 是否改变标志
     */
    public boolean isChange = false;

    public MyDiffMethodNode() {
        super();
    }
}
