package yourname.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import yourname.Entity.Student;
import yourname.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById( id );
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable("id") int id) {
        studentService.removeStudentById( id );
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent( student );
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertStudent(@RequestBody Student student) {
        studentService.insertStudent( student );
    }
    /**
     * Below is the test
     */
    @RequestMapping("create")
    public void create(@RequestBody User user) throws IOException {
        String jsonStr = Files.readString(Path.of("user.json"), StandardCharsets.US_ASCII);

//        URL jsonUrl = new URL("localhost:8080/students");
//        String jsonStr = "{\"studentID\":{\"5\",\"course\":\"Computer\",\"name\":\"Brian\"}";


    }
}
