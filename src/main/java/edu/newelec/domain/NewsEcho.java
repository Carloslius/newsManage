package edu.newelec.domain;

import lombok.Data;

import java.util.Date;

@Data
public class NewsEcho {
    Integer id;
    String title;
    String deScr;
    String proCode;
    String mainImg;
    String detail;
    String source;
    Integer view;
    Integer top;
    Integer state;
    String author;
    Date crTime;
    Date upTime;
}
