package com.example.zz.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.math.BigDecimal;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private TextView Text;
    private StringBuilder str = new StringBuilder("");//可变字符串
    /*flag1判断是否按下数字键，flag2判断是否按下运算符，flag3判断TextView中是否为非法字符，flag4判断是否可以执行单目运算,flag5判断是否按下等于*/
    private boolean Equal=false,Point=false,Sign=false,flag1=false,flag2=false,flag3=false,flag4=false,flag5=false;
    private String result="";//计算结果
    private BigDecimal num1=new BigDecimal("0"),num2=new BigDecimal("0"),num=new BigDecimal("0"),sum=new BigDecimal("0");;//第一、第二操作数，单目运算操作数
    int op=0;//区分加减乘除


    /*数字键点击事件*/
    public class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            final Button BtnSign = findViewById(R.id.BtnSign);
            final Button BtnMultiply = findViewById(R.id.BtnMultiply);
            final Button BtnDivide = findViewById(R.id.BtnDivide);
            final Button BtnMinus = findViewById(R.id.BtnMinus);
            final Button BtnPlus = findViewById(R.id.BtnPlus);
            final Button BtnPoint = findViewById(R.id.BtnPoint);
            switch (v.getId()){
                case R.id.Btn0:
                case R.id.Btn1:
                case R.id.Btn2:
                case R.id.Btn3:
                case R.id.Btn4:
                case R.id.Btn5:
                case R.id.Btn6:
                case R.id.Btn7:
                case R.id.Btn8:
                case R.id.Btn9:
                    BtnPoint.setEnabled(true);
                    BtnSign.setEnabled(true);
                    BtnPlus.setEnabled(true);
                    BtnMinus.setEnabled(true);
                    BtnMultiply.setEnabled(true);
                    BtnDivide.setEnabled(true);
                    flag1=true;
                    flag5=false;
                    flag4=false;
            }
            switch (v.getId()){
                case R.id.Btn0:
                    double a=Double.parseDouble(Text.getText().toString());
                    if (Text.getText().toString().contains(".")||a!=0){

                        str.append("0");
                        Text.setText(str);
                    }
                    else str = new StringBuilder("");
                break;
                case R.id.Btn1:
                    str.append("1");
                    Text.setText(str);
                break;
                case R.id.Btn2:
                    str.append("2");
                    Text.setText(str);
                break;
                case R.id.Btn3:
                    str.append("3");
                    Text.setText(str);
                break;
                case R.id.Btn4:
                    str.append("4");
                    Text.setText(str);
                break;
                case R.id.Btn5:
                    str.append("5");
                    Text.setText(str);
                break;
                case R.id.Btn6:
                    str.append("6");
                    Text.setText(str);
                break;
                case R.id.Btn7:
                    str.append("7");
                    Text.setText(str);
                break;
                case R.id.Btn8:
                    str.append("8");
                    Text.setText(str);
                break;
                case R.id.Btn9:
                    str.append("9");
                    Text.setText(str);
            }
        }
    }
    public class Listener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            final Button BtnSign = findViewById(R.id.BtnSign);
            final Button BtnMultiply = findViewById(R.id.BtnMultiply);
            final Button BtnDivide = findViewById(R.id.BtnDivide);
            final Button BtnMinus = findViewById(R.id.BtnMinus);
            final Button BtnPlus = findViewById(R.id.BtnPlus);
            final Button BtnPoint = findViewById(R.id.BtnPoint);
            final Button BtnPercent = findViewById(R.id.BtnPercent);
            final Button BtnRooting = findViewById(R.id.BtnRooting);
            final Button BtnSquare = findViewById(R.id.BtnSquare);
            final Button BtnReciprocal = findViewById(R.id.BtnReciprocal);

            switch (v.getId()) {
                case R.id.BtnDivide:
                case R.id.BtnMultiply:
                case R.id.BtnMinus:
                case R.id.BtnPlus:
                    if (flag1&&flag2&&!flag5) {
                        if (flag3 && (Objects.equals(result, "除数不能为零") || Objects.equals(result, "结果未定义") || Objects.equals(result, "结果超出15位"))) {
                            result = "0";//如果屏幕上显示上述三句话，结果置为零
                            BtnPoint.setEnabled(true);
                            BtnSign.setEnabled(true);
                            BtnPlus.setEnabled(true);
                            BtnMinus.setEnabled(true);
                            BtnMultiply.setEnabled(true);
                            BtnDivide.setEnabled(true);
                            BtnPercent.setEnabled(true);
                            BtnRooting.setEnabled(true);
                            BtnSquare.setEnabled(true);
                            BtnReciprocal.setEnabled(true);//各个按钮恢复可按状态
                            Text.setText(result);
                            num1 = new BigDecimal("0");
                            Equal = true;
                            flag1 = false;
                            flag2 = false;
                            flag4 = false;
                            str = new StringBuilder("");
                        } else {
                            if (!flag1 && !flag2) {//如果没有按下数字键和加减乘除
                                num1 = sum;//将结果赋值给num1，num2仍为上次计算中的值
                            }
                            else if (flag1&&!flag2&&flag5) {
                                num1 = new BigDecimal(Text.getText().toString());
                            }
                            else num2 = new BigDecimal((Text.getText().toString()));//否则num1由运算符确定，num2为屏幕上显示的数字
                            switch (op) {
                                case 1: {
                                    sum = new BigDecimal("" + num1.add(num2));
                                    result = sum.toPlainString();
                                }
                                break;
                                case 2: {
                                    sum = new BigDecimal("" + num1.subtract(num2));
                                    result = sum.toPlainString();
                                }
                                break;
                                case 3: {
                                    sum = new BigDecimal("" + num1.multiply(num2));
                                    result = sum.toPlainString();
                                }
                                break;
                                case 4: {
                                    if (!Objects.equals(num2, new BigDecimal("0"))) {
                                        sum = num1.divide(num2, 13, BigDecimal.ROUND_HALF_UP);
                                        Double s=sum.doubleValue();
                                        if (s<1e-13)sum=new BigDecimal("0");
                                        result = sum.toPlainString();
                                    } else if (Objects.equals(num1, new BigDecimal("0")) && Objects.equals(num2, new BigDecimal("0"))) {
                                        result = "结果未定义";//如果被除数和除数都为零
                                    } else if (!Objects.equals(num1, new BigDecimal("0")) && Objects.equals(num2, new BigDecimal("0"))) {
                                        result = "除数不能为零";//如果只有除数为零
                                    }
                                }
                            }
                            if (result.indexOf(".") > 0) {
                                result = result.replaceAll("0+?$", "");//去掉多余的0
                                result = result.replaceAll("[.]$", "");//如最后一位是.则去掉
                            }
                            if (!Objects.equals(result, "结果未定义") && !Objects.equals(result, "除数不能为零")) {
                                BigDecimal a = sum.setScale(0, BigDecimal.ROUND_DOWN);
                                String b = String.valueOf(a);
                                if (b.length() > 15) result = "结果超出15位";//结果超出15位屏幕显示“结果超出15位”
                            }
                            if (!Objects.equals(result, "")) Text.setText(result);
                            num1 = new BigDecimal("0");
                            Equal = true;
                            flag1 = false;
                            flag2 = false;
                            flag4 = false;
                            str = new StringBuilder("");
                            if (Objects.equals(result, "除数不能为零") || Objects.equals(result, "结果未定义") || Objects.equals(result, "结果超出15位")) {
                                BtnPoint.setEnabled(false);
                                BtnSign.setEnabled(false);
                                BtnPlus.setEnabled(false);
                                BtnMinus.setEnabled(false);
                                BtnMultiply.setEnabled(false);
                                BtnDivide.setEnabled(false);
                                BtnPercent.setEnabled(false);
                                BtnRooting.setEnabled(false);
                                BtnSquare.setEnabled(false);
                                BtnReciprocal.setEnabled(false);
                                flag3 = true;//如果屏幕上显示的内容位上述三者之一，各个按钮变灰，变为不可按状态，可以按数字键
                            }
                        }
                    }
                    num1 = new BigDecimal(Text.getText().toString());//num1为屏幕上显示的数字
                    num2 = new BigDecimal("0");
                    str = new StringBuilder("");
                    Point = false;//可以按小数点
                    flag2 = true;//标记已按加减乘除
                    flag1 = false;//可以按数字键
                    flag5=false;
            }
            switch (v.getId()) {
                case R.id.BtnDivide:op=4;break;
                case R.id.BtnMultiply:op=3;break;
                case R.id.BtnMinus:op=2;break;
                case R.id.BtnPlus:op=1;
            }
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*为按钮绑定监听器*/
        Button Btn0 = findViewById(R.id.Btn0);
        Button Btn1 = findViewById(R.id.Btn1);
        Button Btn2 = findViewById(R.id.Btn2);
        Button Btn3 = findViewById(R.id.Btn3);
        Button Btn4 = findViewById(R.id.Btn4);
        Button Btn5 = findViewById(R.id.Btn5);
        Button Btn6 = findViewById(R.id.Btn6);
        Button Btn7 = findViewById(R.id.Btn7);
        Button Btn8 = findViewById(R.id.Btn8);
        Button Btn9 = findViewById(R.id.Btn9);
        Button BtnC = findViewById(R.id.BtnC);
        final Button BtnPercent = findViewById(R.id.BtnPercent);
        final Button BtnRooting = findViewById(R.id.BtnRooting);
        final Button BtnSquare = findViewById(R.id.BtnSquare);
        final Button BtnReciprocal = findViewById(R.id.BtnReciprocal);
        final Button BtnSign = findViewById(R.id.BtnSign);
        final Button BtnMultiply = findViewById(R.id.BtnMultiply);
        final Button BtnDivide = findViewById(R.id.BtnDivide);
        final Button BtnMinus = findViewById(R.id.BtnMinus);
        final Button BtnPlus = findViewById(R.id.BtnPlus);
        Button BtnEqual = findViewById(R.id.BtnEqual);
        Button BtnCE = findViewById(R.id.BtnCE);
        final Button BtnPoint = findViewById(R.id.BtnPoint);
        Button BtnBack = findViewById(R.id.BtnBack);
        Text = findViewById(R.id.Text);
        Text.setText("0");
       /*引用外部类*/
        Btn0.setOnClickListener(new MyListener());
        Btn1.setOnClickListener(new MyListener());
        Btn2.setOnClickListener(new MyListener());
        Btn3.setOnClickListener(new MyListener());
        Btn4.setOnClickListener(new MyListener());
        Btn5.setOnClickListener(new MyListener());
        Btn6.setOnClickListener(new MyListener());
        Btn7.setOnClickListener(new MyListener());
        Btn8.setOnClickListener(new MyListener());
        Btn9.setOnClickListener(new MyListener());
        BtnPlus.setOnClickListener(new Listener());
        BtnMinus.setOnClickListener(new Listener());
        BtnMultiply.setOnClickListener(new Listener());
        BtnDivide.setOnClickListener(new Listener());
        BtnEqual.setOnClickListener(new Listener());
        /*小数点按钮点击事件*/
        BtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if ((!Point && "0".equals(Text.getText().toString()))||flag5 ){//如果之前没有按小数点并且屏幕上显示为0，使屏幕显示0.
                        str=new StringBuilder("0.");
                        Text.setText(str);
                        Point = true;
                    } else if (!Text.getText().toString().contains(".")){//如果屏幕上的数字不包含小数点，则在其后添加小数点
                        str = new StringBuilder(Text.getText().toString());
                        str.append(".");
                        Text.setText(str);
                        Point = true;
                    }
            }
        });
        /*C键点击事件*/
        BtnC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                op = 0;
                str = new StringBuilder("");
                num1=new BigDecimal("0");
                num2=new BigDecimal("0");
                num=new BigDecimal("0");
                Point = false;
                Sign=false;
                Equal = false;
                flag1=false;
                flag2 = false;
                flag3=false;
                flag4=false;
                flag5=false;
                Text.setText("0");
                result = "";
                BtnPoint.setEnabled(true);
                BtnSign.setEnabled(true);
                BtnPlus.setEnabled(true);
                BtnMinus.setEnabled(true);
                BtnMultiply.setEnabled(true);
                BtnDivide.setEnabled(true);
                BtnPercent.setEnabled(true);
                BtnRooting.setEnabled(true);
                BtnSquare.setEnabled(true);
                BtnReciprocal.setEnabled(true);//全部初始化
            }
        });
        /*正负号按钮点击事件*/
        BtnSign.setOnClickListener(new View.OnClickListener() {

            String a="0";

            @Override
            public void onClick(View v) {
                a = Text.getText().toString();
                double c = Double.parseDouble(a);
                    if (c>0) {//屏幕上的数字大于零，在其前添加负号
                        a="-"+a;
                        Sign=true;
                    } else if (c<0) {//屏幕上的数字小于零，将负号替换掉
                        a = a.replace("-","");
                        Sign = false;
                    }
                    else if (c==0&&a.contains("0.")&& !a.contains("-")) {//屏幕上的数字等于零，并且屏幕上的数字包含“0.”，在其前添加负号
                        a="-"+a;
                        Sign = true;
                    }
                    else if (c==0&&a.contains("-0.")){//屏幕上的数字等于零，并且屏幕上的数字包含“-0.”，将负号替换掉
                        a = a.replace("-","");
                        Sign = false;
                    }
                Text.setText(a);
                str=new StringBuilder(a);
            }
        });
        /*CE键点击事件*/
        BtnCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text.setText("0");
                str = new StringBuilder("");
                BtnPoint.setEnabled(true);
                BtnSign.setEnabled(true);
                BtnPlus.setEnabled(true);
                BtnMinus.setEnabled(true);
                BtnMultiply.setEnabled(true);
                BtnDivide.setEnabled(true);
                BtnPercent.setEnabled(true);
                BtnRooting.setEnabled(true);
                BtnSquare.setEnabled(true);
                BtnReciprocal.setEnabled(true);//将当前屏幕内容换为“0”，各个按钮变为可按状态，并且初始化
                flag4=false;//可以执行单目运算
                flag5=false;
            }
        });
        /*后退按钮点击事件*/
        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Objects.equals(result, "除数不能为零") || Objects.equals(result, "结果未定义")||Objects.equals(result, "结果超过15位")) {
                    Text.setText("0");
                    BtnPoint.setEnabled(true);
                    BtnSign.setEnabled(true);
                    BtnPlus.setEnabled(true);
                    BtnMinus.setEnabled(true);
                    BtnMultiply.setEnabled(true);
                    BtnDivide.setEnabled(true);
                    BtnPercent.setEnabled(true);
                    BtnRooting.setEnabled(true);
                    BtnSquare.setEnabled(true);
                    BtnReciprocal.setEnabled(true);
                    flag4=false;//各个按钮变为可按状态，并且可以执行单目运算
                }
                else if (str.length() > 1&&!flag5) {
                    if (!Objects.equals(Text.getText().toString(), "-0.")) {
                        str.deleteCharAt(str.length() - 1);
                        Text.setText(str);
                        flag4 = false;//如果字符串长度不为零，则删除字符串末尾的一个字符，并且可以执行单目运算
                    }
                    else{
                        str=new StringBuilder("");
                        Text.setText("0");
                    }
                }
                else if (str.length()==1&&!flag5){
                    str=new StringBuilder("");
                    Text.setText("0");
                    flag4=false;
                }
            }
        });

        BtnRooting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=new BigDecimal(Text.getText().toString());
                double a=num.setScale(20, BigDecimal.ROUND_HALF_UP).doubleValue();
                double b=Math.sqrt(a);
                String c=String.valueOf(b);
                sum=new BigDecimal(c);
                if (c.indexOf(".") > 0) {
                    c = c.replaceAll("0+?$", "");//去掉多余的0
                    c = c.replaceAll("[.]$", "");//如最后一位是.则去掉
                }
                Text.setText(c);
                flag4=true;
            }
        });

        BtnSquare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num=new BigDecimal(Text.getText().toString());
                double a=num.setScale(20, BigDecimal.ROUND_HALF_UP).doubleValue();
                double b=Math.pow(a,2);
                String c=String.valueOf(b);
                sum=new BigDecimal(c);
                if (c.indexOf(".") > 0) {
                    c = c.replaceAll("0+?$", "");//去掉多余的0
                    c = c.replaceAll("[.]$", "");//如最后一位是.则去掉
                }
                Text.setText(c);
                flag4=true;
            }
        });
        BtnReciprocal.setOnClickListener(new View.OnClickListener() {
            String c;
            double b;
            @Override
            public void onClick(View view) {
                num=new BigDecimal(Text.getText().toString());
                double a=num.setScale(20, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (a!=0) {
                    b = 1 / a;
                    c = String.valueOf(b);
                    sum=new BigDecimal(c);
                    if (c.indexOf(".") > 0) {
                        c = c.replaceAll("0+?$", "");//去掉多余的0
                        c = c.replaceAll("[.]$", "");//如最后一位是.则去掉
                    }
                }
                else if (a==0){
                    c="除数不能为零";
                    flag3=true;
                    result=c;
                    BtnPoint.setEnabled(false);
                    BtnSign.setEnabled(false);
                    BtnPlus.setEnabled(false);
                    BtnMinus.setEnabled(false);
                    BtnMultiply.setEnabled(false);
                    BtnDivide.setEnabled(false);
                    BtnPercent.setEnabled(false);
                    BtnRooting.setEnabled(false);
                    BtnSquare.setEnabled(false);
                    BtnReciprocal.setEnabled(false);
                }
                Text.setText(c);
                flag4=true;
            }
        });


        BtnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag3 &&( Objects.equals(result, "除数不能为零") || Objects.equals(result, "结果未定义")||Objects.equals(result, "结果超出15位"))) {
                    result = "0";//如果屏幕上显示上述三句话，结果置为零
                    BtnPoint.setEnabled(true);
                    BtnSign.setEnabled(true);
                    BtnPlus.setEnabled(true);
                    BtnMinus.setEnabled(true);
                    BtnMultiply.setEnabled(true);
                    BtnDivide.setEnabled(true);
                    BtnPercent.setEnabled(true);
                    BtnRooting.setEnabled(true);
                    BtnSquare.setEnabled(true);
                    BtnReciprocal.setEnabled(true);//各个按钮恢复可按状态
                    Text.setText(result);
                    num1=new BigDecimal("0");
                    Equal = true;
                    flag1 = false;
                    flag2 = false;
                    flag4=false;
                    str = new StringBuilder("");
                }
                else {
                    if (!flag1 && !flag2) {//如果没有按下数字键和加减乘除
                        num1 = sum;//将结果赋值给num1，num2仍为上次计算中的值
                    }
                    else if (flag1&&!flag2){
                        num1=new BigDecimal(Text.getText().toString());
                    }
                    else if (flag1&&!flag2&&!flag5){
                        result=Text.getText().toString();
                    }
                    else num2=new BigDecimal((Text.getText().toString()));//否则num1由运算符确定，num2为屏幕上显示的数字
                    switch (op) {
                        case 1: {
                            sum=new BigDecimal("" + num1.add(num2));
                            result = sum.toPlainString();
                        }
                        break;
                        case 2: {
                            sum=new BigDecimal("" + num1.subtract(num2));
                            result = sum.toPlainString();
                        }
                        break;
                        case 3: {
                            sum=new BigDecimal("" + num1.multiply(num2));
                            result = sum.toPlainString();
                        }
                        break;
                        case 4: {
                            if (!Objects.equals(num2, new BigDecimal("0"))) {
                                sum = num1.divide(num2, 13, BigDecimal.ROUND_HALF_UP);
                                Double s=sum.doubleValue();
                                if (s<1e-13)sum=new BigDecimal("0");
                                result = sum.toPlainString();
                            } else if (Objects.equals(num1, new BigDecimal("0")) && Objects.equals(num2, new BigDecimal("0"))) {
                                result = "结果未定义";//如果被除数和除数都为零
                            } else if (!Objects.equals(num1, new BigDecimal("0")) && Objects.equals(num2, new BigDecimal("0"))){
                                result = "除数不能为零";//如果只有除数为零
                            }
                        }
                    }
                    if (result.indexOf(".") > 0) {
                        result = result.replaceAll("0+?$", "");//去掉多余的0
                        result = result.replaceAll("[.]$", "");//如最后一位是.则去掉
                    }
                    if (!Objects.equals(result, "结果未定义") && !Objects.equals(result, "除数不能为零")){
                        BigDecimal a=sum.setScale(0,BigDecimal.ROUND_DOWN);
                        String b=String.valueOf(a);
                        if(b.length()>15)result="结果超出15位";//结果超出15位屏幕显示“结果超出15位”
                    }
                    if (!Objects.equals(result, ""))Text.setText(result);
                    num1 = new BigDecimal("0");
                    Equal = true;
                    flag1 = false;
                    flag2 = false;
                    flag4=false;
                    str = new StringBuilder("");
                    if (Objects.equals(result, "除数不能为零") || Objects.equals(result, "结果未定义")||Objects.equals(result, "结果超出15位")) {
                        BtnPoint.setEnabled(false);
                        BtnSign.setEnabled(false);
                        BtnPlus.setEnabled(false);
                        BtnMinus.setEnabled(false);
                        BtnMultiply.setEnabled(false);
                        BtnDivide.setEnabled(false);
                        BtnPercent.setEnabled(false);
                        BtnRooting.setEnabled(false);
                        BtnSquare.setEnabled(false);
                        BtnReciprocal.setEnabled(false);
                        flag3 = true;//如果屏幕上显示的内容位上述三者之一，各个按钮变灰，变为不可按状态，可以按数字键
                    }
                }
                flag5=true;
            }
        });
    }
}
