import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fixture {
    private List<Team> teams;

    public Fixture(List<Team> teams) {
        if (teams.size() % 2 != 0) {
            teams.add(new Team(0,"Bay"));
        }
        this.teams = teams;
    }

    public List<String> generateFixtures(){
        List<String> fixtures = new ArrayList<>();
        Collections.shuffle(this.teams);

        int totalWeeks = this.teams.size()-1;
        int matchesPerWeek = this.teams.size()/2;

        for(int week = 1; week <matchesPerWeek ; week++ ){
            fixtures.add("Week "+week+":");

            for(int match = 0;match<matchesPerWeek;match++){
                Team homeTeam = this.teams.get(match);
                Team awayTeam = this.teams.get(this.teams.size() - 1 - match);

                if (!homeTeam.getName().equals("Bay") && !awayTeam.getName().equals("Bay")){
                    fixtures.add(homeTeam + " vs " + awayTeam);
                }
            }
            Collections.rotate(this.teams.subList(1, this.teams.size()),1);
        }
        return fixtures;
    }
}
