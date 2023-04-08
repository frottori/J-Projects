import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class LoginPage implements ActionListener {

    UserAccounts info;
    AccountField user,fname,lname,gender,genderbox,balance,value;
    JPasswordField passwordField;
    JLabel passwordLabel, label;
    JButton signIn, signUp,register, logOut,deposit,withdraw,withdrawValue,depositValue;
    JFrame accountWindow, forum, frame;

    LoginPage() {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        //Account Information
        user = new AccountField("username :",40,10,110,50,100,100,300,20);
        fname = new AccountField("First Name :",40,10,120,50,100,100,300,20);     
        lname = new AccountField("Last Name :", 40,40,120,80,100,100,300,20); 
        gender = new AccountField("Gender :", 40,70,120,110,100,100,300,20); 
        String[] options = {"Male", "Female","Other"};
        genderbox = new AccountField("Gender :", 70, 110,options);
        balance = new AccountField("Balance :", 40,100,120,140,100,100,300,20); 
        value = new AccountField("Withdraw :", 0,10000,10);
        info = new UserAccounts();

        passwordField = new JPasswordField();
        passwordField.setBounds(110,100,300,20);
        passwordField.setCaretColor(Color.BLACK); 
        
        passwordLabel = new JLabel("password :");
        passwordLabel.setBounds(40,60,100,100);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Helvetica",Font.PLAIN,15));

        //Title
        label = new JLabel("Bank Login Window");
        label.setFont(new Font("Helvetica",Font.PLAIN,15));
        label.setBounds(170,-70,190,190);
        label.setForeground(Color.WHITE);

        //Buttons
        signIn = new JButton("Sign In");
        signUp = new JButton("Sign Up");
        signIn.setBounds(280,130,70,33);
        signIn.setFocusable(false);
        signIn.addActionListener(this);
        signUp.setBounds(350,130,70,33);
        signUp.setFocusable(false);
        signUp.addActionListener(this);
        register = new JButton("Register");
        register.addActionListener(this);
        logOut = new JButton("Log Out");
        logOut.addActionListener(this);
        withdraw = new JButton("Withdraw");
        withdraw.addActionListener(this);
        deposit = new JButton("Deposit");
        deposit.addActionListener(this);
        withdrawValue = new JButton("Withdraw");
        withdrawValue.addActionListener(this);
        depositValue = new JButton("Deposit");
        depositValue.addActionListener(this);

        //Frames 
        frame.add(label);
        frame.add(user.getLabel());
        frame.add(user.getField());
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signIn);
        frame.add(signUp);
        frame.setResizable(false);
        frame.setBounds(500,100,500,220);
        frame.getContentPane().setBackground(new Color(0x575658));
        frame.setVisible(true);

        accountWindow = new JFrame();
        forum = new JFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String username = user.getField().getText();
        String password = String.valueOf(passwordField.getPassword());
        JFrame balanceWindow = new JFrame();
        
        if (e.getSource()==signIn) {
            if(info.getLoginInfo().containsKey(username)) {
                if(info.getLoginInfo().get(username).equals(password)) {

                    accountWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    accountWindow.setLayout(null);

                    //Title
                    JLabel title = new JLabel("Account Information");
                    title.setFont(new Font("Helvetica",Font.PLAIN,15));
                    title.setBounds(170,-70,190,190);
                    title.setForeground(Color.WHITE);

                    fname.getField().setText(info.getFirstName().get(username)); fname.getField().setEditable(false);
                    lname.getField().setText(info.getLastName().get(username)); lname.getField().setEditable(false);
                    gender.getField().setText(info.getGender().get(username));  gender.getField().setEditable(false);
                    balance.getField().setText(Double.toString(info.getMoney().get(username))); balance.getField().setEditable(false);

                    logOut.setBounds(210,170,70,33);
                    logOut.setFocusable(false);
                    withdraw.setBounds(280,170,70,33);
                    withdraw.setFocusable(false);
                    deposit.setBounds(350,170,70,33);
                    deposit.setFocusable(false);

                    accountWindow.add(fname.getField());
                    accountWindow.add(fname.getLabel());
                    accountWindow.add(lname.getField());
                    accountWindow.add(lname.getLabel());
                    accountWindow.add(gender.getField());
                    accountWindow.add(gender.getLabel());
                    accountWindow.add(balance.getField());
                    accountWindow.add(balance.getLabel());
                    accountWindow.add(logOut);
                    accountWindow.add(withdraw);
                    accountWindow.add(deposit);
                    accountWindow.add(title);
                    accountWindow.getContentPane().setBackground(new Color(0x575658));
                    accountWindow.setBounds(500,100,500,235);
                    accountWindow.setResizable(false);
                    accountWindow.setVisible(true);
                }
                else {
                    user.getField().setText("");
                    passwordField.setText("");
                }
            }
            else {
                user.getField().setText("");
                passwordField.setText("");
            }
        }
        if (e.getSource()==signUp) {
            if (!info.getLoginInfo().containsKey(username))  {  
               
                info.getLoginInfo().put(username,password); 
                forum.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                forum.setLayout(null);

                //Title
                JLabel title = new JLabel("Sign Up Page");
                title.setFont(new Font("Helvetica",Font.PLAIN,15));
                title.setBounds(180,-70,190,190);
                title.setForeground(Color.WHITE);

                info.getMoney().put(username,0.0);

                register.setBounds(350,135,70,33);
                register.setFocusable(false); 

                fname.getField().setText(""); fname.getField().setEditable(true);
                lname.getField().setText(""); lname.getField().setEditable(true);
                
                forum.add(fname.getField());
                forum.add(fname.getLabel());
                forum.add(lname.getField());
                forum.add(lname.getLabel());
                forum.add(genderbox.getLabel());
                forum.add(genderbox.getBox());
                forum.add(register);
                forum.add(title);
                forum.getContentPane().setBackground(new Color(0x575658));
                forum.setBounds(500,100,500,220);
                forum.setResizable(false);
                forum.setVisible(true);
            }
        }
        if(e.getSource()==register)
        {
                info.getFirstName().put(username,fname.getField().getText());
                info.getLastName().put(username,lname.getField().getText());
                info.getGender().put(username,(String) genderbox.getBox().getSelectedItem());
                fname.getField().setEditable(false);
                lname.getField().setEditable(false);
                lname.getField().setEditable(false);
                forum.dispose();
                user.getField().setText("");
                passwordField.setText("");
        }
        if (e.getSource()==logOut){
            
            accountWindow.dispose();
            user.getField().setText("");
            passwordField.setText("");
        }
        if(e.getSource()==withdraw || e.getSource()==deposit){

            balanceWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            balanceWindow.setLayout(null);

            //Title
            JLabel title = new JLabel();
            String action = e.getActionCommand();
            if (action.equals("Withdraw")){
                title.setText("Withdraw");
                withdrawValue.setBounds(350,170,70,33);
                withdrawValue.setFocusable(false);
                withdrawValue.setEnabled(true);
                balanceWindow.add(withdrawValue);
            }
            else {
                title.setText("Deposit");
                depositValue.setBounds(350,170,70,33);
                depositValue.setFocusable(false);
                depositValue.setEnabled(true);
                balanceWindow.add(depositValue);
            }

            title.setFont(new Font("Helvetica",Font.PLAIN,15));
            title.setBounds(220,-70,190,190);
            title.setForeground(Color.WHITE);

            balanceWindow.add(balance.getLabel());
            balanceWindow.add(balance.getField());
            balanceWindow.add(value.getLabel());
            balanceWindow.add(value.getSpinner());
            balanceWindow.add(title);
            balanceWindow.getContentPane().setBackground(new Color(0x575658));
            balanceWindow.setBounds(500,100,500,235);
            balanceWindow.setResizable(false);
            balanceWindow.setVisible(true);
        }
        if (e.getSource()==withdrawValue || e.getSource()==depositValue){

            double x = Double.parseDouble(value.getSpinner().getValue().toString());
            double val;
            String action = e.getActionCommand();
            if (action.equals("Withdraw")){

                double initval = info.getMoney().get(username);
                int ans=0;
                val =  initval - x;
                if (val<0){
                    ImageIcon icon = new ImageIcon("warning.png");
                    ans = JOptionPane.showConfirmDialog(null,
            "Are you sure?\nYou will be in debt.","Large Withdrawal Amount",
                    JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE, icon); 
                }
                if (ans==1){ 
                    val = initval;
                }
                withdrawValue.setEnabled(false);
            }
            else {
                val = info.getMoney().get(username) + x;
                depositValue.setEnabled(false);
               
            }
            info.getMoney().put(username,val);
            balance.getField().setText(Double.toString(info.getMoney().get(username))); balance.getField().setEditable(false);
            accountWindow.add(balance.getField());
            value.getSpinner().setValue(0);
        }  
    }
    public static void main (String[] args) {
        new LoginPage();
    }
}