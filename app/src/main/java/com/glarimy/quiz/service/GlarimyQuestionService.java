package com.glarimy.quiz.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.util.Log;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Question;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.ContentValues.TAG;


public class GlarimyQuestionService implements QuestionService {

    Context context;

    public GlarimyQuestionService(Context context) {
        this.context = context;
    }

    public String uri = "http://www.glarimy.com/q";

    @Override
    public Question get() {
        Question question = new Question();

        if (isConnected()) {
            try {
                String stringUrl = "http://www.glarimy.com/q";
                URL uri = new URL(stringUrl);

                //creating object and calling the async task
                CloudConnection cloudConnection = new CloudConnection();
                cloudConnection.execute(uri);

                //getting the data from AsyncTask
                String jsonString = cloudConnection.get();

                //getting the data from JSONObject and setting to the properties of Question class*/
                JSONObject jsonObject = new JSONObject().getJSONObject(jsonString);

                question.setId(jsonObject.getInt("id"));
                question.setTitle(jsonObject.getString("title"));
                question.setDescription(jsonObject.getString("question"));
                question.setOptionOne(jsonObject.getString("a"));
                question.setOptionTwo(jsonObject.getString("b"));
                question.setOptionThree(jsonObject.getString("c"));
                question.setOptionFour(jsonObject.getString("d"));
            } catch (Exception e) {
                Log.e(TAG, "Exception " + e.getMessage());
            }
        } else {

        }
        return question;
    }


    @Override
    public Answer getAnswer(int questionId) {
        Answer answer = new Answer();
        if (isConnected()) {
            try {
                String stringUrl = " http://www.glarimy.com/q?id=" + questionId;
                URL uri = new URL(stringUrl);

                CloudConnection cloudConnection = new CloudConnection();
                cloudConnection.execute(uri);

                String jsonString = cloudConnection.get();
                JSONObject jsonObject = new JSONObject().getJSONObject(jsonString);

                String correctOption = jsonObject.getString("key");
                answer.setQuestionId(jsonObject.getInt("id"));

                if (correctOption.equals("a"))
                    answer.setCorrectOption(1);
                else if (correctOption.equals("b"))
                    answer.setCorrectOption(2);
                else if (correctOption.equals("c"))
                    answer.setCorrectOption(3);
                else if (correctOption.equals("d"))
                    answer.setCorrectOption(4);
            } catch (Exception e) {
                Log.e(TAG, "Exception " + e.getMessage());
            }
        } else {

        }
        return answer;
    }


    /**
     * method to check the device is connected to a network or not
     *
     * @return true if connected to network else false
     */
    public boolean isConnected() {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ((connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected())
                || (connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * AsyncTask for connecting to the required URL
     * <p>
     * this AsyncTask was called by two methods in this class
     * get() and getAnswer()
     */
    class CloudConnection extends AsyncTask<URL, Void, String> {

        private HttpURLConnection urlConnection;
        private ProgressDialog mDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mDialog = new ProgressDialog(context);
            mDialog.setMessage("Retrieving data...");
            mDialog.show();
        }

        @Override
        protected String doInBackground(URL... params)
        {
            StringBuilder result = new StringBuilder();
            try
            {
                URL url = params[0];
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(20 * 1000);
                urlConnection.setReadTimeout(20 * 1000);

                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String jsonString) {
            super.onPostExecute(jsonString);
            mDialog.dismiss();
        }
    }
}
