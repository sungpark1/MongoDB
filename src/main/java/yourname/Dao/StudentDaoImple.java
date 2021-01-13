package yourname.Dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import yourname.Entity.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Qualifier("version0")
public class StudentDaoImple implements StudentDao {


    private static Map<Integer, Student> students;

    static {
        students = new HashMap<>() {
            {
                put( 1, new Student( 1, "Said", "Computer Science" ) );
                put( 2, new Student( 2, "Alex", "Finance" ) );
                put( 3, new Student( 3, "Anny", "Math" ) );
            }
        };
    }

    @Override
    public Collection<Student> getAllStudents() {
        return this.students.values();
    }

    @Override
    public Student getStudentById(int id) {
        return this.students.get( id );
    }

    @Override
    public void removeStudentById(int id) {
        this.students.remove( id );
    }

    @Override
    public void updateStudent(Student student) {
        Student s = students.get( student.getId() );
        s.setCourse( student.getCourse() );
        s.setName( student.getName() );
        students.put( student.getId(), student );
    }

    @Override
    public void insertStudentToDb(Student student) {
        this.students.put(student.getId(), student);
    }

    /**
     * Below is the test
     * NEED TO SOLVE IMPLEMENT AND EXTEND
     */
    public void insertTest(Student student){
        Student s = students.get( student.getId());
    }



    /**
     Create a JUnit test

     class AccountDaoTest {
     AccountDao accountDao;

     @Test
     void testInsert() {
     AccountMongoEntity acct = new AccountMonogoEntity();
     acct.setEmail("haha@gmail");



     accountDao.insertAccount(acct);
     }
     }
     */

//    @PostMapping("create")
//    public String create(
//            //body -> JsonNode or String
//    ) {
//        Option 1.
//       parse JsonNode or String into AccountMongoEntity
//        AccountMongoEntity account = (convert body)
//        return accountService.create(account);

//        Option 2.
//        Take JsonNode
//        return accountService.create(body);
}
