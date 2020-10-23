package com.training.workshop.iplanalyser.test;

import com.training.workshop.iplanalyser.exception.IPLAnalyserException;
import com.training.workshop.iplanalyser.models.IPLMostRunsCSV;
import com.training.workshop.iplanalyser.service.IPLAnalyser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

public class IPLAnalyserTest {
    private static final String MOST_RUNS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\IPL_Analyser_Workshop\\src\\test\\resources\\WP_IPL2019FactsheetMostRuns.csv";
    private static final String MOST_WICKETS_CSV_FILE_PATH = "C:\\Users\\I524735\\IdeaProjects\\IPL_Analyser_Workshop\\src\\test\\resources\\WP_IPL2019FactsheetMostWkts.csv";

    //UC 1
    @Test
    public void mostRuns() throws IPLAnalyserException {
        IPLAnalyser iplAnalyserObj = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
        double maxAvg = iplAnalyserObj.topBattingAvg();
        Assert.assertEquals(83.2, maxAvg, 0.0);
    }

    // UC 2
    @Test
    public void givenIPLFile_FindMaxStrikeRate() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
        double maxStrikeRate = iplLeagueAnalyser.maxStrikingRates();
        Assert.assertEquals(333.33, maxStrikeRate, 0.0);
    }

    // UC 3
    @Test
    public void playerWithMaximum6Test() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
        String playerWithMax6 = iplLeagueAnalyser.playerWithMaxSixHit();
        Assert.assertEquals("Andre Russell", playerWithMax6);
    }

    @Test
    public void playerWithMaximum4Test() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
        String playerWithMax4 = iplLeagueAnalyser.playerWithMaxFourHit();
        Assert.assertEquals("Shikhar Dhawan", playerWithMax4);
    }

    // UC 4
    @Test
    public void playerWithMaximumStrikingRateWithSixsAndFours() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
        String playerWithBestPerformance = iplLeagueAnalyser.playerWithBestPerformace();
        Assert.assertEquals("Andre Russell",playerWithBestPerformance);
    }

    // UC 5
    @Test
    public void playerWithBestAverageAndBestStrinkingRate() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
        String playerWithBestBattingRate = iplLeagueAnalyser.playerWithBestAverageAndStrikingRate();
        Assert.assertEquals("MS Dhoni",playerWithBestBattingRate);
    }

    // UC 6
    @Test
    public void playerWithMostRunsAndBestAverage() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BATSMAN,MOST_RUNS_CSV_FILE_PATH);
        String playerWithMostRunsAndBestAvg = iplLeagueAnalyser.playerWithMostRunsAndBestAvg();
        Assert.assertEquals("David Warner",playerWithMostRunsAndBestAvg);
    }

    // UC 7
    @Test
    public void TopBowlingAverage() throws IPLAnalyserException {
        IPLAnalyser iplLeagueAnalyser = new IPLAnalyser(IPLAnalyser.PlayerType.BOWLER,MOST_WICKETS_CSV_FILE_PATH);
        Double topBowlingAvg = iplLeagueAnalyser.TopBowlingAvg();
        Assert.assertEquals(166.0,topBowlingAvg,0.0);
    }

}
