package org.scoutant.blokish;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

class EndGameDialog extends Dialog {
    EndGameDialog(final Context context, boolean redwins, String message, final int level) {
        super(context);
        setContentView(R.layout.endgame);
        // Cf layout issue http://groups.google.com/group/android-developers/browse_thread/thread/f0bb813f643604ec?pli=1
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        TextView tv = findViewById(R.id.message);
        tv.setText(message);
        View b = findViewById(R.id.ok);
        b.setOnClickListener(v -> EndGameDialog.this.dismiss());
        findViewById(R.id.icons).setVisibility(redwins ? View.VISIBLE : View.GONE);
    }
}
