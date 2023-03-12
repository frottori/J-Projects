import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class Calc extends JFrame implements ActionListener {

    CalcPad b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,mult,sub,addt,eq,cl,sign,div,perc,comm;
    JTextField text;
    String num1,oper,num2;
    
    Calc() {
        oper = num1 = num2 = "";
        b0 = new CalcPad("0",140,50);
        b1 = new CalcPad("1",70,50);
        b2 = new CalcPad("2",70,50);
        b3 = new CalcPad("3",70,50);
        b4 = new CalcPad("4",70,50);
        b5 = new CalcPad("5",70,50);
        b6 = new CalcPad("6",70,50);
        b7 = new CalcPad("7",70,50);
        b8 = new CalcPad("8",70,50);
        b9 = new CalcPad("9",70,50);
        mult = new CalcPad("x",70,50,0xf3aa37);
        div = new CalcPad("÷",70,50,0xf3aa37);
        sub = new CalcPad("-",70,50,0xf3aa37);
        addt = new CalcPad("+",70,50,0xf3aa37);
        eq = new CalcPad("=",70,50,0xf3aa37);
        cl = new CalcPad("C",70,50,0xf3aa37);
        sign = new CalcPad("±",70,50,0xf3aa37);
        perc = new CalcPad("%",70,50,0xf3aa37);
        comm = new CalcPad(",",70,50);

        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        mult.addActionListener(this);
        div.addActionListener(this);
        sub.addActionListener(this);
        addt.addActionListener(this);
        eq.addActionListener(this);
        cl.addActionListener(this);
        sign.addActionListener(this);
        perc.addActionListener(this);
        comm.addActionListener(this);

        text = new JTextField();
        text.setPreferredSize(new Dimension(280,50));
        text.setEditable(false); 
        text.setFont(new Font("Helvetica",Font.PLAIN,40));
        text.setBackground(new Color(0x666262));
        text.setForeground(Color.WHITE);
        text.setHorizontalAlignment(SwingConstants.RIGHT);

        this.add(text);
        this.add(cl);
        this.add(sign);
        this.add(perc);
        this.add(div);
        this.add(b7);
        this.add(b8);
        this.add(b9);
        this.add(mult);
        this.add(b4);
        this.add(b5);
        this.add(b6);
        this.add(sub);
        this.add(b1);
        this.add(b2);
        this.add(b3);
        this.add(addt);
        this.add(b0);
        this.add(comm);
        this.add(eq);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(290,330));
        this.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        this.getContentPane().setBackground(new Color(0x666262));
        this.setResizable(false);
        this.setTitle("Calculator");
        this.setVisible(true);
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String formatNumber (double tmp) {
        String formatted;
        if (tmp == Math.floor(tmp)) {
            formatted = new DecimalFormat("#").format(tmp);
        }                   
        else {
            formatted = new DecimalFormat("#.##").format(tmp);
            }
        return formatted;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        double numA=0,numB=0;
        int sign1=-1,sign2=-1;
        double tmp;
        String formatted;
        String cpad = e.getActionCommand();

        if (cpad.equals("C")) {
            num1 = num2 = oper = "";
            text.setText("");
        }
        else if (isNumber(cpad) || cpad.equals(",")) {
            if (!oper.equals("")) {
                if(cpad.equals(",") && !num2.equals("")) {
                    num2 += ".";
                }
                else {
                    num2 += cpad;
                }
                text.setText(num2);
            }
	         else {
                if(cpad.equals(",") && !num1.equals("")) {
                    num1 += ".";
                }
                else {
	            num1 += cpad;
                }
                text.setText(num1);
            }
        }
        else if (cpad.equals("±") && !oper.equals("") && !num2.equals("")) {
            tmp = Double.parseDouble(num2);
            tmp *=  sign2;
            formatted=formatNumber(tmp);
            num2 = Double.toString(tmp);
            text.setText(formatted);
            sign2 *= -1;
        }
        else if (cpad.equals("=") && !oper.equals("")) {
                double res=0;
            	numB= Double.parseDouble(num2);
            	numA = Double.parseDouble(num1);
            	switch(oper) {
                	case "+" :  res = numA + numB; break;
                	case "-" :  res = numA - numB; break;
                	case "÷" :  res = numA / numB; break;
                	case "x" :  res = numA * numB; break;
            	}
                formatted=formatNumber(res);
                text.setText(formatted);
                num1 = Double.toString(res);
            	num2 = oper = "";
        }
        else {
            if(cpad.equals("±")){
                tmp = Double.parseDouble(num1);
                tmp *=  sign1;
                formatted=formatNumber(tmp);
                num1 = Double.toString(tmp);
                text.setText(formatted);
                sign1 *= -1;
            }
            else if(cpad.equals("%")) {
                numA = Double.parseDouble(num1);
                numA = numA / 100;
                num1 = Double.toString(numA);
                text.setText(num1);
            }
            else {
            oper += cpad;
            }
        }
    }
    public static void main (String[] args) {
        new Calc();
    }
}