package zoo.Class;

public class Lion extends Animal {
    private String name;
    private int age;

    public Lion(int id, String name, int age) {
        super(id);
        this.name = name;
        this.age = age;
    }

    @Override
    public void eat() {
        System.out.println(name + " : Miam, je mange !");
    }

    @Override
    public void sleep() {
        System.out.println(name + " : Ronpich… je dors !");
    }

    @Override
    public void makeNoise() {
        System.out.println(name + " : Rrrrr ! Je rugis !");
    }

    @Override
    public String toString() {
        return "Lion { id=" + id + ", nom='" + name + '\'' + ", âge=" + age + " }";
    }

    // getters si besoin
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
