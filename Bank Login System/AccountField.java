import javax.swing.*;
import java.awt.*;

public class AccountField {

    private JLabel label;
    private JTextField field;

    public AccountField(String title,int ylabel,int yfield){
                    label = new JLabel(title);
                    label.setBounds(40,ylabel,100,100);
                    label.setForeground(Color.WHITE);

                    field = new JTextField();
                    field.setBounds(120,yfield,300,20);
                    field.setCaretColor(Color.DARK_GRAY); 
                    field.setEditable(true);
    }

    public JLabel getLabel(){
        return label;
    }
    public JTextField getField(){
        return field;
    }
}
