package model;

public class User {

    private String name;
    private int age;
    private int id;

    public User(String name, int age, int id) {
        this.id = id;
        this.name = name;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
