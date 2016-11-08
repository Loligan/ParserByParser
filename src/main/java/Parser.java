import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    static int TOTAL = 0;
    static int GOOD = 0;
    static int BAD = 0;
    static String Split = "!!!";
    static boolean GOOD_OR_NO;

    public static int parse() throws IOException {
        String csvFile = "/home/meldon/IdeaProjects/ParserByParser/src/main/java/people.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";


        try {

            br = new BufferedReader(new FileReader(csvFile), 4096);
            int x = 0;
            while ((line = br.readLine()) != null) {
                TOTAL++;
                String[] people = line.split(cvsSplitBy);

                if (checkedName(people)) {
                    if (checkedDate(people) || checkedBPlace(people) || checkedUniversity(people) || checkedUniversityByName(people)|| checkedWord(people)||checkedActivity(people) || checkedMName(people)) {
                        GOOD++;
                    } else {
                            System.out.println(people[0]);
//                        print_people(people);
                        BAD++;
                    }
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

    public static boolean checkedDate(String[] r) {
        if (r[4].contains(Split)) {
            String date[] = r[4].split(Split);
            if (date[0].equals(date[1]))
                return true;
        } else {
            return false;
        }
        return false;
    }

    public static boolean checkedName(String[] r) {
        try {
            String names[] = r[2].split(Split);
            String nameArr1[] = names[0].split(" ");
            String nameArr2[] = names[1].split(" ");

            for (int i = 0; i < nameArr1.length; i++) {
                for (int j = 0; j < nameArr2.length; j++) {
                    if (nameArr1[i].toUpperCase().equals(nameArr2[j].toUpperCase())) ;
                    return true;
                }

            }
            return false;

        } catch (Exception e) {
            System.out.println(r[0]);
            return false;
        }
    }

    public static boolean checkedBPlace(String[] r) {
        try {
            if (r[5].contains(Split)) {
                String place[] = r[5].split(Split);
                if (place[0].equals(place[1])) {
                    return true;
                } else {
                    Pattern MY_PATTERN = Pattern.compile("([\\w]{1,})");
                    Matcher m = MY_PATTERN.matcher(place[0]);
                    m.find();
                    if (place[1].toUpperCase().contains(m.group().toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;

    }

    public static boolean checkedUniversity(String[] r) {
        try {
            if (r[7].contains(Split)) {
                String university[] = r[7].split(Split);
                Pattern MY_PATTERN = Pattern.compile("([0-9]{4})");
                Matcher m = MY_PATTERN.matcher(university[1]);
                boolean b = false;
                while (m.find()) {
                    if (university[0].toUpperCase().contains(m.group().toUpperCase())) {
                        b = true;
                    }
                }
                if (b) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkedUniversityByName(String[] r) {
        try {
            if (r[7].contains(Split)) {
                String university[] = r[7].split(Split);
                Pattern MY_PATTERN = Pattern.compile("(?:University of\\s|University\\s)([\\w]*)");
                Matcher m = MY_PATTERN.matcher(university[1]);
                boolean b = false;
                while (m.find()) {
                    if (university[0].toUpperCase().contains(m.group(1).toUpperCase())) {
                        b = true;
                    }
                }
                if (b) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }




    public static boolean checkedWord(String[] r){
        try {
            if (r[8].contains(Split)) {
                String university[] = r[8].split(Split);
                Pattern MY_PATTERN = Pattern.compile("([0-9]{4})");
                Matcher m = MY_PATTERN.matcher(university[1]);
                boolean b = false;
                while (m.find()) {
                    if (university[0].toUpperCase().contains(m.group().toUpperCase())) {
                        b = true;
                    }
                }
                if (b) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean checkedActivity(String[] r){
        try {
            if (r[6].contains(Split)) {
                String activity[] = r[6].split(Split);
                Pattern MY_PATTERN = Pattern.compile("([\\w]*)");
                Matcher m = MY_PATTERN.matcher(activity[1]);
                boolean b = false;
                m.find();
                    if (activity[0].toUpperCase().contains(m.group().toUpperCase())) {
                       return true;
                    }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    public static boolean checkedMName(String[] r){
        try {
            if(r[3].toUpperCase()!="NULL"){
                if(r[2].toUpperCase().contains(r[3].toUpperCase())){
                    return true;
                }else {
                    return false;
                }
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        Parser.parse();
    }

}
