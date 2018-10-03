package fr.mougnibas.cook_helper.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.mougnibas.cook_helper.cookhelper.fr.mougnibas.android.ui.splashscreen.SplashScreenFragment;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SplashScreenFragment.newInstance())
                    .commitNow();
        }
    }
}
