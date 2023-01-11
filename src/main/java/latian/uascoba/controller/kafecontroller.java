/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package latian.uascoba.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import latian.uascoba.model.entity.Kafe;
import latian.uascoba.model.jpa.KafeJpaController;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author STRIX
 */
@RestController
@CrossOrigin
@RequestMapping("/kalolauas")
public class kafecontroller {
    
    //manggil entity
    Kafe kf = new Kafe();
    //jpa
    KafeJpaController ctrl = new KafeJpaController();
    
    //get data mapping
    @GetMapping
    public List<Kafe> getData(){
        List<Kafe> data = new ArrayList<>(); //object arraylist
        try{
            data = ctrl.findKafeEntities();
        }catch (Exception e){
            
        }
        return data; //return object
    }
    
    
    //get mapping id
    @GetMapping("/{id}")
    public List<Kafe> getKafeEntities(@PathVariable("id")int id){
        List<Kafe> dataa = new ArrayList<Kafe>(); //new object
        try{
            kf = ctrl.findKafe(id);
        dataa.add(kf);
        }catch (Exception e){}
        return dataa;
    }
    
    
    //post mapping
    @PostMapping
    public String insertData(HttpEntity<String> requestdata){
        String message = "Data berhasil masuk";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            kf = map.readValue(json_receive, Kafe.class);
            ctrl.create(kf);
        }catch(Exception e){
            message = "Data Gagal dimasukkan";
        }
        return message;
    }
    
    @PutMapping
    public String updateData(HttpEntity<String> requestdata){
        String message = "Data berhasil di edit";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            kf = map.readValue(json_receive, Kafe.class);
            ctrl.edit(kf);
        }catch(Exception e){
            message = "Data Gagal edit";
        }
        return message;
    }
    
    @DeleteMapping
    public String deleteData(HttpEntity<String> requestdata){
        String message = "Data berhasil dihapus";
        try{
            String json_receive = requestdata.getBody();
            ObjectMapper map = new ObjectMapper();
            kf = map.readValue(json_receive, Kafe.class);
            ctrl.destroy(kf.getId());
        }catch(Exception e){
            message = "Data Gagal dihapus";
        }
        return message;
    }
}
