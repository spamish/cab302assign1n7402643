package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;


/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerLeage class
 *
 * @author Alan Woodley
 * @contributer Samuel Janetzki
 * @see https://bitbucket.org/cab302/asgn1release
 */
public class SoccerLeagueTests {
	SoccerLeague soccerLeague;
	SoccerTeam firstTeam = null;
	SoccerTeam secondTeam = null;
	SoccerTeam thirdTeam = null;
	
	@Before
	public void ConstructorSoccerLeague() throws LeagueException {
		soccerLeague = new SoccerLeague(2);
		
		try {
			firstTeam = new SoccerTeam("Alpha", "Winners");
			secondTeam = new SoccerTeam("Beta", "Losers");
			thirdTeam = new SoccerTeam("Gamma", "Extras");
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		soccerLeague.registerTeam(firstTeam);
		soccerLeague.registerTeam(secondTeam);
	}

	@Test
	public void TestRegisterTeam() throws LeagueException {
		SoccerLeague testLeague = new SoccerLeague(2);
		testLeague.registerTeam(firstTeam);
		
		int expectedResult = 1;
		assertEquals(expectedResult, testLeague.getRegisteredNumTeams());
	}

	@Test(expected = LeagueException.class)
	public void TestRegisterTeamFull() throws LeagueException {
		soccerLeague.registerTeam(thirdTeam);
	}

	@Test(expected = LeagueException.class)
	public void TestRegisterTeamSeasonStarted() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.registerTeam(thirdTeam);
	}

	@Test(expected = LeagueException.class)
	public void TestRegisterTeamSameName() throws LeagueException {
		soccerLeague.registerTeam(firstTeam);
	}

	@Test
	public void TestRemoveTeam() throws LeagueException {
		soccerLeague.removeTeam(secondTeam);
		
		int expectedResult = 1;
		assertEquals(expectedResult, soccerLeague.getRegisteredNumTeams());
	}

	@Test(expected = LeagueException.class)
	public void TestRemoveTeamEmpty() throws LeagueException {
		SoccerLeague newLeague = new SoccerLeague(2);
		newLeague.removeTeam(firstTeam);
	}

