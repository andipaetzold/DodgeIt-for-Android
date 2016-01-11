package de.andipaetzold.dodgeit.leaderboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.andipaetzold.dodgeit.App;

public class Leaderboard {
    private static Leaderboard instance = new Leaderboard();

    public static Leaderboard getInstance() {
        return instance;
    }

    private Leaderboard() {
    }

    public void submitScore(LeaderboardRecord lr) {
        List<LeaderboardRecord> leaderboard = getRecords();
        leaderboard.add(lr);

        // save
        try {
            // create json
            StringWriter swriter = new StringWriter();
            JsonWriter writer = new JsonWriter(swriter);
            writer.setIndent("  ");

            writer.beginArray();
            for (LeaderboardRecord record: leaderboard) {
                writer.beginObject();
                writer.name("name").value(record.getName());
                writer.name("score").value(record.getScore());
                writer.endObject();
            }
            writer.endArray();

            writer.close();

            // write to shared preference
            SharedPreferences settings = App.getContext().getSharedPreferences("DodgeIt", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("leaderboard", swriter.toString());
            editor.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<LeaderboardRecord> getRecords() {
        SharedPreferences settings = App.getContext().getSharedPreferences("DodgeIt", Context.MODE_PRIVATE);
        String leaderboard = settings.getString("leaderboard", "[]");

        List<LeaderboardRecord> output = new ArrayList<LeaderboardRecord>();

        try {
            JSONArray rootObject = new JSONArray(leaderboard);

            for (int i = 0; i < rootObject.length(); i++) {
                JSONObject record = rootObject.getJSONObject(i);

                String name = record.getString("name");
                int score = record.getInt("score");

                output.add(new LeaderboardRecord(name, score));
            }

        } catch (JSONException e) {
        }

        Collections.sort(output);

        return output;
    }
}
