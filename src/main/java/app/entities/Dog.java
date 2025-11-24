package app.entities;

public class Dog {

    private int id;
    private String name;
    private int age;
    private String breed;
    private String nickName;
    private double weight;

    public Dog() {}

    public Dog(String name, int age, String breed, String nickName, double weight) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.nickName = nickName;
        this.weight = weight;
    }

    public Dog(int id, String name, int age, String breed, String nickName, double weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.nickName = nickName;
        this.weight = weight;
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public int getAge() {return age;}

    public String getBreed() {return breed;}

    public String getNickName() {return nickName;}

    public double getWeight() {return weight;}

    @Override
    public String toString() {
        return "Dog{id=%d, name='%s', age=%d, breed='%s', nickName='%s', weight=%.1f}".formatted(id, name, age, breed, nickName, weight);
    }

}
