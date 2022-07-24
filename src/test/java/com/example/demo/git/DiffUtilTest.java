package com.example.demo.git;

import com.example.demo.dto.diff.DiffResult;
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
        /**
         * diff --git a/src/main/java/com/example/demo/controller/CalculatorController.java b/src/main/java/com/example/demo/controller/CalculatorController.java
         * index a2d92e3..227aaeb 100644
         * --- a/src/main/java/com/example/demo/controller/CalculatorController.java
         * +++ b/src/main/java/com/example/demo/controller/CalculatorController.java
         * @@ -36,6 +36,7 @@
         *              }
         *              if (method.equals("mul")){
         *                  System.out.println(num1*num2);
         * +                System.out.println("修改二下");
         *              }
         *              if (method.equals("div")){
         *                  if (num2 == 0){
         * @@ -43,6 +44,7 @@
         *                      System.out.println("修改一下");
         *                  }else
         *                      System.out.println(num1/num2);
         * +                System.out.println("修改一下");
         *              }
         *          }
         */
        String diffText = GitUtils.diffMethod("44ef696caed38fbbb0dd151128f1181c63041b85", "2990d78b0b285aaa5ff902e0db48b3e38e783a65");
        System.out.println(diffText);
        DiffResult diffData = DiffUtils.getDiffData(diffText);
    }
}
