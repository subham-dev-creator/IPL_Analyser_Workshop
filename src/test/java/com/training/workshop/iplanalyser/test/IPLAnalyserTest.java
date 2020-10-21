package com.training.workshop.iplanalyser.test;

import com.training.workshop.iplanalyser.exception.IPLAnalyserException;
import com.training.workshop.iplanalyser.service.IPLAnalyser;
import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String MOST_RUNS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\IPL_Analyser_Workshop\\src\\test\\resources\\WP_IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKETS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\IPL_Analyser_Workshop\\src\\test\\resources\\WP_IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLMostRunsCSVFile_ShouldReturn_CorrectRecords() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BATSMAN, MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(100, count);

    }

    @Test
    public void givenIPLMostWicketsCSVFile_ShouldReturn_CorrectRecords() {
        IPLAnalyser iplAnalyser = new IPLAnalyser();
        int count = iplAnalyser.loadIPLData(IPLAnalyser.PlayerType.BOWLER, MOST_WICKETS_CSV_FILE_PATH);
        Assert.assertEquals(99, count);

    }
}
