import javax.swing.*;
import java.awt.*;

public class AccountField {

    private JLabel label;
    private JTextField field;
    JComboBox<String> comboBox;

    public AccountField(String title,int ylabel,int yfield){
                    label = new JLabel(title);
                    label.setBounds(40,ylabel,100,100);
                    label.setForeground(Color.WHITE);

                    field = new JTextField();
                    field.setBounds(120,yfield,300,20);
                    field.setCaretColor(Color.DARK_GRAY); 
                    field.setEditable(true);
    }
    public AccountField(String title,int ylabel,int ybox,String[] options) {
        label = new JLabel(title);
        label.setBounds(40,ylabel,100,100);
        label.setForeground(Color.WHITE);

        comboBox = new JComboBox<>(options);
        comboBox.setBounds(120,ybox,305,20);
    }

    public JLabel getLabel(){
        return label;
    }
    public JTextField getField(){
        return field;
    }
    public JComboBox<String> getBox(){
        return comboBox;
    }

}
