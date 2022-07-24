package com.example.demo.git;

import com.example.demo.utils.GitUtils;
import com.example.demo.utils.ShowBranchDiff;
import org.junit.jupiter.api.Test;

/**
 * @author: JJJJ
 * @date:2021/9/6 10:23
 * @Description: TODO
 */
public class GitTest {


    @Test
    void testDiff(){
        System.out.println(GitUtils.diffMethod("44ef696caed38fbbb0dd151128f1181c63041b85", "2990d78b0b285aaa5ff902e0db48b3e38e783a65"));
    }

    @Test
    void test2() throws Exception {
        ShowBranchDiff.getBranchDiff();
    }
}
