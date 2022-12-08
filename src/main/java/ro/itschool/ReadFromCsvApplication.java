package ro.itschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReadFromCsvApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadFromCsvApplication.class, args);
    }

}


//Tema Liviu: sa se creeze un endpoint (controller nou) care genereaza un csv cu header
// (username, identifier, first name, last name) si contine 1000 de useri
// Hint: Java faker

//Tema Roxana: MyUserController creaza un enpoint pentru a descarca intr-un fisier csv tot
// ce se afla in tabelul MyUser

//Tema Sebastian: Operatii CRUD pentru un MyUser (primesti de la postman MyUserModel)
//Returnezi catre postman MyUserModel

//Tema Dani: Creaza un scheduler care sterge toate datele din tabela daca au acelasi username
// https://github.com/TicoiuLucian/LoginForSchedulerWithJavaFX

//Tema George: Sa se adauge linii noi la un fisier csv existent
//ai un endpoint care primeste un fisier csv si un MyUserModel
//Acel MyUserModel va trebui adaugat in fisier