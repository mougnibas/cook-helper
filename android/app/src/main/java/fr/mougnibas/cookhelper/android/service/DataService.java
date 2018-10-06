package fr.mougnibas.cookhelper.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * An intent service used to handle application data, like fetching from an online source, retrieve it for views or manage a cache.
 * This service is intent to be ran indefinitely, because Activities start it AND bind to it.
 * Because of this, the service will NOT be destroyed when all clients unbind to it.
 */
public class DataService extends Service {

    /**
     * Class tag.
     */
    private static final String TAG = DataService.class.getName();

    /**
     * Local binder.
     */
    private IBinder binder = new LocalBinder();

    /**
     * The data retrieved from an online source.
     */
    private String result;

    @Override
    public void onCreate() {

        // Some logging
        Log.i(TAG, "onCreate (begin)");

        // The file filled with online data
        File dataFile = new File(getApplicationContext().getFilesDir(), "data.txt");

        // If the file already exist, just read it.
        // TODO Handle the case when online data has changed. Timestamp cache validity may be a solution
        if (dataFile.exists()) {

            // Just read the unique first line
            try {
                BufferedReader reader = new BufferedReader(new FileReader(dataFile));
                result = reader.readLine();
            } catch (IOException ex) {
                Log.e(TAG, "something go wrong while trying to write the file", ex);
            }

        } else {

            // Start a new worker thread, to not block the UI thread
            new Thread(new FetchOnlineData()).start();
        }

        // Call the superclass method
        super.onCreate();

        // Some logging
        Log.i(TAG, "onCreate (end)");
    }

    @Override
    public IBinder onBind(Intent intent) {

        // Some logging
        Log.i(TAG, "onBind (begin)");

        // Do the work
        IBinder result = binder;

        // Some logging
        Log.i(TAG, "onBind (end)");

        // Return the result
        return result;
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

    /**
     * A runnable task to fetch online data
     */
    private class FetchOnlineData implements Runnable {

        @Override
        public void run() {

            // Some heavy stuff to do
            try {

                // The file filled with online data
                File dataFile = new File(getApplicationContext().getFilesDir(), "data.txt");

                // The file don't exist. Fetch it online
                // Simulate heavy IO wait
                Thread.sleep(5 * 1000);

                // Fake data
                String fakeData = "Awesome work";

                // Data file write
                PrintWriter writer = new PrintWriter(dataFile);
                writer.println(fakeData);
                writer.flush();
                writer.close();

                // Put the result in cache
                result = fakeData;

            } catch (InterruptedException ex) {
                Log.e(TAG, "something go wrong while trying to pause the thread", ex);
            } catch (IOException ex) {
                Log.e(TAG, "something go wrong while trying to write the file", ex);
            }
        }
    }
}
