package Animals;
public class Cat extends Animal {

    public Cat(String name, int weight, String type) {
        super(name, weight, type);
    }

    @Override
    public String makeSound() {
        return "meow meow";
    }


    @Override
    public String eat() {
        System.out.println("Cat eats");
        return "Cat eats";
    }

    @Override
    public void sleep() {
        System.out.println("Cat sleeps");
    }
}