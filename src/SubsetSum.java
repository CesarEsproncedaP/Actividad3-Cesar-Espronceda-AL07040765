import java.util.ArrayList;
import java.util.Scanner;

class SubsetSum {
    /**
     * Lo que se hace aqui es un algoritmo usado para encontrar en base a un conjunto creado por el usuario un resultado por medio de la suma de dichos
     * conjuntos previamente creados. 
     * Este programa permite al usuario ingresar un conjunto de números y un valor objetivo, luego muestra todos los subconjuntos
     * que suman dicho valor e indica si  es o no es posible llegar a dicho valor onjetivo por medio dela suma de los conjuntos creados.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> set = new ArrayList<>();
        
        System.out.println("Ingresa todos los números que tu quieras para crear el conjunto (ingresa 0 para terminar de creaar el conjunto y pasar a la suma deseada):");
        while (true) {
            int num = scanner.nextInt();
            if (num == 0) break;
            set.add(num);
        }
        
        System.out.println("Conjunto ingresado: " + set);
        System.out.println("Ingresa la suma objetivo:");
        int sum = scanner.nextInt();
        
        System.out.println("Buscando subconjuntos que sumen " + sum + " usando recursión...");
        ArrayList<String> subsets = findAllSubsetSums(set, set.size(), sum);
        
        if (subsets.isEmpty()) {
            System.out.println("No existe un subconjunto que sume al valor objetivo.");
        } else {
            System.out.println("Subconjuntos encontrados:");
            for (String subset : subsets) {
                System.out.println("{" + subset + "}");
            }
        }
    }

    /**
     * Encuentra recursivamente todas las posibles combinatorias del conjunto que dan como resultado el valor objetivo.
     *
     * @param set El conjunto de números previamente definidos por el usuario.
     * @param n El tamaño del conjunto creado por el usuario.
     * @param sum El valor objetivo que tiene que dar la suma de los conjuntos.
     * @return Regresa una lista que tiene todas las combinatorias que suman el valor objetivo.
     */
    public static ArrayList<String> findAllSubsetSums(ArrayList<Integer> set, int n, int sum) {
        ArrayList<String> results = new ArrayList<>();
        if (sum == 0) {
            results.add("");
            return results;
        }
        if (n == 0) {
            return results;
        }
        if (set.get(n - 1) > sum) {
            return findAllSubsetSums(set, n - 1, sum);
        }
        ArrayList<String> include = findAllSubsetSums(set, n - 1, sum - set.get(n - 1));
        for (String subset : include) {
            results.add((subset.isEmpty() ? "" : subset + ", ") + set.get(n - 1));
        }
        ArrayList<String> exclude = findAllSubsetSums(set, n - 1, sum);
        results.addAll(exclude);
        return results;
    }
}