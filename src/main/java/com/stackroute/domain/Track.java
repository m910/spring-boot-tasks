package com.stackroute.domain;
//import jdk.jfr.DataAmount;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "track")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Track {
    @Id
    private int id;
    private String name;
    private String comments;


}
