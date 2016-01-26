package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.leaderboard.LeaderboardAdapter;
import de.andipaetzold.dodgeit.leaderboard.LeaderboardRecord;
import de.andipaetzold.dodgeit.util.BackgroundMusic;

public class LeaderboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Firebase.setAndroidContext(getApplicationContext());
        Firebase firebaseRef = new Firebase("https://dodgeit.firebaseio.com");
        firebaseRef.child("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<LeaderboardRecord> records = new ArrayList<LeaderboardRecord>();
                for (DataSnapshot recordSnap : dataSnapshot.getChildren()) {
                    String name = (String) recordSnap.child("name").getValue();
                    Long score = (Long) recordSnap.child("score").getValue();

                    records.add(new LeaderboardRecord(name, score.intValue()));
                }

                Collections.sort(records);

                final LeaderboardAdapter adapter = new LeaderboardAdapter(LeaderboardActivity.this);
                adapter.addAll(records);
                ((ListView) findViewById(R.id.leaderboard_listview_list)).setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(),"An error occurred", Toast.LENGTH_SHORT).show();

                LeaderboardActivity.this.finish();
            }
        });

        Toast.makeText(getApplicationContext(), "Loading...", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BackgroundMusic.getInstance().Play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundMusic.getInstance().Pause();
    }
}
