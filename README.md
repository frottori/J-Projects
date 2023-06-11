# Java_Projects
My first look intο Swing Classes and implementation of simple programs with GUI Environment



//* JLabel = GUI Display Area for a string of text , an image , or both
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font; 

public class MyLabel extends JLabel {

    MyLabel() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        
        ImageIcon image = new ImageIcon("studygirl.jpg");
        Border border = BorderFactory.createLineBorder(Color.black,2);
                                                                                              
        this.setText("Java is Hard");                             //set text of label
        this.setIcon(image);                                           //set image of label
        this.setHorizontalTextPosition(JLabel.CENTER);                 //set text LEFT, CENTER, RIGHT of imageicon
        this.setVerticalTextPosition(JLabel.TOP);                      //set text TOP, CENTER, BOTTOM of imageicon
        this.setForeground(new Color(0x95CAE6));                   //set text color of label
        this.setFont(new Font("Calibri", Font.PLAIN, 20));  //set font and size of text
        this.setIconTextGap(15);                           //set gap of text to image
        this.setBackground(new Color (0xffdfba));                  //set background color
        this.setOpaque(true);                                 //display background color
        this.setBorder(border);
        this.setVerticalAlignment(JLabel.CENTER);                      //set vertical position of icon+text within label
        this.setHorizontalAlignment(JLabel.CENTER);                    //set horizontal position of icon+text within label
        this.setBounds(250, 0,450, 450);               //set x,y position within frame as well as dimension of label

        frame.add(this);
        frame.setVisible(true);
        frame.pack();
    }
    public static void main(String [] args){
        new MyLabel();
    }
}

