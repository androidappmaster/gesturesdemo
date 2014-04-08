package es.appmaster.gesturemaster;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import java.util.ArrayList;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;


public class MainActivity extends ActionBarActivity implements GestureOverlayView.OnGesturePerformedListener {

    private GestureLibrary gestureLib;
    private GestureOverlayView detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detector = (GestureOverlayView) findViewById(R.id.gesture_detector);
        gestureLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        gestureLib.load();
    }

    @Override
    protected void onResume() {
        super.onResume();

        detector.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gestureLib.recognize(gesture);

        for (Prediction prediction : predictions) {
            if (prediction.score > 1.0) {
                StringBuilder toastMessage = new StringBuilder();
                toastMessage.append(getString(R.string.gestures_detected));
                toastMessage.append("\n");
                toastMessage.append(prediction.name);

                Crouton.makeText(this, toastMessage, Style.INFO).show();
            }
        }
    }
}
