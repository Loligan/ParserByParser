

import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;

/**
 * Created by meldon on 04.11.16.
 */
public class Parser {
    public static int parse() throws IOException {
        String csvFile = "/home/meldon/IdeaProjects/ParserByParser/src/main/java/people.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int total = 0;
        try {

            br = new BufferedReader(new FileReader(csvFile),4096);
            int x =0;
            while ((line = br.readLine()) != null) {
                x++;
                String[] people = line.split(cvsSplitBy);

                total++;
            }
            System.out.println("9825 | "+total);

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

public static void checked(Reader r){


}

    public static void main(String[] args) throws IOException {
        Parser.parse();
    }


}
