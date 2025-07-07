package zoo.Interface;

import java.util.List;
import zoo.Class.Animal;

public interface IPen<T extends Animal> {
    String addAnimal(T animal);
    List<T> displayAnimals();
}
