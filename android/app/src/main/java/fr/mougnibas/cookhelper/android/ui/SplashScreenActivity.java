package fr.mougnibas.cookhelper.android.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import fr.mougnibas.cookhelper.android.R;
import fr.mougnibas.cookhelper.android.service.DataService;

/**
 * The application's splash screen activity.
 */
public class SplashScreenActivity extends Activity {

    /**
     * Class tag, for logging.
     */
    private static final String TAG = SplashScreenActivity.class.getName();

    /**
     * Intent to DataService.
     */
    private Intent dataServiceIntent;

    /**
     * Service connection instance.
     */
    private DataServiceConnection dataServiceConnection;

    /**
     * Service instance.
     */
    private DataService dataservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Some logging
        Log.i(TAG, "onCreate (begin)");

        // Base stuff
        super.onCreate(savedInstanceState);

        // Set the content view
        setContentView(R.layout.activity_splash_screen);

        // Reference the service intent and connection
        dataServiceIntent = new Intent(SplashScreenActivity.this, DataService.class);
        dataServiceConnection = new DataServiceConnection();

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
        // Use AsyncTask to update the loading bar and the result text view
        TextView textTextView = findViewById(R.id.splash_text);
        textTextView.setText(getString(R.string.loading_status_begin));
        new UpdateLoadStatusTask().execute();

        // Ask the data service to start to work
        startService(dataServiceIntent);

        // Also bind to the data service
        bindService(dataServiceIntent, dataServiceConnection, Context.BIND_ALLOW_OOM_MANAGEMENT);

        // Some logging
        Log.i(TAG, "onStart (end)");
    }

    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {

        // Some logging
        Log.i(TAG, "bindService (begin)");

        // Do the work
        boolean result = super.bindService(service, conn, flags);

        // Some logging
        Log.i(TAG, "bindService (end)");

        // Return the result
        return result;
    }

    @Override
    public void unbindService(ServiceConnection conn) {

        // Some logging
        Log.i(TAG, "unbindService (begin)");

        // Do the work
        super.unbindService(conn);

        // Some logging
        Log.i(TAG, "unbindService (end)");
    }

    @Override
    protected void onDestroy() {

        // Some logging
        Log.i(TAG, "onDestroy (begin)");

        // On destroy, also unbind the services.
        // This is a HUGE mindf*ck to me, because super.onDestroy *should* take care of unbinds.
        if (dataServiceConnection != null) {
            unbindService(dataServiceConnection);
        }

        // Base stuff to do
        super.onDestroy();

        // Some logging
        Log.i(TAG, "onDestroy (end)");
    }

    // ServiceConnection class
    private class DataServiceConnection implements ServiceConnection {

        /**
         * Inner class tag, for logging.
         */
        private final String INNER_TAG = DataServiceConnection.class.getName();

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            // Some logging
            Log.i(INNER_TAG, "onServiceConnected (begin)");

            // Get service instance. This will ALWAYS be this class instance.
            dataservice = ((DataService.LocalBinder) service).getService();

            // Logging
            Log.i(INNER_TAG, "onServiceConnected (end)");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            // Some logging
            Log.i(INNER_TAG, "onServiceDisconnected (begin)");

            // Dereference the service
            dataservice = null;

            // Some logging
            Log.i(INNER_TAG, "onServiceDisconnected (end)");
        }
    }

    /**
     * An async task used to update the UI about the download status.
     */
    private class UpdateLoadStatusTask extends AsyncTask<Void, Integer, String> {

        /**
         * Inner class tag, for logging.
         */
        private final String INNER_TAG = UpdateLoadStatusTask.class.getName();

        /**
         * Get the data service result.
         * The result may be null for various reasons (service not stated, not finished...).
         * If the result is null, then the download is not finished.
         * If the result is not null, the download is finished.
         * Please note that the service itself may be null, waiting to become initialized.
         *
         * @param voids (not used)
         * @return The data service result.
         */
        @Override
        protected String doInBackground(Void... voids) {

            // Some logging
            Log.i(INNER_TAG, "doInBackground (begin)");

            // Wait for data service to be bound
            while (dataservice == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e(INNER_TAG, "Exception while thead wait for service to be bound", e);
                }
            }

            // Wait for the data service result to be available
            while (!dataservice.isAvailable()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Log.e(INNER_TAG, "Exception while thead wait for service to be available", e);
                }
            }
            String result = dataservice.getResult();

            // Some logging
            Log.i(INNER_TAG, "doInBackground (end)");

            // Return the result
            return result;
        }

        /**
         * Update the UI (front text, progress bar and result text ).
         *
         * @param result The final result.
         */
        @Override
        protected void onPostExecute(String result) {

            // Update the front text view
            TextView textView = findViewById(R.id.splash_text);
            textView.setText(getString(R.string.loading_status_end));

            // Update the progress bar
            ProgressBar progressBar = findViewById(R.id.progressBar);
            progressBar.setProgress(100);

            // Update the result text view
            TextView resultTextView = findViewById(R.id.splash_result);
            resultTextView.setText(result);
        }
    }
}
