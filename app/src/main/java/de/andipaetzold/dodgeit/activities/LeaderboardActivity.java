package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
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

        // check internet connection
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            ((TextView) findViewById(R.id.leaderboard_textview_status)).setText(R.string.leaderboard_textview_offline);
        }

        // update list
        final LeaderboardAdapter adapter = new LeaderboardAdapter(LeaderboardActivity.this);

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

                findViewById(R.id.leaderboard_textview_status).setVisibility(View.GONE);
                findViewById(R.id.leaderboard_listview_list).setVisibility(View.VISIBLE);

                adapter.clear();
                adapter.addAll(records);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getApplicationContext(), "An error occurred", Toast.LENGTH_SHORT).show();
                LeaderboardActivity.this.finish();
            }
        });

        findViewById(R.id.leaderboard_listview_list).setVisibility(View.GONE);
        ((ListView) findViewById(R.id.leaderboard_listview_list)).setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BackgroundMusic.Play();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BackgroundMusic.Pause();
    }
}
