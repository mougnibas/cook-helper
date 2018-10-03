package fr.mougnibas.cookhelper.android;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * A foreground task used to fill the application internal file storage from an online source.
 */
public class DataBackgroundService extends IntentService {

    /**
     * Class tag.
     */
    private static final String TAG = DataBackgroundService.class.getName();

    /**
     * No-arg constructor, required by the api.
     */
    public DataBackgroundService() {
        super("DataBackgroundService");
    }

    @Override
    public void onCreate() {

        // Call the superclass method
        super.onCreate();

        // Some logging
        Log.i(TAG, "onCreate");
    }

    /**
     * This method is invoked on the worker thread with a request to process.
     * Only one Intent is processed at a time, but the processing happens on a
     * worker thread that runs independently from other application logic.
     * So, if this code takes a long time, it will hold up other requests to
     * the same IntentService, but it will not hold up anything else.
     * When all requests have been handled, the IntentService stops itself,
     * so you should not call {@link #stopSelf}.
     *
     * @param intent The value passed to {@link
     *               Context#startService(Intent)}.
     *               This may be null if the service is being restarted after
     *               its process has gone away; see
     *               {@link Service#onStartCommand}
     *               for details.
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        // Some logging
        Log.i(TAG, "onHandleIntent (begin)");

        // Some heavy stuff to do
        try {

            // Simulate heavy IO wait
            Thread.sleep(5 * 1000);

            // Data file write
            File file = new File(getApplicationContext().getFilesDir(), "data.txt");
            PrintWriter writer = new PrintWriter(file);
            writer.println("Awesome work");
            writer.flush();
            writer.close();

        } catch (InterruptedException ex) {
            Log.e(TAG, "something go wrong while trying to pause the thread", ex);
        } catch (IOException ex) {
            Log.e(TAG, "something go wrong while trying to write the file", ex);
        }

        // Job is done. Can be stopped
        stopSelf();

        // Some logging
        Log.i(TAG, "onHandleIntent (end)");
    }

    @Override
    public void onDestroy() {

        // Call the superclass method
        super.onDestroy();

        // Some logging
        Log.i(TAG, "onDestroy");
    }
}
