package de.andipaetzold.dodgeit.leaderboard;

public class LeaderboardRecord implements Comparable<LeaderboardRecord> {
    private String name;
    private int score;

    public LeaderboardRecord(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(LeaderboardRecord another) {
        return another.score - score;
    }
}
