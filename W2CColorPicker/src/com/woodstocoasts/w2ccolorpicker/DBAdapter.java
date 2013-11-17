package com.woodstocoasts.w2ccolorpicker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.woodstocoasts.w2ccolorpicker.DBHelper;
public class DBAdapter {
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = DBAdapter.class.getSimpleName();
	private Context context;
	private SQLiteDatabase database;
	private DBHelper databaseHelper;
	
    public static final String TABLE_COLORS = "Colors";
    public static final String COLUMN_COLORS_ID = "ID";
    public static final String COLUMN_COLORS_ColorGroup = "ColorGroup";
    public static final String COLUMN_COLORS_ColorName = "ColorName";
    public static final String COLUMN_COLORS_Red = "Red";
    public static final String COLUMN_COLORS_Green = "Green";
    public static final String COLUMN_COLORS_Blue = "Blue";

    public static final String TABLE_COLORSGROUP = "ColorsGroup";
    public static final String COLUMN_COLORSGROUP_ID = "ID";
    public static final String COLUMN_COLORSGROUP_ColorGroup = "ColorGroupName";
    
    public DBAdapter (Context context){
    	this.context = context;
    }
    
    public DBAdapter open() throws SQLException {
    	databaseHelper = new DBHelper(context);
    	database = databaseHelper.getWritableDatabase();
    	return this;
    }	
    
    public void close() {
    	databaseHelper.close();
    }
    
    /****
     * 
     * @param colorgroup
     * @param colorname
     * @param red
     * @param green
     * @param blue
     * @return
     */
    private ContentValues createContentValuesColors (int colorgroup, String colorname, int red, int green, int blue){
    	
    	ContentValues values = new ContentValues();
    	
    	values.put(COLUMN_COLORS_ColorGroup, colorgroup);
    	values.put(COLUMN_COLORS_ColorName, colorname);
    	values.put(COLUMN_COLORS_Red, red);
    	values.put(COLUMN_COLORS_Green, green);
    	values.put(COLUMN_COLORS_Blue, blue);

		return values;
    }
    
    /****
     * 
     * @param colorgroup
     * @param colorname
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public long createColor (int colorgroup, String colorname, int red, int green, int blue){
    	
    	ContentValues initialValuesColor = createContentValuesColors(colorgroup, colorname, red, green, blue);
    	
    	return database.insertOrThrow(TABLE_COLORS, null, initialValuesColor);
    	
    }
    
    /***
     * 
     * @param ID
     * @param colorgroup
     * @param colorname
     * @param red
     * @param green
     * @param blue
     * @return
     */
    public boolean updateColor (long ID, int colorgroup, String colorname, int red, int green, int blue){
    	ContentValues updateValuesColor = createContentValuesColors(colorgroup, colorname, red, green, blue);
    	return database.update(TABLE_COLORS, updateValuesColor, COLUMN_COLORS_ID + " = " + ID, null) > 0;
    }
    
    /***
     * 
     * @param ID
     * @return
     */
    public boolean deleteColor (long ID){
    	return database.delete(TABLE_COLORS, COLUMN_COLORS_ID + " = " + ID, null) > 0;
    }
    
    /***
     * 
     * @return
     */
    public Cursor fetchAllColors(){

    	return database.query(TABLE_COLORS, new String[] {COLUMN_COLORS_ID, COLUMN_COLORS_ColorGroup, COLUMN_COLORS_ColorName, COLUMN_COLORS_Red, COLUMN_COLORS_Green, COLUMN_COLORS_Blue}, null, null, null, null, null);
    }

    /***
     * 
     * @param iGroupFilter
     * @return
     */
    public Cursor fetchColorsByGroup(int iGroupFilter){
    	Cursor myCursor = database.query(TABLE_COLORS, new String[] {COLUMN_COLORS_ID, COLUMN_COLORS_ColorGroup, COLUMN_COLORS_ColorName, COLUMN_COLORS_Red, COLUMN_COLORS_Green, COLUMN_COLORS_Blue}, 
				COLUMN_COLORS_ColorGroup + " = " + Integer.toString(iGroupFilter), null, null, null, null); 
    	
    	return myCursor;
    }
    
    /***
     * 
     * @param iRedFilter
     * @param iGreenFilter
     * @param iBlueFilter
     * @return
     */
    public Cursor fetchColorsByRGBComponents(int iRedFilter, int iGreenFilter, int iBlueFilter){
    	Cursor myCursor = database.query(TABLE_COLORS, new String[] {COLUMN_COLORS_ID, COLUMN_COLORS_ColorGroup, COLUMN_COLORS_ColorName, COLUMN_COLORS_Red, COLUMN_COLORS_Green, COLUMN_COLORS_Blue}, 
    			COLUMN_COLORS_Red + " = " + Integer.toString(iRedFilter)
    			+ " and " + COLUMN_COLORS_Green + " = " + Integer.toString(iGreenFilter)
    			+ " and " + COLUMN_COLORS_Blue + " = " + Integer.toString(iBlueFilter)
    			, null, null, null, null); 
    	
    	return myCursor;
    }

    /***
     * 
     * @return
     */
    public Cursor fetchAllColorsGroups(){
    	return database.query(TABLE_COLORSGROUP, new String[] {COLUMN_COLORSGROUP_ID, COLUMN_COLORSGROUP_ColorGroup}, null, null, null, null, null);
    }
    
    
    
}
