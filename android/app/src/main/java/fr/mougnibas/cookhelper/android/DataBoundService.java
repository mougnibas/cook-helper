package fr.mougnibas.cookhelper.android;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * A bound service, used to read data from the application internal file storage.
 */
public class DataBoundService extends Service {

    /**
     * Class tag, for logging.
     */
    private static final String TAG = DataBoundService.class.getName();

    /**
     * Instance of local binder.
     */
    private LocalBinder localBinder = new LocalBinder();

    /**
     * Service result.
     */
    private String result;

    @Override
    public void onCreate() {

        // Base stuff to do
        super.onCreate();

        // Some logging
        Log.i(TAG, "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {

        // Some logging
        Log.i(TAG, "onBind");

        // Base stuff to do
        return localBinder;
    }

    /**
     * Get the service result.
     * @return the service result.
     */
    public  String getResult() {

        // Read the result from internal file only once (cache it)
        if (result == null) {

            try {
                File file = new File(getApplicationContext().getFilesDir(), "data.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                result = reader.readLine();
            } catch (IOException ex) {
                String msg = "something go wrong while trying to read the file";
                Log.e(TAG, msg, ex);
                throw new RuntimeException(msg, ex);
            }
        }

        // Return the result
        return result;
    }

    @Override
    public void onDestroy() {

        // Base stuff to do
        super.onDestroy();

        // Some logging
        Log.i(TAG, "onDestroy");
    }


    /**
     * Local binder, used by UI to access this service.
     */
    public class LocalBinder extends Binder {
        DataBoundService getService() {
            return DataBoundService.this;
        }
    }
}
