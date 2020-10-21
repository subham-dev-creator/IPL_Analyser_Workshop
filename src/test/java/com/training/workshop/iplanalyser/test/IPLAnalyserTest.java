package com.training.workshop.iplanalyser.test;

import com.training.workshop.iplanalyser.exception.IPLAnalyserException;
import com.training.workshop.iplanalyser.service.IPLAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class IPLAnalyserTest {
    private static final String MOST_RUNS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\IPL_Analyser_Workshop\\src\\test\\resources\\WP_IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKETS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\IPL_Analyser_Workshop\\src\\test\\resources\\WP_IPL2019FactsheetMostWkts.csv";

    //UC 1
    @Test
    public void mostRuns() throws IPLAnalyserException {
        IPLAnalyser iplAnalyserObj = new IPLAnalyser();
        double maxAvg = iplAnalyserObj.topBattingAvg(MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(83.2, maxAvg, 0.0);
    }

}
