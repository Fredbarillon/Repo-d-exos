package exceptions;


public class ArrayMain {
    public static void main(String[] args) {
        int[] tab = {1,2,3,4,5};
        int i =6;
        try {
            if (i <0 || i >= tab.length) throw new ArrayIndexOutOfBoundsException("index hors limite");
            System.out.println(tab[i]);

        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Erreur : " + e.getMessage());
        }

    }
}
