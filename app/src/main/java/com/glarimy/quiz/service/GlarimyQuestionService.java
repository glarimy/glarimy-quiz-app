package com.glarimy.quiz.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Question;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
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
                HttpURLConnection urlConnection = null;
                URL url = new URL(uri);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();

                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception " + e.getMessage());
                }
                String jsonString = sb.toString();
                reader.close();

                JSONObject jsonObject = new JSONObject().getJSONObject(jsonString);

                //getting the data from JSONObject and setting to the properties of Question class*/

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
        }
        return question;
    }

    @Override
    public Answer getAnswer(int questionId) {

        Answer answer = new Answer();

        if (isConnected()) {
            try {
                HttpURLConnection urlConnection = null;

                URL url = new URL("http://www.glarimy.com/q?id=" + questionId);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setDoOutput(true);
                urlConnection.connect();

                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                StringBuilder sb = new StringBuilder();
                String line;

                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append('\n');
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception " + e.getMessage());
                }
                String jsonString = sb.toString();
                reader.close();

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
        }
        return answer;
    }

    /**method to check the device is connected to a network or not
     * @return true if connected to network else false
     */
    public boolean isConnected()
    {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if ((connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null && connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected())
                || (connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI) != null && connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .isConnected()))
        {
            return true;
        }
        else {
            return false;
        }
    }
}
