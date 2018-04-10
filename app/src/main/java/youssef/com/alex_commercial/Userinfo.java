package youssef.com.alex_commercial;

import com.google.firebase.auth.UserInfo;

/**
 * Created by mohamed on 10/04/2018.
 */

public class Userinfo {
    String name,photo;


    public Userinfo()
    {

    }

  public Userinfo(String nom,String img)
  {
      this.name=nom;
      this.photo=img;

  }
}
