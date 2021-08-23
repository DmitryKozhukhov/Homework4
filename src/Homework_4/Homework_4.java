package Homework_4;

import java.util.Random;
import java.util.Scanner;

public class Homework_4 {
    public static void main(String[] args) {
        Start(13);
    }

    static void Start(int SIZE){

        char[][] Pole = Pole(SIZE);

        do {
            doPlayerMove(Pole,SIZE);
            printPole(Pole);
            if (isWin(Pole, 'X')){
                System.out.println("Вы выиграли !!!");
                break;
            }
            if (isDraw(Pole)){
                System.out.println("Ничья !!!");
                break;
            }

            doPlayAI(Pole);
            printPole(Pole);
            if (isWin(Pole, 'O')){
                System.out.println("Вы проиграли !!!");
                break;
            }

        } while (true);
    }


    static char[][] Pole(int SIZE) {
        char[][] Pole = new char[SIZE][SIZE];
        for (int i = 0; i < Pole.length; i++) {
            for (int j = 0; j < Pole.length; j++) {
                Pole[i][j] = '-';
            }
        }
        return Pole;
    }
    static void printPole(char[][] Pole){
        for (int i = 0; i < Pole.length; i++) {
            for (int j = 0; j < Pole.length; j++) {
                System.out.print(Pole[i][j]);
                System.out.print(" ");
            }   System.out.println();
        }       System.out.println();
    }

    static void doPlayerMove(char[][] Pole, int SIZE){
        int h,v;
        do {
            h = coordinate(Pole.length - 1, 'h', SIZE );
            v = coordinate(Pole.length - 1, 'v', SIZE );
        } while (isNotCellValid(Pole, h, v));
        Pole[h][v] = 'X';
    }

    static void doPlayAI(char[][] Pole){
        Random random = new Random();
        int h, v;
        do {
            h = random.nextInt(Pole.length);
            v = random.nextInt(Pole.length);
        } while (isNotCellValid(Pole, h, v));
        Pole[h][v] = 'O';
    }
    static int coordinate(int lastIndex, char coordName, int SIZE){
        Scanner sc = new Scanner(System.in);
        int coord;
        do {
            System.out.printf("Введите %s-координату от 1 до %s %n", coordName, SIZE, 1);
            coord = sc.nextInt() - 1;
        } while (coord < 0 || coord > lastIndex);
        return coord;
    }

    static boolean isCellValid(char[][] Pole, int h, int v){
        return Pole[h][v] == '-';
    }
    static boolean isNotCellValid(char[][] Pole, int h, int v) {
        return !isCellValid(Pole, h, v);
    }

    static boolean isWin(char[][] Pole, char symbol) {

        // horizontal
        for (int i = 0; i < Pole.length; i++) {
            for (int j = 2; j < Pole.length; j++) {
                if (Pole[i][j] == symbol && Pole[i][j - 1] == symbol && Pole[i][j - 2] == symbol) {
                    return true;
                }
            }
        }

        // vertical
        for (int i = 2; i < Pole.length; i++) {
            for (int j = 0; j < Pole.length; j++) {
                if (Pole[i][j] == symbol && Pole[i - 1][j] == symbol && Pole[i - 2][j] == symbol) {
                    return true;
                }
            }

        }
        // diagonals
        for (int i = 2; i < Pole.length; i++) {
            for (int j = 2; j < Pole.length; j++) {
                if (Pole[i][j] == symbol && Pole[i - 1][j - 1] == symbol && Pole[i - 2][j - 2] == symbol) {
                    return true;
                }
            }
        }
        for (int i = 0; i < Pole.length - 2; i++) {
            for (int j = 2; j < Pole.length; j++) {
                if (Pole[i][j] == symbol && Pole[i + 1][j - 1] == symbol && Pole[i + 2][j - 2] == symbol) {
                    return true;
                }
            }
        }   return false;
    }
    static boolean isDraw(char[][] Pole){
        for (int i = 0; i < Pole.length; i++) {
            for (int j = 0; j < Pole.length; j++) {
                if (isCellValid(Pole, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
