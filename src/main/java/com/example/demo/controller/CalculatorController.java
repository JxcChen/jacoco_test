package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: JJJJ
 * @date:2021/6/24 9:22
 * @Description: TODO
 */
@RestController
public class CalculatorController {


    @GetMapping("/add")
    public void getReport(Param param){
        String method = param.getMethod();
        Integer num1 = param.getNum1();
        Integer num2 = param.getNum2();
        if (num1 == null){
            System.out.println("数值1不能为空");

        }else if (num2 == null){
            System.out.println("数值2不能为空啊！！");

        }else {
            if (method.equals("add")){
                System.out.println(num1+num2+"修改");
            }
            if (method.equals("del")){
                System.out.println(num1-num2);
            }
            if (method.equals("mul")){
                System.out.println(num1*num2);
            }
            if (method.equals("div")){
                if (num2 == 0){
                    System.out.println("被除数不能为0"+"修改");
                }else
                    System.out.println(num1/num2);
            }
        }

    }

    @GetMapping("/add2")
    public void getReport2(Param param){
        String method = param.getMethod();
        Integer num1 = param.getNum1();
        Integer num2 = param.getNum2();
        if (num1 == null){
            System.out.println("数值1不能为空！！");

        }else if (num2 == null){
            System.out.println("数值2不能为空啊！！");

        }else {
            if (method.equals("add")){
                System.out.println(num1+num2+"修改");
            }
            if (method.equals("del")){
                System.out.println(num1-num2);
            }
            if (method.equals("mul")){
                System.out.println(num1*num2);
            }
            if (method.equals("div")){
                if (num2 == 0){
                    System.out.println("被除数不能为0"+"修改");
                }else
                    System.out.println(num1/num2);
            }
        }

    }
}
