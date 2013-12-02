package com.woodstocoasts.photostory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

public class Utility {
	public static String getDateTimeUTCFromMillis(long milliSeconds,
			String dateFormat) {
		// Create a DateFormatter object for displaying date in specified
		// format.
		DateFormat formatter = new SimpleDateFormat(dateFormat,
				Locale.getDefault());
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

		// Create a calendar object that will convert the date and time value in
		// milliseconds to date.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);

		return formatter.format(calendar.getTime());
	}

	public static void BackupDatabase(Context ctx) throws IOException {
		// Nel MANIFEST
		// ricordarsi di mettere il permesso di scrittura su SD esterna
		
		boolean success = true;
		File file = null;
		file = new File(Environment.getExternalStorageDirectory()
				+ "/W2C_Backup");

		if (file.exists()) {
			success = true;
		} else {
			success = file.mkdir();
		}

		if (success) {
			// String inFileName = "/data/data/com.woodstocoasts.photostory/databases/W2CPhotoStory.db";
			String inFileName = ctx.getFilesDir().getParent().toString() + "/databases/W2CPhotoStory.db";
			Log.v("BK", ctx.getFilesDir().getParent().toString() );
			File dbFile = new File(inFileName);
			FileInputStream fis = new FileInputStream(dbFile);

			String outFileName = Environment.getExternalStorageDirectory()
					+ "/W2C_Backup/W2CPhotoStory.db";
			// Open the empty db as the output stream
			OutputStream output = new FileOutputStream(outFileName);
			// transfer bytes from the inputfile to the outputfile
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}

			output.flush();
			output.close();
			fis.close();
		}
	}
	
	
	/***
	 *  Le risorse per scalare le Bitmap
	 *  
	 *  http://stackoverflow.com/questions/16749044/android-scaling-imageview-from-setimagebitmap
	 * 	http://stackoverflow.com/questions/5082703/android-out-of-memory-error-with-lazy-load-images
	 * http://stackoverflow.com/questions/12001793/android-imageview-setimagebitmap-vs-setimagedrawable
	 * http://stackoverflow.com/questions/8510335/simplecursoradapter-with-imageview-and-textview
	 * 
	 */
	
	//decodes image and scales it to reduce memory consumption
    public static Bitmap decodeFile(File f){
        try {
            //decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(new FileInputStream(f),null,o);

            //Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE=320;
            int width_tmp=o.outWidth, height_tmp=o.outHeight;
            int scale=1;
            while(true){
                if(width_tmp/2<REQUIRED_SIZE || height_tmp/2<REQUIRED_SIZE)
                    break;
                width_tmp/=2;
                height_tmp/=2;
                scale*=2;
            }

            //decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize=scale;
            o2.inPurgeable = true;
            o2.inDither = true;
            return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        } catch (FileNotFoundException e) {}
        return null;
    }

}
