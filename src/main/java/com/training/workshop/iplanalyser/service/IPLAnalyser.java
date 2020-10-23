package com.training.workshop.iplanalyser.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.training.workshop.iplanalyser.Utility.CSVBuilderException;
import com.training.workshop.iplanalyser.Utility.CSVBuilderFactory;
import com.training.workshop.iplanalyser.exception.IPLAnalyserException;
import com.training.workshop.iplanalyser.models.IPLMostRunsCSV;
import com.training.workshop.iplanalyser.models.IPLMostWicketsCSV;

public class IPLAnalyser {
    Map<String,IPLMostRunsCSV> iplMostRunMap;
    Map<String,IPLMostWicketsCSV> iplMostWicketMap;
    ArrayList<IPLMostRunsCSV> iplMostRunsList;
    ArrayList<IPLMostWicketsCSV> iplMostWicketsList;

    public enum PlayerType {BATSMAN, BOWLER}

    public IPLAnalyser(PlayerType pl,String CsvFilePath) throws IPLAnalyserException {
        if(pl==PlayerType.BATSMAN) {
            Map<String, IPLMostRunsCSV> iplMap = new HashMap<>();
            try (Reader reader = Files.newBufferedReader(Paths.get(CsvFilePath))) {
                Iterator iplIterator = CSVBuilderFactory.createCSVBuilder().getCSVFileIterator(reader, IPLMostRunsCSV.class);
                Iterable<IPLMostRunsCSV> iplCSV = () -> iplIterator;


                StreamSupport.stream(iplCSV.spliterator(), false)
                        .map(IPLMostRunsCSV.class::cast)
                        .forEach(iplCSVObj -> iplMap.put(iplCSVObj.player, new IPLMostRunsCSV(iplCSVObj)));

                this.iplMostRunMap = iplMap;
                this.iplMostRunsList = new ArrayList<>(iplMostRunMap.values());
            } catch (IOException | CSVBuilderException e) {
                throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.INCORRECT_PATH);
            }
        }
        else{
            Map<String, IPLMostWicketsCSV> iplMap = new HashMap<>();
            try (Reader reader = Files.newBufferedReader(Paths.get(CsvFilePath))) {
                Iterator iplIterator = CSVBuilderFactory.createCSVBuilder().getCSVFileIterator(reader, IPLMostWicketsCSV.class);
                Iterable<IPLMostWicketsCSV> iplCSV = () -> iplIterator;


                StreamSupport.stream(iplCSV.spliterator(), false)
                        .map(IPLMostWicketsCSV.class::cast)
                        .forEach(iplCSVObj -> iplMap.put(iplCSVObj.player, new IPLMostWicketsCSV(iplCSVObj)));

                this.iplMostWicketMap = iplMap;
                this.iplMostWicketsList = new ArrayList<>(iplMostWicketMap.values());
            } catch (IOException | CSVBuilderException e) {
                throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.INCORRECT_PATH);
            }
        }
    }


    // Returns Top Batting Avg
    public double topBattingAvg() throws IPLAnalyserException {
        double maxAvgScore = iplMostRunsList.stream().filter(x -> !x.average.equals("-"))
                                .map(x -> Double.parseDouble(x.average))
                                .max(Double::compare).get();
        ArrayList<IPLMostRunsCSV> maxAvgPlayerList = (ArrayList<IPLMostRunsCSV>) iplMostRunsList.stream()
                .filter(x -> x.average.equals(Double.toString(maxAvgScore))).collect(Collectors.toList());
        for (IPLMostRunsCSV data : maxAvgPlayerList)
            System.out.println(data.player);
        return maxAvgScore;
    }

    // Max Striking Rate For Batsman
    public double maxStrikingRates() throws IPLAnalyserException {
        double maxStrikingRate = iplMostRunsList.stream().map(x -> Double.parseDouble(String.valueOf(x.strikeRate))).max(Double::compare).get();
        ArrayList<IPLMostRunsCSV> maxStrikeRateList = (ArrayList<IPLMostRunsCSV>) iplMostRunsList.stream()
                .filter(x -> String.valueOf(x.strikeRate).equals(Double.toString(maxStrikingRate))).collect(Collectors.toList());
        System.out.println("Max Strike Rates Player");
        for (IPLMostRunsCSV data : maxStrikeRateList)
            System.out.println(data.player);
        return maxStrikingRate;
    }

    // Returns Name Of Player With Max number of 6s Hit
    public String playerWithMaxSixHit() throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> sortedMax6 = (ArrayList<IPLMostRunsCSV>) iplMostRunsList.stream()
                .sorted((player1, player2) -> Integer.compare(player1.sixes, player2.sixes))
                .collect(Collectors.toList());
        Collections.reverse(sortedMax6);
        System.out.println("Player with maximum sixes is");
        System.out.println(sortedMax6.get(0).player + " with total number of sixes " + sortedMax6.get(0).sixes);
        return sortedMax6.get(0).player;
    }

    // Returns Player Name With Max Four Hits
    public String playerWithMaxFourHit() throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> sortedMax4 = (ArrayList<IPLMostRunsCSV>) iplMostRunsList.stream().sorted((player1, player2) -> {
            return player2.fours - player1.fours;
        }).collect(Collectors.toList());
        System.out.println("Players with maximum number of 4s is");
        System.out.println(sortedMax4.get(0).player + " with total number of fours " + sortedMax4.get(0).fours);
        return sortedMax4.get(0).player;
    }

    // Returns Player Name With Best Performance
    public String playerWithBestPerformace() throws IPLAnalyserException {
        double bestPerformance=0;
        for(IPLMostRunsCSV pl : iplMostRunsList) {
            pl.performanceFactor = pl.strikeRate * 0.2 + pl.sixes * 0.5 + pl.fours * 0.3;
            bestPerformance=Math.max(pl.performanceFactor,bestPerformance);
        }

        double finalBestPerformance = bestPerformance;
        String result =iplMostRunsList.stream().filter(x -> x.performanceFactor== finalBestPerformance).collect(Collectors.toList()).get(0).player;
        System.out.println("Best Performing Batsman is : " + result);
        return result;
    }

    // Returns Player With Best Average And Striking Rates
    public String playerWithBestAverageAndStrikingRate() {
        Comparator<IPLMostRunsCSV> compareByName = Comparator
                .comparing(IPLMostRunsCSV::getAverage)
                .thenComparing(IPLMostRunsCSV::getStrikeRate);
        List<IPLMostRunsCSV> list = iplMostRunsList.stream().sorted(compareByName).collect(Collectors.toList());
        return  list.get(list.size()-1).player;
    }

    // Returns Player Name Which Most Runs And Best Average
    public String playerWithMostRunsAndBestAvg() {
        Comparator<IPLMostRunsCSV> compare = Comparator
                .comparing(IPLMostRunsCSV::getRuns)
                .thenComparing(IPLMostRunsCSV::getAverage);
        List<IPLMostRunsCSV> list = iplMostRunsList.stream().sorted(compare).collect(Collectors.toList());
        return  list.get(list.size()-1).player;
    }

    // Returns Top Bowling Average
    public Double topBowlingAvg() {
        Comparator<IPLMostWicketsCSV> compare = Comparator
                .comparing(IPLMostWicketsCSV::getAverage);
        List<IPLMostWicketsCSV> list = iplMostWicketsList.stream().sorted(compare).collect(Collectors.toList());
        return  list.get(list.size()-1).getAverage();
    }

    // Returns Top Striking Rate For Bowler
    public Double topStrikingRateBowling() {
        Comparator<IPLMostWicketsCSV> compare = Comparator
                .comparing(IPLMostWicketsCSV::getAverage);
        List<IPLMostWicketsCSV> list = iplMostWicketsList.stream().sorted(compare).collect(Collectors.toList());
        System.out.println("Top Striking Rate For Bowler : " + list.get(list.size()-1).getStrikeRate());
        return  list.get(list.size()-1).getStrikeRate();
    }

    // Returns Best Economic Bowler Name
    public String bestEconomyBowler() {
        Comparator<IPLMostWicketsCSV> compare = Comparator
                .comparing(IPLMostWicketsCSV::getEconomy);
        List<IPLMostWicketsCSV> list = iplMostWicketsList.stream().sorted(compare).collect(Collectors.toList());
        System.out.println("Best Economic Bowler is : " + list.get(0).getPlayer());
        return  list.get(0).getPlayer();
    }

    // Returns best Bowler With Striking Rate And Five Wickets And Four Wickets (Performance Factor)
    public String bestBowlerStrikingRateWith5WicketsAnd4Wickets() {
        double bestPerformance=0;
        for(IPLMostWicketsCSV pl : iplMostWicketsList) {
            pl.performanceFactor = pl.getStrikeRate() * 0.2 + pl.getFiveWickets() * 0.5 + pl.getFourWickets() * 0.3;
            bestPerformance=Math.max(pl.performanceFactor,bestPerformance);
        }
        double finalBestPerformance = bestPerformance;
        String result =iplMostWicketsList.stream().filter(x -> x.performanceFactor== finalBestPerformance).collect(Collectors.toList()).get(0).player;
        System.out.println("Best Striking Rate With  Bowler is : " + result);
        return  result;
    }

    // Returns Player name With Great Bowling Average And Best Striking Rate
    public String bestBowlerWithAverageAndStrikingRate() {
        Comparator<IPLMostWicketsCSV> compare = Comparator
                .comparing(IPLMostWicketsCSV::getAverage)
                .thenComparing(IPLMostWicketsCSV::getStrikeRate);
        List<IPLMostWicketsCSV> list = iplMostWicketsList.stream().sorted(compare).collect(Collectors.toList());
        System.out.println("Best Bowler With Great Bowling Average And Best Striking Rate : " + list.get(list.size()-1).getPlayer());
        return  list.get(list.size()-1).getPlayer();
    }

}
