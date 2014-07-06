package android.graph;

import android.app.Activity;
import android.graph.R;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.app.Fragment;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SearchListActivity extends Fragment {

    private String search;

    private Bundle data;

    private View fragment;

    private ListView listView;

    private Activity parentActivity;

    private ServerUtility instance;

    private ProgressBar mProgressBar;
    
    private List<Restaurant> restaurants;

    @Override
    public final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public final View onCreateView(final LayoutInflater inflater, final
            ViewGroup container, final Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.activity_searchlist, container,
                    false);
        instance = ServerUtility.getInstance();
        parentActivity = getActivity();
        listView = (ListView) fragment.findViewById(R.id.listView);
        mProgressBar = (ProgressBar) fragment.findViewById(R.id.
                progressBar1);
        data = getArguments();
        search = data.getString("Name");

        new AsyncTaskGetAccounts().execute();
        listView.setOnItemClickListener(new OnItemClickListener() {
            
            public void onItemClick(final AdapterView<?> arg0,
                    final View arg1, final int arg2, final long arg3) {
                Bundle i = new Bundle();
//                objectId, restNameKor, 
//                restNameEng, restAddr, restPhoneNum, restPriceRange, restLike, restMonOpen,
//                restTueOpen, restWedOpen, restThuOpen, restFriOpen, restSatOpen, restSunOpen,
//                restMonClose, restTueClose, restWedClose, restThuClose, restFriClose, restSatClose,
//                restSunClose
                
                i.putString("NAMEKOR", restaurants.get(arg2).getRestNameKor());
                i.putString("ADDRESS", restaurants.get(arg2).getRestAddr());
                i.putString("PHONENUM", Integer.valueOf(restaurants.get(arg2).getRestPhoneNum()).toString());
                i.putString("PRICERANGE", Integer.valueOf(restaurants.get(arg2).getRestPriceRange()).toString());
                i.putString("LIKE", Integer.valueOf(restaurants.get(arg2).getRestLike()).toString());
                i.putString("MONOPEN", Integer.valueOf(restaurants.get(arg2).getRestMonOpen()).toString());
                i.putString("TUEOPEN", Integer.valueOf(restaurants.get(arg2).getRestTueOpen()).toString());
                i.putString("WEDOPEN", Integer.valueOf(restaurants.get(arg2).getRestWedOpen()).toString());
                i.putString("THUOPEN", Integer.valueOf(restaurants.get(arg2).getRestThuOpen()).toString());
                i.putString("FRIOPEN", Integer.valueOf(restaurants.get(arg2).getRestFriOpen()).toString());
                i.putString("SATOPEN", Integer.valueOf(restaurants.get(arg2).getRestSatOpen()).toString());
                i.putString("SUNOPEN", Integer.valueOf(restaurants.get(arg2).getRestSunOpen()).toString());
                i.putString("MONCLOSE", Integer.valueOf(restaurants.get(arg2).getRestMonClose()).toString());
                i.putString("TUECLOSE", Integer.valueOf(restaurants.get(arg2).getRestTueClose()).toString());
                i.putString("WEDCLOSE", Integer.valueOf(restaurants.get(arg2).getRestWedClose()).toString());
                i.putString("THUCLOSE", Integer.valueOf(restaurants.get(arg2).getRestThuClose()).toString());
                i.putString("FRICLOSE", Integer.valueOf(restaurants.get(arg2).getRestFriClose()).toString());
                i.putString("SATCLOSE", Integer.valueOf(restaurants.get(arg2).getRestSatClose()).toString());
                i.putString("SUNCLOSE", Integer.valueOf(restaurants.get(arg2).getRestSunClose()).toString());
               
                
                RestaurantInformationActivity frag = new RestaurantInformationActivity();
                frag.setArguments(i);
                ((MainActivity) parentActivity).addFragment(frag,
                    getString(R.string.title_activity_restaurantinformation));
            }
        });

        return fragment;
    }

    @Override
    public final void onCreateOptionsMenu(final Menu menu,
                final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(false);
        getActivity().setTitle(getString(R.string.title_activity_searchlist));
        inflater.inflate(R.menu.result, menu);
    }

    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.searchlist_menu_refresh:
        new AsyncTaskGetAccounts().execute();
            break;
        case R.id.searchlist_menu_main:
            getFragmentManager().popBackStack();
            break;
        case R.id.searchlist_menu_settings:

        ((MainActivity) parentActivity).
                    addFragment(new RecommandationActivity(),
                    getString(R.string.recommandation));
            break;
        default:
            super.onOptionsItemSelected(item);
        }
        return true;

    }

    private class ListAdapter extends BaseAdapter {
        private Context mContext;

        public ListAdapter(final Context context) {
            mContext = context;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return true;
        }

        @Override
        public boolean isEnabled(final int i) {
            return true;
        }

        public int getCount() {
            return restaurants.size();
        }

        public Object getItem(final int i) {
            return restaurants.get(i);
        }

        public long getItemId(final int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        public View getView(final int i, final View aView,
                        final ViewGroup viewGroup) {
            LayoutInflater li = (LayoutInflater) mContext.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = li.inflate(R.layout.restaurant_row_layout,
                    viewGroup, false);
            TextView restName = (TextView) view.findViewById(R.id.restName);
            TextView phoneNumber = (TextView) view.
                        findViewById(R.id.phoneNumber);
            TextView address = (TextView) view.findViewById(R.id.address);
            restName.setText(restaurants.get(i).getRestNameKor());
            phoneNumber.setText(Integer.valueOf(restaurants.get(i).getRestPhoneNum()).toString());
            address.setText(restaurants.get(i).getRestAddr());
            return view;
        }

        @Override
        public int getItemViewType(final int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }

    /**
     * AsyncTaskGetAccounts connects to serverutility.
     */
    private class AsyncTaskGetAccounts extends
        AsyncTask<Void, Void, ArrayList<Restaurant>> {

        @Override
        protected ArrayList<Restaurant> doInBackground(
                    final Void... params) {
            publishProgress();
            return instance.getRestaurants(search);
        }

        @Override
        protected void onProgressUpdate(final Void... params) {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(
                final ArrayList<Restaurant> lists) {
            super.onPostExecute(lists);
            mProgressBar.setVisibility(View.INVISIBLE);
            if (lists != null) {
                restaurants = lists;
                final ListAdapter arrayAdapter =
                    new ListAdapter(parentActivity);
                listView.setAdapter(arrayAdapter);
            }
        }
    }
}

