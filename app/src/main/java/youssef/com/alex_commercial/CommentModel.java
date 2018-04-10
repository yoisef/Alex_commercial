package youssef.com.alex_commercial;

/**
 * Created by mohamed on 22/03/2018.
 */

public class CommentModel {


    public String usercom;
    public String usernom;
    public String userphot;



    public CommentModel() {

    }

    public CommentModel(String comment, String name, String image) {
        this.usercom = comment;
        this.usernom = name;
        this.userphot = image;

    }

    public String getUsercom() {
        return usercom;
    }

    public void setUsercom(String usercom) {
        this.usercom = usercom;
    }

    public String getUsernom() {
        return usernom;
    }

    public void setUsernom(String usernom) {
        this.usernom = usernom;
    }

    public String getUserphot() {
        return userphot;
    }

    public void setUserphot(String userphot) {
        this.userphot = userphot;


    }
}
