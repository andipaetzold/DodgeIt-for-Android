package de.andipaetzold.dodgeit.activities;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import de.andipaetzold.dodgeit.R;
import de.andipaetzold.dodgeit.leaderboard.Leaderboard;
import de.andipaetzold.dodgeit.leaderboard.LeaderboardRecord;

public class ScoreDialog extends DialogFragment implements View.OnClickListener {
    private EditText editText;

    private int score;
    private Activity activity;

    public ScoreDialog() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_score, container);

        editText = (EditText) view.findViewById(R.id.nameDialog_editText_name);
        editText.requestFocus();

        view.findViewById(R.id.nameDialog_button_send).setOnClickListener(this);

        getDialog().setTitle("Game Over");

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nameDialog_button_send:
                String name = editText.getText().toString();

                if (name != "") {
                    Leaderboard.getInstance().submitScore(new LeaderboardRecord(name, score));
                }

                getDialog().dismiss();
                activity.finish();
                break;
        }
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
