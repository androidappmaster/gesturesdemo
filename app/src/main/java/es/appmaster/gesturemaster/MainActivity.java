package es.appmaster.gesturemaster;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


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
                Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
