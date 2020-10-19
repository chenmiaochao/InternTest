package sdcj.nsk.pj001.utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author shutsuno
 * @param errorId , args[]
 * @return msg
 * @thows MissingResourceException
 * @implNote プロパティファイルからエラーメッセージを取得し、画面に返すメソッド
 */
public class MessageUtil {
	//TODO: propertiesファイルからerrorIDを検索し、存在するなら妥当なメッセージを返す
	public static String errorMessage(String errorId,String args[]) {

		//ResourceBundle : プロパティファイルの読み込みに必要な関数
		 ResourceBundle bundle = null;

		    //validateMsg.propertiesの読み込み
	        try {
	            bundle = ResourceBundle.getBundle("validateMsg");
	        } catch (MissingResourceException e) {
	            e.printStackTrace();
	            return null;
	        }

	        String value = null;

	        try {

	        	//エラーIDからエラーメッセージを取得
	            value = bundle.getString(errorId);

	            //置換文字列があれば必要分置換を行う
	            if(args!=null) {
	            	for(int i=0 ; i<args.length ; i++) {
		            	value = value.replace("{"+ i +"}", args[i]);
		            }
	            }

	        //該当するエラーIDが存在しない場合はnullを返す
	        } catch (MissingResourceException e) {
	            e.printStackTrace();
	            return null;
	        }

	    //エラーメッセージを返す
		return value;

	}
}