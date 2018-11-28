package DAO;
import model.Moderator;

import java.util.ArrayList;
import java.util.List;

public class Dao implements IDao {

    private List<Moderator> userList = new ArrayList<>();
    @Override
    public Moderator getUserByID(int id) {
        Moderator res = new Moderator();
        res.setId(-1);
        for (Moderator user : userList){
            if (user.getId() == id){
                return user;
            }
        }
        return res;
    }

    @Override
    public Moderator getUserByLoginPasword(String login, String password) {
        Moderator res = new Moderator();
        res.setId(-1);
        for (Moderator user : userList){
            if(user.getLogin().equals(login)&& user.getPassword().equals(password)){
                return user;
            }
        }
        return res;
    }

    @Override
    public Moderator.ROLE getRoleUser(String login, String password) {
       Moderator.ROLE unknown = Moderator.ROLE.UNKNOWN;
       for(Moderator user : userList){
           if (user.getLogin().equals(login)&& user.getPassword().equals(password)){
               return user.getRole();

           }
       }
       return unknown;
    }

    @Override
    public Boolean add(Moderator moderator) {
       for (Moderator user : userList){
           if(user.getLogin().equals(moderator)){
               return false;
           }
       }
       return userList.add(moderator);
    }

    @Override
    public Boolean userIsExist(String login, String password) {
        Boolean res = false;
        for(Moderator user : userList){
            if (user.getLogin().equals(login) && user.getPassword().equals(password)){
                res = true;
            }
                break;
        }
        return res;
    }

}
