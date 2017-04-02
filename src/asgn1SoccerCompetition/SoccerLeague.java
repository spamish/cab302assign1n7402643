package asgn1SoccerCompetition;

import java.util.ArrayList;
//import java.util.Collections;

import asgn1Exceptions.LeagueException;
import asgn1Exceptions.TeamException;

/**
 * A class to model a soccer league. Matches are played between teams and points awarded for a win,
 * loss or draw. After each match teams are ranked, first by points, then by goal difference and then
 * alphabetically. 
 * 
 * @author Alan Woodley
 * @contributer Samuel Janetzki
 * @see https://bitbucket.org/cab302/asgn1release
 */
public class SoccerLeague implements SportsLeague{
	// Specifies the number of team required/limit of teams for the league
	private int requiredTeams;
	// Specifies is the league is in the off season
	private boolean offSeason;
	ArrayList<SoccerTeam> teams;
	
	/**
	 * Generates a model of a soccer team with the specified number of teams. 
	 * A season can not start until that specific number of teams has been added. 
	 * Once that number of teams has been reached no more teams can be added unless
	 * a team is first removed. 
	 * 
	 * @param requiredTeams The number of teams required/limit for the league.
	 */
	// TO DO
	public SoccerLeague (int requiredTeams){
		this.requiredTeams = requiredTeams;
		teams = new ArrayList<SoccerTeam>(requiredTeams);
	}

	
	/**
	 * Registers a team to the league.
	 * 
	 * @param team Registers a team to play in the league.
	 * @throws LeagueException If the season has already started, if the maximum number of 
	 * teams allowed to register has already been reached or a team with the 
	 * same official name has already been registered.
	 */
	// TO DO
	public void registerTeam(SoccerTeam team) throws LeagueException {
		teams.add(team);
	}
	
	/**
	 * Removes a team from the league.
	 * 
	 * @param team The team to remove
	 * @throws LeagueException if the season has not ended or if the team is not registered into the league.
	 */
	// TO DO
	public void removeTeam(SoccerTeam team) throws LeagueException{
		teams.remove(team);
	}
	
	/** 
	 * Gets the number of teams currently registered to the league
	 * 
	 * @return the current number of teams registered
	 */
	// TO DO
	public int getRegisteredNumTeams(){
		return teams.size();
	}
	
	/**
	 * Gets the number of teams required for the league to begin its 
	 * season which is also the maximum number of teams that can be registered
	 * to a league.

	 * @return The number of teams required by the league/maximum number of teams in the league
	 */
	public int getRequiredNumTeams(){
		return requiredTeams;
	}
	
	/** 
	 * Starts a new season by reverting all statistics for each team to initial values.
	 * 
	 * @throws LeagueException if the number of registered teams does not equal the required number of teams or if the season has already started
	 */
	// DONE
	public void startNewSeason() throws LeagueException{
		offSeason = false;
		java.util.Iterator<SoccerTeam> iterator = teams.iterator();
		
		while (iterator.hasNext()) {
			iterator.next().resetStats();
		}

		this.sortTeams();
	}
	

	/**
	 * Ends the season.
	 * 
	 * @throws LeagueException if season has not started
	 */
	// TO DO
	public void endSeason() throws LeagueException{
		offSeason = true;
	}
	
	/**
	 * Specifies if the league is in the off season (i.e. when matches are not played).
	 * @return True If the league is in its off season, false otherwise.
	 */
	public boolean isOffSeason(){
		return this.offSeason;
	}
	
	
	
	/**
	 * Returns a team with a specific name.
	 * 
	 * @param name The official name of the team to search for.
	 * @return The team object with the specified official name.
	 * @throws LeagueException if no team has that official name.
	 */
	// TO DO
	public SoccerTeam getTeamByOfficalName(String name) throws LeagueException{
		java.util.Iterator<SoccerTeam> iterator = teams.iterator();
		
		while (iterator.hasNext()) {
			SoccerTeam value = iterator.next();
			if (value.getOfficialName().equals(name)) {
				return value;
			}
		}
		
		return null;
	}
		
	/**
	 * Plays a match in a specified league between two teams with the respective goals. After each match the teams are
	 * resorted.
     *
	 * @param homeTeamName The name of the home team.
	 * @param homeTeamGoals The number of goals scored by the home team.
	 * @param awayTeamName The name of the away team.
	 * @param awayTeamGoals The number of goals scored by the away team.
	 * @throws LeagueException If the season has not started or if both teams have the same official name. 
	 */
	// TO DO
	public void playMatch(String homeTeamName, int homeTeamGoals, String awayTeamName, int awayTeamGoals) throws LeagueException{
		java.util.Iterator<SoccerTeam> iterator = teams.iterator();
		
		while (iterator.hasNext()) {
			SoccerTeam value = iterator.next();
			
			try {
				if (value.getOfficialName().equals(homeTeamName)) {
					value.playMatch(homeTeamGoals, awayTeamGoals);
				}
				
				if (value.getOfficialName().equals(awayTeamName)) {
					value.playMatch(awayTeamGoals, homeTeamGoals);
				}
			} catch (TeamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		this.sortTeams();
	}
	
	/**
	 * Displays a ranked list of the teams in the league  to the screen.
	 */
	// TO DO
	public void displayLeagueTable(){
		java.util.Iterator<SoccerTeam> iterator = teams.iterator();
		
		while (iterator.hasNext()) {
			iterator.next().displayTeamDetails();
		}
	}	
	
	/**
	 * Returns the highest ranked team in the league.
     *
	 * @return The highest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	// DONE
	public SoccerTeam getTopTeam() throws LeagueException{
		SoccerTeam value = teams.get(0);
		
		for (int i = 1; i < teams.size(); i++) {
			if (value.compareTo(teams.get(i)) > 0) {
				value = teams.get(i);
			}
		}
		
		return value;
	}

	/**
	 * Returns the lowest ranked team in the league.
     *
	 * @return The lowest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	// DONE
	public SoccerTeam getBottomTeam() throws LeagueException{
		SoccerTeam value = teams.get(0);
		
		for (int i = 1; i < teams.size(); i++) {
			if (value.compareTo(teams.get(i)) < 0) {
				value = teams.get(i);
			}
		}
		
		return value;
	}

	/** 
	 * Sorts the teams in the league.
	 */
	// DONE
    public void sortTeams(){
    	for (int i = 0; i < (teams.size() - 1); i++) {
    		for (int j = (i + 1); j < teams.size(); j++) {
    			if (teams.get(i).compareTo(teams.get(j)) > 0) {
    				SoccerTeam temp = teams.get(i);
    				
    				teams.set(i, teams.get(j));
    				teams.set(j, temp);
    			}
    		}
    	}
    }
    
    /**
     * Specifies if a team with the given official name is registered to the league.
     * 
     * @param name The name of a team.
     * @return True if the team is registered to the league, false otherwise. 
     */
    // DONE
    public boolean containsTeam(String name){
    	java.util.Iterator<SoccerTeam> iterator = teams.iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next().getOfficialName().equals(name)) {
				return true;
			}
		}
		
		return false;
    }
    
}
