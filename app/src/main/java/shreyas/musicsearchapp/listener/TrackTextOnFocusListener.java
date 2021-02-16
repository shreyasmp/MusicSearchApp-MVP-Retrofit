package shreyas.musicsearchapp.listener;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by shreyasmp on 9/8/17.
 */

public class TrackTextOnFocusListener implements View.OnFocusChangeListener {

    private Context context;
    private EditText editText;
    private ImageView imageView;

    public TrackTextOnFocusListener(Context context, EditText editText, ImageView imageView) {
        this.context = context;
        this.editText = editText;
        this.imageView = imageView;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (editText.getText().toString().isEmpty()) {
                imageView.setVisibility(GONE);
            } else {
                imageView.setVisibility(VISIBLE);
            }
        } else {
            if (editText.getText().toString().isEmpty()) {
                imageView.setVisibility(GONE);
            } else {
                imageView.setVisibility(GONE);
            }
        }
    }
}
