package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.leaderboard.Leaderboard;
import de.andipaetzold.dodgeit.leaderboard.LeaderboardAdapter;

public class LeaderboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        LeaderboardAdapter adapter = new LeaderboardAdapter(this);
        adapter.addAll(Leaderboard.getInstance().getRecords());

        ((ListView)findViewById(R.id.leaderboard_listview_list)).setAdapter(adapter);
    }
}
