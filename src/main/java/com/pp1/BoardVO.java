package com.pp1;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {
    private int seq;
    private String title;
    private String writer;
    private String content;
    private Date regdate;
    private int cnt;
}
