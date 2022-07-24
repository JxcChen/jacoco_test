package com.example.demo.utils;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.ast.MyDiffMethodNode;
import com.example.demo.dto.ast.MyMethodVisitor;
import com.example.demo.dto.diff.DiffJavaFile;
import com.example.demo.dto.diff.DiffResult;
import org.eclipse.jdt.core.dom.*;

/**
 * @author: JJJJ
 * @date:2021/7/1 16:23
 * @Description: ast解析
 */


public class JdtAstGenerator {

    private String className;
    private final DiffResult diffResult;
    public List<MyDiffMethodNode> methodNodeList = new ArrayList<MyDiffMethodNode>();



    public JdtAstGenerator(String javaFilePath, String diffStr) {
        diffResult = DiffUtils.getDiffData(diffStr);
        parse(getCompilationUnit(javaFilePath));
    }
    public List<MyDiffMethodNode> getMethodNodeList() {
        return methodNodeList;
    }
    /**
     * get compilation unit of source code
     * @param javaFilePath
     * @return CompilationUnit
     */
    public static CompilationUnit getCompilationUnit(String javaFilePath){
        byte[] input = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
            input = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(input);
            bufferedInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        ASTParser astParser = ASTParser.newParser(AST.JLS8);
        astParser.setSource(new String(input).toCharArray());
        astParser.setKind(ASTParser.K_COMPILATION_UNIT);

        CompilationUnit result = (CompilationUnit) (astParser.createAST(null));

        return result;
    }

    public void parse(CompilationUnit cu){
        // 获取所有方法节点
        MyMethodVisitor mv = new MyMethodVisitor();
        cu.accept(mv);
        // 获取包名
        String packName = cu.getPackage().getName().toString();
        List types = cu.types();
        TypeDeclaration type = null;
        if (types.size() > 0){
            // 如果有值则 取出第一个类名存起来
            type = (TypeDeclaration)types.get(0);
            className = type.getName().toString();
        }else {
            className = "noClassName";
        }
        // 组装包和类名
        String packageClassName = packName.replaceAll("\\.","/") + "/" + className + ".java";
        // 获取差异数据
        ArrayList<DiffJavaFile> diffJavaFileList = diffResult.getDiffJavaFileList();
        // 遍历方法节点
        for(MethodDeclaration m: mv.getMethodList()){
            // 记录该方法是否改变
            MyDiffMethodNode myDiffMethodNode = new MyDiffMethodNode();
            // 获取方法起始行和结束行
            myDiffMethodNode.startLine = cu.getLineNumber(m.getStartPosition());
            myDiffMethodNode.endLine = cu.getLineNumber(m.getStartPosition() + m.getLength());
            myDiffMethodNode.methodNode = m;
            // 判断 差异行号是否在该范围
            for (DiffJavaFile djf : diffJavaFileList){
                if(packageClassName.equals(djf.getJavaFileDir())){
                    for (int line : djf.getDiffLinesNum()){
                        if(line>= myDiffMethodNode.startLine && line<=myDiffMethodNode.endLine){
                            myDiffMethodNode.isChange = true;
                            break;
                        }
                    }
                }
            }
            methodNodeList.add(myDiffMethodNode);
        }
    }
}
