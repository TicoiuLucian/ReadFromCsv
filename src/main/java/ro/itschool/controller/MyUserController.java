package ro.itschool.controller;


import jakarta.annotation.Resource;
import org.hibernate.FetchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ro.itschool.controller.model.MyUserModel;
import ro.itschool.csv.helper.CsvHelper;
import ro.itschool.entity.MyUser;
import ro.itschool.repository.MyUserRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/upload")
public class MyUserController {

    @Autowired
    private MyUserRepository myUserRepository;

    @PostMapping
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {

        // 1.trimitem fisierul csv din postman
        // 2.transformam fisierul csv in lista de obiecte (model)
        // 3.transformam fiecare model in entitate
        // 4.salvam entitatea in db

        try {
            List<MyUser> myUserList = CsvHelper.getAllUsers(file).stream()
                    .map(MyUserModel::toEntity)
                    .collect(Collectors.toList());

            myUserRepository.saveAll(myUserList);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }

    /* @GetMapping("/download")
    public ResponseEntity getFile() {
        String filename = "username.csv";
        InputStreamResource file = new InputStreamResource(myUserRepository.load());

        return (ResponseEntity) ResponseEntity.ok();
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }*/
   /* @GetMapping(value = "/exportCSV", produces = "text/csv")
    public ResponseEntity<Resource> exportCSV() {
        String[] csvHeader = {
                "Username", "Identifier", "First name", "Last name"
        };
        List<List<String>> csvBody = new ArrayList<>();
        csvBody.add(Arrays.asList("booker12", "9012", "Rachel", "Booker"));
        csvBody.add(Arrays.asList("grey07", "2070", "Laura", "Grey"));
        csvBody.add(Arrays.asList("johnson81", "4081", "Craig", "Johnson"));
        csvBody.add(Arrays.asList("jenkins46", "9346", "Mary", "Jenkins"));
        csvBody.add(Arrays.asList("smith79", "5079", "Jamie", "Smith"));

        ByteArrayInputStream byteArrayOutputStream;

        FetchMode CSVFormat;
        CSVFormat = null;
        try (
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                // defining the CSV printer
                CsvPrinter csvPrinter = new CsvPrinter(
                        new PrintWriter(out),
                        // withHeader is optional
                        CSVFormat.DEFAULT.withHeader(csvHeader)
                );
        ) {
            // populating the CSV content
            for (List<String> record : csvBody)
                csvPrinter.printRecord(record);

            // writing the underlying stream
            csvPrinter.flush();

            byteArrayOutputStream = new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        InputStreamResource fileInputStream = new InputStreamResource(byteArrayOutputStream);

        String csvFileName = "username.csv";

        // setting HTTP headers
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + csvFileName);
        // defining the custom Content-Type
        headers.set(HttpHeaders.CONTENT_TYPE, "text/csv");

        return new ResponseEntity<>( fileInputStream, headers, HttpStatus.OK);
    } */
}