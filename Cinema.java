package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row  = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seat = sc.nextInt();
        int menu = 1;
        char[][] cinema = cinemaStartVision(row, seat);
        while (menu != 0) {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            System.out.println();
            menu = sc.nextInt();
            switch (menu) {
                case 1:
                    cinemaVie(cinema, row, seat);
                    break;
                case 2:
                    chooseSeat(cinema, sc, row, seat);
                    break;
                case 3:
                    showStatistics(cinema, row, seat);
                default:
                    break;

            }
        }
    }
    public static char[][] cinemaStartVision(int row, int seat){
        char[][] arrayCinema = new char[row + 1][seat + 1];
        System.out.println("Cinema:");
        for (int i = 0; i < row + 1; i++){
            for (int j = 0; j < seat + 1; j++){
                if (i == 0 && j == 0) {
                    arrayCinema[i][j] = ' ';
                    System.out.print(arrayCinema[i][j] + " ");
                } else if (i == 0) {
                    arrayCinema[i][j] = (char)(j + '0');
                    System.out.print(arrayCinema[i][j] + " " );
                } else if (j == 0) {
                    arrayCinema[i][j] =  (char)(i + '0');
                    System.out.print(arrayCinema[i][j] + " ");
                } else{
                    arrayCinema[i][j] = 'S';
                    System.out.print(arrayCinema[i][j] + " ");
                }
            }
            System.out.println();
        }
        return arrayCinema;
    }
    public static int cost(int row, int seat, int chooseRow, String mode){
        if (row * seat < 60){
            System.out.println("Ticket price: $10");
            if (mode.equals("one")) {
                return 10;
            }
            else {
                return row * seat * 10;
            }
        }
        else {
            int firstPart = row / 2;
            int secondPart = row - firstPart;
            if (mode.equals("one")) {
                if (chooseRow <= firstPart) {
                    System.out.println("Ticket price: $10");
                    return 10;
                } else {
                    System.out.println("Ticket price: $8");
                    return 8;
                }
            }
            else{
                return firstPart * seat * 10 + secondPart * seat * 8;
            }
        }
        //System.out.println();
    }
    public static void cinemaVie(char[][] cinema, int row, int seat){
        System.out.println("Cinema:");
        for (int i = 0; i < row + 1; i++) {
            for (int j = 0; j < seat + 1; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void chooseSeat(char[][] cinema, Scanner sc, int row, int seat){
        boolean chooseGood = false;
        while (!chooseGood) {
            System.out.println();
            System.out.println("Enter a row number:");
            int chooseRow = sc.nextInt();
            System.out.println("Enter a seat number in that row:");
            int chooseSeat = sc.nextInt();
            if (chooseRow > row || chooseSeat > seat){
                System.out.println("Wrong input!");
                cost(row, seat, chooseRow, "one");
            }
            else {
                if (cinema[chooseRow][chooseSeat] != 'B') {
                    cinema[chooseRow][chooseSeat] = 'B';
                    cost(row, seat, chooseRow, "one");
                    chooseGood = true;
                } else {
                    System.out.println("That ticket has already been purchased!");
                }
            }
        }
    }
    public static void showStatistics(char[][] cimema, int row, int seats){
        int counterSeat = 0;
        int currentIncome = 0;
        for (int i = 0; i <= row; i++){
            for (int j = 0; j <= seats; j++)
                if (cimema[i][j] == 'B') {
                    currentIncome += cost(row, seats, i, "one");
                    counterSeat += 1;
                }
        }
        System.out.println("Number of purchased tickets: " + counterSeat);
        String formattedDouble = String.format("Percentage: %.2f", (float)(counterSeat* 100)/(row*seats));
        System.out.println(formattedDouble + "%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + cost(row,seats,8,"total"));
    }
}
