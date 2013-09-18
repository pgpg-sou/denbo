package com.example.dneshiboshiken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpResponse;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Children;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import com.google.api.services.drive.model.Permission;

public class DriveModule {

	private static String a;
	static final int REQUEST_AUTHORIZATION = 2;
	// private static File rFile = null;
	// private static File file = null;
	// private static File la = null;

	/**
	 * ファイルのkey accountName First start
	 *
	 * getSharePreferences foldersId フォルダ名とID fileIds ファイル名とID fileDate
	 *
	 * */

	/**
	 * 全フォルダリスト取得 基本的に初期にYukariフォルダを検索するために用いる
	 */
	/**
	 * Retrieve a list of File resources.
	 *
	 * @param service
	 *            Drive API service instance.
	 * @return List of File resources.
	 */

	static List<File> retrieveAllFiles(Drive service,
			GoogleAccountCredential credential, final Activity activity)
			throws IOException {
		List<File> result = new ArrayList<File>();
		Files.List request = service
				.files()
				.list()
				.setQ("title = 'Yukari' and mimeType = 'application/vnd.google-apps.folder' and trashed = false");

		while (MainActivity.get == false) {

		}
		OAuth(credential, activity);
		do {
			try {

				FileList files = request.execute();
				// GoogleDrive.listData.addAll(files.getItems());
				result.addAll(files.getItems());
				request.setPageToken(files.getNextPageToken());
			} catch (IOException e) {
				Log.e(" retrieveAllFiles", "An error occurred: " + e.toString());
				request.setPageToken(null);
			}
		} while (request.getPageToken() != null
				&& request.getPageToken().length() > 0);
		// Log.i("request size", String.valueOf(GoogleDrive.listData.size()));
		// for (int i = 0; i < 3; i++) {
		// // adapter.add(item.getTitle());
		// Log.i("RESULT", GoogleDrive.listData.get(i).getTitle());
		// }
		return result;
	}

	/*
	 * ファイルのダウンロード用のメソッド
	 */
	/**
	 * Print a file's metadata.
	 *
	 * @param service
	 *            Drive API service instance.
	 * @param fileId
	 *            ID of the file to print metadata for.
	 */
	static void printFile(Drive service, String fileId) {

		try {
			File file = service.files().get(fileId).execute();
			Log.i("Title", "Title: " + file.getTitle());
			Log.i("Description", "Description: " + file.getDescription());
			Log.i("MIME type", "MIME type: " + file.getMimeType());
		} catch (IOException e) {
			Log.e("ERROR", e.toString());
		}
	}

	/**
	 *
	 * @param service
	 *            DriveApiサービスのインスタンス
	 * @param file
	 *            Driveのファイルのインスタンス
	 * @return InputStream containing the file's content if successful,
	 *         {@code null} otherwise.
	 * */
	static InputStream downloadFile(Drive service, File file) {
		if (file.getDownloadUrl() != null && file.getDownloadUrl().length() > 0) {
			try {
				HttpResponse resp = service.getRequestFactory()
						.buildGetRequest(new GenericUrl(file.getDownloadUrl()))
						.execute();
				Log.e("DOWN", "COMPLETE");
				return resp.getContent();
			} catch (IOException e) {
				// An error occurred.
				e.printStackTrace();
				Log.e("DOWNLOAD ERROR", e.toString());
				return null;
			}
		} else {
			// The file doesn't have any content stored on Drive.
			Log.e("ERROR", "The file doesn't have any content stored on Drive.");
			return null;
		}

	}

	/*
	 * フォルダ内のファイル、フォルダ取得、nameしていしてくれるとすぐにいくと思う
	 */
	static List<File> printFilesInFolder(Drive service, String folderId,
			String query) throws IOException {
		Children.List request;
		Log.d("folderId",folderId);
		if (query == null) {
			request = service.children().list(folderId).setQ("trashed != true");
		} else {
			Log.d("query",query);
			request = service.children().list(folderId).setQ(query + " and trashed != true");
		}
		Log.d("getQuery",request.getQ());
		List<File> cFile = new ArrayList<File>();
		do {
			try {
				ChildList children = request.execute();
				Log.d("children.getItems",String.valueOf(children.size()));

				for (ChildReference child : children.getItems()) {
					//Log.i("FILE ID LIST","File name "+ service.files().get(child.getId()).execute().getTitle());
					cFile.add(service.files().get(child.getId()).execute());
					Log.d("childreference for","cFile.add");
				}
				request.setPageToken(children.getNextPageToken());
			} catch (IOException e) {
				System.out.println("An error occurred: " + e);
				request.setPageToken(null);
			}
		} while (request.getPageToken() != null
				&& request.getPageToken().length() > 0);
		return cFile;
	}

