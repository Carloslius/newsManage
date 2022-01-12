package edu.newelec.domain;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    Integer id;
    String name;
    Integer permission;
    String acct;
    String pwd;
    Date crTime;
    Date upTime;
}
