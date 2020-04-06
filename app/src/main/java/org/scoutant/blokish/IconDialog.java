package org.scoutant.blokish;

import android.app.Dialog;
import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

class IconDialog extends Dialog {
    private OnClick listener;

    IconDialog(final Context context, int title_id) {
        super(context);
        setContentView(R.layout.simple_dialog);
        TextView tv = findViewById(R.id.title);
        tv.setText(title_id);
        getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        findViewById(R.id.no).setOnClickListener(v -> cancel());
        findViewById(R.id.yes).setOnClickListener(v -> {
            listener.onClick();
            cancel();
        });
    }

    void setListener(final OnClick listener) {
        this.listener = listener;
    }

    public interface OnClick {
        void onClick();
    }
}
