package Classes;

import java.sql.*;

import static Servlets.ListOfServices.services;

/**
 * Created by damir on 28.10.16.
 */
public class Service {
    String name;
    int cost;
    int number;
    String description;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setNumber(){
        this.number =services.indexOf(this)+1;
    }
    public int getNumber(){
        return number;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setCost(int cost){
        this.cost = cost;
    }
    public int getCost(){
        return cost;
    }

    public int getId(String name, String cost){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/clinic";
            String login = "admin";
            String passwordd = "zub";
            Connection con = DriverManager.getConnection(url, login, passwordd);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM services");
            while (resultSet.next()){
                if (getName().equals(resultSet.getString("name"))&&getCost() == (Integer.parseInt(resultSet.getString("cost")))){
                  return resultSet.getInt("id");
                }


            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
