package fr.mougnibas.cookhelper.android.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * An intent service used to handle application data, like fetching from an online source, retrieve it for views or manage a cache.
 */
public class DataService extends IntentService {

    /**
     * Class tag.
     */
    private static final String TAG = DataService.class.getName();

    /**
     * No-arg constructor, required by the api.
     */
    public DataService() {
        super(DataService.class.getName());
    }

    /**
     * The data retrieved from an online source.
     */
    private String result;

    @Override
    public void onCreate() {

        // Some logging
        Log.i(TAG, "onCreate (begin)");

        // Call the superclass method
        super.onCreate();

        // Some logging
        Log.i(TAG, "onCreate (end)");
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

            // The file filled with online data
            File file = new File(getApplicationContext().getFilesDir(), "data.txt");

            // If the file already exist, just read it.
            // TODO Handle the case when online data has changed. Timestamp cache validity may be a solution
            if (file.exists()) {

                // Just read the unique first line
                BufferedReader reader = new BufferedReader(new FileReader(file));
                result = reader.readLine();

            } else {

                // The file don't exist. Fetch it online
                // Simulate heavy IO wait
                Thread.sleep(5 * 1000);

                // Fake data
                String fakeData = "Awesome work";

                // Data file write
                PrintWriter writer = new PrintWriter(file);
                writer.println(fakeData);
                writer.flush();
                writer.close();

                // Put the result in cache
                result = fakeData;
            }

        } catch (InterruptedException ex) {
            Log.e(TAG, "something go wrong while trying to pause the thread", ex);
        } catch (IOException ex) {
            Log.e(TAG, "something go wrong while trying to write the file", ex);
        }

        // Job is done. Can be stopped.
        stopSelf();

        // Some logging
        Log.i(TAG, "onHandleIntent (end)");
    }

    /**
     * Get the service result.
     *
     * @return the service result.
     */
    public String getResult() {

        // Some logging
        Log.i(TAG, "getResult (begin)");

        // The result must be fetched. Throw an exception if it's not the case.
        if (result == null) {

            // Create an exception
            String msg = "This method is not supposed to be called before the service has not" +
                    "finished to handle the intent";
            RuntimeException ex = new RuntimeException(msg);

            // Log it
            Log.e(TAG, msg, ex);

            // Throw it
            throw ex;
        }

        // Some logging
        Log.i(TAG, "getResult (end)");

        // Return the result
        return result;
    }

    @Override
    public void onDestroy() {

        // Some logging
        Log.i(TAG, "onDestroy (begin)");

        // Call the superclass method
        super.onDestroy();

        // Some logging
        Log.i(TAG, "onDestroy (end)");
    }

    /**
     * Local binder, used by UI to access this service.
     */
    public class LocalBinder extends Binder {
        public DataService getService() {
            return DataService.this;
        }
    }
}
