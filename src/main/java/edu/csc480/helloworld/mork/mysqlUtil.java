package edu.csc480.helloworld.mork;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class mysqlUtil{
  static InitialContext _ctx = null;
  static DataSource _db = null;

  private static InitialContext getContext() throws Exception{
    if(_ctx != null){
      return _ctx;
    } else {
      return _ctx = new InitialContext();
    }
  }
  private static DataSource getDB() throws Exception {
    if(_db != null){
      return _db;
    } else {
      return _db = getContext().doLookup("jdbc/myDB");
    }
  }

  public static Connection getConnection() throws Exception {
    DataSource db = getDB();
    return db.getConnection();
  }

  public static void cleanup(Connection con, Statement query, ResultSet res){
    if (res != null) {
        try {
            res.close();
        } catch (SQLException sqlEx) { } // ignore

        res = null;
    }
    if (query != null) {
        try {
            query.close();
        } catch (SQLException sqlEx) { } // ignore

        query = null;
    }
    if (con != null) {
        try {
            con.close();
        } catch (SQLException sqlEx) { } // ignore

        con = null;
    }
  }
}
