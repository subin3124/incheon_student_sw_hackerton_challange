package com.youngsun.hackerton.DBA;

import com.youngsun.hackerton.VO.fighting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class fightingDBA {
    Connection conn;
    Statement st;
    ResultSet rs;
    public fightingDBA() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerton?serverTimezone=UTC","root","kim1160");

    }
    public void InsertFighting(fighting f) throws SQLException {
        st = conn.createStatement();
        st.executeUpdate("INSERT INTO fighting VALUES ('"+f.getMsg()+"')");

    }
    public List<fighting> returnf() throws SQLException {
        st = conn.createStatement();
       List<fighting> fightingList = new ArrayList<>();
        rs = st.executeQuery("SELECT * FROM fighting");
        while(rs.next()){
          fighting f = new fighting();
          f.setMsg(rs.getString("msg"));
          fightingList.add(f);
        }
        return fightingList;
    }
}
