package com.example.demo.git;

import com.example.demo.utils.DiffUtils;
import com.example.demo.utils.GitUtils;
import org.junit.jupiter.api.Test;

/**
 * @author: JJJJ
 * @date:2021/9/6 11:08
 * @Description: TODO
 */
public class DiffUtilTest {

    @Test
    void test01(){
        String diffText = GitUtils.diffMethod("4b9d76e7c0e158288465e0c6bf4ea02df12cb21a", "1029534b53b122017a50924c128fc5cce40164a5");
        DiffUtils.getDiffData(diffText);
    }
}
