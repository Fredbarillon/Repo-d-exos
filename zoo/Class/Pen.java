package zoo.Class;

import zoo.Interface.IPen;
import java.util.ArrayList;
import java.util.List;

public class Pen<T extends Animal> implements IPen<T> {
    private List<T> animals = new ArrayList<>();

    @Override
    public String addAnimal(T animal) {
        animals.add(animal);
        return animal.getClass().getSimpleName() + " ajouté → " + animal;
    }

    @Override
    public List<T> displayAnimals() {
        return new ArrayList<>(animals);
    }
}
