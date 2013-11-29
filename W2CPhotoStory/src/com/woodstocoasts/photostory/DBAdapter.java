package com.woodstocoasts.photostory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
@SuppressWarnings("unused")
public class DBAdapter {
	
	
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

    private ContentValues createContentValuesPhotoStream(
    		String ownerID,
    		int recordType,
    		long recordRef,
    		String creationDateTimeUTC,
    		String updateDateTimeUTC,
    		String title,
    		String description,
    		int status,
    		boolean isFavorite,
    		boolean isSynchronized,
    		String bitmapFileType,
    		String bitmapFileName,
    		float captureGPSLatitude,
    		float captureGPSLongitude,
    		float captureGPSAccuracy
    		){
    	
    	ContentValues values = new ContentValues();
    	
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_OwnerID, ownerID);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_RecordType, recordType);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_RecordRef, recordRef);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_CreationDateTimeUTC, creationDateTimeUTC);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_UpdateDateTimeUTC, updateDateTimeUTC);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_Title, title);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_Description, description);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_Status, status);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_Favorite, isFavorite);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_Synchronized, isSynchronized);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_BitmapFileType, bitmapFileType);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName, bitmapFileName);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLatitude, captureGPSLatitude);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLongitude, captureGPSLongitude);
    	values.put(DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSAccuracy, captureGPSAccuracy);


		return values;
    }
    
    public long createPhotoStreamRecord (
    		String ownerID,
    		int recordType,
    		long recordRef,
    		String creationDateTimeUTC,
    		String updateDateTimeUTC,
    		String title,
    		String description,
    		int status,
    		boolean isFavorite,
    		boolean isSynchronized,
    		String bitmapFileType,
    		String bitmapFileName,
    		float captureGPSLatitude,
    		float captureGPSLongitude,
    		float captureGPSAccuracy
    		){
    	
    	ContentValues initialValuesPhotoStream = createContentValuesPhotoStream(
        		 ownerID,
        		 recordType,
        		 recordRef,
        		 creationDateTimeUTC,
        		 updateDateTimeUTC,
        		 title,
        		 description,
        		 status,
        		 isFavorite,
        		 isSynchronized,
        		 bitmapFileType,
        		 bitmapFileName,
        		 captureGPSLatitude,
        		 captureGPSLongitude,
        		 captureGPSAccuracy
    			);
    	
    	return database.insertOrThrow(DBHelper.TABLE_PHOTOSTREAM, null, initialValuesPhotoStream);
    	
    }
    
    
    public boolean updatePhotoStreamRecord  (long ID, 
    		String ownerID,
    		int recordType,
    		long recordRef,
    		String creationDateTimeUTC,
    		String updateDateTimeUTC,
    		String title,
    		String description,
    		int status,
    		boolean isFavorite,
    		boolean isSynchronized,
    		String bitmapFileType,
    		String bitmapFileName,
    		float captureGPSLatitude,
    		float captureGPSLongitude,
    		float captureGPSAccuracy
    		
    		){
    	ContentValues updateValuesPhotoStreamRecord  = createContentValuesPhotoStream(ownerID, recordType, recordRef, creationDateTimeUTC, updateDateTimeUTC, title, description, status, isFavorite, isSynchronized, bitmapFileType, bitmapFileName, captureGPSLatitude, captureGPSLongitude, captureGPSAccuracy);
    	return database.update(DBHelper.TABLE_PHOTOSTREAM, updateValuesPhotoStreamRecord, DBHelper.COLUMN_PHOTOSTREAM_ID + " = " + ID, null) > 0;
    }
    
    
    public boolean deletePhotoStreamRecord (long ID){
    	return database.delete(DBHelper.TABLE_PHOTOSTREAM, DBHelper.COLUMN_PHOTOSTREAM_ID + " = " + ID, null) > 0;
    }
    
    public Cursor fetchAllPhotoStreamRecords (){

    	Cursor myCursor =  database.query(DBHelper.TABLE_PHOTOSTREAM, new String[] {
    			DBHelper.COLUMN_PHOTOSTREAM_ID,
    			DBHelper.COLUMN_PHOTOSTREAM_OwnerID,
    			DBHelper.COLUMN_PHOTOSTREAM_RecordType,
    			DBHelper.COLUMN_PHOTOSTREAM_RecordRef,
    			DBHelper.COLUMN_PHOTOSTREAM_CreationDateTimeUTC,
    			DBHelper.COLUMN_PHOTOSTREAM_UpdateDateTimeUTC,
    			DBHelper.COLUMN_PHOTOSTREAM_Title,
    			DBHelper.COLUMN_PHOTOSTREAM_Description,
    			DBHelper.COLUMN_PHOTOSTREAM_Status,
    			DBHelper.COLUMN_PHOTOSTREAM_Favorite,
    			DBHelper.COLUMN_PHOTOSTREAM_Synchronized,
    			DBHelper.COLUMN_PHOTOSTREAM_BitmapFileType,
    			DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLatitude,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLongitude,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSAccuracy
    	
    	}, null, null, null, null, null);
    	
    	return myCursor;
    }
 
    
    public Cursor fetchPhotoStreamRecordsByType(int iRecordTypeFilter){
    	Cursor myCursor = database.query(DBHelper.TABLE_PHOTOSTREAM, new String[] {
    			DBHelper.COLUMN_PHOTOSTREAM_ID,
    			DBHelper.COLUMN_PHOTOSTREAM_OwnerID,
    			DBHelper.COLUMN_PHOTOSTREAM_RecordType,
    			DBHelper.COLUMN_PHOTOSTREAM_RecordRef,
    			DBHelper.COLUMN_PHOTOSTREAM_CreationDateTimeUTC,
    			DBHelper.COLUMN_PHOTOSTREAM_UpdateDateTimeUTC,
    			DBHelper.COLUMN_PHOTOSTREAM_Title,
    			DBHelper.COLUMN_PHOTOSTREAM_Description,
    			DBHelper.COLUMN_PHOTOSTREAM_Status,
    			DBHelper.COLUMN_PHOTOSTREAM_Favorite,
    			DBHelper.COLUMN_PHOTOSTREAM_Synchronized,
    			DBHelper.COLUMN_PHOTOSTREAM_BitmapFileType,
    			DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLatitude,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLongitude,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSAccuracy
    	}, 
    			DBHelper.COLUMN_PHOTOSTREAM_RecordType + " = " + Integer.toString(iRecordTypeFilter), null, null, null, null); 
    	
    	return myCursor;
    }
    
    public Cursor fetchPhotoStreamRecordsByStatus(int iRecordStatusFilter){
    	Cursor myCursor = database.query(DBHelper.TABLE_PHOTOSTREAM, new String[] {
    			DBHelper.COLUMN_PHOTOSTREAM_ID,
    			DBHelper.COLUMN_PHOTOSTREAM_OwnerID,
    			DBHelper.COLUMN_PHOTOSTREAM_RecordType,
    			DBHelper.COLUMN_PHOTOSTREAM_RecordRef,
    			DBHelper.COLUMN_PHOTOSTREAM_CreationDateTimeUTC,
    			DBHelper.COLUMN_PHOTOSTREAM_UpdateDateTimeUTC,
    			DBHelper.COLUMN_PHOTOSTREAM_Title,
    			DBHelper.COLUMN_PHOTOSTREAM_Description,
    			DBHelper.COLUMN_PHOTOSTREAM_Status,
    			DBHelper.COLUMN_PHOTOSTREAM_Favorite,
    			DBHelper.COLUMN_PHOTOSTREAM_Synchronized,
    			DBHelper.COLUMN_PHOTOSTREAM_BitmapFileType,
    			DBHelper.COLUMN_PHOTOSTREAM_BitmapFileName,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLatitude,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSLongitude,
    			DBHelper.COLUMN_PHOTOSTREAM_CaptureGPSAccuracy
    	}, 
    			DBHelper.COLUMN_PHOTOSTREAM_Status + " = " + Integer.toString(iRecordStatusFilter), null, null, null, null); 
    	
    	return myCursor;
    }
}
