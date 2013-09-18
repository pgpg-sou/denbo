package com.example.dneshiboshiken;


import java.util.Arrays;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

public class GoogleDriveBuild{
	static final int NETWORK_ERROR = 2;
	static GoogleAccountCredential credential;
	static Drive service;
	
	// GoogleDriveをビルドする
	static Drive getDriveService(GoogleAccountCredential credential) {
		return new Drive.Builder(AndroidHttp.newCompatibleTransport(),
				new GsonFactory(), credential).build();
	}
	
	static boolean netWorkCheck(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo in = cm.getActiveNetworkInfo();
		if (in != null) {
			return cm.getActiveNetworkInfo().isConnected();
			
		}
		return false;
	}
	
	static void driveAccountCredential(Context context){
		credential = GoogleAccountCredential.usingOAuth2(context,
				Arrays.asList(DriveScopes.DRIVE_FILE));
	}
	static void driveService(String accountName){
		credential.setSelectedAccountName(accountName);
		service=getDriveService(credential);
		//DriveModule.OAuth(credential);
	}

}
