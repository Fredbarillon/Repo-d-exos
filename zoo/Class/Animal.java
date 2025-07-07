package zoo.Class;

public abstract class Animal {
    protected int id;

    public Animal(int id) {
        this.id = id;
    }

    public abstract void eat();
    public abstract void sleep();
    public abstract void makeNoise();

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Animal #" + id;
    }

}
