package com.woodstocoasts.w2ccolorpicker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "W2CColors.db";
    private static final int DATABASE_VERSION = 1;

    
    public static final String TABLE_COLORS = "Colors";
    public static final String COLUMN_COLORS_ID = "_id";
    public static final String COLUMN_COLORS_ColorGroup = "ColorGroup";
    public static final String COLUMN_COLORS_ColorName = "ColorName";
    public static final String COLUMN_COLORS_Red = "Red";
    public static final String COLUMN_COLORS_Green = "Green";
    public static final String COLUMN_COLORS_Blue = "Blue";
    public static final String COLUMN_COLORS_Hex = "Hex";

    public static final String TABLE_COLORSGROUP = "ColorsGroup";
    public static final String COLUMN_COLORSGROUP_ID = "_id";
    public static final String COLUMN_COLORSGROUP_ColorGroup = "ColorGroupName";
    
    
 /*    
    CREATE TABLE Colors (ID INTEGER PRIMARY KEY, ColorGroup NUMERIC, ColorName TEXT, Red NUMERIC, Green NUMERIC, Blue NUMERIC);
    CREATE TABLE ColorsGroup (ID INTEGER PRIMARY KEY, ColorGroupName TEXT);
    INSERT INTO ColorsGroup VALUES(1,'CSS3');
    INSERT INTO ColorsGroup VALUES(2,'Pantone PMS');
*/
    
    private static final String DATABASE_CREATE_1 = "CREATE TABLE IF NOT EXISTS " + TABLE_COLORS + " ("
					+ COLUMN_COLORS_ID + " INTEGER PRIMARY KEY, " 
					+ COLUMN_COLORS_ColorGroup + " NUMERIC, " 
					+ COLUMN_COLORS_ColorName + " TEXT, " 
					+ COLUMN_COLORS_Red + " NUMERIC, " 
					+ COLUMN_COLORS_Green + " NUMERIC, " 
					+ COLUMN_COLORS_Blue + " NUMERIC, "
					+ COLUMN_COLORS_Hex + " TEXT);"
					
					+ "CREATE TABLE IF NOT EXISTS " + TABLE_COLORSGROUP + " ("
					+ COLUMN_COLORSGROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
					+ COLUMN_COLORSGROUP_ColorGroup + " TEXT);";
    
    private static final String DATABASE_CREATE_2 = "CREATE TABLE IF NOT EXISTS " + TABLE_COLORSGROUP + " ("
			+ COLUMN_COLORSGROUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
			+ COLUMN_COLORSGROUP_ColorGroup + " TEXT);";
	
	
    /***
     * 
     * @param context
     */
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	
	/***
	 * 
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.d("DB_CREATE", DATABASE_CREATE_1);
		Log.d("DB_CREATE", DATABASE_CREATE_2);
		db.execSQL(DATABASE_CREATE_1);
		db.execSQL(DATABASE_CREATE_2);
		db.execSQL("INSERT INTO " + TABLE_COLORSGROUP + " VALUES(1,'CSS3');");
		db.execSQL("INSERT INTO " + TABLE_COLORSGROUP + " VALUES(2,'Pantone PMS');");

	
		for (ColorElement ce: ColorElement.DB){
				db.execSQL("INSERT INTO " + TABLE_COLORS + " (" + COLUMN_COLORS_ColorGroup + ", " + COLUMN_COLORS_ColorName + ", " + COLUMN_COLORS_Red + ", " + COLUMN_COLORS_Green + ", " + COLUMN_COLORS_Blue + ", " + COLUMN_COLORS_Hex + " ) VALUES(" + ce.getColorGroup() + ", '" + ce.getColorName() + "', " + ce.getRed() + ", " + ce.getGreen() + ", " + ce.getBlue() + ", null);");	
		}
		
		//db.execSQL("INSERT INTO " + TABLE_COLORS + " VALUES(1, 'CSS3', 'NERO', 0,0,0);");
		
	}

	/***
	 * 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 Log.w(DBHelper.class.getName(),
		"Upgrading database from version " + oldVersion + " to "
		+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLORS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLORSGROUP);
		onCreate(db);
	}

}
