import java.util.Scanner;

class AlgoritmoBacktrackingSudoku {
    private static final int SIZE = 9;

    /**
     * Ejecuta el algoritmo creado para resolver los Sudokus usando backtracking. 
     * Al igual permit al usuario seleccionar uno de los 3 problemas de sudoku predefinidos o incluso crear su propio sudoku y muestra su solución.
     */
    public static void run() {
        //Defino los tableros de sudoku
        int[][] board1 = {
            {0, 0, 2, 0, 0, 0, 4, 5, 0},
            {5, 6, 4, 0, 0, 0, 7, 0, 3},
            {0, 0, 9, 0, 5, 3, 0, 6, 0},
            {8, 0, 5, 0, 9, 0, 3, 4, 0},
            {3, 2, 1, 0, 0, 7, 0, 8, 6},
            {4, 0, 6, 0, 3, 2, 1, 0, 0},
            {6, 0, 0, 7, 0, 0, 0, 0, 4},
            {2, 4, 0, 0, 0, 5, 0, 3, 0},
            {0, 1, 8, 3, 0, 0, 0, 0, 0}
        };
        int[][] board2 = {
            {3, 0, 6, 0, 7, 8, 5, 4, 0},
            {0, 5, 0, 0, 0, 0, 0, 0, 3},
            {1, 8, 0, 0, 0, 5, 0, 0, 0},
            {0, 3, 5, 0, 9, 4, 0, 0, 0},
            {0, 4, 0, 0, 0, 1, 3, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 3, 0, 4, 0, 0, 0, 9},
            {4, 0, 9, 0, 0, 0, 7, 0, 0},
            {0, 2, 0, 0, 0, 6, 0, 0, 0}
        };
        int[][] board3 = {
            {1, 9, 0, 0, 3, 0, 7, 0, 0},
            {0, 6, 8, 0, 7, 2, 0, 0, 0},
            {0, 7, 4, 0, 0, 8, 0, 6, 0},
            {0, 3, 1, 2, 0, 0, 0, 0, 8},
            {0, 8, 5, 0, 1, 0, 0, 9, 4},
            {0, 0, 9, 0, 8, 0, 0, 2, 0},
            {0, 0, 0, 9, 0, 0, 0, 3, 6},
            {4, 2, 3, 0, 0, 0, 0, 0, 0},
            {9, 5, 6, 0, 0, 3, 8, 0, 1}
        };

        Scanner scanner = new Scanner(System.in);
        System.out.println("Elige un tablero de Sudoku para resolver:");
        System.out.println("Tablero 1:");
        printBoard(board1);
        System.out.println("Tablero 2:");
        printBoard(board2);
        System.out.println("Tablero 3:");
        printBoard(board3);
        System.out.println("4. Crear tu propio Sudoku");
        System.out.println("Ingresa el número del tablero (1, 2, 3 o 4):");
        int boardChoice = scanner.nextInt();
        
        int[][] selectedBoard;
        switch (boardChoice) {
            case 1:
                selectedBoard = board1;
                break;
            case 2:
                selectedBoard = board2;
                break;
            case 3:
                selectedBoard = board3;
                break;
            case 4:
                selectedBoard = createCustomBoard(scanner);
                break;
            default:
                System.out.println("Tu opción no es valida, se seleccionará el tablero 1 por default.");
                selectedBoard = board1;
                break;
        }

        System.out.println("Tablero inicial:");
        printBoard(selectedBoard);
        System.out.println("Resolviendo Sudoku usando backtracking...");
        if (solveSudoku(selectedBoard)) {
            System.out.println("Solución encontrada:");
            printBoard(selectedBoard);
        } else {
            System.out.println("No existe solución para este Sudoku.");
        }
    }

