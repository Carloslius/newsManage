package edu.newelec.domain;

import lombok.Data;

import java.util.Date;

@Data
public class News {
    Integer id;
    String title;
    String deScr;
    Integer proCode;
    String mainImg;
    String detail;
    String source;
    Integer view;
    Integer top;
    Integer state;
    Integer author;
    Date crTime;
    Date upTime;
}
