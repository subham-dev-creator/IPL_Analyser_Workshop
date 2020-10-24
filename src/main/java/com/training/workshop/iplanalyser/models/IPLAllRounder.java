package com.training.workshop.iplanalyser.models;

import com.opencsv.bean.CsvBindByName;

public class IPLAllRounder {
    public String player;

    public int match;

    public int innings;

    public int notOut;

    public int runsBatting;

    public String highestScore;

    public double averageBatting;

    public double strikeRateBatting;

    public int hundreds;

    public int fifties;

    public int fours;

    public int sixes;

    public double overs;

    public int runsBowling;

    public int wickets;

    public double averageBowling;

    public double economy;

    public double strikeRateBowling;

    public int fourWickets;

    public int fiveWickets;

    public IPLAllRounder(String player, int match, int innings, int notOut, int runsBatting, String highestScore, double averageBatting, double strikeRateBatting, int hundreds, int fifties, int fours, int sixes, double overs, int runsBowling, int wickets, double averageBowling, double economy, double strikeRateBowling, int fourWickets, int fiveWickets) {
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.notOut = notOut;
        this.runsBatting = runsBatting;
        this.highestScore = highestScore;
        this.averageBatting = averageBatting;
        this.strikeRateBatting = strikeRateBatting;
        this.hundreds = hundreds;
        this.fifties = fifties;
        this.fours = fours;
        this.sixes = sixes;
        this.overs = overs;
        this.runsBowling = runsBowling;
        this.wickets = wickets;
        this.averageBowling = averageBowling;
        this.economy = economy;
        this.strikeRateBowling = strikeRateBowling;
        this.fourWickets = fourWickets;
        this.fiveWickets = fiveWickets;
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

    public int getRunsBatting() {
        return runsBatting;
    }

    public void setRunsBatting(int runsBatting) {
        this.runsBatting = runsBatting;
    }

    public String getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(String highestScore) {
        this.highestScore = highestScore;
    }

    public double getAverageBatting() {
        return averageBatting;
    }

    public void setAverageBatting(double averageBatting) {
        this.averageBatting = averageBatting;
    }

    public double getStrikeRateBatting() {
        return strikeRateBatting;
    }

    public void setStrikeRateBatting(double strikeRateBatting) {
        this.strikeRateBatting = strikeRateBatting;
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

    public double getOvers() {
        return overs;
    }

    public void setOvers(double overs) {
        this.overs = overs;
    }

    public int getRunsBowling() {
        return runsBowling;
    }

    public void setRunsBowling(int runsBowling) {
        this.runsBowling = runsBowling;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    public double getAverageBowling() {
        return averageBowling;
    }

    public void setAverageBowling(double averageBowling) {
        this.averageBowling = averageBowling;
    }

    public double getEconomy() {
        return economy;
    }

    public void setEconomy(double economy) {
        this.economy = economy;
    }

    public double getStrikeRateBowling() {
        return strikeRateBowling;
    }

    public void setStrikeRateBowling(double strikeRateBowling) {
        this.strikeRateBowling = strikeRateBowling;
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
