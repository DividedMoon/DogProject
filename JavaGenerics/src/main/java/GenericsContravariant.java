public class GenericsContravariant {
    public static void main(String[] args) {
        Animal[] animals = new Animal[2];
        Cat[] cats = new Cat[2];
        for (Cat animal : (Cat[]) animals) {
            animal.eat();
        }

        System.out.println(animals.getClass());
        System.out.println(cats.getClass());

    }
}

class Dog extends Animal {

    public Dog() {
        super("dog");
    }
}
