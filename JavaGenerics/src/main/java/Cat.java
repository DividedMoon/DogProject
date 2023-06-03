public class Cat extends Animal implements Printable {
    @Override
    public void print(String line) {
        System.out.println(line);
    }

    public Cat() {
        super("Cat");
    }
}
