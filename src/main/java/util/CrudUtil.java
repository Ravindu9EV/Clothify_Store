package util;

import db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String SQL,Object... args) throws SQLException {
            PreparedStatement pst= DBConnection.getInstance().getConnection().prepareStatement(SQL);
            for(int i=0;i<args.length;i++){
                pst.setObject(i+1,args[i]);
            }
            if(SQL.startsWith("Select") || SQL.startsWith("SELECT")){
                return (T) pst.executeQuery();
            }else{
                return (T) (Boolean) (pst.executeUpdate()>0);
            }

    }
}
