package zoo.Class;

public class Elephant extends Animal {
    private String name;
    private int age;
    private double weight;

    public Elephant(int id, String name, int age, double weight) {
        super(id);
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    @Override
    public void eat() {
        System.out.println(name + " : Crumch… je mange ");
    }

    @Override
    public void sleep() {
        System.out.println(name + " : Zzz… je dors debout !");
    }

    @Override
    public void makeNoise() {
        System.out.println(name + " : Tooot… je barris !");
    }

    @Override
    public String toString() {
        return "Elephant { id=" + id
                + ", nom='" + name + '\''
                + ", âge=" + age + " ans"
                + ", poids=" + weight + " kg }";
    }

    // Getters si besoin
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }
}
