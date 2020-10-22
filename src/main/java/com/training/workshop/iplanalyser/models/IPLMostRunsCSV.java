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

    public Double getAverage() {
        if(average.equals("-"))
            return 0.0;
        return Double.parseDouble(average);
    }

    public double getStrikeRate() {
        return strikeRate;
    }

    public void setStrikeRate(double strikeRate) {
        this.strikeRate = strikeRate;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getInnings() {
        return innings;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public int getNotOut() {
        return notOut;
    }

    public void setNotOut(int notOut) {
        this.notOut = notOut;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public String getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(String highestScore) {
        this.highestScore = highestScore;
    }

    public int getHundreds() {
        return hundreds;
    }

    public void setHundreds(int hundreds) {
        this.hundreds = hundreds;
    }

    public int getFifties() {
        return fifties;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public double getPerformanceFactor() {
        return performanceFactor;
    }

    public void setPerformanceFactor(double performanceFactor) {
        this.performanceFactor = performanceFactor;
    }
}
