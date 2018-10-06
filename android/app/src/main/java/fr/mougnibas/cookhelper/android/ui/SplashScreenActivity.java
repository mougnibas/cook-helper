package fr.mougnibas.cookhelper.android.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import fr.mougnibas.cookhelper.android.R;
import fr.mougnibas.cookhelper.android.service.DataService;
import fr.mougnibas.cookhelper.android.service.DataBoundService;

/**
 * The application's splash screen activity.
 */
public class SplashScreenActivity extends Activity {

    /**
     * Class tag, for logging.
     */
    private static final String TAG = SplashScreenActivity.class.getName();

    /**
     * Service connection instance.
     */
    private MyServiceConnection myServiceConnection = new MyServiceConnection();

    /**
     * Service instance.
     */
    private DataBoundService dataBoundService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Some logging
        Log.i(TAG, "onCreate (begin)");

        // Base stuff
        super.onCreate(savedInstanceState);

        // Set the content view
        setContentView(R.layout.activity_splash_screen);

        // Some logging
        Log.i(TAG, "onCreate (end)");
    }

    @Override
    protected void onStart() {

        // Some logging
        Log.i(TAG, "onStart (begin)");

        // Call the super-method
        super.onStart();

        // Change UI label value
        // TODO Use AsyncTask to update the loading bar
        TextView textView = findViewById(R.id.splash_text);
        textView.setText("Loading data...");

        // Start the data background service
        startService(new Intent(SplashScreenActivity.this, DataService.class));

        // Some logging
        Log.i(TAG, "onStart (end)");
    }

    /**
     * Unbind the service.
     */
    private void doUnbindService() {

        // Unbind the service
        if (dataBoundService != null)
        {
            unbindService(myServiceConnection);
        }
    }

    @Override
    protected void onDestroy() {

        // Base stuff to do
        super.onDestroy();

        // Unbind nicely the service
        doUnbindService();

        // Some logging
        Log.i(TAG, "onDestroy");
    }

    // ServiceConnection class
    private class MyServiceConnection implements ServiceConnection {

        /**
         * Inner class tag, for logging.
         */
        private final String INNER_TAG = MyServiceConnection.class.getName();

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            // Get service instance. This will ALWAYS be this class instance.
            dataBoundService = ((DataBoundService.LocalBinder) service).getService();

            // Logging
            Log.i(INNER_TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            // Dereference the service
            dataBoundService = null;

            // Logging
            Log.i(INNER_TAG, "onServiceDisconnected");
        }
    };
}
