package com.example.lab1demorest.database;

import com.example.lab1demorest.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepositoryService{
    private Repository repository;

    @Autowired
    public RepositoryService(Repository repository){
        this.repository = repository;
    }

    public void save(Result result){
        repository.save(new EntityDB(result));
    }

    public List<Result> getAllResults(){
        List<Result> results = new ArrayList<>();
        repository.findAll().forEach(e->results.add(e.getResult()));
        return results;
    }

    public boolean containsInDatabase(BigInteger index){
        for (Result result: getAllResults()) {
            if(index.equals(result.getIndex())) return true;
        }
        return false;
    }

    public Result getByIndex(BigInteger index){
        for (Result result: getAllResults()) {
            if(index.equals(result.getIndex())) return result;
        }
        return null;
    }

    public EntityDB getById(int id){
        for(EntityDB entity: repository.findAll()){
            if(entity.getId() == id){
                return entity;
            }
        }

        return null;
    }
}
