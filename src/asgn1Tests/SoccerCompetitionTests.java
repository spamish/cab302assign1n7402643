package asgn1Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;
import asgn1SoccerCompetition.SoccerCompetition;
import asgn1SoccerCompetition.SoccerLeague;
import asgn1SoccerCompetition.SoccerTeam;

/**
 * A set of JUnit tests for the asgn1SoccerCompetition.SoccerCompetition class
 *
 * @author Alan Woodley
 * @contributer Samuel Janetzki
 * @see https://bitbucket.org/cab302/asgn1release
 */
public class SoccerCompetitionTests {
	SoccerCompetition newCompetition;
	SoccerCompetition soccerCompetition;
	SoccerCompetition startedCompetition;
	SoccerTeam firstTeam = null;
	SoccerTeam secondTeam = null;
	SoccerTeam thirdTeam = null;
	SoccerTeam fourthTeam = null;
	
	@Before
	public void ConstructorSoccerLeague() throws CompetitionException {
		newCompetition = new SoccerCompetition("The Best Competition in the World", 2, 2);
		soccerCompetition = new SoccerCompetition("The Best Competition in the World", 2, 2);
		startedCompetition = new SoccerCompetition("The Best Competition in the World", 2, 2);
		
		try {
			firstTeam = new SoccerTeam("Alpha", "Winners");
			secondTeam = new SoccerTeam("Beta", "Losers");
			thirdTeam = new SoccerTeam("Gamma", "Extras");
			fourthTeam = new SoccerTeam("Delta", "Bonuses");
		} catch (TeamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SoccerLeague soccerLeague;
		
		soccerLeague = soccerCompetition.getLeague(0);
		try {
			soccerLeague.registerTeam(firstTeam);
			soccerLeague.registerTeam(secondTeam);
		} catch (LeagueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		soccerLeague = soccerCompetition.getLeague(1);
		try {
			soccerLeague.registerTeam(thirdTeam);
			soccerLeague.registerTeam(fourthTeam);
		} catch (LeagueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		soccerLeague = startedCompetition.getLeague(0);
		try {
			soccerLeague.registerTeam(firstTeam);
			soccerLeague.registerTeam(secondTeam);
		} catch (LeagueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		soccerLeague = startedCompetition.getLeague(1);
		try {
			soccerLeague.registerTeam(thirdTeam);
			soccerLeague.registerTeam(fourthTeam);
		} catch (LeagueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		startedCompetition.startSeason();
	}

	@Test
	public void TestGetLeague() throws CompetitionException {
		soccerCompetition.getLeague(0);
		soccerCompetition.getLeague(1);
	}

	@Test(expected = CompetitionException.class)
	public void TestGetLeagueBelowZero() throws CompetitionException {
		soccerCompetition.getLeague(-1);
	}

	@Test(expected = CompetitionException.class)
	public void TestGetLeagueAboveMax() throws CompetitionException {
		soccerCompetition.getLeague(2);
	}

	@Test
	public void TestStartSeason() throws CompetitionException {
		soccerCompetition.startSeason();
	}

	/*@Test
	public void TestStartSeasonStarted() throws CompetitionException {
		startedCompetition.startSeason();
	}*/

	/*@Test
	public void TestStartSeasonNotEnoughTeams() throws CompetitionException {
		newCompetition.startSeason();
	}*/

	@Test
	public void TestEndSeason() throws CompetitionException {
		startedCompetition.endSeason();
	}

	/*@Test
	public void TestEndSeasonEnded() throws CompetitionException {
		soccerCompetition.endSeason();
	}*/
	
}

