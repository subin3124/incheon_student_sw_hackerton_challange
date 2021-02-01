package com.youngsun.hackerton.DBA;

import java.sql.*;

public class loginDBA {
    Connection conn;
    Statement st;
    ResultSet rs;
    public loginDBA() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerton?serverTimezone=UTC","root","kim1160");

    }
    public void login(String id, String pw) throws SQLException {
        st = conn.createStatement();
        st.executeUpdate("INSERT INTO user VALUES ('"+id+"','"+pw+"')");
    }

}
