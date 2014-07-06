package android.graph;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RestaurantInformationActivity extends Fragment {

    private ListView listView;

    private ListAdapter listAdapter;

    private View fragmentView;

    private Activity parentActivity;

    private int restaurantRow;

    private int restPhoneNumberRow;
    
    private int restAddressRow;
    
    private int restHoursRow;

    private int nameRow;
    
    private int phoneNumRow;
    
    private int addrRow;
    
    private int hoursRow;


    private int logoutRow;

    private int rowCount;

    private Bundle data;
    
    private String restNameKor;
    private String restAddr;
    private String restPhoneNum;
    private String restPriceRange;
    private String restLike;
    private String restMonOpen;
    private String restTueOpen;
    private String restWedOpen;
    private String restThuOpen;
    private String restFriOpen;
    private String restSatOpen;
    private String restSunOpen;
    private String restMonClose;
    private String restTueClose;
    private String restWedClose;
    private String restThuClose;
    private String restFriClose;
    private String restSatClose;
    private String restSunClose;
    private String restMonHour;
    private String restTueHour;
    private String restWedHour;
    private String restThuHour;
    private String restFriHour;
    private String restSatHour;
    private String restSunHour;
    
    public static final int LENGTH3 = 3;
    @Override
    public final void onCreate(final
            Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        rowCount = 0;
        restaurantRow = rowCount++;
        nameRow = rowCount++;
        restPhoneNumberRow = rowCount++;
        phoneNumRow = rowCount++;
        restAddressRow = rowCount++;
        addrRow = rowCount++;
        restHoursRow = rowCount++;
        hoursRow = rowCount++;

    }

    @Override
    public final View onCreateView(final LayoutInflater inflater,
            final ViewGroup container,
            final Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.
                activity_restaurant_information, container, false);
        parentActivity = getActivity();
        listAdapter = new ListAdapter(parentActivity);
        System.out.println("listAdapter " + listAdapter);
        System.out.println(listAdapter == null);
        listView = (ListView) fragmentView.findViewById(R.id.listView);
        listView.setAdapter(listAdapter);
        
        data = getArguments();
        restNameKor = data.getString("NAMEKOR");
        restAddr = data.getString("ADDRESS");
        restPhoneNum = data.getString("PHONENUM");
        restPriceRange = data.getString("PRICERANGE");
        restLike = data.getString("LIKE");
        restMonOpen = data.getString("MONOPEN");
        restMonClose = data.getString("MONCLOSE");
        restMonHour = "MON:  " + restMonOpen + "   :   " + restMonClose;
        restTueOpen = data.getString("TUEOPEN");
        restTueClose = data.getString("TUECLOSE");
        restTueHour = "TUE:  " +restTueOpen + "   :   " + restTueClose;
        restWedOpen = data.getString("WEDOPEN");
        restWedClose = data.getString("WEDCLOSE");
        restWedHour = "WED:  " +restWedOpen + "   :   " + restWedClose;
        restThuOpen = data.getString("THUOPEN");
        restThuClose = data.getString("THUCLOSE");
        restThuHour = "THU:  " +restThuOpen + "   :   " + restThuClose;
        restFriOpen = data.getString("FRIOPEN");
        restFriClose = data.getString("FRICLOSE");
        restFriHour = "FRI:  " +restFriOpen + "   :   " + restFriClose;
        restSatOpen = data.getString("SATOPEN");
        restSatClose = data.getString("SATCLOSE");
        restSatHour = "SAT:  " +restSatOpen + "   :   " + restSatClose;
        restSunOpen = data.getString("SUNOPEN");
        restSunClose = data.getString("SUNCLOSE");
        restSunHour = "SUN:  " +restSunOpen + "   :   " + restSunClose;
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(final AdapterView<?> adapterView,
                    final View view, final int i, final long l) {
                if (i == logoutRow) {
                    AlertDialog.Builder builder = new AlertDialog.
                            Builder(parentActivity);
                    builder.setMessage(getString(R.string.areYouSure));
                    builder.setTitle(getString(R.string.app_name));
                    builder.setPositiveButton(getString(R.string.OK),
                            new DialogInterface.OnClickListener() {
                        public void onClick(final DialogInterface dialog,
                        final int which) {
                        }
                    });
                    builder.show().setCanceledOnTouchOutside(true);
                }
            }
        });
        return fragmentView;
    }
    
    @Override
    public final void onCreateOptionsMenu(final Menu menu,
            final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        parentActivity.getActionBar().setDisplayHomeAsUpEnabled(true);
        parentActivity.setTitle(getString(R.string.restaurant));
        inflater.inflate(R.menu.restaurantinformation, menu);
    }

    private class ListAdapter extends BaseAdapter {

        private Context mContext;

        public ListAdapter(final Context context) {
            mContext = context;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(final int i) {
            return i == logoutRow;
        }

        public int getCount() {
            return rowCount;
        }

        public Object getItem(final int i) {
            return null;
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
            int type = getItemViewType(i);
            View view = null;
            if (type == 0) {
                LayoutInflater li = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.restaurantinformation_section_layout,
                        viewGroup, false);
                TextView textView = (TextView) view.findViewById(
                        R.id.restaurantinformation_section_text);
                
                if (i == restaurantRow) {
                    textView.setText(getString(R.string.restaurant));
                } else if (i == restPhoneNumberRow) {
                    textView.setText(getString(R.string.phonenumber));
                } else if (i == restAddressRow) {
                    textView.setText(getString(R.string.address));
                } else if (i == restHoursRow) {
                    textView.setText(getString(R.string.hours));
                } 
            } else if (type == 1) {
                LayoutInflater li = (LayoutInflater) mContext.
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.restaurantinformation_row_two_layout,
                        viewGroup, false);
                TextView title = (TextView) view.findViewById(R.id.
                        restaurantinformation_row_title);
                SharedPreferences settings =  parentActivity.
                        getSharedPreferences(getString(
                        R.string.preferences_table), 0);
                if (i == nameRow) {
                    title.setText(restNameKor);
                } else if (i == phoneNumRow) {
                    title.setText(restPhoneNum);
                } else if (i == addrRow) {
                    title.setText(restAddr);
                } 
            } else if (type == 2) {
                LayoutInflater li = (LayoutInflater) mContext.
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = li.inflate(R.layout.restaurantinformation_row_five_layout,
                        viewGroup, false);
                
                View divider = view.findViewById(R.id.restaurantinformation_row_divider2);
                if (i == hoursRow) {
                    TextView monText = (TextView) view.findViewById(R.id.
                            restaurantinformation_row_mon);
                    TextView tueText = (TextView) view.findViewById(R.id.
                            restaurantinformation_row_tue);
                    TextView wedText = (TextView) view.findViewById(R.id.
                            restaurantinformation_row_wed);
                    TextView thuText = (TextView) view.findViewById(R.id.
                            restaurantinformation_row_thu);
                    TextView friText = (TextView) view.findViewById(R.id.
                            restaurantinformation_row_fri);
                    TextView satText = (TextView) view.findViewById(R.id.
                            restaurantinformation_row_sat);
                    TextView sunText = (TextView) view.findViewById(R.id.
                            restaurantinformation_row_sun);
                    monText.setText(restMonHour);
                    tueText.setText(restTueHour);
                    wedText.setText(restWedHour);
                    thuText.setText(restThuHour);
                    friText.setText(restFriHour);
                    satText.setText(restSatHour);
                    sunText.setText(restSunHour);
                    divider.setVisibility(View.INVISIBLE);
                }
            }  
            return view;
        }

        @Override
        public int getItemViewType(final int i) {
            if (i == restaurantRow || i == restAddressRow || i == restHoursRow || i == restPhoneNumberRow) {
                return 0;
            } else if (i == nameRow || i == phoneNumRow || i == addrRow) {
                return 1;
            } else if (i == hoursRow) {
                return 2;
            } else {
                return 1;
            }
        }

        @Override
        public int getViewTypeCount() {
            return LENGTH3;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    }
}