    /**
     * Aqui lo que se hace es que le permite al usuario crear un tablero de Sudoku personalizado ingresando los valores fila por fila,
     * los valores deben ser números del 0 al 9, en donde el 0 básicamente es una celda vacía.
     * @param scanner El Scanner sirve para leer la entrada del usuario.
     * @return Regresa la tabla de 9x9 que representa el tablero del sudoku creado por el usuario.
     */
    private static int[][] createCustomBoard(Scanner scanner) {
        int[][] customBoard = new int[SIZE][SIZE];
        System.out.println("Crea tu Sudoku (ingresa 9 números por fila siguiendo las reglas del sudoku, usa 0 para celdas vacías):");
        //Esta opcion al igual se puede usar para resolver problemas de sudoku, la probé con mi mamá ya que ella tiene un juego en su celular de sudoku y 
        // funcionó a la perfeccion, pudo pasar el nivel sin errores.

        // El sudoku usado con mi mamá fué el siguiente: 
        // {0, 3, 4, 8, 0, 1, 0, 9, 0},
        // {6, 0, 2, 3, 0, 0, 5, 0, 1},
        // {1, 8, 9, 0, 0, 0, 0, 0, 3},
        // {0, 4, 0, 0, 0, 2, 0, 6, 5},
        // {2, 0, 6, 0, 0, 8, 0, 7, 4},
        //  {8, 0, 0, 0, 0, 4, 1, 0, 9},
        // {0, 2, 0, 6, 0, 0, 9, 0, 0},
        // {7, 6, 0, 0, 1, 0, 4, 0, 0},
        // {0, 9, 1, 0, 0, 0, 6, 0, 8}
        for (int i = 0; i < SIZE; i++) {
            System.out.println("Ingresa los " + SIZE + " valores para la fila " + (i + 1) + ":");
            for (int j = 0; j < SIZE; j++) {
                customBoard[i][j] = scanner.nextInt();
            }
        }
        return customBoard;
    }

    /**
     * Resuelve el tablero seleccionado de Sudoku utilizando el algoritmo de backtracking.
     * @param board Es el tablero de Sudoku el cuál tiene una dimensión de 9x9.
     * @return Regresa el valor "true" si es que se encuentra una solución, "false" en caso de que la solución del sudoku no sea posible.
     */
    public static boolean solveSudoku(int[][] board) {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int num = 1; num <= SIZE; num++) {
            if (isSafe(board, row, col, num)) {
                board[row][col] = num;
                if (solveSudoku(board)) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    /**
     * Verifica si es seguro que se coloque un número en una posición del tablero del sudoku. 
     * @param board Es el tablero de Sudoku el cuál tiene una dimensión de 9x9.
     * @param row Es la fila en donde se quiere colocar o insertar el número.
     * @param col Es la columna donde se quiere colocar o insertar el número.
     * @param num El número que se quiere poner y este solo puede ser un numero del 1 al 9.
     * @return Regresa "true" si el número puede colocarse de manera correcta y "false" en caso de que no se pueda debido 
     * a que no se sigan  las reglas del juego.
     */
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        for (int d = 0; d < SIZE; d++) {
            if (board[row][d] == num) {
                return false;
            }
        }
        for (int r = 0; r < SIZE; r++) {
            if (board[r][col] == num) {
                return false;
            }
        }
        int sqrt = (int) Math.sqrt(SIZE);
        int boxRowStart = row - row % sqrt;
        int boxColStart = col - col % sqrt;
        for (int r = boxRowStart; r < boxRowStart + sqrt; r++) {
            for (int d = boxColStart; d < boxColStart + sqrt; d++) {
                if (board[r][d] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
    * Imprime el tablero del Sudoku, decidi agregar unas lineas para hacer un tipo de separador 
    * para que este fuera más fácil de leer y que no se perdiera tanto la vista al estar viendolo.
    * @param board Es el tablero de Sudoku el cuál tiene una dimensión de 9x9.
    */
    public static void printBoard(int[][] board) {
        for (int r = 0; r < SIZE; r++) {
            for (int d = 0; d < SIZE; d++) {
                System.out.print(board[r][d] + " ");
                if (d == 2 || d == 5) {
                    System.out.print("| ");
                }
            }
            System.out.println();
            if (r == 2 || r == 5) {
                System.out.println("------+-------+------");
            }
        }
        System.out.println();
    }
}