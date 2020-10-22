package com.training.workshop.iplanalyser.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.training.workshop.iplanalyser.Utility.CSVBuilderException;
import com.training.workshop.iplanalyser.Utility.CSVBuilderFactory;
import com.training.workshop.iplanalyser.exception.IPLAnalyserException;
import com.training.workshop.iplanalyser.models.IPLMostRunsCSV;

public class IPLAnalyser {
    Map<String,IPLMostRunsCSV> iplMostRunMap;

    public IPLAnalyser(String mostRunsCsvFilePath) throws IPLAnalyserException {
        Map<String, IPLMostRunsCSV> iplMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(mostRunsCsvFilePath))) {
            Iterator iplIterator = CSVBuilderFactory.createCSVBuilder().getCSVFileIterator(reader, IPLMostRunsCSV.class);
            Iterable<IPLMostRunsCSV> iplCSV = () -> iplIterator;


            StreamSupport.stream(iplCSV.spliterator(), false)
                    .map(IPLMostRunsCSV.class::cast)
                    .forEach(iplCSVObj -> iplMap.put(iplCSVObj.player, new IPLMostRunsCSV(iplCSVObj)));

            this.iplMostRunMap = iplMap;
        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.INCORRECT_PATH);
        }
    }


    // Returns Top Batting Avg
    public double topBattingAvg() throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> iplMostRunsList = new ArrayList<>(iplMostRunMap.values());
        double maxAvgScore = iplMostRunsList.stream().filter(x -> !x.average.equals("-"))
                                .map(x -> Double.parseDouble(x.average))
                                .max(Double::compare).get();
        ArrayList<IPLMostRunsCSV> maxAvgPlayerList = (ArrayList<IPLMostRunsCSV>) iplMostRunsList.stream()
                .filter(x -> x.average.equals(Double.toString(maxAvgScore))).collect(Collectors.toList());
        for (IPLMostRunsCSV data : maxAvgPlayerList)
            System.out.println(data.player);
        return maxAvgScore;
    }

    // Max
    public double maxStrikingRates() throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> iplMostRunsList = new ArrayList<>(iplMostRunMap.values());
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
        ArrayList<IPLMostRunsCSV> iplMostRunsList = new ArrayList<>(iplMostRunMap.values());
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
        ArrayList<IPLMostRunsCSV> iplMostRunsList = new ArrayList<>(iplMostRunMap.values());
        ArrayList<IPLMostRunsCSV> sortedMax4 = (ArrayList<IPLMostRunsCSV>) iplMostRunsList.stream().sorted((player1, player2) -> {
            return player2.fours - player1.fours;
        }).collect(Collectors.toList());
        System.out.println("Players with maximum number of 4s is");
        System.out.println(sortedMax4.get(0).player + " with total number of fours " + sortedMax4.get(0).fours);
        return sortedMax4.get(0).player;
    }

    // Returns Player Name With Best Performance
    public String playerWithBestPerformace() throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> iplMostRunsList = new ArrayList<>(iplMostRunMap.values());
        double bestPerformance=0;
        for(IPLMostRunsCSV pl : iplMostRunsList) {
            pl.performanceFactor = pl.strikeRate * 0.2 + pl.sixes * 0.5 + pl.fours * 0.3;
            bestPerformance=Math.max(pl.performanceFactor,bestPerformance);
        }

        double finalBestPerformance = bestPerformance;
        return iplMostRunsList.stream().filter(x -> x.performanceFactor== finalBestPerformance).collect(Collectors.toList()).get(0).player;
    }

}
