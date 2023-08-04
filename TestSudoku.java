import java.util.*;
import java.lang.*;
import java.io.*;

class TestSudoku {
    public static void main(String args[]) {
        Sudoku s = new Sudoku();
        Scanner sc = new Scanner(System.in);
        Stopwatch1 stopwatch1 = new Stopwatch1();
        int m, k, c, l = 0, level = 0;
        boolean b;
        String filepath = "..\\highScore.txt"; /*set file path */
        double HighScore[] = new double[5];
        double time;
        String line;
        System.out.println("\n\n-------------------------------------------------------------");
        System.out.println("                         SUDOKU GAME");
        System.out.println("-------------------------------------------------------------");
        File f = new File(filepath);

        if (f.exists() == false) {
            try {
                FileWriter fw = new FileWriter(filepath);
                BufferedWriter bw = new BufferedWriter(fw);
                f.createNewFile();
                bw.write("0\n0\n0\n0\n0");
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            while ((line = br.readLine()) != null) {
                HighScore[level] = Double.parseDouble(line);
                level++;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainMenu: {
            while (true) {
                System.out.println("-------------------------------------------------------------");
                System.out.println("                         1. PLAY GAME");
                System.out.println("                         2. SOLVE SUDOKU");
                System.out.println("                         3. VIEW HIGH SCORE");
                System.out.println("                         4. Exit");
                System.out.println("-------------------------------------------------------------");
                System.out.print("                     --->>Enter choice : ");
                k = sc.nextInt();
                if (k == 4)
                    break;
                switch (k) {
                case 1: {
                    s.showLevels();
                    c = sc.nextInt();
                    switch (c) {
                    case 1:
                        s.sudoku(20);
                        level = 1;
                        break;
                    case 2:
                        s.sudoku(25);
                        level = 2;
                        break;
                    case 3:
                        s.sudoku(30);
                        level = 3;
                        break;
                    case 4:
                        s.sudoku(35);
                        level = 4;
                        break;
                    case 5:
                        s.sudoku(40);
                        level = 5;
                        break;
                    default:
                        break;
                    }
                    s.generate();
                    /*System.out.println("\n");
                    s.printAns();
                    System.out.println("\n");*/
                    stopwatch1.start();
                    s.getAns();
                    stopwatch1.stop();
                    b = s.compare();
                    System.out.println("      YOU HAVE TAKEN " + String.format("%.2f", stopwatch1.getElapsedSeconds())
                            + " SECONDS TO SOLVE THIS SUDOKU");
                    time = stopwatch1.getElapsedSeconds();

                    if ((b == true && time < HighScore[level - 1]) || (b == true && HighScore[level - 1] == 0)) {
                        System.out.println("-------------------------------------------------------------");
                        System.out.println("                       NEW HIGH SCORE");
                        System.out.println("-------------------------------------------------------------");
                        HighScore[level - 1] = time;
                        try {
                            FileWriter fw = new FileWriter(filepath);
                            BufferedWriter bw = new BufferedWriter(fw);
                            line = String.valueOf(HighScore[0]) + "\n" + String.valueOf(HighScore[1]) + "\n"
                                    + String.valueOf(HighScore[2]) + "\n" + String.valueOf(HighScore[3]) + "\n"
                                    + String.valueOf(HighScore[4]);
                            bw.write(line);
                            bw.close();
                            fw.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    ExitPlayGame: {
                        while (true) {
                            while (b) {
                                System.out.println("-------------------------------------------------------------");
                                System.out.println("             1.Generate another question");
                                System.out.println("             2. Go Back To Main Menu");
                                System.out.println("             3.Exit");
                                System.out.print("           --->>Enter choice : ");
                                m = sc.nextInt();
                                System.out.println("-------------------------------------------------------------");
                                switch (m) {
                                case 1: {
                                    s.showLevels();
                                    c = sc.nextInt();
                                    switch (c) {
                                    case 1:
                                        s.sudoku(20);
                                        level = 1;
                                        break;
                                    case 2:
                                        s.sudoku(25);
                                        level = 2;
                                        break;
                                    case 3:
                                        s.sudoku(30);
                                        level = 3;
                                        break;
                                    case 4:
                                        s.sudoku(35);
                                        level = 4;
                                        break;
                                    case 5:
                                        s.sudoku(40);
                                        level = 5;
                                        break;
                                    default:
                                        break;
                                    }
                                    s.generate();
                                    stopwatch1.start();
                                    s.getAns();
                                    stopwatch1.stop();
                                    b = s.compare();
                                    System.out.println("      YOU HAVE TAKEN " + String.format("%.2f", stopwatch1.getElapsedSeconds())
                                                    + " SECONDS TO SOLVE THIS SUDOKU");
                                    time = stopwatch1.getElapsedSeconds();
                                    if ((b == true && time < HighScore[level - 1]) || (b == true && HighScore[level - 1] == 0)) {
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println("                       NEW HIGH SCORE");
                                        System.out.println("-------------------------------------------------------------");
                                        HighScore[level - 1] = time;
                                        try {
                                            FileWriter fw = new FileWriter(filepath);
                                            BufferedWriter bw = new BufferedWriter(fw);
                                            line = String.valueOf(HighScore[0]) + "\n" + String.valueOf(HighScore[1])
                                                    + "\n" + String.valueOf(HighScore[2]) + "\n"
                                                    + String.valueOf(HighScore[3]) + "\n"
                                                    + String.valueOf(HighScore[4]);
                                            bw.write(line);
                                            bw.close();
                                            fw.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    break;
                                }
                                case 2:
                                    break ExitPlayGame;
                                case 3:
                                    break MainMenu;
                                default:
                                    break;
                                }
                            }
                            while (b == false) {
                                System.out.println("-------------------------------------------------------------");
                                System.out.println("             1.Do you want to see answer");
                                System.out.println("             2.Try Again");
                                System.out.println("             3.Generate another question");
                                System.out.println("             4. Go Back To Main Menu");
                                System.out.println("             5.Exit");
                                System.out.print("           --->>Enter choice : ");
                                m = sc.nextInt();
                                System.out.println("-------------------------------------------------------------");
                                switch (m) {
                                case 1: {
                                    System.out.println("-------------------------------------------------------------");
                                    System.out.println("                  Answer of Sudoku Puzzle");
                                    System.out.println("-------------------------------------------------------------");
                                    s.printAns();
                                    break;
                                }
                                case 2: {
                                    stopwatch1.start();
                                    s.getAns();
                                    stopwatch1.stop();
                                    b = s.compare();
                                    System.out.println("      YOU HAVE TAKEN " + String.format("%.2f", stopwatch1.getElapsedSeconds())
                                                    + " SECONDS TO SOLVE THIS SUDOKU");
                                    time = stopwatch1.getElapsedSeconds();
                                    if ((b == true && time < HighScore[level - 1]) || (b == true && HighScore[level - 1] == 0)) {
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println("                       NEW HIGH SCORE");
                                        System.out.println("-------------------------------------------------------------");
                                        HighScore[level - 1] = time;
                                        try {
                                            FileWriter fw = new FileWriter(filepath);
                                            BufferedWriter bw = new BufferedWriter(fw);
                                            line = String.valueOf(HighScore[0]) + "\n" + String.valueOf(HighScore[1])
                                                    + "\n" + String.valueOf(HighScore[2]) + "\n"
                                                    + String.valueOf(HighScore[3]) + "\n"
                                                    + String.valueOf(HighScore[4]);
                                            bw.write(line);
                                            bw.close();
                                            fw.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    break;
                                }
                                case 3: {
                                    s.showLevels();
                                    c = sc.nextInt();
                                    switch (c) {
                                    case 1:
                                        s.sudoku(20);
                                        level = 1;
                                        break;
                                    case 2:
                                        s.sudoku(25);
                                        level = 2;
                                        break;
                                    case 3:
                                        s.sudoku(30);
                                        level = 3;
                                        break;
                                    case 4:
                                        s.sudoku(35);
                                        level = 4;
                                        break;
                                    case 5:
                                        s.sudoku(40);
                                        level = 5;
                                        break;
                                    default:
                                        break;
                                    }
                                    s.generate();
                                    stopwatch1.start();
                                    s.getAns();
                                    stopwatch1.stop();
                                    b = s.compare();
                                    System.out.println("      YOU HAVE TAKEN " + String.format("%.2f", stopwatch1.getElapsedSeconds())
                                                    + " SECONDS TO SOLVE THIS SUDOKU");
                                    time = stopwatch1.getElapsedSeconds();
                                    if ((b == true && time < HighScore[level - 1]) || (b == true && HighScore[level - 1] == 0)) {
                                        System.out.println("-------------------------------------------------------------");
                                        System.out.println("                       NEW HIGH SCORE");
                                        System.out.println("-------------------------------------------------------------");
                                        HighScore[level - 1] = time;
                                        try {
                                            FileWriter fw = new FileWriter(filepath);
                                            BufferedWriter bw = new BufferedWriter(fw);
                                            line = String.valueOf(HighScore[0]) + "\n" + String.valueOf(HighScore[1])
                                                    + "\n" + String.valueOf(HighScore[2]) + "\n"
                                                    + String.valueOf(HighScore[3]) + "\n"
                                                    + String.valueOf(HighScore[4]);
                                            bw.write(line);
                                            bw.close();
                                            fw.close();
                                        } catch (IOException e) {  
                                            e.printStackTrace();
                                        }
                                    }
                                    break;
                                }
                                case 4:
                                    break ExitPlayGame;
                                case 5:
                                    break MainMenu;
                                default:
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    s.getQue();
                    s.find(0, 0);
                    System.out.println("-------------------------------------------------------------");
                    System.out.println("                            Answer");
                    System.out.println("-------------------------------------------------------------");
                    s.printQueAns();
                    break;
                }
                case 3: {
                    try {
                        FileReader fr = new FileReader(filepath);
                        BufferedReader br = new BufferedReader(fr);
                        l = 0;
                        while ((line = br.readLine()) != null) {
                            switch (l) {
                            case 0:
                                System.out.print("VERY EASY : ");
                                break;
                            case 1:
                                System.out.print("EASY      : ");
                                break;
                            case 2:
                                System.out.print("MEDIUM    : ");
                                break;
                            case 3:
                                System.out.print("HARD      : ");
                                break;
                            case 4:
                                System.out.print("VERY HARD : ");
                                break;
                            default:
                                break;
                            }
                            System.out.println(line);
                            l++;
                        }
                        br.close();
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default:
                    break;
                }
            }
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("                     THANK YOU FOR PLAYING");
        System.out.println("-------------------------------------------------------------");
        sc.close();
    }
}

class Sudoku {
    int a[][] = new int[9][9];
    int ans[][] = new int[9][9];
    int q[][] = new int[9][9];
    int temp[][] = new int[9][9];
    int s[][] = new int[9][9];
    int n = 9, MissingDigits;
    Scanner sc = new Scanner(System.in);

    public void showInstruction(){
        System.out.println("\nPlease enter sudoku like this ---> 1 2 3 4 5 6 7 8 9 ");
    }
    public void sudoku(int level) {
        MissingDigits = level;
    }

    public void showLevels() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("                            LEVEL");
        System.out.println("                         1. VERY EASY");
        System.out.println("                         2. EASY");
        System.out.println("                         3. MEDIUM");
        System.out.println("                         4. HARD");
        System.out.println("                         5. VERY HARD");
        System.out.println("-------------------------------------------------------------");
        System.out.print("                    --->>Enter choice : ");
    }

    public void generate() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                a[i][j] = 0;
            }
        }
        fillValues();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                ans[i][j] = a[i][j];
            }
        }
        remove();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                q[i][j] = a[i][j];
            }
        }
        // Method to print partially filled grid
        System.out.println("-------------------------------------------------------------");
        System.out.println("              Partially Generated Sudoku Puzzle");
        System.out.println("-------------------------------------------------------------");
        prints(q);
        showInstruction();
    }

    public void getAns() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("                         Enter Answer");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                temp[i][j] = sc.nextInt();
            }
        }
    }

    public void getQue() {
        System.out.println("\nFill Empty space with Zero");
        System.out.println("\nPlease enter sudoku like this ---> 1 0 3 4 5 0 7 8 9 ");
        System.out.println("-------------------------------------------------------------");
        System.out.println("                       Enter Question");
        System.out.println("-------------------------------------------------------------");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                s[i][j] = sc.nextInt();
            }
        }
    }

    public boolean compare() {
        if (Arrays.deepEquals(ans, temp)) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("                        Right Answer");
            System.out.println("-------------------------------------------------------------");
            return true;
        } else {
            System.out.println("-------------------------------------------------------------");
            System.out.println("                        Wrong Answer");
            System.out.println("-------------------------------------------------------------");
            return false;
        }
    }

    public void printAns() {
        prints(ans);
    }

    public void printQueAns() {
        prints(s);
    }

    public void fillValues() {
        // Fill the diagonal of 3X3 matrices
        fillvaluesinDiagonal();
        // Fill remaining grids
        remaining(0, 3);
    }

    // Fill the diagonal of 3X3 matrices
    void fillvaluesinDiagonal() {
        for (int i = 0; i < n; i = i + 3) {
            fillvaluesinMatrix(i, i);
        }
    }

    // Fill a 3X3 matrix.
    void fillvaluesinMatrix(int row, int col) {
        int num;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                do {
                    num = (int) Math.floor((Math.random() * n + 1));
                } while (!checkMatrix(row, col, num));
                a[row + i][col + j] = num;
            }
        }
    }

