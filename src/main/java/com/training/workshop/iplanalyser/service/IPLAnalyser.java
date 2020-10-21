package com.training.workshop.iplanalyser.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
}
