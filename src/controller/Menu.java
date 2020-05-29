/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author lenovo
 */
public class Menu {
    
    public int id;
    public String food_name;
    public String food_price;
    public String food_type;
    
    public Menu(int id, String food_name, String food_price, String food_type){
        this.id = id;
        this.food_name = food_name;
        this.food_price = food_price;
        this.food_type = food_type;
    }
    
    public ArrayList<Menu> m_array;
    
     public static Connection getConnection()throws Exception{
       
       try{
           String driver = "com.mysql.jdbc.Driver";
           String url = "jdbc:mysql://localhost:3306/menu";
           String username = "root";
           String password = "root";
           Class.forName(driver);
           
           Connection conn = DriverManager.getConnection(url, username, password);
           System.out.println("Connected");
           return conn;
       }catch(Exception e){
           System.out.println("ERRRRROOOOOOOORRRRRRRRRRRRRR" + e);
       }
       
       return null;
   }
    
      public static ArrayList<Menu> get() throws Exception {
       
       try{
           Connection con = getConnection();
           PreparedStatement statement = con.prepareStatement("SELECT id,food_name,food_price,food_type FROM menu.menus");
           ResultSet result = statement.executeQuery();
           System.out.println(result);
           ArrayList<Menu> array = new ArrayList<Menu>();
           while(result.next()){
               int id = result.getInt("id");
               String food_name = result.getString("food_name");
               String food_price = result.getString("food_price");
               String food_type = result.getString("food_type");
               
               Menu m = new Menu(id,food_name,food_price,food_type);
               
               array.add(m);
           }
           return array;
           
           
           
       }catch(Exception e){System.out.println(e);}
       return null;
   }
     public static void post(String food_name,String food_price, String food_type) throws Exception{
        try{
        Connection con = getConnection();
        PreparedStatement posted = con.prepareStatement("INSERT INTO menu.menus (food_name, food_price, food_type) VALUES ('"+food_name+"', '"+food_price+"','"+food_type+"')");
        posted.executeUpdate();
                }catch(Exception e){
                    System.out.println(e);
                }
        finally{
            System.out.println("Insert Completed");
        }
    }
     
     public static ArrayList<Menu> query(String aranan) throws Exception{
        if(aranan.length()== 0){
            System.out.println("Boştur");
        } 
        else {
        try{
            Connection con = getConnection();
           PreparedStatement statement = con.prepareStatement("SELECT * FROM menu.menus WHERE food_name LIKE '%"+aranan+"%'");
           ResultSet result = statement.executeQuery();
            System.out.println(result + "  <- Result");
           ArrayList<Menu> array = new ArrayList<Menu>();
           while(result.next()){
               
               int id = result.getInt("id");
               String food_name = result.getString("food_name");
               String food_price = result.getString("food_price");
               String food_type = result.getString("food_type");
               
               Menu m = new Menu(id,food_name,food_price,food_type);
               
               array.add(m);
           }
           System.out.println(array);
           return array;
            
            
        }catch(Exception e){System.out.println("Bulunamdı");}
        
        }
        return null;
    }
     
    public static void delete(int id) throws Exception{
        try{
            
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("Delete from menu.menus where id = "+id );
            statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public static int toDelete(String aranan) throws Exception{
        try{
            
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement("SELECT id FROM menu.menus WHERE food_name LIKE '%"+aranan+"%'");
            ResultSet result = statement.executeQuery();
            ArrayList<Menu> array = new ArrayList<Menu>();
           while(result.next()){
               
               int id = result.getInt("id");
               
               System.out.println(id + "  <- İİİDDDD");
               return id;
           }
            return -1;
            
            
        }catch(Exception e){
            System.out.println(e);
            return -1;
            
        }
    }
     
}
