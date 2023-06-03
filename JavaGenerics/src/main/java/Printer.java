import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Printer <T extends Animal & Printable> {
    private final T t;
    public Printer(T t) {
        this.t = t;
    }
    public void print() {
        t.print("I am a " + t.type);
        t.eat();
    }

    private static <T,V> T shout(T t, V v) {
        System.out.println("Shouting "+t);
        System.out.println("Shouting "+v);
        return t;
    }

    private static <T extends Animal> void shout2(T animal) {
        System.out.println("Shouting "+animal);
    }

    private void shout3(T animal) {
        System.out.println("Shouting "+animal);
    }

    public static void main(String[] args) throws Exception {
        /*Printer<Cat> animalPrinter = new Printer<>(new Cat());
        animalPrinter.print();*/
        ArrayList<Animal> animals = new ArrayList<>();
        ArrayList<Integer> ints = new ArrayList<>();
        System.out.println(animals.getClass());
        System.out.println(ints.getClass());
        ints.add(1);
        ints.getClass().getMethod("add", Object.class).invoke(ints, "string");
        for (int i=0;i<ints.size();i++) {
            System.out.println(ints.get(i).getClass());
        }

    }
}
