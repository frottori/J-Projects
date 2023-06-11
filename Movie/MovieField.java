import javax.swing.*;
import java.awt.*;

public class MovieField {

    private JLabel label;
    private JTextField field;
    JComboBox<String> box;

    public MovieField(String title,int xlabel,int ylabel,int wlabel,int hlabel,int size){
        label = new JLabel(title);
        label.setBounds(xlabel,ylabel,wlabel,hlabel);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Helvetica",Font.PLAIN,size));
    }

    public MovieField(String title,int xlabel,int ylabel,int xfield,int yfield,int wlabel,int hlabel){
            label = new JLabel(title);
            label.setBounds(xlabel,ylabel,wlabel,hlabel);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Helvetica",Font.PLAIN,17));

            field = new JTextField();
            field.setBounds(xfield,yfield,300,20);
            field.setCaretColor(Color.BLACK); 
            field.setEditable(true);
    }

    public MovieField(String title,int xlabel,int ylabel,int xbox, int ybox,int wlabel,int hlabel, String[] options) {
        label = new JLabel(title);
        label.setBounds(xlabel,ylabel,wlabel,hlabel);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Helvetica",Font.PLAIN,17));

        box = new JComboBox<>(options);
        box.setBounds(xbox,ybox,300,20);
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JTextField getField() {
        return this.field;
    }

    public void setField(JTextField field) {
        this.field = field;
    }

    public JComboBox<String> getBox() {
        return this.box;
    }

    public void setBox(JComboBox<String> box) {
        this.box = box;
    }
 
}