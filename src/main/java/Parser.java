

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by meldon on 04.11.16.
 */
public class Parser {
    static int TOTAL = 0;
    static int GOOD = 0;
    static int BAD = 0;
    static String Split = "!!!";
    static boolean GOOD_OR_NO;

    public static int parse() throws IOException {
        String csvFile = "C:\\Users\\meldon\\IdeaProjects\\ParserByParser\\src\\main\\java\\people.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";


        try {

            br = new BufferedReader(new FileReader(csvFile), 4096);
            int x = 0;
            while ((line = br.readLine()) != null) {
                GOOD_OR_NO = false;
                x++;
                String[] people = line.split(cvsSplitBy);
                checkedDate(people);

                if (!GOOD_OR_NO) {
                    System.out.println(people[0]);
                    BAD++;
                } else {
                    GOOD++;
                }
            }
            System.out.println(TOTAL + " | " + GOOD + " | " + BAD);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }

    public static void print_people(String[] people) {
        System.out.println(
                people[0] + "\n" +
                        people[1] + "\n" +
                        people[2] + "\n" +
                        people[3] + "\n" +
                        people[4] + "\n" +
                        people[5] + "\n" +
                        people[6] + "\n" +
                        people[7] + "\n" +
                        people[8] + "\n" +
                        people[9] + "\n"
        );
    }

    public static void checkedDate(String[] r) {

        TOTAL++;
        if (r[4].contains(Split)) {
            String date[] = r[4].split(Split);
            if (date[0].equals(date[1]))
                checkedName(r);
        } else {
            checkedBPlace(r);
        }
    }

    public static void checkedName(String[] r) {
        String names[] = r[2].split(Split);
        String nameArr1[] = names[0].split(" ");
        String nameArr2[] = names[1].split(" ");

        for (int i = 0; i < nameArr1.length; i++) {
            for (int j = 0; j < nameArr2.length; j++) {
                if (nameArr1[i].equals(nameArr2[j])) ;
                GOOD_OR_NO = true;
            }

        }
        if (GOOD_OR_NO) {
            GOOD_OR_NO = true;
        } else {
            GOOD_OR_NO = false;
        }
    }

    public static void checkedBPlace(String[] r) {
        try {
            if (r[5].contains(Split)) {
                String place[] = r[5].split(Split);
                if (place[0].equals(place[1])) {
                    System.out.println("1");
                    System.out.println(place[0]+"|"+place[1]);
                    GOOD_OR_NO = true;
                } else {
                    String[] placeOne = null;
                    String[] placeTwo = null;

                    if (place[0].contains(",")) {
                        System.out.println("2");
                        placeOne = place[0].split(",");
                    }
                    if (place[0].contains(" ")) {
                        System.out.println("3");
                        placeOne = place[0].split(" ");
                    } else {
                        placeOne[0] = place[0];
                    }

                    if (place[1].contains(",")) {
                        System.out.println("4");
                        placeTwo = place[1].split(",");
                    }
                    if (place[1].contains(" ")) {
                        System.out.println("5");
                        placeTwo = place[1].split(" ");
                    } else {
                        System.out.println("6");
                        placeTwo[1] = place[1];
                    }

                    if (placeOne[0].equals(placeTwo[0])) {
                        System.out.println("7");
                        GOOD_OR_NO = true;
                    }else {
                        System.out.println(placeOne[0]+"       "+placeTwo[0]);
                    }
                }
            } else {
                System.out.println("else");
                checkedUniversity(r);
            }

        } catch (Exception e) {
            checkedUniversity(r);
        }

    }

    public static void checkedUniversity(String[] r) {
        try {
            if (r[7].contains(Split)) {
                String university[] = r[7].split(Split);
                Pattern MY_PATTERN = Pattern.compile("([0-9]{4})");
                Matcher m = MY_PATTERN.matcher(university[1]);

                if(university[2].contains(m.group(1))){
                    GOOD_OR_NO = true;
                }
            } else {
                GOOD_OR_NO = false;
            }
        } catch (Exception e) {
            GOOD_OR_NO = false;
        }
    }


    public static void main(String[] args) throws IOException {
        Parser.parse();
        System.out.println();
    }


}
