package com.example.microriot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter

public class Config {
    private UUID id;
    private String roles;
    private String key;
    private String value;
    private String url;
    private String note;
    private Date createdAT;
    private Date updatedAT;
    private String region;
}
