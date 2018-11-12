package Utils;

import model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Utils {
//    creat test user with context servlet
    public static User creatTestUser( int id,String name,int age){
        User user = new User(name,age,id);
        return user;
    }

    public static boolean idIsNumber(HttpServletRequest rq){
       final String id = rq.getParameter("id");
        return (id!=null)&& id.matches("\\d");
    }


    public static boolean rqIsValid(HttpServletRequest rq){
            final String name = rq.getParameter("name");
            final String age = rq.getParameter("age");
            return  name != null && name.length()> 0
                    && age != null && age.length()> 0
                    && age.matches("\\d");
    }

    public static boolean rqIsInValid(final String id, Map<Integer,User> users){
        return !(id != null && id.matches("\\d")
                            && users.get(Integer.valueOf(id))!= null);
    }

}
