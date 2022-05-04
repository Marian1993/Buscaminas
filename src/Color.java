public class Color {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void assignarColors(int num){

        switch (num){

            case 1:
                System.out.print(ANSI_BLUE + num + ANSI_RESET);
                break;
            case 2:
                System.out.print(ANSI_GREEN + num + ANSI_RESET);
                break;
            case 3:
                System.out.print(ANSI_PURPLE + num + ANSI_RESET);
                break;
            case 4:
                System.out.print(ANSI_YELLOW + num + ANSI_RESET);
                break;
            case 5:
                System.out.print(ANSI_CYAN + num + ANSI_RESET);
                break;
            case 6:
                System.out.print(ANSI_BLACK + num + ANSI_RESET);
                break;
            case 7:
            case 8:
                System.out.print(ANSI_RED + num + ANSI_RESET);
                break;
        }

    }
}
