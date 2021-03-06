package Entities;

public class Worker {
    private String person_number;
    private String name;
    private String address;


    public Worker(String person_number, String name, String address) {
        this.person_number = person_number;
        this.name = name;
        this.address = address;
    }

    public Worker(String person_number) {
        this.person_number = person_number;
    }

    public String getPerson_number() {
        return person_number;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "person_number='" + person_number + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
