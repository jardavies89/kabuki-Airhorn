package jared.kabukiairhorn;

import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KabukiActivity extends AppCompatActivity {

    @BindView(R.id.kabuki_button)
    ImageButton mKabukiButton;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kabuki);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        super.onDestroy();
    }

    private void init() {
        mKabukiButton.setOnTouchListener(kabukiButtonListener);
        mediaPlayer = MediaPlayer.create(this, R.raw.kabuki);
    }

    private View.OnTouchListener kabukiButtonListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    swapButtonAsset(true);
                    startTheKabuki();
                    break;
                case MotionEvent.ACTION_UP:
                    swapButtonAsset(false);
                    break;
            }

            return false;
        }
    };

    private void swapButtonAsset(boolean isSelected) {
        if (isSelected) {
            mKabukiButton.setImageDrawable(ActivityCompat.getDrawable(this, R.drawable.button_pressed));
        } else {
            mKabukiButton.setImageDrawable(ActivityCompat.getDrawable(this, R.drawable.button_not_pressed));
        }
    }

    private void startTheKabuki() {
        if(mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(0);
        }
        
        mediaPlayer.start();
    }


}