	/*
	 * 再帰を使用したファイルのダウンロード
	 */
	static boolean fDownloader(File in, java.io.File out, Drive service,
			Activity activity) {
		Log.d("fdownloader","fdownloader");
		if (in.getMimeType().equals("application/vnd.google-apps.folder")) {
			Log.d("in.getMimetype","in.getMimetype");
			out.mkdirs();
			List<File> inFiles;
			try {
				Log.i("printFilesInFolder","printFilesInFolder");
				inFiles = printFilesInFolder(service, in.getId(), null);
				java.io.File[] outFiles = new java.io.File[inFiles.size()];
				for (int i = 0; i < inFiles.size(); i++) {
					Log.i("inFiles.size()","inFiles.size()"+String.valueOf(inFiles.size()));
					outFiles[i] = new java.io.File(out.getPath() + "/"+ inFiles.get(i).getTitle());
					Log.e("fdownloader", "filedownload");
					SharedPreferences dll = activity.getSharedPreferences("filePath", Activity.MODE_PRIVATE);
					SharedPreferences.Editor editor = dll.edit();
					editor.putString(inFiles.get(i).getTitle(), out.getPath()+ "/" + inFiles.get(i).getTitle());
					editor.commit();
					fDownloader(inFiles.get(i), outFiles[i], service, activity);
				}
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
				Log.e("fDownload ERROR","fDownload ERROR"+e.toString());
			}
		} else {
			// FileCopy(in, out);
			Log.e("FILE NAME", in.getTitle());
			if (date(in, activity)) {
				InputStream data = downloadFile(service, in);
				if (data != null) {
					DriveModule a = new DriveModule();
					a.fileSave(data, out.getPath());
					saveEditDate("fileDate", in.getTitle(), activity);
				}
			}

		}
		return false;
	}

