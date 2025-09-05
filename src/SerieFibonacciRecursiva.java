import java.util.Scanner;

class SerieFibonacciRecursiva {
    /**
     * Aqui lo que se hace es ejecutar el cálculo del enésimo número de la serie de Fibonacci. 
     * Permitiendo al usuario ingresar múltiples valores de n hasta que decida regresar al menú principal ingresando 0.
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            System.out.println("Ingresa el valor de n para calcular el enésimo número de Fibonacci (n >= 0) si el valor es 0 se regresará al menú anterior:");
            n = scanner.nextInt();
            if (n < 0) {
                System.out.println("Error: n debe ser mayor o igual a 0.");
                continue;
            }
            if (n == 0) {
                System.out.println("Regresando al menú principal...");
                break;
            }
            System.out.println("Calculando Fibonacci(" + n + ") usando la recursión");
            int result = fib(n);
            System.out.println("Resultado: F(" + n + ") = " + result);
        } while (true);
    }

    /**
     * Aquí se calcula el enésimo número de la serie de Fibonacci de forma recursiva.
     *
     * @param n Indica el índice del número de Fibonacci y este tiene que ser mayor a 0 o en caso de que se quiera regresar al menú anterior el valor 
     * tiene que ser igual a 0.
     * @return Es el resultado del enésimo numero Fibonacci elegido por el usuario.
     */
    public static int fib(int n) {
        if (n <= 1) {
            return n; // Caso base: Imprime lo que el usario ingresó en n si es que se eligió 1 o 0.
        }
        return fib(n - 1) + fib(n - 2); // Caso recursivo: Se suman  los dos números anteriores
    }
}