package com.youngsun.hackerton.DBA;

import com.youngsun.hackerton.VO.ScoreResult;

import java.sql.*;

public class ScoreDBA {
    Connection conn;
    Statement st;
    ResultSet rs;
    public ScoreDBA() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hackerton?serverTimezone=UTC","root","비번");

    }
    public void InsertResult(String id, ScoreResult sr) throws SQLException {
        st = conn.createStatement();
        st.executeUpdate("INSERT INTO scores (id, sleepscore, foodscore, healthscore, smartscore, maskscore, mmscore, airscore, handscore, finscore) VALUES ('"+id+"',"+sr.getSleepscore()+","+sr.getFoodscore()+","+sr.getHealthscore()+","+sr.getSmartscore()+","+sr.getMaskscore()+","+sr.getMmscore()+","+sr.getAirscore()+","+sr.getHandscore()+","+sr.getFinscore()+")");

    }

}
