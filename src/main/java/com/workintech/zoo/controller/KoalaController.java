package com.workintech.zoo.controller;

import com.workintech.zoo.entity.Koala;
import com.workintech.zoo.exceptions.ZooException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/koala")
public class KoalaController {
    private Map<Integer, Koala> koalas;
    @PostConstruct
    public void init(){
        koalas = new HashMap<>();
    }

    @GetMapping
    public List<Koala> getAll(){
        return koalas.values().stream().toList();
    }

    @GetMapping("/{id}")
    public Koala getById(@PathVariable Integer id){
        if(!koalas.containsKey(id)){
            throw new ZooException("Bu id bulunmuyor id:"+id, HttpStatus.NOT_FOUND);
        }
        return koalas.get(id);
    }

    @PostMapping
    public Koala create(@RequestBody Koala koala){
        koalas.put(koala.getId(),koala);
        return koala;
    }

    @PutMapping("/{id}")
    public Koala update(@PathVariable Integer id,@RequestBody Koala koala){
        koalas.put(id,koala);
        return koala;
    }

    @DeleteMapping("/{id}")
    public Koala remove (@PathVariable Integer id){
        if(!koalas.containsKey(id)){
            throw new ZooException("Bu id bulunmuyor id:"+id, HttpStatus.NOT_FOUND);
        }
        return koalas.remove(id);
    }
}

