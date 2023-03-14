package com.example.kafkaProducer.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataModel {

    public DataModel(Integer id, String name, String dob)
    {
        Id = id;
        Name = name;
        DOB = dob;
    }
    private Integer Id;
    private String Name;
    private String DOB;

//    @Override
//    public String toString()
//    {
//        return MessageFormat.format("Id : ", Id, Name, DOB);
//    }
}
