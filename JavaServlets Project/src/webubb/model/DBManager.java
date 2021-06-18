package webubb.model;

import webubb.domain.Asset;
import webubb.domain.User;
import webubb.domain.ImageTile;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class DBManager {

    public static Connection connect() {
        Connection connection = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/lab9web", "root", "");
        } catch(Exception ex) {
            System.out.println("eroare la connect:"+ex.getMessage());
            ex.printStackTrace();
        }
        return connection;
    }


}
