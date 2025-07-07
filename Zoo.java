package zoo;

import zoo.Class.Elephant;
import zoo.Class.Lion;
import zoo.Class.Pen;

public class Zoo {
    public static void main(String[] args) {

        Pen<Lion> enclosLions         = new Pen<>();
        Pen<Elephant> enclosElephants = new Pen<>();

        Lion leo     = new Lion(1, "Léo", 5);
        Lion nala    = new Lion(2, "Nala", 4);
        Elephant Tuut = new Elephant(3, "Tuut", 10, 1520.0);

        System.out.println(enclosLions.addAnimal(leo));
        System.out.println(enclosLions.addAnimal(nala));
        System.out.println(enclosElephants.addAnimal(Tuut));

        // System.out.println(enclosLions.addAnimal(dora));
        System.out.println("\n Lions dans l’enclos ");
        for (Lion lion : enclosLions.displayAnimals()) {
            System.out.println(lion);
        }

        System.out.println("\n Éléphants dans l’enclos ");
        for (Elephant elephant : enclosElephants.displayAnimals()) {
            System.out.println(elephant);
        }
    }
}
