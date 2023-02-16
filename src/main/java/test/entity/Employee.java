package test.entity;

public class Employee {
    String name;
    Integer age;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Employee() {
    }

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
