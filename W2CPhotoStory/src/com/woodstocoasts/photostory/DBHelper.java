package com.woodstocoasts.photostory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

	public enum PHOTOSTREAM_RecordType {Album, Photo}	
//	
//	@SuppressWarnings("unused")
//	public enum PHOTOSTREAM_RecordType {
//		Album(0), Photo(1);
//        private final int value;
//
//        private PHOTOSTREAM_RecordType(int value) {
//            this.value = value;
//        }
//        public int PHOTOSTREAM_RecordType() {
//            return value;
//        }
//    }
//	
	public enum PHOTOSTREAM_RecordStatus {Available, Deleted, Hidden, Private}	
	
//	@SuppressWarnings("unused")
//	public enum PHOTOSTREAM_RecordStatus {
//		Available(0), Deleted(1), Hidden(2), Private(3);
//        private final int value;
//
//        private PHOTOSTREAM_RecordStatus(int value) {
//            this.value = value;
//        }
//        public int PHOTOSTREAM_RecordStatus() {
//            return value;
//        }
//    }

	private static final String LOG_TAG = DBHelper.class.getSimpleName();

	private static final String DATABASE_NAME = "W2CPhotoStory.db";
    private static final int DATABASE_VERSION = 1;
    
    public static final String TABLE_PHOTOSTREAM = "PhotoStream";
	
    public static final String COLUMN_PHOTOSTREAM_ID = "_id";
	public static final String COLUMN_PHOTOSTREAM_OwnerID = "OwnerID";
	public static final String COLUMN_PHOTOSTREAM_RecordType = "RecordType";
	public static final String COLUMN_PHOTOSTREAM_RecordRef = "RecordRef";
	public static final String COLUMN_PHOTOSTREAM_CreationDateTimeUTC = "CreationDateTimeUTC";
	public static final String COLUMN_PHOTOSTREAM_UpdateDateTimeUTC = "UpdateDateTimeUTC";
	public static final String COLUMN_PHOTOSTREAM_Title = "Title";
	public static final String COLUMN_PHOTOSTREAM_Description = "Description";
	public static final String COLUMN_PHOTOSTREAM_Status = "Status";
	public static final String COLUMN_PHOTOSTREAM_Favorite = "Favorite";
	public static final String COLUMN_PHOTOSTREAM_Synchronized = "Synchronized";
	public static final String COLUMN_PHOTOSTREAM_BitmapFileType = "BitmapFileType";
	public static final String COLUMN_PHOTOSTREAM_BitmapFileName = "BitmapFileName";
	public static final String COLUMN_PHOTOSTREAM_CaptureDateTimeUTC = "CaptureDateTimeUTC";
	public static final String COLUMN_PHOTOSTREAM_CaptureGPSLatitude = "CaptureGPSLatitude";
	public static final String COLUMN_PHOTOSTREAM_CaptureGPSLongitude = "CaptureGPSLongitude";
	public static final String COLUMN_PHOTOSTREAM_CaptureGPSAccuracy = "CaptureGPSAccuracy";
	

    
    private static final String DATABASE_CREATE_1 = "CREATE TABLE IF NOT EXISTS " + TABLE_PHOTOSTREAM + " (" +
					" " + COLUMN_PHOTOSTREAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					" " + COLUMN_PHOTOSTREAM_OwnerID + " TEXT, "  +
					" " + COLUMN_PHOTOSTREAM_RecordType + " INTEGER, " +
					" " + COLUMN_PHOTOSTREAM_RecordRef + " INTEGER, " +
					" " + COLUMN_PHOTOSTREAM_CreationDateTimeUTC + " TEXT, " +
					" " + COLUMN_PHOTOSTREAM_UpdateDateTimeUTC + " TEXT, " +
					" " + COLUMN_PHOTOSTREAM_Title + " TEXT, " +
					" " + COLUMN_PHOTOSTREAM_Description + " TEXT, " +
					" " + COLUMN_PHOTOSTREAM_Status + " INTEGER, " +
					" " + COLUMN_PHOTOSTREAM_Favorite + " INTEGER, " +
					" " + COLUMN_PHOTOSTREAM_Synchronized + " INTEGER, " +
					" " + COLUMN_PHOTOSTREAM_BitmapFileType + " TEXT, " +
					" " + COLUMN_PHOTOSTREAM_BitmapFileName + " TEXT, " +
					" " + COLUMN_PHOTOSTREAM_CaptureDateTimeUTC + " TEXT, " +
					" " + COLUMN_PHOTOSTREAM_CaptureGPSLatitude + " NUMERIC, " +
					" " + COLUMN_PHOTOSTREAM_CaptureGPSLongitude + " NUMERIC, " +
					" " + COLUMN_PHOTOSTREAM_CaptureGPSAccuracy + " NUMERIC);";
    

	
	
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
		Log.d(LOG_TAG + ": DB_CREATE", DATABASE_CREATE_1);
		db.execSQL(DATABASE_CREATE_1);

		
	}

	/***
	 * 
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		 Log.d(LOG_TAG + ": DB_UPDATE\n",
		"Upgrading database from version " + oldVersion + " to "
		+ newVersion + ", which will destroy all old data");
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHOTOSTREAM);

		onCreate(db);
	}

}
