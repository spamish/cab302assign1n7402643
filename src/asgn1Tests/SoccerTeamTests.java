package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 * @contributer Samuel Janetzki
 * @see https://bitbucket.org/cab302/asgn1release
 */
public class SoccerTeamTests {
	SoccerTeam soccerTeam;
	
	// Testing for constructor exception handling
	
	@Before
	public void ConstructorSoccerTeam() throws TeamException {
		soccerTeam = new SoccerTeam("Official", "Nickname");
	}

	@Test(expected = TeamException.class)
	public void ConstructorNoTeamNames() throws TeamException {
		new SoccerTeam("", "");
	}

	@Test(expected = TeamException.class)
	public void ConstructorNoOfficialName() throws TeamException {
		new SoccerTeam("", "Nickname");
	}

	@Test(expected = TeamException.class)
	public void ConstructorNoNickname() throws TeamException {
		new SoccerTeam("Official", "");
	}
	
	// Testing for retrieving team names

	@Test
	public void TestGetOfficialName() {
		String expectedResult = "Official";
		assertEquals(expectedResult, soccerTeam.getOfficialName());
	}

	@Test
	public void TestGetNickname() {
		String expectedResult = "Nickname";
		assertEquals(expectedResult, soccerTeam.getNickName());
	}
	
	// Testing for goals scored behaviour

