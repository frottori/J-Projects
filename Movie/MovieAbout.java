import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieAbout extends JFrame implements ActionListener{

    JButton exit;
    MovieAbout(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("About");
        this.setBounds(620,230,300,300);
        this.setLayout(null);

        MovieField appName = new MovieField("Movie©",105,-20,200,100,25);
        MovieField developerName = new MovieField("Effrosyni Varsou",85,20,200,100,17);
        MovieField code = new MovieField("21390021",110,40,100,100,17);
        MovieField time = new MovieField("2/6/23 - 3/6/23",95,60,200,100,17);
        JLabel icon = new JLabel();
        icon.setBounds(105, 130, 100, 100);
        ImageIcon logo = new ImageIcon("icon.png");
        icon.setIcon(logo);

        exit = new JButton("exit");
        exit.setBounds(115, 230, 60, 30);
        exit.addActionListener(this);

        this.add(appName.getLabel());
        this.add(developerName.getLabel());
        this.add(code.getLabel());
        this.add(time.getLabel());
        this.add(icon);
        this.add(exit);
        this.getContentPane().setBackground(new Color(0x575658));
        this.setResizable(false);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exit){
            this.dispose();
        }
    }
}
