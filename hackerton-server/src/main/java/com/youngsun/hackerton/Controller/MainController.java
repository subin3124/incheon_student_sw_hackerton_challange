package com.youngsun.hackerton.Controller;

import com.youngsun.hackerton.DBA.ScoreDBA;
import com.youngsun.hackerton.DBA.fightingDBA;
import com.youngsun.hackerton.DBA.loginDBA;
import com.youngsun.hackerton.VO.ScoreResult;
import com.youngsun.hackerton.VO.fighting;
import com.youngsun.hackerton.VO.login;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {

    @RequestMapping("/GetResult")
    @ResponseBody
    private ScoreResult GetResult(@RequestBody String json) throws ParseException, SQLException {
        JSONParser parser = new JSONParser();
        JSONObject obj1 = (JSONObject) parser.parse(json);
        ScoreResult result =setMsg(obj1);
        ScoreDBA scoreDBA = new ScoreDBA();
        scoreDBA.InsertResult(obj1.get("id").toString(),result);
        return result;
    }
    private ScoreResult setMsg(JSONObject obj){
        ScoreResult sr = new ScoreResult();
        System.out.println(obj.toString());
        sr.setSleepscore(Integer.parseInt(obj.get("sleepscore").toString()));
        sr.setMaskscore(Integer.parseInt(obj.get("covidscore").toString()));

        //수면분야
        if(sr.getSleepscore()<=10){
            sr.setSleepmsg("당신은 부적절한 수면을 취하고 있습니다. 지금 당장 꿈나라로.");
        }else if(sr.getSleepscore()>10&&sr.getSleepscore()<=20){
            sr.setSleepmsg("당신은 수면에 더 노력하는 것이 좋을것 같습니다.");
        }else if(sr.getSleepscore()>20){
            sr.setSleepmsg("당신은 훌륭한 수면을 취하고 있습니다.");
        }
        //식생활분야
        if(sr.getFinscore()<=10){
            sr.setFoodmsg("당신은 부적절한 식습관을 취하고 있습니다.");
        }else if(sr.getFoodscore()>10&&sr.getFoodscore()<=20){
            sr.setFoodmsg("당신의 식습관은 좋은 편이 아닙니다.");
        }else if(sr.getFoodscore()>20&&sr.getFoodscore()<=30){
            sr.setFoodmsg("당신은 조금 더 노력한다면 훌륭한 식습관을 취할 수 있습니다.");
        }else if(sr.getFoodscore()>30){
            sr.setFoodmsg("당신의 식습관은 아주 훌륭합니다.");
        }
        //건강분야
        if(sr.getHealthscore()<=0){
            sr.setHealthmsg("당신은 운동을 하는편이 좋습니다.");
        }else if(sr.getHealthscore()>=1){
            sr.setHealthmsg("당신은 적절한 운동을 취하고 있습니다.");
        }
        //전자기기 사용
        if(sr.getSmartscore()<=10){
            sr.setSmartmsg("당신은 전자기기 사용을 줄이는 것이 좋습니다.");
        } else if(sr.getSmartscore()>10&&sr.getSmartscore()<=15){
            sr.setSmartmsg("당신은 전자기를 적절하게 사용하고 있습니다.");
        }
        if(sr.getMaskscore()<=15) {
            sr.setMaskmsg("당신은 마스크 착용을 제대로 하지않고 외출을 하였습니다.");
        }else if(sr.getMaskscore()>15&&sr.getSmartscore()<=30){
            sr.setMaskmsg("당신은 마스크를 쓰는것을 노력하여야 합니다.");
        }else if(sr.getMaskscore()>30){
            sr.setMaskmsg("당신은 마스크를 완벽하게 착용하였습니다.");
        }
        //코로나 방역
        return sr;
    }
    @RequestMapping("/login")
    @ResponseBody
    private void login(@RequestBody String json) throws ParseException, SQLException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(json);
        loginDBA loginDBA = new loginDBA();
        loginDBA.login(obj.get("id").toString(),obj.get("pw").toString());

    }
    @RequestMapping("/fighting")
    @ResponseBody()
    private void fighting(@RequestBody String json) throws ParseException, SQLException {
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(json);
        fightingDBA dba = new fightingDBA();
        fighting f = new fighting();
        System.out.println(json);
        f.setMsg(obj.get("msg").toString());
        dba.InsertFighting(f);
    }
    @RequestMapping("/listf")
    @ResponseBody
    private List<fighting> lf() throws SQLException {
        fightingDBA DBA = new fightingDBA();
       return DBA.returnf();
    }


}
