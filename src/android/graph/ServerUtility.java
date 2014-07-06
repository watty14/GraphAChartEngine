package android.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ServerUtility {

	//Table names

    /**
    *
    */
   public static final String TABLE_USER = "User";
   public static final String TABLE_OWNER = "OWNER";
   
   public static final String TABLE_MENU = "MENU";
   public static final String TABLE_RESTAURANT = "RESTAURANT";
   
   // User Columns
   public static final String COLUMN_USERNAME = "username";
   
   // Restaurant Columns
   public static final String COLUMN_RESTNAMEKOR = "restNameKor";
   public static final String COLUMN_RESTADDR = "restAddr";
   public static final String COLUMN_RESTNAMEENG = "restNameEng";
   public static final String COLUMN_RESTPHONENUM = "restPhoneNum";
   public static final String COLUMN_RESTPRICERANGE = "restPriceRange";
   public static final String COLUMN_RESTLIKE = "restLike";
   public static final String COLUMN_RESTMONOPEN = "restMonOpen";
   public static final String COLUMN_RESTTUEOPEN = "restTueOpen";
   public static final String COLUMN_RESTWEDOPEN = "restWedOpen";
   public static final String COLUMN_RESTTHUOPEN = "restThuOpen";
   public static final String COLUMN_RESTFRIOPEN = "restFriOpen";
   public static final String COLUMN_RESTSATOPEN = "restSatOpen";
   public static final String COLUMN_RESTSUNOPEN = "restSunOpen";
   public static final String COLUMN_RESTMONCLOSE = "restMonClose";
   public static final String COLUMN_RESTTUECLOSE = "restTueClose";
   public static final String COLUMN_RESTWEDCLOSE = "restWedClose";
   public static final String COLUMN_RESTTHUCLOSE = "restThuClose";
   public static final String COLUMN_RESTFRICLOSE = "restFriClose";
   public static final String COLUMN_RESTSATCLOSE = "restSatClose";
   public static final String COLUMN_RESTSUNCLOSE = "restSunClose";
   public static final String COLUMN_UPDATEDAT = "updatedAt";
   public static final String COLUMN_ID = "objectId";
   public static final String COLUMN_DATE = "createdAt";
   
   // Menu table columns
   public static final String COLUMN_MENUMAIN = "menuMain";
   public static final String COLUMN_MENUNAME = "menuName";
   public static final String COLUMN_RESTID = "restId";

    private static ServerUtility instance = null;

    private final String applicationID =
        "2Wq0mQ8he5Ij9ZeO1aEmrdpSdUBpJOVP3QOpNYyf";

    private final String clientKey =
        "rvBu3YaaPdPTwns9M3qnPMCQl0qwuR87FnXtlQKz";

    protected ServerUtility() {
        //protects from instantiating attempts
    }

    public static ServerUtility getInstance() {
        if (instance == null) {
            instance = new ServerUtility();
        }
        return instance;
    }

    public void initalize(Context context) {
        Parse.initialize(context, applicationID, clientKey);
    }

    public boolean isAlreadyLoggedIn() {
        return ParseUser.getCurrentUser() != null;
    }

    public void logOutUser() {
    	ParseUser.logOut();
    }

    public boolean logInUser(final String username,
    		final String password) {
        ParseUser user = null;
        try {
            user = ParseUser.logIn(username, password);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return user != null;
    }

    public Boolean signUpUser(final String username,
    		final String email, final String password) {
        ParseUser user = new ParseUser();
        boolean created = false;
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        try {
            user.signUp();
            created = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return created;
    }
    
    public Boolean addOwner(String username) {
    	ParseObject owner = new ParseObject(TABLE_OWNER);
        owner.put(COLUMN_USERNAME, username);
        try {
            owner.save();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    	return true;
    }
    /**
     *
     *
     * @param table
     * @param key
     * @param value
     * @return
     */
    private ParseObject queryFirst(final String table,
    		final String key, final Object value) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(table);
        ParseObject result = null;
        query.whereEqualTo(key, value);
        try {
            result = query.getFirst();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     *
     * @param object
     * @param column
     * @return
     */
    private JSONArray getJSONArray(final ParseObject object,
    		final String column) {
        JSONArray array = object.getJSONArray(column);
        try {
            if (array.get(0).equals("")) {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }
    
    public final ArrayList<Restaurant> getRestaurants(String search) {
        ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
        ArrayList<Restaurant> listByName = new ArrayList<Restaurant>();
        ArrayList<Restaurant> listByMenu = new ArrayList<Restaurant>();
        
        listByName = (ArrayList<Restaurant>) getRestaurantsRestHelper(search);
        listByMenu = (ArrayList<Restaurant>) getRestaurantsMenuHelper(search);
        for (Restaurant name : listByName) {
            if (!restaurantList.contains(name)) {
                restaurantList.add(name);
            }
        }
        for (Restaurant name : listByMenu) {
            if (!restaurantList.contains(name)) {
                restaurantList.add(name);
            }
        }
        return restaurantList;
    }
    
    private static List<Restaurant> getRestaurantsRestHelper(String search) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_RESTAURANT);
        query.whereEqualTo(COLUMN_RESTNAMEENG, search);
        List<ParseObject> results = null;
        try {
            results = query.find();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        ArrayList<Restaurant> list = new ArrayList<Restaurant>();
        if (results != null) {
            for (ParseObject result : results) {
                String objectId = result.getObjectId();
                String restNameKor = result.getString(COLUMN_RESTNAMEKOR);
                String restNameEng = result.getString(COLUMN_RESTNAMEENG);
                String restAddr = result.getString(COLUMN_RESTADDR);
                int restPhoneNum = result.getInt(COLUMN_RESTPHONENUM);
                int restPriceRange = result.getInt(COLUMN_RESTPRICERANGE);
                int restLike = result.getInt(COLUMN_RESTLIKE);
                int restMonOpen = result.getInt(COLUMN_RESTMONOPEN);
                int restTueOpen = result.getInt(COLUMN_RESTTUEOPEN);
                int restWedOpen = result.getInt(COLUMN_RESTWEDOPEN);
                int restThuOpen = result.getInt(COLUMN_RESTTHUOPEN);
                int restFriOpen = result.getInt(COLUMN_RESTFRIOPEN);
                int restSatOpen = result.getInt(COLUMN_RESTSATOPEN);
                int restSunOpen = result.getInt(COLUMN_RESTSUNOPEN);
                int restMonClose = result.getInt(COLUMN_RESTMONCLOSE);
                int restTueClose = result.getInt(COLUMN_RESTTUECLOSE);
                int restWedClose = result.getInt(COLUMN_RESTWEDCLOSE);
                int restThuClose = result.getInt(COLUMN_RESTTHUCLOSE);
                int restFriClose = result.getInt(COLUMN_RESTFRICLOSE);
                int restSatClose = result.getInt(COLUMN_RESTSATCLOSE);
                int restSunClose = result.getInt(COLUMN_RESTSUNCLOSE);
                
                Restaurant rest = new Restaurant(objectId, restNameKor, 
                        restNameEng, restAddr, restPhoneNum, restPriceRange, restLike, restMonOpen,
                        restTueOpen, restWedOpen, restThuOpen, restFriOpen, restSatOpen, restSunOpen,
                        restMonClose, restTueClose, restWedClose, restThuClose, restFriClose, restSatClose,
                        restSunClose);
                list.add(rest);
            }
        }
        
        query.whereEqualTo(COLUMN_RESTNAMEKOR, search);
        List<ParseObject> results2 = null;
        try {
            results2 = query.find();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        if (results2 != null) {
            for (ParseObject result : results2) {
                String objectId = result.getObjectId();
                String restNameKor = result.getString(COLUMN_RESTNAMEKOR);
                String restNameEng = result.getString(COLUMN_RESTNAMEENG);
                String restAddr = result.getString(COLUMN_RESTADDR);
                int restPhoneNum = result.getInt(COLUMN_RESTPHONENUM);
                int restPriceRange = result.getInt(COLUMN_RESTPRICERANGE);
                int restLike = result.getInt(COLUMN_RESTLIKE);
                int restMonOpen = result.getInt(COLUMN_RESTMONOPEN);
                int restTueOpen = result.getInt(COLUMN_RESTTUEOPEN);
                int restWedOpen = result.getInt(COLUMN_RESTWEDOPEN);
                int restThuOpen = result.getInt(COLUMN_RESTTHUOPEN);
                int restFriOpen = result.getInt(COLUMN_RESTFRIOPEN);
                int restSatOpen = result.getInt(COLUMN_RESTSATOPEN);
                int restSunOpen = result.getInt(COLUMN_RESTSUNOPEN);
                int restMonClose = result.getInt(COLUMN_RESTMONCLOSE);
                int restTueClose = result.getInt(COLUMN_RESTTUECLOSE);
                int restWedClose = result.getInt(COLUMN_RESTWEDCLOSE);
                int restThuClose = result.getInt(COLUMN_RESTTHUCLOSE);
                int restFriClose = result.getInt(COLUMN_RESTFRICLOSE);
                int restSatClose = result.getInt(COLUMN_RESTSATCLOSE);
                int restSunClose = result.getInt(COLUMN_RESTSUNCLOSE);
                
                Restaurant rest = new Restaurant(objectId, restNameKor, 
                        restNameEng, restAddr, restPhoneNum, restPriceRange, restLike, restMonOpen,
                        restTueOpen, restWedOpen, restThuOpen, restFriOpen, restSatOpen, restSunOpen,
                        restMonClose, restTueClose, restWedClose, restThuClose, restFriClose, restSatClose,
                        restSunClose);
                list.add(rest);
            }
        }
        return list;
    }
    
    
    private static List<Restaurant> getRestaurantsMenuHelper(final String search) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery(TABLE_MENU);
        query.whereEqualTo(COLUMN_MENUNAME, search);
        List<ParseObject> results = null;
        try {
            results = query.find();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        
        ArrayList<String> restList = new ArrayList<String>();
        if (results !=  null) {
            for (ParseObject result : results) {
                String objectId = result.getString(COLUMN_RESTID);
                restList.add(objectId);
            }
            ArrayList<Restaurant> list = (ArrayList<Restaurant>) getRestaurantsMenuHelperHelper(restList);
            return list;
        }
        return null;
    }
    
    private static List<Restaurant> getRestaurantsMenuHelperHelper(final ArrayList<String> list) {
        ParseQuery<ParseObject> queryRest = ParseQuery.getQuery(TABLE_RESTAURANT);
        queryRest.whereEqualTo(COLUMN_ID, list);
        List<ParseObject> results = null;
        try {
            results = queryRest.find();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        ArrayList<Restaurant> restList = new ArrayList<Restaurant>();
        if (results != null) {
            for (ParseObject result : results) {
                String objectId = result.getObjectId();
                String restNameKor = result.getString(COLUMN_RESTNAMEKOR);
                String restNameEng = result.getString(COLUMN_RESTNAMEENG);
                String restAddr = result.getString(COLUMN_RESTADDR);
                int restPhoneNum = result.getInt(COLUMN_RESTPHONENUM);
                int restPriceRange = result.getInt(COLUMN_RESTPRICERANGE);
                int restLike = result.getInt(COLUMN_RESTLIKE);
                int restMonOpen = result.getInt(COLUMN_RESTMONOPEN);
                int restTueOpen = result.getInt(COLUMN_RESTTUEOPEN);
                int restWedOpen = result.getInt(COLUMN_RESTWEDOPEN);
                int restThuOpen = result.getInt(COLUMN_RESTTHUOPEN);
                int restFriOpen = result.getInt(COLUMN_RESTFRIOPEN);
                int restSatOpen = result.getInt(COLUMN_RESTSATOPEN);
                int restSunOpen = result.getInt(COLUMN_RESTSUNOPEN);
                int restMonClose = result.getInt(COLUMN_RESTMONCLOSE);
                int restTueClose = result.getInt(COLUMN_RESTTUECLOSE);
                int restWedClose = result.getInt(COLUMN_RESTWEDCLOSE);
                int restThuClose = result.getInt(COLUMN_RESTTHUCLOSE);
                int restFriClose = result.getInt(COLUMN_RESTFRICLOSE);
                int restSatClose = result.getInt(COLUMN_RESTSATCLOSE);
                int restSunClose = result.getInt(COLUMN_RESTSUNCLOSE);
                
                Restaurant rest = new Restaurant(objectId, restNameKor, 
                        restNameEng, restAddr, restPhoneNum, restPriceRange, restLike, restMonOpen,
                        restTueOpen, restWedOpen, restThuOpen, restFriOpen, restSatOpen, restSunOpen,
                        restMonClose, restTueClose, restWedClose, restThuClose, restFriClose, restSatClose,
                        restSunClose);
                restList.add(rest);
            }
        }
        return restList;
    }



}