import java.util.Scanner;

class Program {
    private static void ret_err(String str) {
        System.err.println(str);
        System.exit(-1);
    }

    private static int get_names(Scanner scan, String[] names) {
        int count = 0;
        for (int i = 0; i < 10; i++, count++) {
            names[i] = scan.nextLine();
            if (names[i].length() > 10) {
                ret_err("Name is long");
            }
            if (names[i].equals(".")) {
                break;
            }
        }
        return count;
    }

    private static void get_timetable(Scanner scan, Boolean[][] timetable_weak) {
        int i = 0;
        String str = "";
        Scanner scan_str;
        int time = 0;
        int day = 0;
        while (i <= 10) {
            str = scan.nextLine();
            if (str.equals(".")) {
                break;
            }
            scan_str = new Scanner(str);
            time = scan_str.nextInt();
            if (time < 1 || time > 5) {
                ret_err("Time is not valid");
            }
            day = parse_days(scan_str.nextLine());
            if (day == -1) {
                ret_err("Day of week is not valid");
            }
            timetable_weak[day][time - 1] = true;
            i++;
        }
    }

    private static int parse_days(String str) {
        return switch (str) {
            case " TU" -> 0;
            case " WE" -> 1;
            case " TH" -> 2;
            case " FR" -> 3;
            case " ST" -> 4;
            case " SU" -> 5;
            case " MO" -> 6;
            default -> -1;
        };
    }

    private static String ret_days(int i) {
        return switch (i) {
            case 0 -> "TU";
            case 1 -> "WE";
            case 2 -> "TH";
            case 3 -> "FR";
            case 4 -> "ST";
            case 5 -> "SU";
            case 6 -> "MO";
            default -> "NAN";
        };
    }

    private static void print_timetable(Boolean[][] week) {
        System.out.print("          ");
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 5; j++) {
                if (week[i % 7][j] != null) {
                    System.out.format("%d:00 %s %2d|", j + 1, ret_days(i % 7), i + 1);
                }
            }
        }
        System.out.println();
    }

    private static int found_id_name(String[] names, String name) {
        int id = -1;
        for (int i = 0; i < 10; i++) {
            if (names[i].equals(name)) {
                id = i;
                break;
            }
        }
        return id;
    }

    private static void get_presence(Scanner scan, Boolean[][][] presence, String[] names) {
        String line = scan.nextLine();
        while (!line.equals(".")) {
            String[] str = line.split(" ");
            line = scan.nextLine();
            if (str.length != 4) {
                ret_err("Illegal input");
            }
            int id_name = found_id_name(names, str[0]);
            int time = Integer.parseInt(str[1]);
            int day = Integer.parseInt(str[2]);
            if (id_name == -1 || day < 1 || day > 30 || time > 5 || time < 1) {
                ret_err("Illegal input");
            }
            if (str[3].equals("HERE")) {
                presence[id_name][day - 1][time - 1] = true;
            } else if (str[3].equals("NOT_HERE")) {
                presence[id_name][day - 1][time - 1] = false;
            } else {
                ret_err("Illegal input");
            }

        }
    }

    private static void print_presence(Boolean[][][] presence, Boolean[][] week, String[] names) {
        int k = 0;
        while (!names[k].equals(".")) {
            System.out.format("%10s", names[k]);
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 5; j++) {
                    if (week[i % 7][j] != null) {
                        if (presence[k][i][j] == null) {
                            System.out.print("          |");
                        } else {
                            int flag = presence[k][i][j] ? 1 : -1;
                            System.out.format("%10d|", flag);
                        }
                    }
                }
            }
            System.out.println();
            k++;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] names = new String[10];
        Boolean[][] timetable_weak = new Boolean[7][5];
        int count_names = get_names(scan, names);
        Boolean[][][] presence = new Boolean[count_names][30][5];
        get_timetable(scan, timetable_weak);
        get_presence(scan, presence, names);
        print_timetable(timetable_weak);
        print_presence(presence, timetable_weak, names);
    }
}

