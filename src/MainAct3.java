import java.util.Scanner;

public class MainAct3 {
    /**
     * Esta es la parte principal del programa, es la que se ejecuta y muestra el menú al usuario
     * , en dicho menú se pueden seleccionar 3 diferentes problemas(Fibonacci, Subset Sum y Sudoku), 
     * al igual se puede ejecutar la opción #4 qu es salir del programa.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
        System.out.println("--------- César Espronceda | AL07040765 ---------");
       System.out.println("| Bienveenido a mi programa para la actividad 3 |");
            System.out.println("|                                               |");    
            System.out.println("|       Elige el problema que quieres ver:      |");
            System.out.println("|                                               |");    
            System.out.println("| 1. Fibonacci                                  |");
            System.out.println("| 2. Subset Sum                                 |");
            System.out.println("| 3. Sudoku                                     |");
            System.out.println("| 4. Salir                                      |");
            System.out.println("------------------------------------------------");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    SerieFibonacciRecursiva.run();
                    break;
                case 2:
                    SubsetSum.run();
                    break;
                case 3:
                    AlgoritmoBacktrackingSudoku.run();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Tu opción no es válida, por favor ingresa un valor del 1 al 4 ");
            }
            System.out.println(); 
        } while (choice != 4);
        scanner.close();
    }
}