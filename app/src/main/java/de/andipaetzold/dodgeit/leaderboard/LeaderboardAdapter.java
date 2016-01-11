package de.andipaetzold.dodgeit.leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.andipaetzold.dodgeit.R;

public class LeaderboardAdapter extends ArrayAdapter<LeaderboardRecord> {
    public LeaderboardAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            Context c = getContext();
            LayoutInflater inflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflator.inflate(R.layout.list_item_leaderboard, null);
        }

        LeaderboardRecord data = getItem(position);
        if (data != null) {
            ((TextView) convertView.findViewById(R.id.leaderboard_textview_position)).setText(String.valueOf(position + 1));
            ((TextView) convertView.findViewById(R.id.leaderboard_textview_name)).setText(data.getName());
            ((TextView) convertView.findViewById(R.id.leaderboard_textview_score)).setText(String.valueOf(data.getScore()));
        }
        return convertView;
    }
}
