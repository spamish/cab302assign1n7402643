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
	
	@Test
	public void TestNoGamesPlayed() {
		int testAnswer = sportsTeamForm.getNumGames();
		int expectedAnswer = 0;
		assertEquals(expectedAnswer, testAnswer);
	}
	
	@Test
	public void TestResultAdded() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		int testAnswer = sportsTeamForm.getNumGames();
		int expectedAnswer = 1;
		assertEquals(expectedAnswer, testAnswer);
	}
	
	@Test
	public void TestFiveResultsAdded() {
		for (int i = 0; i < 5; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		int testAnswer = sportsTeamForm.getNumGames();
		int expectedAnswer = 5;
		assertEquals(expectedAnswer, testAnswer);
	}
	
	@Test
	public void TestSixResultAdded() {
		for (int i = 0; i < 6; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		int testAnswer = sportsTeamForm.getNumGames();
		int expectedAnswer = 5;
		assertEquals(expectedAnswer, testAnswer);
	}

	@Test
	public void TestResetResults() {
		for (int i = 0; i < 5; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		sportsTeamForm.resetForm();
		int testAnswer = sportsTeamForm.getNumGames();
		int expectedAnswer = 0;
		assertEquals(expectedAnswer, testAnswer);
	}

	@Test
	public void TestResetAndAddResult() {
		for (int i = 0; i < 5; i++) {
			sportsTeamForm.addResultToForm(WLD.WIN);
		}
		sportsTeamForm.resetForm();
		sportsTeamForm.addResultToForm(WLD.WIN);
		int testAnswer = sportsTeamForm.getNumGames();
		int expectedAnswer = 1;
		assertEquals(expectedAnswer, testAnswer);
	}

	@Test
	public void TestNoResultsString() {
		String testAnswer = sportsTeamForm.toString();
		String expectedAnswer = "-----";
		assertEquals(expectedAnswer, testAnswer);
	}

	@Test
	public void TestSingleResultString() {
		sportsTeamForm.addResultToForm(WLD.DRAW);
		String testAnswer = sportsTeamForm.toString();
		String expectedAnswer = "D----";
		assertEquals(expectedAnswer, testAnswer);
	}
	
	@Test
	public void TestFiveResultString() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		String testAnswer = sportsTeamForm.toString();
		String expectedAnswer = "LWDLW";
		assertEquals(expectedAnswer, testAnswer);
	}
	
	@Test
	public void TestSixResultString() {
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		sportsTeamForm.addResultToForm(WLD.WIN);
		sportsTeamForm.addResultToForm(WLD.LOSS);
		sportsTeamForm.addResultToForm(WLD.DRAW);
		String testAnswer = sportsTeamForm.toString();
		String expectedAnswer = "DLWDL";
		assertEquals(expectedAnswer, testAnswer);
	}
}
