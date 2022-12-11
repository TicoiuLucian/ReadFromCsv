package ro.itschool.controller;



import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.MyUser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;


@RestController
public class MyCsvGeneratorController {
     @RequestMapping(value="/generate")
     public void  generateUsers() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {

          try {
               //initialization of Faker
               Faker faker = new Faker();
               //filepath of the csv file
               CSVWriter csvWriter = new CSVWriter(new FileWriter("src/main/resources/users.csv"));
               //write the CSV header
               csvWriter.writeNext(new String[]{"username","identifier","first name","last name"});
               //loop for 1000 users
               for (int i = 1; i <= 1000; i++) {
                    //write username, id, firstName and lastName
                    csvWriter.writeNext(new String[]{
                            faker.name().username(),
                            String.valueOf(faker.number().numberBetween(1,1000)),
                            faker.name().firstName(),
                            faker.name().lastName()
                    });
               }
               //close Writer
               csvWriter.close();
          }
          catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }
}

