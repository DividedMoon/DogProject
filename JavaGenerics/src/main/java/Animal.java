public class Animal {
    String type;
    public void eat() {
        System.out.println(type+ " is eating");
    }

    public Animal(String type) {
        this.type = type;
    }
}
