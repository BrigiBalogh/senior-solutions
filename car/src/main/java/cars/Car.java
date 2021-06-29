package cars;

import java.util.List;

public class Car {

    private String brand;
    private String type;
    private int age;
    private Condition condition;

    private List<KmState> kmStates;


    public Car(String brand, String type, int age, Condition condition, List<KmState> kmStates) {
        this.brand = brand;
        this.type = type;
        this.age = age;
        this.condition = condition;
        this.kmStates = kmStates;
    }

    public String getBrand() {
        return brand;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public Condition getCondition() {
        return condition;
    }

    public List<KmState> getKmStates() {
        return kmStates;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", type='" + type + '\'' +
                ", age=" + age +
                ", condition=" + condition +
                ", kmStates=" + kmStates +
                '}';
    }
}
