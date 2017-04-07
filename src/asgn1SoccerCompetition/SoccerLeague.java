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
	// An array list containing the SoccerTeam objects for each team registered.
	private ArrayList<SoccerTeam> teams;
	
	/**
	 * Generates a model of a soccer team with the specified number of teams. 
	 * A season can not start until that specific number of teams has been added. 
	 * Once that number of teams has been reached no more teams can be added unless
	 * a team is first removed. 
	 * 
	 * @param requiredTeams The number of teams required/limit for the league.
	 */
	public SoccerLeague (int requiredTeams) {
		this.requiredTeams = requiredTeams;
		this.teams = new ArrayList<SoccerTeam>(requiredTeams);
		this.offSeason = true;
	}

	
	/**
	 * Registers a team to the league.
	 * 
	 * @param team Registers a team to play in the league.
	 * @throws LeagueException If the season has already started, if the maximum number of 
	 * teams allowed to register has already been reached or a team with the 
	 * same official name has already been registered.
	 */
	public void registerTeam(SoccerTeam team) throws LeagueException {
		if (!offSeason) throw new LeagueException("SoccerLeague.registerTeam: Season has already started");
		if (teams.size() >= requiredTeams) throw new LeagueException("SoccerLeague.registerTeam: Maximum number of teams registered");
		if (teams.contains(team)) throw new LeagueException("SoccerLeague.registerTeam: Team already registered");
		
		// Append team to this league
		this.teams.add(team);
	}
	
	/**
	 * Removes a team from the league.
	 * 
	 * @param team The team to remove
	 * @throws LeagueException if the season has not ended or if the team is not registered into the league.
	 */
	public void removeTeam(SoccerTeam team) throws LeagueException{
		if (!offSeason) throw new LeagueException("SoccerLeague.removeTeam: Season hasn't ended");
		if (!teams.contains(team)) throw new LeagueException("SoccerLeague.removeTeam: Team not registered");
		
		// Remove team from this league
		this.teams.remove(team);
	}
	
	/** 
	 * Gets the number of teams currently registered to the league
	 * 
	 * @return the current number of teams registered
	 */
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
	public void startNewSeason() throws LeagueException{
		if (!offSeason) throw new LeagueException("SoccerLeague.startNewSeason: Season has already started");
		if (teams.size() != requiredTeams) throw new LeagueException("SoccerLeague.startNewSeason: Not enough teams registered");
		
		// Set this league to on season
		this.offSeason = false;
		java.util.Iterator<SoccerTeam> iterator = this.teams.iterator();
		
		// Iterate through each team and reset stats
		while (iterator.hasNext()) {
			iterator.next().resetStats();
		}

		// Sort the teams in alphabetical order
		this.sortTeams();
	}
	
	/**
	 * Ends the season.
	 * 
	 * @throws LeagueException if season has not started
	 */
	public void endSeason() throws LeagueException{
		if (offSeason) throw new LeagueException("SoccerLeague.endSeason: Season hasn't started");
		
		// Set this league to off season
		this.offSeason = true;
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
	public SoccerTeam getTeamByOfficalName(String name) throws LeagueException{
		if (!this.containsTeam(name)) throw new LeagueException("SoccerLeague.getTeamByOfficialName: Team not registered");
		
		// Set value as a null soccer team and create an iterator to loop through each team
		SoccerTeam value = null;
		java.util.Iterator<SoccerTeam> iterator = teams.iterator();
		
		// If a match is found then break and return the team
		while (iterator.hasNext()) {
			value = iterator.next();
			if (value.getOfficialName().equals(name)) {
				break;
			}
		}
		
		return value;
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
	public void playMatch(String homeTeamName, int homeTeamGoals, String awayTeamName, int awayTeamGoals) throws LeagueException{
		if (offSeason) throw new LeagueException("SoccerLeague.playMatch: Season hasn't started");
		if (homeTeamName.equals(awayTeamName)) throw new LeagueException("SoccerLeague.playMatch: Both teams the same");
		
		// Create iterator to loop through each team
		java.util.Iterator<SoccerTeam> iterator = this.teams.iterator();
		
		while (iterator.hasNext()) {
			SoccerTeam value = iterator.next();

			// Find the home team and add the game outcome
			if (value.getOfficialName().equals(homeTeamName)) {
				try {
					value.playMatch(homeTeamGoals, awayTeamGoals);
				} catch (TeamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
				
			// Find the away team and add the the game outcome 
			if (value.getOfficialName().equals(awayTeamName)) {
				try {
					value.playMatch(awayTeamGoals, homeTeamGoals);
				} catch (TeamException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		// Update the leaderboard to match new points given
		this.sortTeams();
	}
	
	/**
	 * Displays a ranked list of the teams in the league  to the screen.
	 */
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
	public SoccerTeam getTopTeam() throws LeagueException{
		if (teams.size() < requiredTeams) throw new LeagueException("SoccerLeague.getTopTeam: Number of teams less than required");
		
		// Get the first element and hold it in a temporary team object
		SoccerTeam value = teams.get(0);
		
		// If any subsequent elements have a higher score than the current temporary team then replace it with that team
		for (int i = 1; i < teams.size(); i++) {
			if (value.compareTo(teams.get(i)) > 0) {
				value = teams.get(i);
			}
		}
		
		// Return the top team
		return value;
	}

	/**
	 * Returns the lowest ranked team in the league.
     *
	 * @return The lowest ranked team in the league. 
	 * @throws LeagueException if the number of teams is zero or less than the required number of teams.
	 */
	public SoccerTeam getBottomTeam() throws LeagueException{
		if (teams.size() < requiredTeams) throw new LeagueException("SoccerLeague.getBottomTeam: Number of teams less than required");
		
		// Get the first element and hold it in a temporary team object
		SoccerTeam value = teams.get(0);

		// If any subsequent elements have a lower score than the current temporary team then replace it with that team
		for (int i = 1; i < teams.size(); i++) {
			if (value.compareTo(teams.get(i)) < 0) {
				value = teams.get(i);
			}
		}

		// Return the bottom team
		return value;
	}

	/** 
	 * Sorts the teams in the league.
	 */
    public void sortTeams(){
    	// Insertion Sort in descending order of score
    	for (int i = 1; i < teams.size(); i++) {
    		SoccerTeam temp = teams.get(i);
    		int j = i - 1;
    		
    		while ((j >= 0) && (teams.get(j).compareTo(temp)) > 0) {
    			this.teams.set(j + 1, teams.get(j));
    			j--;
    		}
    		
    		this.teams.set(j + 1, temp);
    	}
    }
    
    /**
     * Specifies if a team with the given official name is registered to the league.
     * 
     * @param name The name of a team.
     * @return True if the team is registered to the league, false otherwise. 
     */
    public boolean containsTeam(String name){
    	java.util.Iterator<SoccerTeam> iterator = teams.iterator();
		
    	// Loop through teams and return true if a match is found
		while (iterator.hasNext()) {
			if (iterator.next().getOfficialName().equals(name)) {
				return true;
			}
		}
		
		return false;
    }
}
