package Utils;

import model.User;

import javax.servlet.http.HttpServletRequest;

public class Utils {
//    creat test user with context servlet
    public static User creatTestUser( int id,String name,int age){
        User user = new User(name,age,id);
        return user;
    }

//    public static boolean idIsNumb(HttpServletRequest rq){
//
//    }
    public static boolean rqIsValid(HttpServletRequest rq){
        final String name = rq.getParameter("name");
        final String age = rq.getParameter("age");
        return  name !=null && name.length()> 0
                && age!=null && age.length()> 0
                && age.matches("\\d");
    }

}
