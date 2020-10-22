package com.training.workshop.iplanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class IPLMostRunsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "NO", required = true)
    public int notOut;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public String highestScore;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "SR", required = true)
    public double strikeRate;

    @CsvBindByName(column = "100", required = true)
    public int hundreds;

    @CsvBindByName(column = "50", required = true)
    public int fifties;

    @CsvBindByName(column = "4s", required = true)
    public int fours;

    @CsvBindByName(column = "6s", required = true)
    public int sixes;

    public double performanceFactor;

    public IPLMostRunsCSV(IPLMostRunsCSV iplMostRunsCSV) {
        player = iplMostRunsCSV.player;
        match = iplMostRunsCSV.match;
        innings = iplMostRunsCSV.innings;
        average = iplMostRunsCSV.average;
        fifties = iplMostRunsCSV.fifties;
        fours = iplMostRunsCSV.fours;
        sixes = iplMostRunsCSV.sixes;
        highestScore = iplMostRunsCSV.highestScore;
        hundreds = iplMostRunsCSV.hundreds;
        notOut = iplMostRunsCSV.notOut;
        runs = iplMostRunsCSV.runs;
        strikeRate = iplMostRunsCSV.strikeRate;
    }

    public IPLMostRunsCSV(){
    }

}
