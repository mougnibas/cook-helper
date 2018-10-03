package fr.mougnibas.cookhelper.android.ui.splashscreen;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.mougnibas.cookhelper.R;
import fr.mougnibas.cookhelper.android.SplashScreenActivity;

public class SplashScreenFragment extends Fragment {

    /**
     * Class tag, for logging.
     */
    private static final String TAG = SplashScreenFragment.class.getName();

    private SplashScreenViewModel mViewModel;

    public static SplashScreenFragment newInstance() {
        return new SplashScreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Some logging
        Log.i(TAG, "onCreateView");

        return inflater.inflate(R.layout.splash_screen_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Some logging
        Log.i(TAG, "onActivityCreated");

        mViewModel = ViewModelProviders.of(this).get(SplashScreenViewModel.class);
        // TODO: Use the ViewModel
    }

}
