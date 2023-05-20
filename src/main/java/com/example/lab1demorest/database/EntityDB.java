package com.example.lab1demorest.database;

import com.example.lab1demorest.entity.Result;
import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "results")
public class EntityDB {
    @Id
    @Column(name = "id")
//    @SequenceGenerator(name = "resultsIdSequence", sequenceName = "id_generator", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resultsIdSequence")
    private int id;

    @Column(name = "index")
    private int index;

    @Column (name = "result")
    private String result;

    public EntityDB(){}
//    public EntityDB(Result result){
//        this.index = Integer.parseInt(result.getIndex().toString());
//        this.result = result.getResult().toString();
//    }
    public EntityDB(Result result){
        this.result = result.getResult().toString();
        this.index = Integer.parseInt(result.getIndex().toString());
        this.id = this.index;
    }

    public Result getResult(){
        BigInteger first = new BigInteger(result);
        BigInteger second = BigInteger.valueOf(index);

        return new Result(first, second);
    }

    public int getId() {
        return id;
    }
}
