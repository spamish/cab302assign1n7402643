/**
 * 
 */
package asgn1SoccerCompetition;

import java.util.ArrayList;

import asgn1Exceptions.CompetitionException;
import asgn1Exceptions.LeagueException;

/**
 * A class to model a soccer competition. The competition contains one or more number of leagues, 
 * each of which contain a number of teams. Over the course a season matches are played between
 * teams in each league. At the end of the season a premier (top ranked team) and wooden spooner 
 * (bottom ranked team) are declared for each league. If there are multiple leagues then relegation 
 * and promotions occur between the leagues.
 * 
 * @author Alan Woodley
 * @contributer Samuel Janetzki
 * @see https://bitbucket.org/cab302/asgn1release
 */
public class SoccerCompetition implements SportsCompetition{
	
	String name;
	ArrayList<SoccerLeague> leagues;
	
	/**
	 * Creates the model for a new soccer competition with a specific name,
	 * number of leagues and number of teams in each league
	 * 
	 * @param name The name of the competition.
	 * @param numLeagues The number of leagues in the competition.
	 * @param numTeams The number of teams in each league.
	 */
	// TO DO
	public SoccerCompetition(String name, int numLeagues, int numTeams){
		this.name = name;
		leagues = new ArrayList<SoccerLeague>(numLeagues);
		
		for (int i = 0; i < numLeagues; i++) {
			leagues.add(new SoccerLeague(numTeams));
		}
	}
	
	/**
	 * Retrieves a league with a specific number (indexed from 0). Returns an exception if the 
	 * league number is invalid.
	 * 
	 * @param leagueNum The number of the league to return.
	 * @return A league specified by leagueNum.
	 * @throws CompetitionException if the specified league number is less than 0.
	 *  or equal to or greater than the number of leagues in the competition.
	 */
	// TO DO
	public SoccerLeague getLeague(int leagueNum) throws CompetitionException{
		return leagues.get(leagueNum);
	}
	

	/**
	 * Starts a new soccer season for each league in the competition.
	 */
	// TO DO
	public void startSeason() {
		java.util.Iterator<SoccerLeague> iterator = leagues.iterator();
		
		try {
			while (iterator.hasNext()) {
				iterator.next().startNewSeason();
			}
		} catch (LeagueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/** 
	 * Ends the season of each of the leagues in the competition. 
	 * If there is more than one league then it handles promotion
	 * and relegation between the leagues.  
	 * 
	 */
	// TO DO
	public void endSeason()  {
		java.util.Iterator<SoccerLeague> iterator = leagues.iterator();
		java.util.LinkedList<SoccerTeam> promotion = new java.util.LinkedList<SoccerTeam>();
		java.util.LinkedList<SoccerTeam> demotion = new java.util.LinkedList<SoccerTeam>();
		
		try {
			while (iterator.hasNext()) {
				SoccerLeague value = iterator.next();
				value.endSeason();
				promotion.add(value.getTopTeam());
				demotion.add(value.getBottomTeam());
			}
		} catch (LeagueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		promotion.removeFirst();
		demotion.removeLast();
		
		for (int i = 1; i < leagues.size(); i++) {
			SoccerTeam proTeam = promotion.removeFirst();
			SoccerTeam demTeam = demotion.removeFirst();
			
			try {
				leagues.get(i).removeTeam(proTeam);
				leagues.get(i - 1).removeTeam(demTeam);

				leagues.get(i - 1).registerTeam(proTeam);
				leagues.get(i).registerTeam(demTeam);
			} catch (LeagueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** 
	 * For each league displays the competition standings.
	 */
	// TO DO
	public void displayCompetitionStandings(){
		int i = 0;
		System.out.println("+++++" + this.name + "+++++");
		java.util.Iterator<SoccerLeague> iterator = leagues.iterator();
		
		while (iterator.hasNext()) {
			System.out.println("---- League" + (++i) + " ----");
			System.out.println("Official Name" +  '\t' +  "Nick Name" + '\t' + "Form" + '\t' +  "Played" + '\t' + "Won" + '\t' + "Lost" + '\t' + "Drawn" + '\t' + "For" + '\t' + "Against" + '\t' + "GlDiff" + '\t' + "Points");
			
			iterator.next().displayLeagueTable();
		}
	}
	

}
