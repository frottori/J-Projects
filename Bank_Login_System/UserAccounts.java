import java.util.HashMap;

public class UserAccounts {

    private HashMap <String,String> loginInfo = new HashMap<String,String>();
    private HashMap <String,Double> money = new HashMap<String,Double>();
    private HashMap <String,String> gender = new HashMap<String,String>();
    private HashMap <String,String> firstName = new HashMap<String,String>();
    private HashMap <String,String> lastName = new HashMap<String,String>();


    public UserAccounts(){
        loginInfo.put("dimas","pizza");
        loginInfo.put("frottori","froso2003");
        loginInfo.put("filegeiasou","penguin1234");

        money.put("frottori",100.0);
        money.put("filegeiasou",50.0);
        money.put("dimas",25983.0);

        gender.put("frottori","Female");
        gender.put("filegeiasou","Male");
        gender.put("dimas","Male");

        firstName.put("dimas","Alexandros");
        firstName.put("frottori","Frosso");
        firstName.put("filegeiasou","Aggelos");

        lastName.put("dimas","Dimas");
        lastName.put("filegeiasou","Mentzelos");
        lastName.put("frottori","Varsou");
    }
    public HashMap<String,String> getLoginInfo(){
        return loginInfo;
    }

    public HashMap<String,Double> getMoney(){
        return money;
    }

    public HashMap<String,String> getGender(){
        return gender;
    }

    public HashMap<String,String> getFirstName(){
        return firstName;
    }

    public HashMap<String,String> getLastName(){
        return lastName;
    }
}