package ro.itschool.csv.helper;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.web.multipart.MultipartFile;
import ro.itschool.controller.model.MyUserModel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class CsvHelper {

    public static List<MyUserModel> getAllUsers(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CsvToBean csvReader = new CsvToBeanBuilder(reader)
                .withType(MyUserModel.class)
                .withSeparator(',')
                .withIgnoreLeadingWhiteSpace(true)
                .withIgnoreEmptyLine(true)
                .withSkipLines(1) //da skip la header
                .build();
        return csvReader.parse();
    }
}
