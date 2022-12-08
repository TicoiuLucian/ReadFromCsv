package ro.itschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ro.itschool.controller.model.MyUserModel;
import ro.itschool.csv.helper.CsvHelper;
import ro.itschool.entity.MyUser;
import ro.itschool.repository.MyUserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
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


}
