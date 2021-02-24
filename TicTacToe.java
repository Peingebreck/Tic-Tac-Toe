package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    static int n = 3;
    static String player = "X";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[][] matrix = new String[n][n];
        printField(matrix);
        while (true) {
            System.out.print("Enter the coordinates: ");
            String input = sc.nextLine();
            checkInputCoordinates(matrix, input);
            if (!"Game not finished".equals(findState(matrix))) {
                System.out.println(findState(matrix));
                break;
            }
        }
    }

    static void printField(String[][] matrix) {
        System.out.println("---------");
        for (int i = 0; i < n; i++) {
            System.out.print("| ");
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] == null ? "  " : matrix[i][j] + " ");
            }
            System.out.print("|" + '\r' + '\n');
        }
        System.out.println("---------");
    }

    static void checkInputCoordinates (String[][] matrix, String input) {
        String[] coordinates = input.split(" ");
        if (coordinates.length == 2 && coordinates[0].matches("\\d+") && coordinates[1].matches("\\d+")) {
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            if (x < 1 || y < 1 || x > 3 || y > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if ("X".equals(matrix[x - 1][y - 1]) || "O".equals(matrix[x- 1][y - 1])) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                matrix[x - 1][y - 1] = player;
                printField(matrix);
                player = "X".equals(player) ? "O" : "X";
            }
        } else {
            System.out.println("You should enter numbers!");
        }
    }

    static String findState(String[][] matrix) {
        if (!findWinner(matrix, "X") && !findWinner(matrix, "O")) {
            if (countXAndO(matrix)[0] + countXAndO(matrix)[1] < n * n) {
                return "Game not finished";
            } else {
                return "Draw";
            }
        } else if (findWinner(matrix, "X")) {
            return "X wins";
        } else if (findWinner(matrix, "O")) {
            return "O wins";
        } else {
            return "Unknown state";
        }
    }

    static int[] countXAndO(String[][] matrix) {
        int[] countXAndO = new int[2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ("X".equals(matrix[i][j])) {
                    countXAndO[0]++;
                }
                if ("O".equals(matrix[i][j])) {
                    countXAndO[1]++;
                }
            }
        }
        return countXAndO;
    }

    static boolean findWinner(String[][] matrix, String player) {
        boolean wins = false;
        int[] rowCounter = new int[n];
        int[] columnCounter = new int[n];
        int mainCounter = 0;
        int sideCounter = 0;

        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (player.equals(matrix[i][j])) {
                    rowCounter[i]++;
                    columnCounter[j]++;
                    if (i == j) {
                        mainCounter++;
                    }
                    if (i + j ==  n - 1) {
                        sideCounter++;
                    }
                }
                if (columnCounter[j] == n) {
                    wins = true;
                    break;
                }
            }
            if (rowCounter[i] == n) {
                wins = true;
                break;
            }
        }
        if (mainCounter == n || sideCounter == n) {
            wins = true;
        }
        return wins;
    }
}
