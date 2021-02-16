package shreyas.musicsearchapp.watcher;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by shreyasmp on 9/8/17.
 * <p>
 * Watcher for displaying clear button cross in search box
 */

public class TrackTextWatcher implements TextWatcher {

    private Context context;
    private EditText editText;
    private ImageView imageView;

    public TrackTextWatcher(Context context, EditText editText, ImageView imageView) {
        this.context = context;
        this.editText = editText;
        this.imageView = imageView;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (editText.getText().toString().isEmpty()) {
            imageView.setVisibility(GONE);
        } else {
            imageView.setVisibility(VISIBLE);
        }
    }
}
