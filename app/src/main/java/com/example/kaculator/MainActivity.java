package com.example.kaculator;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        // number button and click events
        Button zero = (Button) findViewById(R.id.B0);
        Button one = (Button) findViewById(R.id.B1);
        Button two = (Button) findViewById(R.id.B2);
        Button three = (Button) findViewById(R.id.B3);
        Button four = (Button) findViewById(R.id.B4);
        Button five = (Button) findViewById(R.id.B5);
        Button six = (Button) findViewById(R.id.B6);
        Button seven = (Button) findViewById(R.id.B7);
        Button eight = (Button) findViewById(R.id.B8);
        Button nine = (Button) findViewById(R.id.B9);

        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);


        //operator button and click event

        Button back = (Button) findViewById(R.id.back);
        Button plus = (Button) findViewById(R.id.plus);
        Button sub =  (Button) findViewById(R.id.subtract);
        Button div =  (Button) findViewById(R.id.divide);
        Button mul =  (Button) findViewById(R.id.multi);
        Button per = (Button) findViewById(R.id.per);

        back.setOnClickListener(this);
        plus.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        per.setOnClickListener(this);

        //other button button
        Button equal = (Button) findViewById(R.id.equal);
        Button dot = (Button) findViewById(R.id.dot);
        Button plusMinSign = (Button) findViewById(R.id.plusMinsign);
        Button clearAll = (Button) findViewById(R.id.Clearall);

        equal.setOnClickListener(this);
        dot.setOnClickListener(this);
        plusMinSign.setOnClickListener(this);
        clearAll.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {


        TextView t = (TextView) findViewById(R.id.txt1) , t2 = (TextView) findViewById(R.id.txt2);
        TextView operator  = (TextView) findViewById(R.id.operator);
        String sign ;

        if(t.getText().toString().contains("Infinity") || t.getText().toString().equalsIgnoreCase("NaN"))
        {
            t.setText("");
            return;
        }
        switch (v.getId())
        {
            case R.id.B0:
            {
                t.append("0");
                break;
            }

            case R.id.B1:
            {
                t.append("1");
                break;
            }

            case R.id.B2:
            {
                t.append("2");
                break;
            }

            case R.id.B3:
            {
                t.append("3");
                break;
            }

            case R.id.B4:
            {
                t.append("4");
                break;
            }

            case R.id.B5:
            {
                t.append("5");
                break;
            }

            case R.id.B6:
            {
                t.append("6");
                break;
            }

            case R.id.B7:
            {
                t.append("7");
                break;
            }

            case R.id.B8:
            {
                t.append("8");
                break;
            }

            case R.id.B9:
            {
                t.append("9");
                break;
            }

            case R.id.back:
            {
                String text = t.getText().toString();

                if(text.contains("Infinity") || text.equalsIgnoreCase("NaN"))
                {
                    t.setText("");
                    return;
                }
                if(text.length() > 0)
                    t.setText(text.substring(0, text.length() - 1));
                break;
            }

            case R.id.plus:
            {
                if (OperatorNeedChange(t , operator , "+"))
                {
                    return;
                }
                operatorCase(t , t2 , operator , "+");
                break;
            }

            case R.id.multi:
            {
                if (OperatorNeedChange(t , operator , "*"))
                {
                    return;
                }
                operatorCase(t , t2 , operator , "*");
                break;
            }

            case R.id.subtract:
            {
                if (OperatorNeedChange(t , operator , "-"))
                {
                    return;
                }
                operatorCase(t , t2 , operator , "-");
                break;
            }

            case R.id.divide:
            {
                if (OperatorNeedChange(t , operator , "/"))
                {
                    return;
                }
                operatorCase(t , t2 , operator , "/");
                break;
            }

            case R.id.equal:
            {
                if(t2.length() > 0 && (t.length() > 0))
                {
                    sign = operator.getText().toString();

                    Float val1, val2  ;
                    val1 = Float.valueOf(t.getText().toString());
                    val2 = Float.valueOf(t2.getText().toString());

                    t.setText(String.valueOf(eval(val1 , val2 , sign )));
                    t2.setText("");
                    operator.setText("");

                    ifDotZero(t);
                }
                break;
            }
            case R.id.dot:
            {
                String temp = t.getText().toString();
                if(temp.contains("."))
                {
                    return;
                }
                else
                {
                    if(t.length() < 1 )
                    {
                        t.append("0");
                    }
                    t.append(".");
                }
                break;
            }

            case R.id.per:
            {
                if (t.length() < 1)
                {
                    return;
                }
                sign = operator.getText().toString();

                switch (sign)
                {
                    case "*":
                    {
                        Float val1, val2  ;
                        val1 = Float.valueOf(t.getText().toString());
                        val2 = Float.valueOf(t2.getText().toString());

                        val1 = (val1*val2) / 100;

                        t.setText(String.valueOf(val1));
                        t2.setText("");
                        operator.setText("");

                        ifDotZero(t);

                        break;
                    }

                    case "/":
                    {
                        Float val1, val2  ;
                        val1 = Float.valueOf(t.getText().toString());
                        val2 = Float.valueOf(t2.getText().toString());

                        val1 = (100*val2) / val1;


                        t.setText(String.valueOf(val1));
                        t2.setText("");
                        operator.setText("");

                        ifDotZero(t);

                        break;
                    }

                    case "+":
                    {
                        Float val1, val2  ;
                        val1 = Float.valueOf(t.getText().toString());
                        val2 = Float.valueOf(t2.getText().toString());

                        val1 = (val1*val2) / 100;
                        val1 += val2;

                        t.setText(String.valueOf(val1));
                        t2.setText("");
                        operator.setText("");

                        ifDotZero(t);

                        break;
                    }

                    case "-":
                    {
                        Float val1, val2  ;
                        val1 = Float.valueOf(t.getText().toString());
                        val2 = Float.valueOf(t2.getText().toString());

                        val1 = (val1*val2) / 100;
                        val1 -= val2;

                        t.setText(String.valueOf(val1));
                        t2.setText("");
                        operator.setText("");

                        ifDotZero(t);

                        break;
                    }
                }

                break;
            }

            case R.id.plusMinsign:
            {
                if(t.length() < 1)
                {
                    t.setText("0");
                }
                Float val1 = Float.valueOf(t.getText().toString());
                val1 *= -1;

                t.setText(String.valueOf(val1));

                ifDotZero(t);

                break;
            }

            case R.id.Clearall:
            {
                t.setText("");
                t2.setText("");
                operator.setText("");
            }
        }


    }

    Float eval(Float v1 , Float v2 , String sign)
    {
        switch (sign)
        {
            case "+":
            {
                return v2+v1 ;
            }
            case "-":
            {
                return v2-v1 ;
            }
            case "*":
            {
                return v2*v1 ;
            }
            case "/":
            {
                return  v2/v1;
            }
        }
        return v1 ;
    }

    String checkSign(TextView operator , String sign)
    {
        if(!(operator.length() > 0) )
        {
            operator.setText(sign);
        }
        else
        {
            String temp = operator.getText().toString();
            operator.setText(sign);
            return temp;
        }
        return sign;
    }

    void mainEval(TextView t , TextView t2 , String sign)
    {
        Float val1, val2  ;
        val1 = Float.valueOf(t.getText().toString());
        val2 = Float.valueOf(t2.getText().toString());

        val2 = eval(val1 , val2 , sign);

        t.setText("");
        t2.setText(val2.toString());

    }

    boolean operatorCase(TextView t , TextView t2 , TextView operator , String sign)
    {
      /*  if(errStat)
        {
            return false;
        }*/

        sign = checkSign(operator , sign);


        if( !(t2.length() > 0) )
        {
            t2.setText(t.getText());
            t.setText("");

        }
        else
        {
            mainEval(t , t2 , sign);
        }
        return true;
    }

    boolean OperatorNeedChange(TextView t , TextView operator , String sign)
    {
        if(t.length() < 1 && operator.length() > 0)
        {
            operator.setText(sign);
            return true;
        }
        return false;
    }

    void ifDotZero(TextView t)
    {
        String temp = t.getText().toString();

        String substring = temp.substring(temp.length() - 2 , temp.length()  );

        if(substring.equals(".0"))
        {
            t.setText(temp.substring(0 , temp.length() - 2));
        }
    }

}
