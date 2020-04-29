package latrespbo;

import java.sql.Connection;
import java.sql.DriverManager;

public class konek {
    private static Connection konek;
    public static Connection getKoneksi() {
        if (konek == null) {
            try {
                String url = "jdbc:mysql://localhost/latrespbo";
                String username = "root";
                String password = "root";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                konek = DriverManager.getConnection(url, username, password);
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        return konek;
    }
}
