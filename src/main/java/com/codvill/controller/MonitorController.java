package com.codvill.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codvill.service.MonitorService;

@Controller
public class MonitorController {
    
    @Autowired
    MonitorService ms;

    @PostMapping("/vmonitorList")
    @ResponseBody
    public ResponseEntity<Object> monitorList() {
        System.out.println("monitorList 작동");
        JSONObject obj = new JSONObject();


        try {
            obj=ms.monitorList();
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }

        
    }

    @PostMapping("/vmonitorCoordinateUpdate")
    @ResponseBody
    public ResponseEntity<Object> monitorCoordinateUpdate(@RequestBody Map<String, Object> param) {
        System.out.println("monitorList 작동");
        JSONObject obj = new JSONObject();
        System.out.println(param);

        try {
            obj=ms.monitorCoordinateUpdate(param);
            return ResponseEntity.ok(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(null);
        }

        
    }




}
