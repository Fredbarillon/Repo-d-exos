package zoo.Interface;

import zoo.Class.Animal;

import java.util.List;

public interface IPen {
     String addAnimal(Animal animal);
     List<Animal> displayAnimals();
}
