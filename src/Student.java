public class Student {
    private String name;
    private String surname;
    private String nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Student(String name, String surname){
        this.name = name;
        this.surname = surname;

    }
}
