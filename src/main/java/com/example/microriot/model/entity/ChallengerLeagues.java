package com.example.microriot.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "CHALLENGERLEAGUES")
public class ChallengerLeagues {

    @Column(name = "LEAGUEID")
    private String leagueId;
    @Column(name = "QUEUETYPE")
    private String queueType;
    @Column(name = "TIER")
    private String tier;
    @Column(name = "RANK")
    private String rank;

    @Id
    @Column(name = "SUMMONERID")
    private String summonerId;
    @Column(name = "SUMMONERNAME")
    private String summonerName;
    @Column(name = "LEAGUEPOINTS")
    private int leaguePoints;
    @Column(name = "WINS")
    private int wins;
    @Column(name = "LOSSES")
    private int losses;
    @Column(name = "VETERAN")
    private boolean veteran;
    @Column(name = "INACTIVE")
    private boolean inactive;
    @Column(name = "FRESHBLOOD")
    private boolean freshBlood;
    @Column(name = "HOTSTREAK")
    private boolean hotStreak;

    @Column(name = "REGION")
    private String region ;
}
