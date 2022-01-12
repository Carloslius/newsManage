package edu.newelec.domain;

import lombok.Data;

import java.util.Date;

@Data
public class NewsPro {
    Integer id;
    String name;
    Integer sort;
    Date crTime;
    Date upTime;
}
