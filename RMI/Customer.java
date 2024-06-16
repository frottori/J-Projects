import java.io.Serializable;

public class Customer implements Serializable{
    private String type;
    private int num;
    private String name;

    public Customer(String name, String type, int num) {
        this.name = name;
        this.type = type;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Customer: " + name + " booked " + num + " rooms of type " + type + "\n";
    }
}