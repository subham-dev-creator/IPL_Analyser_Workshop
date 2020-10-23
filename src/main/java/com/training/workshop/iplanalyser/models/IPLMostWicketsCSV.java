package com.training.workshop.iplanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class IPLMostWicketsCSV {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Ov", required = true)
    public double overs;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Wkts", required = true)
    public int wickets;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "Econ", required = true)
    public double economy;

    @CsvBindByName(column = "SR", required = true)
    public String strikeRate;

    @CsvBindByName(column = "4w", required = true)
    public int fourWickets;

    @CsvBindByName(column = "5w", required = true)
    public int fiveWickets;

    public IPLMostWicketsCSV(IPLMostWicketsCSV iplCSVObj) {
        this.player = iplCSVObj.player;
        this.match = iplCSVObj.match;
        this.innings = iplCSVObj.innings;
        this.overs = iplCSVObj.overs;
        this.runs = iplCSVObj.runs;
        this.wickets = iplCSVObj.wickets;
        this.average = iplCSVObj.average;
        this.economy = iplCSVObj.economy;
        this.strikeRate = iplCSVObj.strikeRate;
        this.fourWickets = iplCSVObj.fourWickets;
        this.fiveWickets = iplCSVObj.fiveWickets;
    }

    public IPLMostWicketsCSV(){}

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

    public double getOvers() {
        return overs;
    }

    public void setOvers(double overs) {
        this.overs = overs;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public Double getAverage() {
        if(average.equals("-"))
            return 0.0;
        return Double.parseDouble(average);
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public Double getStrikeRate() {
        if(strikeRate.equals("-"))
            return 0.0;
        return Double.parseDouble(strikeRate);
    }

    public void setStrikeRate(String strikeRate) {
        this.strikeRate = strikeRate;
    }

    public int getFourWickets() {
        return fourWickets;
    }

    public void setFourWickets(int fourWickets) {
        this.fourWickets = fourWickets;
    }

    public int getFiveWickets() {
        return fiveWickets;
    }

    public void setFiveWickets(int fiveWickets) {
        this.fiveWickets = fiveWickets;
    }
}
