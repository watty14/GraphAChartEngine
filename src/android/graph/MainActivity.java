package android.graph;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graph.R;
import android.graph.R.anim;
import android.graph.R.id;
import android.graph.R.layout;

public class MainActivity extends Activity {

    /**
         * Instance variable for ServerUtility.
         */
    private ServerUtility server;

    /**
         * Instance variable for FragmentManager.
         */
    private FragmentManager fragmentManager;

    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        server = ServerUtility.getInstance();
        Fragment fragment = new CategoryActivity();
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
         * Adds the fragment.
     *
     * @param fragment The fragment to be added
     * @param tag The label of the fragment
         */
    public final void addFragment(final Fragment fragment, final String tag) {
        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.
                beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.no_anim_show,
                R.anim.no_anim,
                R.anim.no_anim_show, R.anim.slide_right_away);
        fragmentTransaction.replace(R.id.container, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
         * Finishes the fragment.
         */
    public final void finishFragment() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        }
    }

    /**
         * Performs action when back is pressed.
         */
    public final void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            finishFragment();
        } else {
            finish();
        }
    }
}
