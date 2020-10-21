package com.training.workshop.iplanalyser.test;

import com.training.workshop.iplanalyser.exception.IPLAnalyserException;
import com.training.workshop.iplanalyser.models.IPLMostRunsCSV;
import com.training.workshop.iplanalyser.service.IPLAnalyser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

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

    // UC 2
    @Test
    public void givenIPLFile_FindMaxStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser();
        double maxStrikeRate = iplLeagueAnalyser.maxStrikingRates(MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals(333.33, maxStrikeRate, 0.0);
    }

    // UC 3
    @Test
    public void playerWithMaximum6Test() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser();
        String playerWithMax6 = iplLeagueAnalyser.playerWithMaxSixHit(MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals("Andre Russell", playerWithMax6);
    }

    @Test
    public void playerWithMaximum4Test() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser();
        String playerWithMax4 = iplLeagueAnalyser.playerWithMaxFourHit(MOST_RUNS_CSV_FILE_PATH);
        Assert.assertEquals("Shikhar Dhawan", playerWithMax4);
    }

}
