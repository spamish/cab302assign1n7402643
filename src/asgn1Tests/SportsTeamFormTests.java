package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1SoccerCompetition.SportsTeamForm;
import asgn1SportsUtils.WLD;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerTeamForm class
 *
 * @author Alan Woodley
 * @contributer Samuel Janetzki
 * @see https://bitbucket.org/cab302/asgn1release
 */
public class SportsTeamFormTests {
	SportsTeamForm sportsTeamForm;
	
	@Before
	public void ConstructSportsTeamForm() {
		sportsTeamForm = new SportsTeamForm();
	}
	
	// Testing for adding games
	
	@Test
	public void TestNoGamesPlayed() {
		int expectedResult = 0;
		assertEquals(expectedResult, sportsTeamForm.getNumGames());
	}
	
	@Test
	public void TestResultAdded() {
		sportsTeamForm.addResultToForm(WLD.WIN);

		int expectedResult = 1;
		assertEquals(expectedResult, sportsTeamForm.getNumGames());
	}
	
	@Test
	public void TestFiveResultsAdded() {
		for (int i = 0; i < 5; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		
		int expectedResult = 5;
		assertEquals(expectedResult, sportsTeamForm.getNumGames());
	}
	
	@Test
	public void TestSixResultAdded() {
		for (int i = 0; i < 6; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		
		int expectedResult = 5;
		assertEquals(expectedResult, sportsTeamForm.getNumGames());
	}

	// Testing for reset behaviour
	
	@Test
	public void TestResetResults() {
		for (int i = 0; i < 5; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		sportsTeamForm.resetForm();
		
		int expectedResult = 0;
		assertEquals(expectedResult, sportsTeamForm.getNumGames());
	}

	@Test
	public void TestResetAndAddResult() {
		for (int i = 0; i < 5; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		sportsTeamForm.resetForm();
		sportsTeamForm.addResultToForm(WLD.WIN);
		
		int expectedResult = 1;
		assertEquals(expectedResult, sportsTeamForm.getNumGames());
	}

	// Testing for string output
	
	@Test
	public void TestNoResultsString() {
		String expectedResult = "-----";
		assertEquals(expectedResult, sportsTeamForm.toString());
	}

	@Test
	public void TestSingleResultString() {
		sportsTeamForm.addResultToForm(WLD.DRAW);
		
		String expectedResult = "D----";
		assertEquals(expectedResult, sportsTeamForm.toString());
	}

	@Test
	public void TestThreeResultString() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		
		String expectedResult = "DLW--";
		assertEquals(expectedResult, sportsTeamForm.toString());
	}
	
	@Test
	public void TestFiveResultString() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		
		String expectedResult = "LWDLW";
		assertEquals(expectedResult, sportsTeamForm.toString());
	}
	
	@Test
	public void TestSixResultString() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		
		String expectedResult = "DLWDL";
		assertEquals(expectedResult, sportsTeamForm.toString());
	}
	
}