    // Method to check whether it follows rules to put in cell
    boolean check(int i, int j, int num) {
        return (checkRow(i, num) && checkColumn(j, num) && checkMatrix(i - i % 3, j - j % 3, num));
    }

    // Method to check whether it follows rules to put in row
    boolean checkRow(int i, int num) {
        for (int j = 0; j < n; j++) {
            if (a[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    // Method to check whether it follows rules to put in column
    boolean checkColumn(int j, int num) {
        for (int i = 0; i < n; i++) {
            if (a[i][j] == num) {
                return false;
            }
        }
        return true;
    }

    // Returns false if given 3 x 3 block contains number.
    boolean checkMatrix(int StartingOfRow, int StartingOfColumn, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[StartingOfRow + i][StartingOfColumn + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Method to fill remaining 3X3 matrices
    boolean remaining(int i, int j) {
        if (j >= n && i < n - 1) {
            i = i + 1;
            j = 0;
        }
        if (i >= n && j >= n) {
            return true;
        }
        if (i < 3) {
            if (j < 3) {
                j = 3;
            }
        } else if (i < n - 3) {
            if (j == (int) (i / 3) * 3) {
                j = j + 3;
            }
        } else {
            if (j == n - 3) {
                i = i + 1;
                j = 0;
                if (i >= n) {
                    return true;
                }
            }
        }
        for (int num = 1; num <= n; num++) {
            if (check(i, j, num)) {
                a[i][j] = num;
                if (remaining(i, j + 1)) {
                    return true;
                }
                a[i][j] = 0;
            }
        }
        return false;
    }

    public void remove() {
        int count = MissingDigits;
        while (count != 0) {
            int c = (int) Math.floor((Math.random() * (n * n) + 1));
            int i = (c / n);
            int j = (c % 9);
            if (j != 0) {
                j--;
            }
            if (i >= 9 || j >= 9) {
                continue;
            }
            if (a[i][j] != 0) {
                count--;
                a[i][j] = 0;
            }
        }
    }

    // Method to solve partially filled grid and assign values to all unassigned
    // locations in such a way that
    // They meet the requirements for Sudoku solution(no same numbers in that
    // particular rows, columns and boxes)
    boolean find(int row, int col) {
        if (row == n - 1 && col == n) {
            return true;
        }
        if (col == n) {
            row++;
            col = 0;
        }
        if (s[row][col] != 0) {
            return find(row, col + 1);
        }
        for (int num = 1; num < 10; num++) {
            if (checks(s, row, col, num)) {
                s[row][col] = num;
                if (find(row, col + 1)) {
                    return true;
                }
            }
            s[row][col] = 0;
        }
        return false;
    }

    // check whether generated value in particular row and column is right or not.
    boolean checks(int[][] s, int row, int col, int k) {
        // Check if the same generated number is found in the similar row then return
        // false
        for (int i = 0; i <= 8; i++) {
            if (s[row][i] == k) {
                return false;
            }
        }
        // Check if the same generated number is found in the similar column then return
        // false
        for (int i = 0; i <= 8; i++) {
            if (s[i][col] == k) {
                return false;
            }
        }
        // Check if the same generated number is found in that particular 3X3 matrix
        // then return false
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (s[i + row - (row % 3)][j + col - (col % 3)] == k) {
                    return false;
                }
            }
        }
        // else return true,
        return true;
    }

    // Method to print sudoku.
    void prints(int x[][]) {
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                System.out.println("---------------------");

            }
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    System.out.print("|");
                }
                if (j != 8) {
                    if (x[i][j] != 0) {
                        System.out.print(x[i][j] + " ");
                    } else {
                        System.out.print("_ ");
                    }
                } else {
                    System.out.print(x[i][j]);
                }
                if (j == 2 || j == 5 || j == 8) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i == 2 || i == 5 || i == 8) {
                System.out.println("---------------------");
            }
        }
    }
}

class Stopwatch1 {
    private final double nanoSecondsPerSecond = 1000000000;
    private double stopWatchStartTime = 0;
    private double stopWatchStopTime = 0;
    private boolean stopWatchRunning = false;

    public void start() {
        this.stopWatchStartTime = System.nanoTime();
        this.stopWatchRunning = true;
    }

    public void stop() {
        this.stopWatchStopTime = System.nanoTime();
        this.stopWatchRunning = false;
    }

    public double getElapsedSeconds() {
        double elapsedTime;

        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - stopWatchStartTime);
        else
            elapsedTime = (stopWatchStopTime - stopWatchStartTime);

        return elapsedTime / nanoSecondsPerSecond;
    }
}
