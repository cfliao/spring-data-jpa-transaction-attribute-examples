package nccucs;

import nccucs.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("theService")
public class TheService {
    private static final Logger log = LoggerFactory.getLogger(TheService.class);
    //    @Autowired
//    private GradeRepository gradeRepository;

    @Autowired
    UserService userService;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("nccucs");
        appContext.refresh();

        TheService svc = (TheService) appContext.getBean("theService");
        UserService userService = (UserService) appContext.getBean("userService");
        try {
            svc.testTransaction1();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            userService.printUsers();
        }

        appContext.close();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void testTransaction1() {
        userService.insertUsers();
        userService.updateUser();
    }

}
