package com.example.koji.api;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


/**
 * Created by koji on 西暦18/05/06.
 */

/**
 * AsyncTaskを使った非同期処理のきほん
 * https://dev.classmethod.jp/smartphone/android/asynctask/
 *
 * [Android] HttpURLConnection:GETリクエストでテキストを取得する
 * https://dev.classmethod.jp/smartphone/android/asynctask/
 */

public class HttpGetTask extends AsyncTask<URL, Void, String> {

    private TextView textView;

    /**
     * コンストラクタ
     */
    public HttpGetTask(TextView textView) {
        super();
        this.textView = textView;
    }

    @Override
    /**
     * バックグランドで行う処理
     */
    protected String doInBackground(URL... urls) {
        // 取得したテキストを格納する変数
        final StringBuilder result = new StringBuilder();
        // アクセス先URL
        final URL url = urls[0];

        HttpURLConnection con = null;
        try {
            // ローカル処理
            // コネクション取得
            con = (HttpURLConnection) url.openConnection();
            con.connect();

            Log.i("con", String.valueOf(con));

            // HTTPレスポンスコード
            final int status = con.getResponseCode();
            Log.i("status", String.valueOf(status));
            if (status == HttpURLConnection.HTTP_OK) {
                // 通信に成功した
                // テキストを取得する
                final InputStream in = con.getInputStream();
                final String encoding = "utf-8";
                final InputStreamReader inReader = new InputStreamReader(in, encoding);
                final BufferedReader bufReader = new BufferedReader(inReader);
                String line = null;
                // 1行ずつテキストを読み込む
                while((line = bufReader.readLine()) != null) {
                    result.append(line);
                }
                bufReader.close();
                inReader.close();
                in.close();
            }

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (con != null) {
                // コネクションを切断
                con.disconnect();
            }
        }

        Log.i("result", String.valueOf(result));
        return result.toString();
    }

    @Override
    /**
     * バックグランド処理が完了し、UIスレッドに反映する
     */
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        textView.setText(String.valueOf(result));
    }
}