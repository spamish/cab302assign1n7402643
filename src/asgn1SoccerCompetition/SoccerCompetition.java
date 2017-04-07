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
	// An array list containing the SoccerLeague objects for each league in the competition
	private ArrayList<SoccerLeague> leagues;
	
	/**
	 * Creates the model for a new soccer competition with a specific name,
	 * number of leagues and number of teams in each league
	 * 
	 * @param name The name of the competition.
	 * @param numLeagues The number of leagues in the competition.
	 * @param numTeams The number of teams in each league.
	 */
	public SoccerCompetition(String name, int numLeagues, int numTeams){
		this.name = name;
		this.leagues = new ArrayList<SoccerLeague>(numLeagues);
		
		// Add leagues in the competition
		for (int i = 0; i < numLeagues; i++) {
			this.leagues.add(new SoccerLeague(numTeams));
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
	public SoccerLeague getLeague(int leagueNum) throws CompetitionException{
		if (leagueNum < 0) throw new CompetitionException("SoccerCompetition.getLeague: League number less than Zero");
		if (leagueNum >= leagues.size()) throw new CompetitionException("SoccerCompetition.getLeague: League number out of range");
		
		return leagues.get(leagueNum);
	}

	/**
	 * Starts a new soccer season for each league in the competition.
	 */
	public void startSeason() {
		java.util.Iterator<SoccerLeague> iterator = this.leagues.iterator();
		
		// Loop through leagues and start each season
		while (iterator.hasNext()) {
			try {
				iterator.next().startNewSeason();
			} catch (LeagueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/** 
	 * Ends the season of each of the leagues in the competition. 
	 * If there is more than one league then it handles promotion
	 * and relegation between the leagues.  
	 * @throws LeagueException 
	 * 
	 */
	public void endSeason()  {
		java.util.Iterator<SoccerLeague> iterator = this.leagues.iterator();
		java.util.LinkedList<SoccerTeam> promotion = new java.util.LinkedList<SoccerTeam>();
		java.util.LinkedList<SoccerTeam> demotion = new java.util.LinkedList<SoccerTeam>();
		
		// End the season for each league and add both the top and bottom teams to the promotion and demotion linked list
		while (iterator.hasNext()) {
			SoccerLeague value = iterator.next();
			
			
			try {
				value.endSeason();
				promotion.add(value.getTopTeam());
				demotion.add(value.getBottomTeam());
			} catch (LeagueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
		
		// Remove the top team in the first league and the bottom team in the last league
		promotion.removeFirst();
		demotion.removeLast();
		
		// For each league remove the team to be promoted or demoted. Then register the team to their next league
		for (int i = 1; i < leagues.size(); i++) {
			SoccerTeam proTeam = promotion.removeFirst();
			SoccerTeam demTeam = demotion.removeFirst();

			try {
				this.leagues.get(i).removeTeam(proTeam);
				this.leagues.get(i - 1).removeTeam(demTeam);
	
				this.leagues.get(i - 1).registerTeam(proTeam);
				this.leagues.get(i).registerTeam(demTeam);
			} catch (LeagueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		}
	}

	/** 
	 * For each league displays the competition standings.
	 */
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
