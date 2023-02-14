package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        String[][] game = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = "_";
            }
        }
        int update;
        int status;
        boolean finished = false;
        String player = "X";

        printGame(game);


        while (!finished) {
            do {
                update = updateGame(game, player);
                switch (update) {
                    case 0:
                        System.out.println("You should enter numbers");
                        break;
                    case 1:
                        System.out.println("Coordinates should be from 1 to 3!");
                        break;
                    case 2:
                        System.out.println("This cell is occupied! Choose another one!");
                        break;
                }
            } while (update != 3);

            printGame(game);
            status = checkGameStatus(game);
            switch (status) {
                case 1:
                    System.out.println("X wins");
                    finished = true;
                    break;
                case 2:
                    System.out.println("O wins");
                    finished = true;
                    break;
                case 4:
                    System.out.println("Draw");
                    finished = true;
                    break;
            }
            if ("X".equals(player)) {
                player = "O";
            } else {
                player = "X";
            }

        }

        /*
        int status = checkGameStatus(game);
        switch(status) {
            case 0:
                System.out.println("Impossible");
                break;
            case 1:
                System.out.println("X wins");
                break;
            case 2:
                System.out.println("O wins");
                break;
            case 3:
                System.out.println("Game not finished");
                break;
            case 4:
                System.out.println("Draw");
                break;
        }
        */
    }


    public static void printGame(String[][] game) {

        int k = 0;
        System.out.println("---------");
        for (String [] line : game) {
            for (String X_O : line){
                if (k % 3 == 0) {
                    System.out.print("| ");
                }
                System.out.print(X_O);
                System.out.print(" ");
                if (k % 3 == 2) {
                    System.out.println("|");
                }
                k++;
            }
        }
        System.out.println("---------");

    }

    public static String[][] getGame() {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("");
        String[][] game = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                game[i][j] = scanner.next();
            }
        }

        return game;
    }

    public static int checkGameStatus(String[][] game) {

        int X = 0;
        int O = 0;
        int[] rowX = new int[3];
        int[] rowO = new int[3];
        int[] colX = new int[3];
        int[] colO = new int[3];
        int[] diaX = new int[2];
        int[] diaO = new int[2];
        boolean completeX = false;
        boolean completeO = false;
        boolean none = false;



        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                boolean antiDia = (i == 0 && j == 2) || (i == 1 && j == 1) || (i == 2 && j == 0);

                if ("X".equals(game[i][j])) {
                    X++;
                    rowX[i]++;
                    colX[j]++;
                    if (i == j) {
                        diaX[0]++;
                    }
                    if (antiDia) {
                        diaX[1]++;
                    }
                } else if ("O".equals(game[i][j])){
                    O++;
                    rowO[i]++;
                    colO[j]++;
                    if (i == j) {
                        diaO[0]++;
                    }
                    if (antiDia) {
                        diaO[1]++;
                    }
                } else {
                    none = true;
                }
            }
        }

        if (X - O > 1 || O - X > 1) {
            return 0;
        }
        for (int i = 0; i < 3; i++) {
            if (rowX[i] == 3 || colX[i] == 3){
                completeX = true;
            }
            if (i != 2 && diaX[i] == 3){
                completeX = true;
            }

            if (rowO[i] == 3 || colO[i] == 3){
                completeO = true;
            }
            if (i != 2 && diaO[i] == 3){
                completeO = true;
            }
        }

        if (completeX && completeO) {
            return 0;
        } else if (completeX) {
            return 1;
        } else if (completeO) {
            return 2;
        } else if (none){
            return 3;
        } else {
            return 4;
        }
    }

    public static int updateGame(String[][] game, String player) {
        Scanner scanner = new Scanner(System.in);

        int[] cell = new int[2];

        for (int i = 0; i < 2; i++) {

            if (scanner.hasNextInt()) {
                cell[i] = scanner.nextInt() - 1;
            } else {
                return 0;
            }
        }

        if (cell[0] < 0 || 2 < cell[0] || cell[1] < 0 || 2 < cell[1]){
            return 1;
        }

        if ("_".equals(game[cell[0]][cell[1]])) {
            game[cell[0]][cell[1]] = player;
            return 3;
        } else {
            return 2;
        }

    }
}