	/*
	 * ファイルが存在するかのチェック。 あった場合は削除してからアップロード サンプル if (DriveModule.checkFile(item,
	 * "Procon2013", "test.txt", service, credential, file1.getPath(),
	 * "text/plane")) { Log.d("CHECK", "TRUE"); } else { Log.d("CHECK",
	 * "FALSE"); } DriveModule.fDownloader(item, file, service); break; }
	 */
	static boolean checkFile(File in, String folderName, String fileName,
			Drive service, GoogleAccountCredential credential, String PATH,
			String mimeType, Activity activity) {
		OAuth(credential, activity);
		if (in.getMimeType().equals("application/vnd.google-apps.folder")) {
			Log.d("IN ID", in.getId());
			List<File> inFiles;
			try {
				inFiles = printFilesInFolder(service, in.getId(), "trashed = false");
				if (in.getTitle().equals(folderName)) {
					for (File file : inFiles) {
						if (file.getTitle().equals(fileName)) {
							try {
								service.files().delete(file.getId()).execute();
							} catch (IOException e) {
								Log.e("ERROR", e.toString());
							}
						}
						Log.d("ID", file.getId());
					}
					fileCreate(service, credential, in.getId(), PATH, mimeType,
							activity);

				}
				for (int i = 0; i < inFiles.size(); i++) {
					checkFile(inFiles.get(i), folderName, fileName, service,
							credential, PATH, mimeType, activity);
				}
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	/*
	 * フォルダ作成
	 */
	static File folderCreate(GoogleAccountCredential credential, Drive service,
			String folderName, String baseFolderId) {
		File g_Folder;
		String token = "";// credential.getToken();
		Log.i("", "String token = credential.getToken()" + token);

		File folder = new File();
		folder.setTitle(folderName);
		folder.setDescription("Yukari");
		folder.setMimeType("application/vnd.google-apps.folder");

		if (baseFolderId != null) {
			ParentReference parent = new ParentReference();
			parent.setId(baseFolderId);
			List<ParentReference> parents = new ArrayList<ParentReference>();
			parents.add(parent);
			folder.setParents(parents);
		}
		try {
			g_Folder = service.files().insert(folder).execute();
			Log.i("", "g_Folder=service.files().insert(folder).execute()");
			Permission permission = new Permission();
			 permission.setValue("");
			 permission.setType("user");
			 permission.setRole("owner");
			// permissionについて http://goo.gl/MNLVif
			service.permissions().insert(g_Folder.getId(), permission)
					.execute();
			return g_Folder;
		} catch (Exception e) {
			// Toast.makeText(GoogleDrive.this, "エラー",
			// Toast.LENGTH_LONG).show();
			Log.e("", "error" + e.toString());
			return null;
		}
	}

	/*
	 * フォルダ作成 初回用
	 */
	static File firstFolderCreate(GoogleAccountCredential credential,
			Drive service, String folderName) {
		File g_Folder;
		String token = "";// credential.getToken();
		try {
//			File folder = new File();
//			folder.setTitle(folderName);
//			folder.setDescription("Yukari");
//			folder.setMimeType("application/vnd.google-apps.folder");
//
//			g_Folder = service.files().insert(folder).execute();
//			Log.i("", "g_Folder=service.files().insert(folder).execute()");
//			Permission permission = new Permission() ;
//			 permission.setValue("me");
//			 permission.setRole("owner");
//			 permission.setType("user");
//			// permissionについて http://goo.gl/MNLVif
//			service.permissions().insert(g_Folder.getId(), permission)
//					.execute();
			File body = new File();
			body.setTitle(folderName);
			body.setMimeType("application/vnd.google-apps.folder");
			g_Folder = service.files().insert(body).execute();

			return g_Folder;
		} catch (Exception e) {
			// Toast.makeText(GoogleDrive.this, "エラー",
			// Toast.LENGTH_LONG).show();
			Log.e("", "error" + e.toString());
			return null;
		}

	}

	/*
	 * ファイルをアップロードする。
	 */
	static File fileCreate(final Drive service,
			final GoogleAccountCredential credential, final String folderId,
			final String PATH, final String mimeType, final Activity activity) {
		new Thread(new Runnable() {
			boolean a = false;

			@Override
			public void run() {
				while (!a) {
					// TODO Auto-generated method stub
					try {
						DriveModule.OAuth(credential, activity);

						ParentReference parent = new ParentReference();
						parent.setId(folderId);
						List<ParentReference> parents = new ArrayList<ParentReference>();
						parents.add(parent);

						java.io.File jfile = new java.io.File(PATH);
						FileContent mediaContent = new FileContent(mimeType,
								jfile);
						File body = new File();
						body.setTitle(jfile.getName());
						body.setDescription("Yukari 用データ。改変しないこと！！");
						body.setMimeType(mimeType);
						body.setParents(parents);
						File fileid = MainActivity.UPLOAD_FILE = service
								.files().insert(body, mediaContent).execute();


						// ファイルの日にちは編集して保存ボタンを押された時に処理する
						saveEditDate("fileIds",jfile.getName(),activity);
//						SharedPreferences ab = activity.getSharedPreferences(
//								"fileIds", Activity.MODE_PRIVATE);
//						SharedPreferences.Editor editor;
//						editor = ab.edit();
//						editor.putString(fileid.getTitle(), fileid.getId());
//						editor.commit();
						Log.i("fileId",
								fileid.getTitle() + "/" + fileid.getId());
						a = true;
						// finish();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		return MainActivity.UPLOAD_FILE;
	}

	/**
	 * Update an existing file's metadata and content.
	 *
	 * @param service
	 *            Drive API service instance.
	 * @param fileId
	 *            ID of the file to update.
	 * @param newTitle
	 *            New title for the file.
	 * @param newMimeType
	 *            New MIME type for the file.
	 * @param newFilename
	 *            Filename of the new content to upload.
	 * @param newRevision
	 *            Whether or not to create a new revision for this file.
	 * @return Updated file metadata if successful, {@code null} otherwise.
	 */
	private static File updateFile(Drive service, String fileId,
			String newTitle, String newMimeType, String newFilename,
			boolean newRevision) {
		try {
			// First retrieve the file from the API.
			File file = service.files().get(fileId).execute();

			// File's new metadata.
			file.setTitle(newTitle);
			file.setDescription("Yukari");
			file.setMimeType(newMimeType);

			// File's new content.
			java.io.File fileContent = new java.io.File(newFilename);
			FileContent mediaContent = new FileContent(newMimeType, fileContent);

			// Send the request to the API.
			File updatedFile = service.files()
					.update(fileId, file, mediaContent).execute();

			return updatedFile;
		} catch (IOException e) {
			System.out.println("An error occurred: " + e);
			return null;
		}
	}

	/**
	 * OAuthトークン表示 何か処理をするときはここの処理をかませる
	 */
	static void OAuth(final GoogleAccountCredential credential,
			final Activity activity) {
		new Thread(new Runnable() {
			@Override
			public void run() {

				// TODO Auto-generated method stub
				try {

					String token = credential.getToken();
					MainActivity.get = true;
					Log.i("", token);

				} catch (UserRecoverableAuthException e) {
					Log.e("OAUTH", e.toString());
					// TODO Auto-generated catch block
					activity.startActivityForResult(e.getIntent(), REQUEST_AUTHORIZATION);
					MainActivity.get = false;

				} catch (GoogleAuthException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Log.e("OAUTH", e.toString());
					MainActivity.get = false;

				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e("OAUTH", e.toString());
					e.printStackTrace();
				}
			}
		}).start();

	}

	/**
	 * googleDriveのファイルを受け取って日付を比較してtrue falseで返す 新しかったらtrue古かったらfalse
	 * */
	static boolean date(File item, Activity activity) {
		// adapter.add(item.getTitle());
		String day = item.getCreatedDate().toString();
		// Log.d("DATE ()",day+"CREATE→"+item.getCreatedDate().toString());
		String[] days = day.split("T", 0);
		String datetime = "";
		for (int i = 0; i < days.length; i++) {

			datetime += days[i];
			Log.i("TIME", datetime);
		}
		StringBuilder sb = new StringBuilder(datetime);
		for (int i = 0; i < 5; i++) {
			sb.deleteCharAt(datetime.length() - 5);
		}

		SimpleDateFormat C = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Date date = null;
		Date dates = null;
		try {
			String d2 = sb.toString();
			date = C.parse(d2);
			// Log.d("datetime１１２２  ", date.toString());

			SharedPreferences.Editor editor;
			SharedPreferences pref = activity.getSharedPreferences("fileDate",
					Activity.MODE_PRIVATE);
			// datetime itemの日
			String d1;
			if (pref.getString(item.getTitle(), null) == null) {
				d1 = "1996-08-0111:11:11";
			} else {
				d1 = pref.getString(item.getTitle(), null);
				Log.d("pref day", d1.toString());
			}

			dates = C.parse(d1);
			Log.i("date", date.toString());
			Log.i("dates", dates.toString());
			int diff = date.compareTo(dates);
			Log.d("diff", String.valueOf(diff));

			// 保存した日付よりも新しい場合
			if (diff > 0) {
				Log.d("IN", "IN");

				// 新しい場合に日付更新(現在日時保存)する
				Date Now = new Date();
				long Time = Now.getTime();
				Time = Time + 100000L;
				String n;
				n = C.format(new Date(Time));
				editor = pref.edit();
				editor.putString(item.getTitle(), n);
				editor.commit();
				Log.d("LATEST DAY", n.toString());

				return true;

			}
			// Log.d("compare to", Now.toString());
		} catch (Exception e) {
			Log.e("ERROR", e.toString());
		}
		return false;
	}

	/**
	 * ファイルを探してもしあったらダウンロード パスと名前指定でできる
	 * */
	static boolean checkDrive(Drive service, String folderId, String fileName,
			String path, String mimeType,Activity activity) {
		Children.List request;
		try{
		request = service.children().list(folderId).setQ("title = '" + fileName + "' and mimeType = '" + mimeType+ "' and trashed = false");
		List<File> cFile = new ArrayList<File>();
		do {
			try {
				ChildList children = request.execute();
				for (ChildReference child : children.getItems()) {
					Log.i("FILE ID LIST",
							"File name "
									+ service.files().get(child.getId())
											.execute().getTitle());
					cFile.add(service.files().get(child.getId()).execute());
					// Log.e("TITLE",file.getTitle())
				}
				request.setPageToken(children.getNextPageToken());
			} catch (IOException e) {
				System.out.println("An error occurred: " + e);
				request.setPageToken(null);
			}
		} while (request.getPageToken() != null
				&& request.getPageToken().length() > 0);
		if (cFile.size() > 1) {
			Log.e("ファイルの重複", "ファイルの重複");
		}
		File dj = cFile.get(0);
		if (date(dj, activity)) {
			InputStream data = downloadFile(service, dj);
			if (data != null) {
				DriveModule a = new DriveModule();
				a.fileSave(data,path);
				saveEditDate("fileDate",dj.getTitle(), activity);
				return true;
			}
		}
		}catch (IOException e){
			Log.e("DL Error",e.toString());
		return false;
		}
		return false;
	}

	// static List<File> printFilesInFolder(Drive service, String folderId,
	// String query) throws IOException {
	// Children.List request;
	// if (query != null) {
	// request = service.children().list(folderId);
	// } else {
	// request =
	// service.children().list(folderId).setQ(query+" and trashed = false");
	// }
	// List<File> cFile = new ArrayList<File>();
	// do {
	// try {
	// ChildList children = request.execute();
	// for (ChildReference child : children.getItems()) {
	// Log.i("FILE ID LIST","File name "+
	// service.files().get(child.getId()).execute().getTitle());
	// cFile.add(service.files().get(child.getId()).execute());
	// // Log.e("TITLE",file.getTitle())
	// }
	// request.setPageToken(children.getNextPageToken());
	// } catch (IOException e) {
	// System.out.println("An error occurred: " + e);
	// request.setPageToken(null);
	// }
	// } while (request.getPageToken() != null
	// && request.getPageToken().length() > 0);
	// return cFile;
	// }
	/**
	 * 編集日の保存
	 * */
	static void saveEditDate(String prefName, String fileName, Activity activity) {
		SimpleDateFormat C = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Date Now = new Date();
		long Time = Now.getTime();
		Time = Time + 100000L;
		String n;
		n = C.format(new Date(Time));
		// Log.d("n day",n);
		SharedPreferences.Editor editor;
		SharedPreferences pref = activity.getSharedPreferences(prefName,
				Activity.MODE_PRIVATE);
		editor = pref.edit();
		editor.putString(fileName, n);
		// Toast.makeText(MainActivity.this, data,
		// Toast.LENGTH_LONG).show();
		editor.commit();
	}

	/**
	 * ファイル保存。
	 * */
	static void fileSave(final InputStream inputStream, final String path1) {

		Thread thread = new Thread(new Runnable() {
			boolean a = false;

			@Override
			public void run() {
				while (!a) {
					java.io.File dstFile = new java.io.File(path1);
					// dstFile.mkdir();
					OutputStream output = null;
					try {
						output = new FileOutputStream(dstFile);
						int DEFAULT_BUFFER_SIZE = 1024 * 5;
						byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
						int size = -1;
						while (-1 != (size = inputStream.read(buffer))) {
							output.write(buffer, 0, size);
						}
						output.close();
						a = true;
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						Log.e("OUPUT ERROR", e.toString());
						a = true;
					}
				}
			}
		});
		thread.start();

	}

	static File fileDataCreate(String fileId) {
		try {
			File file = GoogleDriveBuild.service.files().get(fileId).execute();
			return file;
		} catch (Exception e) {
			Log.e("CREATE ERROR", e.toString());
			return null;
		}

	}

	/**
	 * Yukariとファイルを除いたパスを投げてください。フォルダを作成します。
	 */
	static void folderCreator(String rootFolderId,
			GoogleAccountCredential credential, Drive service, String path,
			Activity activity) throws IOException {
		try {
			String[] folders = path.split("/", 0);
			String f;
			for (String folder : folders) {
				f = folderChecker(rootFolderId, folder, activity).getId();
				rootFolderId = f;
				Log.i("folder name", folder);
			}
		} catch (Exception e) {
		}
	}

	/**
	 * フォルダが有るか確認します。 同時に高速化を兼ねてフォルダのIDを保存します。 最初に必ず必要な処理です。
	 * */
	static File folderChecker(String folderId, String folderName,
			Activity activity) throws IOException {
		List<File> fileList = printFilesInFolder(
				GoogleDriveBuild.service,
				folderId,
				"title = '"
						+ folderName
						+ "' and mimeType = 'application/vnd.google-apps.folder'");
		File a = null;
		File b = null;
		boolean filefound = false;
		for (File listFile : fileList) {
			Log.i("FILENAME", "FILENAME..." + listFile.getTitle());
			if (listFile.getMimeType().toString()
					.equals("application/vnd.google-apps.folder")
					&& listFile.getTitle().equals(folderName)) {
				Log.i("true", listFile.getTitle());
				a = listFile;
				filefound = true;
				SharedPreferences.Editor editor;
				SharedPreferences pref = activity.getSharedPreferences(
						"foldersId", Activity.MODE_PRIVATE);
				editor = pref.edit();
				editor.putString(a.getTitle(), a.getId());
				editor.commit();
				Log.i("FOLDER ID", pref.getString(a.getTitle(), "NOT FOUND!!"));
				return a;
			} else {
			}
		}
		if (filefound == false) {
			b = folderCreate(GoogleDriveBuild.credential,
					GoogleDriveBuild.service, folderName, folderId);
			SharedPreferences.Editor editor;
			SharedPreferences pref = activity.getSharedPreferences("foldersId",
					Activity.MODE_PRIVATE);
			editor = pref.edit();
			editor.putString(b.getTitle(), b.getId());
			editor.commit();
			Log.i("FOLDER ID", pref.getString(b.getTitle(), "NOT FOUND!!"));
			return b;
		}
		Log.e("RETURN NULL!", "RETURN NULL!");
		return null;
	}

}