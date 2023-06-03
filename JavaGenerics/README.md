## 泛型的作用
在没有泛型的情况的下，通过对类型 Object 的引用来实现参数的“任意化”，“任意化”带来的缺点是要做显式的强制类型转换，而这种转换是要求开发者对实际参数类型可以预知的情况下进行的。对于强制类型转换错误的情况，编译器可能不提示错误，在运行的时候才出现异常，这是本身就是一个安全隐患。那么泛型的好处就是在**编译的时候能够检查类型安全**，并且所有的强制转换都是自动和隐式的。


泛型的好处不言而喻，从`Go`语言就可以看到泛型能够为我们的编码工作带来很大的便利。`Java`中使用泛型也很简单，会用集合类就会用泛型了。

## 泛型的创建
使用泛型很简单，困难的是如何编写一段泛型代码，让使用者能够方便的使用，就像集合类一样。创建简单的泛型也并不困难，包含一些尖括号，例如`<T>`，`<T,R>`即可。

### 泛型在`Class`上的创建
```java
public class Printer<T> {
    T thing2Print;
    public void print() {
        System.out.println(thing2Print);
    }
    public Printer(T thing2Print) {
        this.thing2Print = thing2Print;
    }
    
    public static void main(String[] args) {
        Printer<String> printer = new Printer<>("Hello World");
        printer.print();

        Printer<Integer> printer = new Printer<>(2023);
        printer.print();
    }
}
```

这里存在的问题就是`T`类型是由使用者指定的，我们无法约束使用者传入的类型如果使用者传入的类型不是我们期望的类型，那么就会出现异常。因此可以通过`extends`关键字来约束泛型的类型。

```java
public class Printer<T extends Printable> { }
```
此时就可以使用`Printable`对象所包含的属性和方法了。
> 值得关注的是，这里只能使用`extends`关键字，不能使用`implement`关键字。如果存在多个接口，可以使用`&`符号连接。但**继承类只能有一个并且必须放在最前面**

```java
public class Printer<T extends Animal & Serializable & Printable> { }
```

### 泛型在`Method`上的创建
* 在返回值上使用
```java
class Shouter<T> {
    private static <T extends Animal> void shout2(T animal) {
        System.out.println("Shouting "+animal);
    }

    private void shout3(T animal) {
        System.out.println("Shouting "+animal);
    }
}
```
<mark>注意</mark>：在静态方法中，不能使用类上定义的泛型，需要在方法上重新定义，例如上面的两个函数。根本原因有以下两点：

1. 静态方法和普通方法的生命周期不同

* 静态方法生命周期属于类加载的时候，在Java中泛型只是一个占位符，必须传递具体类型才可以使用，也就是**类实例化的时候才传递具体参数类型**，由于静态方法的加载在类实例化之前，也就是说在类未实例化的时候，类中的泛型还没有传递真正的类型参数，这时候静态方法就已经加载完成。显然，静态方法访问不到泛型类中的泛型，所以需要加`<T>`声明使用哪种泛型类型。
* 这和静态方法不能调用普通方法，访问普通变量类似，都是因为**静态声明与非静态声明方法的生命周期不同**。

2. 在Java中泛型都会在编译之后被**擦除**为原始类型

* 在参数上使用
```java
class Shouter {
    private static void shout(List<? extends Animal> animals) {
        for (Animal animal : animals) {
            animal.eat();
        }
    }
}
```
### 泛型中的通配符
#### `?` 无界通配符
对于不确定或者不关心实际要操作的类型，可以使用`?`，表示可以持有任何类型。一般用在**形参**上。

#### `? extends T` 上界通配符
用`extends`关键字来约束泛型的类型，表示参数化的类型只能是`T`类型或者`T`类型的**子类**。

#### `? super T` 下界通配符
用`super`关键字来约束泛型的类型，表示参数化的类型只能是`T`类型或者`T`类型的**父类**。

对于确定类型`T`和不确定类型`?`的使用时机，其实很好理解，想固定参数化类型就用`T`，辅之以约束，不关心就用`?`。
## 泛型的擦除
Java的泛型基本上都是在编译器这个层次上实现的，在生成的字节码中是**不包含**泛型中的类型信息的，使用泛型的时候加上类型参数，在编译器编译的时候会去掉，这个过程成为类型擦除。

例如：`List<String>` 和 `List<Integer>` 在编译后都变成 `List`。
```java
public class Test {
    public static void main(String[] args) {
       List<Animal> animals = new ArrayList<>();
       List<Integer> ints = new ArrayList<>();
       System.out.println(animals.getClass());
       System.out.println(ints.getClass());
       ints.add(1);
       ints.getClass().getMethod("add", Object.class).invoke(ints, "string");
    }
}
```
List<Animal> 和 List<Integer> 在编译后都变成 List，所以在运行时，JVM 是不知道泛型信息的，即使不能直接添加 String 类型的对象到 List<Integer> 中，但是可以通过**反射**的方式绕过编译器的检查，添加 String 类型的对象到 List<Integer> 中。

<img src="./浅谈对Java泛型的理解/javaGenerics1.png">

## 泛型的协变与逆变
感觉上类似于隐式类型转换，具体细节还需研究。。。

## 参考来源
* [【你真的理解Java中的泛型吗】](https://www.bilibili.com/video/BV1ds4y1E7uW/)
* [Java 泛型擦除](https://www.cnblogs.com/hongdada/p/13993251.html)
* [Java泛型的协变与逆变](https://juejin.cn/post/6911302681583681544)