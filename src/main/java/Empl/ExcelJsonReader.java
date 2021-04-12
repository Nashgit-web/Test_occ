package main.java.Empl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import com.opencsv.CSVWriter;
import main.java.Empl.CSVEmployee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
public class ExcelJsonReader {
    public static void main(String args[]) throws FileNotFoundException, IOException, ParseException, CsvException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("/Users/RohiniG/Desktop/Employee.json"));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray companyList = (JSONArray) jsonObject.get("emp");
        //System.out.println(companyList);
        ObjectMapper Dom = new ObjectMapper();
        List<CSVEmployee> langList = Dom.readValue(companyList.toString(), new TypeReference<List<CSVEmployee>>(){});
       // System.out.println(langList);
        List<CSVEmployee> beans = new CsvToBeanBuilder<CSVEmployee>(new FileReader("/Users/RohiniG/Desktop/Emp1.csv"))
                .withType(CSVEmployee.class)
                .withSkipLines(1)
                .build()
                .parse();
        beans.addAll(langList);
        TreeSet<CSVEmployee> data = beans.stream()
                .collect(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(CSVEmployee::getSalary)
                                .thenComparing(CSVEmployee::getLastName)
                                .thenComparing(CSVEmployee::getAddress)
                                .thenComparing(CSVEmployee::getAge)
                                .thenComparing(CSVEmployee::getFirstName)

                )));
       data.stream().forEach(s->System.out.println(s.getSalary()));
        writeToCSV(data);

    }


    private static void writeToCSV(TreeSet<CSVEmployee> data) throws IOException {
            CSVWriter csvWriter = new CSVWriter(new FileWriter("new.csv"));
            List<String[]> records = new ArrayList<String[]>();
            records.add(new String[] { "firstName", "lastName", "Address", "Age","Salary" });
            Iterator<CSVEmployee> it = data.descendingIterator();
            while (it.hasNext()) {
                CSVEmployee emp = it.next();
                records.add(new String[] {  emp.getFirstName(), emp.getLastName(),emp.getAddress(), emp.getAge(), emp.getSalary() });
            }
            csvWriter.writeAll(records);
            csvWriter.close();
        }

    }




