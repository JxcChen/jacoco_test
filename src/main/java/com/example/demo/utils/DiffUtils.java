package com.example.demo.utils;

import java.util.ArrayList;

public class DiffUtils {
    public static void main(String[] args) {
        getDiffData();

    }
    public static void getDiffData(){
        String str = "diff --git a/src/test/java/com/example/demo/SpringbootTestApplicationTests.java b/src/test/java/com/example/demo/SpringbootTestApplicationTests.java\n" +
                "index 52d9be6..c66ce63 100644\n" +
                "--- a/src/test/java/com/example/demo/SpringbootTestApplicationTests.java\n" +
                "+++ b/src/test/java/com/example/demo/SpringbootTestApplicationTests.java\n" +
                "@@ -8,6 +8,7 @@ class SpringbootTestApplicationTests {\n" +
                "\n" +
                "        @Test\n" +
                "        void contextLoads() {\n" +
                "+               System.out.println(\"test\");\n" +
                "        }\n" +
                "\n" +
                " }";
        //  将字符串根据换行符分割成数组
        String[] diffData = str.split("\n");
        ArrayList<String> classDir = new ArrayList<>();
        int diffLine = 0;
        for (String data : diffData
                ) {
            if (data.startsWith("+++")  && data.endsWith(".java") ){
                System.out.println(data.substring(data.indexOf("com/")));
            }
            if (data.startsWith("@@")){
                System.out.println(data.substring(data.indexOf("-")+1,data.indexOf(",")));
            }
            if (data.replaceAll("\t","").startsWith("-")){
                System.out.println(diffLine+" : "+data);
            }
            if (data.replaceAll("\t","").startsWith("+")){
                System.out.println(diffLine+" : "+data);
            }
            diffLine++;
        }


    }

}
