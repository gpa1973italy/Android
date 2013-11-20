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
    	
    	values.put(DBHelper.COLUMN_COLORS_ColorGroup, colorgroup);
    	values.put(DBHelper.COLUMN_COLORS_ColorName, colorname);
    	values.put(DBHelper.COLUMN_COLORS_Red, red);
    	values.put(DBHelper.COLUMN_COLORS_Green, green);
    	values.put(DBHelper.COLUMN_COLORS_Blue, blue);

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
    	
    	return database.insertOrThrow(DBHelper.TABLE_COLORS, null, initialValuesColor);
    	
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
    	return database.update(DBHelper.TABLE_COLORS, updateValuesColor, DBHelper.COLUMN_COLORS_ID + " = " + ID, null) > 0;
    }
    
    /***
     * 
     * @param ID
     * @return
     */
    public boolean deleteColor (long ID){
    	return database.delete(DBHelper.TABLE_COLORS, DBHelper.COLUMN_COLORS_ID + " = " + ID, null) > 0;
    }
    
    /***
     * 
     * @return
     */
    public Cursor fetchAllColors(){

    	return database.query(DBHelper.TABLE_COLORS, new String[] {DBHelper.COLUMN_COLORS_ID, DBHelper.COLUMN_COLORS_ColorGroup, DBHelper.COLUMN_COLORS_ColorName, DBHelper.COLUMN_COLORS_Red, DBHelper.COLUMN_COLORS_Green, DBHelper.COLUMN_COLORS_Blue}, null, null, null, null, null);
    }

    /***
     * 
     * @param iGroupFilter
     * @return
     */
    public Cursor fetchColorsByGroup(int iGroupFilter){
    	Cursor myCursor = database.query(DBHelper.TABLE_COLORS, new String[] {DBHelper.COLUMN_COLORS_ID, DBHelper.COLUMN_COLORS_ColorGroup, DBHelper.COLUMN_COLORS_ColorName, DBHelper.COLUMN_COLORS_Red, DBHelper.COLUMN_COLORS_Green, DBHelper.COLUMN_COLORS_Blue}, 
    			DBHelper.COLUMN_COLORS_ColorGroup + " = " + Integer.toString(iGroupFilter), null, null, null, null); 
    	
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
    	Cursor myCursor = database.query(DBHelper.TABLE_COLORS, new String[] {DBHelper.COLUMN_COLORS_ID, DBHelper.COLUMN_COLORS_ColorGroup, DBHelper.COLUMN_COLORS_ColorName, DBHelper.COLUMN_COLORS_Red, DBHelper.COLUMN_COLORS_Green, DBHelper.COLUMN_COLORS_Blue}, 
    			DBHelper.COLUMN_COLORS_Red + " = " + Integer.toString(iRedFilter)
    			+ " and " + DBHelper.COLUMN_COLORS_Green + " = " + Integer.toString(iGreenFilter)
    			+ " and " + DBHelper.COLUMN_COLORS_Blue + " = " + Integer.toString(iBlueFilter)
    			, null, null, null, null); 
    	
    	return myCursor;
    }

    /***
     * 
     * @return
     */
    public Cursor fetchAllColorsGroups(){
    	return database.query(DBHelper.TABLE_COLORSGROUP, new String[] {DBHelper.COLUMN_COLORSGROUP_ID, DBHelper.COLUMN_COLORSGROUP_ColorGroup}, null, null, null, null, null);
    }
    
    
    
}
