/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.springteam.websocketpractise.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author siux
 */
@Controller
@RequestMapping("welcome")
public class WelcomeController {
    
    @RequestMapping( path = "first", method = RequestMethod.GET)
    public ResponseEntity<String> first() throws IOException{
        
        ClassPathResource res = new ClassPathResource("app.properties");
        
        
        Properties prop = new Properties();
        
        prop.load(new FileInputStream(res.getFile()));
        
        
        return ResponseEntity.ok().body("First Request And response. " + prop.getProperty("host"));
    }
    
    
}