	@Test
	public void TestNoGoalsScored() {
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalsScoredSeason());
	}

	@Test
	public void TestThreeGoalsScored() throws TeamException {
		soccerTeam.playMatch(3, 0);
		
		int expectedResult = 3;
		assertEquals(expectedResult, soccerTeam.getGoalsScoredSeason());
	}

	@Test
	public void TestMinimumGoalsScored() throws TeamException {
		soccerTeam.playMatch(0, 0);
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalsScoredSeason());
	}

	@Test(expected = TeamException.class)
	public void TestNegativeGoalsScored() throws TeamException {
		soccerTeam.playMatch(-1, 0);
	}

	@Test
	public void TestMaximumGoalsScored() throws TeamException {
		soccerTeam.playMatch(20, 0);
		
		int expectedResult = 20;
		assertEquals(expectedResult, soccerTeam.getGoalsScoredSeason());
	}

	@Test(expected = TeamException.class)
	public void TestExcessiveGoalsScored() throws TeamException {
		soccerTeam.playMatch(21, 0);
	}

	@Test
	public void TestMultipleGoalsScored() throws TeamException {
		soccerTeam.playMatch(3, 0);
		soccerTeam.playMatch(4, 0);
		
		int expectedResult = 7;
		assertEquals(expectedResult, soccerTeam.getGoalsScoredSeason());
	}

	@Test
	public void TestResetGoalsScored() throws TeamException {
		soccerTeam.playMatch(3, 0);
		soccerTeam.resetStats();
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalsScoredSeason());
	}

	@Test
	public void TestResetAddGoalsScored() throws TeamException {
		soccerTeam.playMatch(3, 0);
		soccerTeam.resetStats();
		soccerTeam.playMatch(4, 0);
		
		int expectedResult = 4;
		assertEquals(expectedResult, soccerTeam.getGoalsScoredSeason());
	}

	// Testing for goals conceded behaviour

	@Test
	public void TestNoGoalsConceded() {
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalsConcededSeason());
	}

	@Test
	public void TestThreeGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, 3);
		
		int expectedResult = 3;
		assertEquals(expectedResult, soccerTeam.getGoalsConcededSeason());
	}

	@Test
	public void TestMinimumGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, 0);
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalsConcededSeason());
	}

	@Test(expected = TeamException.class)
	public void TestNegativeGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, -1);
	}

	@Test
	public void TestMaximumGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, 20);
		
		int expectedResult = 20;
		assertEquals(expectedResult, soccerTeam.getGoalsConcededSeason());
	}

	@Test(expected = TeamException.class)
	public void TestExcessiveGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, 21);
	}

	@Test
	public void TestMultipleGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, 3);
		soccerTeam.playMatch(0, 4);
		
		int expectedResult = 7;
		assertEquals(expectedResult, soccerTeam.getGoalsConcededSeason());
	}

	@Test
	public void TestResetGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, 3);
		soccerTeam.resetStats();
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalsConcededSeason());
	}

	@Test
	public void TestResetAddGoalsConceded() throws TeamException {
		soccerTeam.playMatch(0, 3);
		soccerTeam.resetStats();
		soccerTeam.playMatch(0, 4);
		
		int expectedResult = 4;
		assertEquals(expectedResult, soccerTeam.getGoalsConcededSeason());
	}

	// Testing for win, loss and draw behaviour

	@Test
	public void TestNoWLD() {
		int expectedResult = 0;
		assertEquals("getMatchesWon", expectedResult, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedResult, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedResult, soccerTeam.getMatchesDrawn());
	}

	@Test
	public void TestResetWLD() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		
		int expectedResult = 0;
		assertEquals("getMatchesWon", expectedResult, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedResult, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedResult, soccerTeam.getMatchesDrawn());
	}

	@Test
	public void TestGamesWon() throws TeamException {
		soccerTeam.playMatch(5, 3);
		
		int expectedWin = 1;
		int expectedLoss = 0;
		int expectedDraw = 0;
		assertEquals("getMatchesWon", expectedWin, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedLoss, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedDraw, soccerTeam.getMatchesDrawn());
	}

	@Test
	public void TestResetAddWins() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(5, 3);
		
		int expectedWin = 1;
		int expectedLoss = 0;
		int expectedDraw = 0;
		assertEquals("getMatchesWon", expectedWin, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedLoss, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedDraw, soccerTeam.getMatchesDrawn());
	}

	@Test
	public void TestGamesLost() throws TeamException {
		soccerTeam.playMatch(3, 5);
		
		int expectedWin = 0;
		int expectedLoss = 1;
		int expectedDraw = 0;
		assertEquals("getMatchesWon", expectedWin, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedLoss, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedDraw, soccerTeam.getMatchesDrawn());
	}

	@Test
	public void TestResetAddLosses() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(3, 5);
		
		int expectedWin = 0;
		int expectedLoss = 1;
		int expectedDraw = 0;
		assertEquals("getMatchesWon", expectedWin, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedLoss, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedDraw, soccerTeam.getMatchesDrawn());
	}

	@Test
	public void TestGamesDrawn() throws TeamException {
		soccerTeam.playMatch(3, 3);
		
		int expectedWin = 0;
		int expectedLoss = 0;
		int expectedDraw = 1;
		assertEquals("getMatchesWon", expectedWin, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedLoss, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedDraw, soccerTeam.getMatchesDrawn());
	}
	
	@Test
	public void TestResetAddDraws() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(3, 3);
		
		int expectedWin = 0;
		int expectedLoss = 0;
		int expectedDraw = 1;
		assertEquals("getMatchesWon", expectedWin, soccerTeam.getMatchesWon());
		assertEquals("getMatchesLost", expectedLoss, soccerTeam.getMatchesLost());
		assertEquals("getMatchesDrawn", expectedDraw, soccerTeam.getMatchesDrawn());
	}
	
	// Testing for competition points calculation
	
	@Test
	public void TestCompetitionPointsNoGames() {
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}

	@Test
	public void TestCompetitionPointsWW() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(5, 3);
		
		int expectedResult = 6;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}
	
	@Test
	public void TestCompetitionPointsWL() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 5);
		
		int expectedResult = 3;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}
	
	@Test
	public void TestCompetitionPointsWD() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		
		int expectedResult = 4;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}

	@Test
	public void TestCompetitionPointsLL() throws TeamException {
		soccerTeam.playMatch(3, 5);
		soccerTeam.playMatch(3, 5);
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}
	
	@Test
	public void TestCompetitionPointsLD() throws TeamException {
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		
		int expectedResult = 1;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}

	@Test
	public void TestCompetitionPointsDD() throws TeamException {
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 3);
		
		int expectedResult = 2;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}

	@Test
	public void TestCompetitionPointsReset() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}

	@Test
	public void TestCompetitionPointsResetAddGame() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(5, 3);
		
		int expectedResult = 3;
		assertEquals(expectedResult, soccerTeam.getCompetitionPoints());
	}
	
	// Testing for goal difference calculation

	@Test
	public void TestGoalDifferenceNoGames() {
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}

	@Test
	public void TestGoalDifferencePositive() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(9, 7);
		
		int expectedResult = 4;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}
	
	@Test
	public void TestGoalDifferenceNetural() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(7, 9);
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}
	
	@Test
	public void TestGoalDifferenceNegative() throws TeamException {
		soccerTeam.playMatch(3, 5);
		soccerTeam.playMatch(7, 9);
		
		int expectedResult = -4;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}
	
	@Test
	public void TestGoalDifferenceReset() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}

	@Test
	public void TestGoalDifferenceResetPositive() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(4, 3);
		soccerTeam.playMatch(6, 5);
		
		int expectedResult = 2;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}

	@Test
	public void TestGoalDifferenceResetNeutral() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(4, 3);
		soccerTeam.playMatch(5, 6);
		
		int expectedResult = 0;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}

	@Test
	public void TestGoalDifferenceResetNegative() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(3, 4);
		soccerTeam.playMatch(5, 6);
		
		int expectedResult = -2;
		assertEquals(expectedResult, soccerTeam.getGoalDifference());
	}
	
	// Testing for form string formatting
	
	@Test
	public void TestFormStringNoGames() {
		String expectedResult = "-----";
		assertEquals(expectedResult, soccerTeam.getFormString());
	}
	
	@Test
	public void TestFormStringSingleGame() throws TeamException {
		soccerTeam.playMatch(5, 3);
		
		String expectedResult = "W----";
		assertEquals(expectedResult, soccerTeam.getFormString());
	}
	
	@Test
	public void TestFormStringThreeGames() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		
		String expectedResult = "LDW--";
		assertEquals(expectedResult, soccerTeam.getFormString());
	}
	
	@Test
	public void TestFormStringFiveGames() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		
		String expectedResult = "DWLDW";
		assertEquals(expectedResult, soccerTeam.getFormString());
	}
	
	@Test
	public void TestFormStringSixGames() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		
		String expectedResult = "LDWLD";
		assertEquals(expectedResult, soccerTeam.getFormString());
	}
	
	@Test
	public void TestFormStringReset() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		
		String expectedResult = "-----";
		assertEquals(expectedResult, soccerTeam.getFormString());
	}
	
	@Test
	public void TestFormStringResetAddGame() throws TeamException {
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		soccerTeam.playMatch(3, 5);
		soccerTeam.resetStats();
		soccerTeam.playMatch(5, 3);
		soccerTeam.playMatch(3, 3);
		
		String expectedResult = "DW---";
		assertEquals(expectedResult, soccerTeam.getFormString());
	}

	// Testing for comparison behaviour

	@Test
	public void TestCompareToNamesSameTeamNoGames() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Alpha", "Ones");
		SoccerTeam referenceTeam = new SoccerTeam("Alpha", "Ones");

		assertTrue("return of compareTo should be 0", referenceTeam.compareTo(comparisonTeam) == 0);
	}

	@Test
	public void TestCompareToNamesBetterTeamNoGames() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Beta", "Twos");
		SoccerTeam referenceTeam = new SoccerTeam("Gamma", "Threes");
		
		assertTrue("return of compareTo should be +ve", referenceTeam.compareTo(comparisonTeam) > 0);
	}

	@Test
	public void TestCompareToNamesWorseTeamNoGames() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Epsilon", "Fives");
		SoccerTeam referenceTeam = new SoccerTeam("Delta", "Fours");

		assertTrue("return of compareTo should be -ve", referenceTeam.compareTo(comparisonTeam) < 0);
	}

	@Test
	public void TestCompareToNamesSameTeam() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Alpha", "Ones");
		SoccerTeam referenceTeam = new SoccerTeam("Alpha", "Ones");
		
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);
		
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);

		assertTrue("return of compareTo should be 0", referenceTeam.compareTo(comparisonTeam) == 0);
	}

	@Test
	public void TestCompareToNamesBetterTeam() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Beta", "Twos");
		SoccerTeam referenceTeam = new SoccerTeam("Gamma", "Threes");
		
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);
		
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);

		assertTrue("return of compareTo should be +ve", referenceTeam.compareTo(comparisonTeam) > 0);
	}

	@Test
	public void TestCompareToNamesWorseTeam() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Epsilon", "Fives");
		SoccerTeam referenceTeam = new SoccerTeam("Delta", "Fours");
		
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);
		
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);

		assertTrue("return of compareTo should be -ve", referenceTeam.compareTo(comparisonTeam) < 0);
	}

	@Test
	public void TestCompareToGoalDifferenceBetterTeam() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Official", "Nickname");
		SoccerTeam referenceTeam = new SoccerTeam("Official", "Nickname");
		
		comparisonTeam.playMatch(7, 0);
		comparisonTeam.playMatch(0, 3);
		comparisonTeam.playMatch(0, 3);

		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		
		assertTrue("return of compareTo should be +ve", referenceTeam.compareTo(comparisonTeam) > 0);
	}

	@Test
	public void TestCompareToGoalDifferenceWorseTeam() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Official", "Nickname");
		SoccerTeam referenceTeam = new SoccerTeam("Official", "Nickname");
		
		comparisonTeam.playMatch(5, 0);
		comparisonTeam.playMatch(0, 3);
		comparisonTeam.playMatch(0, 3);

		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		
		assertTrue("return of compareTo should be -ve", referenceTeam.compareTo(comparisonTeam) < 0);
	}

	@Test
	public void TestCompareToCompetitionPointsBetterTeam() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Official", "Nickname");
		SoccerTeam referenceTeam = new SoccerTeam("Official", "Nickname");
		
		comparisonTeam.playMatch(5, 0);
		comparisonTeam.playMatch(0, 5);
		comparisonTeam.playMatch(3, 3);

		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		referenceTeam.playMatch(3, 3);
		
		assertTrue("return of compareTo should be +ve", referenceTeam.compareTo(comparisonTeam) > 0);
	}

	@Test
	public void TestCompareToCompetitionPointsWorseTeam() throws TeamException {
		SoccerTeam comparisonTeam = new SoccerTeam("Official", "Nickname");
		SoccerTeam referenceTeam = new SoccerTeam("Official", "Nickname");
		
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);
		comparisonTeam.playMatch(5, 5);

		referenceTeam.playMatch(4, 0);
		referenceTeam.playMatch(0, 4);
		referenceTeam.playMatch(2, 2);
		
		assertTrue("return of compareTo should be -ve", referenceTeam.compareTo(comparisonTeam) < 0);
	}
	
}