package nccucs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
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
        long start = System.currentTimeMillis();
        TheService svc = (TheService) appContext.getBean("theService");
        UserService userService = (UserService) appContext.getBean("userService");
        try {
            svc.testTransaction1();
            //svc.testPerformance();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;
            userService.printUsers();
            System.out.println(timeElapsed);
        }
        appContext.close();
    }

    @Transactional
    public void testTransaction1() {
        userService.insertUsers();
        userService.updateUser();
    }

    @Transactional
    public void testPerformance() {
        for (int i = 0; i < 10000; i++) {
            userService.insertUsers();
            userService.updateUser();
        }
    }

}