	@Test(expected = LeagueException.class)
	public void TestRemoveTeamSeasonNotEnded() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.removeTeam(firstTeam);
	}

	@Test(expected = LeagueException.class)
	public void TestRemoveTeamNotRegistered() throws LeagueException {
		soccerLeague.removeTeam(thirdTeam);
	}

	@Test
	public void TestGetRegisteredNumberOfTeams() throws LeagueException {
		SoccerLeague newLeague = new SoccerLeague(2);
		int expectedResult = 0;
		assertEquals(expectedResult++, newLeague.getRegisteredNumTeams());
		
		newLeague.registerTeam(firstTeam);
		assertEquals(expectedResult++, newLeague.getRegisteredNumTeams());
		
		newLeague.registerTeam(secondTeam);
		assertEquals(expectedResult++, newLeague.getRegisteredNumTeams());
	}

	@Test
	public void TestGetRequiredNumberOfTeams() {
		SoccerLeague firstLeague = new SoccerLeague(0);
		SoccerLeague secondLeague = new SoccerLeague(1);
		SoccerLeague thirdLeague = new SoccerLeague(10);
		
		int firstResult = 0;
		assertEquals(firstResult, firstLeague.getRequiredNumTeams());
		int secondResult = 1;
		assertEquals(secondResult, secondLeague.getRequiredNumTeams());
		int thirdResult = 10;
		assertEquals(thirdResult, thirdLeague.getRequiredNumTeams());
	}

	@Test
	public void TestStartNewSeason() throws LeagueException {
		assertTrue("season shouldn't have started", soccerLeague.isOffSeason());
		soccerLeague.startNewSeason();
		assertTrue("season should have started", !soccerLeague.isOffSeason());
	}

	@Test(expected = LeagueException.class)
	public void TestStartNewSeasonNotEnoughTeams() throws LeagueException {
		soccerLeague.removeTeam(firstTeam);
		soccerLeague.startNewSeason();
	}

	@Test(expected = LeagueException.class)
	public void TestStartNewSeasonAlreadyStarted() throws LeagueException {
		soccerLeague.startNewSeason();
		assertTrue("season should have started", !soccerLeague.isOffSeason());
		soccerLeague.startNewSeason();
	}

	@Test
	public void TestEndSeason() throws LeagueException {
		soccerLeague.startNewSeason();
		assertTrue("season should have started", !soccerLeague.isOffSeason());
		soccerLeague.endSeason();
		assertTrue("season should have ended", soccerLeague.isOffSeason());
	}

	@Test(expected = LeagueException.class)
	public void TestEndSeasonNotStarted() throws LeagueException {
		soccerLeague.endSeason();
	}

	@Test
	public void TestGetTeamByOfficialName() throws LeagueException {
		SoccerTeam soccerTeam = soccerLeague.getTeamByOfficalName("Alpha");
		
		assertSame(firstTeam, soccerTeam);
	}

	@Test(expected = LeagueException.class)
	public void TestGetTeamByOfficialNameNotRegistered() throws LeagueException {
		soccerLeague.getTeamByOfficalName("Gamma");
	}

	@Test
	public void TestPlayMatch() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("Alpha", 5, "Beta", 7);
		
		int expectedAlphaGoals = 5;
		int expectedBetaGoals = 7;	
		SoccerTeam alphaTeam = soccerLeague.getTeamByOfficalName("Alpha");
		SoccerTeam betaTeam = soccerLeague.getTeamByOfficalName("Beta");
		assertEquals("alpha scored   ", expectedAlphaGoals, alphaTeam.getGoalsScoredSeason());
		assertEquals("alpha conceded ", expectedBetaGoals, alphaTeam.getGoalsConcededSeason());
		assertEquals("beta  scored   ", expectedBetaGoals, betaTeam.getGoalsScoredSeason());
		assertEquals("beta  conceded ", expectedAlphaGoals, betaTeam.getGoalsConcededSeason());
	}

	@Test(expected = LeagueException.class)
	public void TestPlayMatchSeasonNotStarted() throws LeagueException {
		soccerLeague.playMatch("Alpha", 5, "Beta", 7);
	}

	@Test(expected = LeagueException.class)
	public void TestPlayMatchSameName() throws LeagueException {
		soccerLeague.startNewSeason();
		soccerLeague.playMatch("Alpha", 5, "Alpha", 7);
	}

	@Test
	public void TestGetTopTeam() throws LeagueException {
		assertSame(firstTeam, soccerLeague.getTopTeam());
	}

	@Test(expected = LeagueException.class)
	public void TestGetTopTeamNoTeams() throws LeagueException {
		SoccerLeague newLeague = new SoccerLeague(2);
		newLeague.getTopTeam();
	}

	@Test(expected = LeagueException.class)
	public void TestGetTopTeamLessTeams() throws LeagueException {
		soccerLeague.removeTeam(firstTeam);
		soccerLeague.getTopTeam();
	}

	@Test
	public void TestGetBottomTeam() throws LeagueException {
		assertSame(secondTeam, soccerLeague.getBottomTeam());
	}

	@Test(expected = LeagueException.class)
	public void TestGetBottomTeamNoTeams() throws LeagueException {
		SoccerLeague newLeague = new SoccerLeague(2);
		newLeague.getBottomTeam();
	}

	@Test(expected = LeagueException.class)
	public void TestGetBottomTeamLessTeams() throws LeagueException {
		soccerLeague.removeTeam(firstTeam);
		soccerLeague.getBottomTeam();
	}

	@Test
	public void TestSortTeams() throws LeagueException {
		SoccerLeague newLeague = new SoccerLeague(3);
		
		newLeague.registerTeam(secondTeam);
		newLeague.registerTeam(thirdTeam);
		newLeague.registerTeam(firstTeam);
		
		newLeague.sortTeams();
		
		assertSame(firstTeam, newLeague.getTopTeam());
		assertSame(thirdTeam, newLeague.getBottomTeam());
	}

	@Test
	public void TestContainsTeamRegistered() throws LeagueException {
		assertTrue(soccerLeague.containsTeam("Beta"));
	}

	@Test
	public void TestContainsTeamNotRegistered() throws LeagueException {
		assertTrue(!soccerLeague.containsTeam("Gamma"));
	}
	
}
