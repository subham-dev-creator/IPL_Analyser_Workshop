package com.training.workshop.iplanalyser.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.training.workshop.iplanalyser.exception.IPLAnalyserException;
import com.training.workshop.iplanalyser.models.IPLMostRunsCSV;

public class IPLAnalyser {

    // Returns Top Batting Avg
    public double topBattingAvg(String PATH) throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> list = mostRuns(PATH);
        double maxAvgScore = list.stream().filter(x -> !x.average.equals("-"))
                                .map(x -> Double.parseDouble(x.average))
                                .max(Double::compare).get();
        ArrayList<IPLMostRunsCSV> maxAvgPlayerList = (ArrayList<IPLMostRunsCSV>) list.stream()
                .filter(x -> x.average.equals(Double.toString(maxAvgScore))).collect(Collectors.toList());
        for (IPLMostRunsCSV data : maxAvgPlayerList)
            System.out.println(data.player);
        return maxAvgScore;
    }

    // Returns ArrayList Of MostRun
    private ArrayList<IPLMostRunsCSV> mostRuns(String PATH) throws IPLAnalyserException {

        ArrayList<IPLMostRunsCSV> list = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(Paths.get(PATH))){
            CsvToBeanBuilder<IPLMostRunsCSV> builder = new CsvToBeanBuilder<IPLMostRunsCSV>(reader);
            builder.withType(IPLMostRunsCSV.class);
            builder.withIgnoreLeadingWhiteSpace(true);

            CsvToBean<IPLMostRunsCSV> csvToBean = builder.build();

            Iterator<IPLMostRunsCSV> iplBattingitr = csvToBean.iterator();
            while (iplBattingitr.hasNext()) {

                IPLMostRunsCSV csvReader = iplBattingitr.next();
                list.add(csvReader);
            }
            return list;
        } catch (IOException E1) {
            throw new IPLAnalyserException("Invalid Path Provided", IPLAnalyserException.ExceptionType.INCORRECT_PATH);
        }

    }

    //
    public double maxStrikingRates(String mostRunsCsvFilePath) throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> list = mostRuns(mostRunsCsvFilePath);
        double maxStrikingRate = list.stream().map(x -> Double.parseDouble(x.strikeRate)).max(Double::compare).get();
        ArrayList<IPLMostRunsCSV> maxStrikeRateList = (ArrayList<IPLMostRunsCSV>) list.stream()
                .filter(x -> x.strikeRate.equals(Double.toString(maxStrikingRate))).collect(Collectors.toList());
        System.out.println("Max Strike Rates Player");
        for (IPLMostRunsCSV data : maxStrikeRateList)
            System.out.println(data.player);
        return maxStrikingRate;
    }

    // Returns Name Of Player With Max number of 6s Hit
    public String playerWithMaxSixHit(String mostRunsCsvFilePath) throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> list = mostRuns(mostRunsCsvFilePath);
        ArrayList<IPLMostRunsCSV> sortedMax6 = (ArrayList<IPLMostRunsCSV>) list.stream()
                .sorted((player1, player2) -> Integer.compare(player1.sixes, player2.sixes))
                .collect(Collectors.toList());
        Collections.reverse(sortedMax6);
        System.out.println("Player with maximum sixes is");
        System.out.println(sortedMax6.get(0).player + " with total number of sixes " + sortedMax6.get(0).sixes);
        return sortedMax6.get(0).player;
    }

    // Retuns Player Name With Max Four Hits
    public String playerWithMaxFourHit(String mostRunsCsvFilePath) throws IPLAnalyserException {
        ArrayList<IPLMostRunsCSV> list = mostRuns(mostRunsCsvFilePath);
        ArrayList<IPLMostRunsCSV> sortedMax4 = (ArrayList<IPLMostRunsCSV>) list.stream().sorted((player1, player2) -> {
            return player2.fours - player1.fours;
        }).collect(Collectors.toList());
        System.out.println("Players with maximum number of 4s is");
        System.out.println(sortedMax4.get(0).player + " with total number of fours " + sortedMax4.get(0).fours);
        return sortedMax4.get(0).player;
    }
}
