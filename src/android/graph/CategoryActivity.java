package android.graph;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class CategoryActivity extends Fragment {

    private Button searchButton;
    
    private Button category1;
    
    private Button category2;
    
    private Button category3;
    
    private EditText searchText;

    private View fragment;

    private Activity parentActivity;

    private ServerUtility instance;


    private ProgressBar mProgressBar;

    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public final View onCreateView(final LayoutInflater inflater, final
            ViewGroup container, final Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.activity_category, container,
                    false);
        
        instance = ServerUtility.getInstance();
        parentActivity = getActivity();
        
        searchButton = (Button) fragment.findViewById(R.id.searchButton);
        searchText = (EditText) fragment.findViewById(R.id.searchEditText);
        category1 = (Button) fragment.findViewById(R.id.category1Button);
        category2 = (Button) fragment.findViewById(R.id.category2Button);
        category3 = (Button) fragment.findViewById(R.id.category3Button);
        
        searchButton.setOnClickListener(new View.OnClickListener() {
            
            public void onClick(View v) {
                Bundle i = new Bundle();
                try {
                    String search = searchText.getText().toString();
                    i.putString("Name", search);
                    SearchListActivity frag = new SearchListActivity();
                    frag.setArguments(i);
                    ((MainActivity) parentActivity).addFragment(frag, getString(R.string.title_activity_searchlist));
                } catch (NullPointerException e1) {
                    Activity activity = getActivity();
                    Toast.makeText(activity, "Empty...", Toast.LENGTH_SHORT).show();
                }

            }
        });
        
        mProgressBar = (ProgressBar) fragment.findViewById(R.id.
                progressBar1);
        
        return fragment;
    }


    @Override
    public final void onCreateOptionsMenu(final Menu menu,
                final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
        getActivity().setTitle(getString(R.string.app_name));
        inflater.inflate(R.menu.search, menu);
    }

    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.main_menu_refresh:
            return true;
        case R.id.main_menu_results:

        ((MainActivity) parentActivity).
                    addFragment(new SearchListActivity(),
                    getString(R.string.result));
            break;
        case R.id.main_menu_settings:

        ((MainActivity) parentActivity).
                    addFragment(new RecommandationActivity(),
                    getString(R.string.recommandation));
            break;
        default:
            super.onOptionsItemSelected(item);
        }
        return true;

    }
}
