package DAO;

import model.Moderator;

public interface IDao <T> {

    T getUserByID(int Id);

    T getUserByLoginPasword(String login,String passworsd);

    T getRoleUser(String login, String password);

    T add(Moderator moderator);

    T userIsExist(String login,String password);


}
