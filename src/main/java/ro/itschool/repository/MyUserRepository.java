package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.MyUser;

import java.io.InputStream;

public interface MyUserRepository extends JpaRepository<MyUser, Long> {
    InputStream load();
}
