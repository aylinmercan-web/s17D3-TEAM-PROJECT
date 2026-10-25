package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Kangaroo;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/kangaroo")
public class KangarooController {
    private Map<Integer , Kangaroo> kangaroos ;

    @PostConstruct
    public void init() {
        kangaroos = new HashMap<>();
        kangaroos.put(1,new Kangaroo(1, "Aylin", 1.70, 63.0, "female" , false));
    }

    @GetMapping
    public List<Kangaroo> getAll() {
        return kangaroos.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Kangaroo getById(@PathVariable Integer id){
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Bu id bulunmuyor id:"+id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.get(id);
    }

    @PostMapping
    public Kangaroo create(@RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @PutMapping("/{id}")
    public Kangaroo update(@PathVariable Integer id, @RequestBody Kangaroo kangaroo) {
        kangaroos.put(kangaroo.getId(), kangaroo);
        return kangaroo;
    }

    @DeleteMapping("/{id}")
    public Kangaroo delete(@PathVariable Integer id) {
        if(!kangaroos.containsKey(id)){
            throw new ZooException("Bu id bulunmuyor id:"+id, HttpStatus.NOT_FOUND);
        }
        return kangaroos.remove(id);
    }



}
