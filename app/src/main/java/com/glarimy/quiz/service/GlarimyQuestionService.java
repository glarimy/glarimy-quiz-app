package com.glarimy.quiz.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Handler;

import com.glarimy.quiz.app.MainActivity;
import com.glarimy.quiz.model.Answer;
import com.glarimy.quiz.model.Question;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class GlarimyQuestionService implements QuestionService {
    Context context;
    Question question = new Question();
    Answer answer = new Answer();


    public GlarimyQuestionService(Context context) {
        this.context = context;
    }

    @Override
    public Question get() throws MalformedURLException, ExecutionException, InterruptedException, JSONException {

        if (isConnected()) {
            String stringUrl = "http://www.glarimy.com/q";
            URL uri = new URL(stringUrl);

            //creating object and calling the async task
            final CloudConnection cloudConnection = new CloudConnection((MainActivity) context);
            cloudConnection.execute(uri);

            //getting the data from AsyncTask
            String jsonString = cloudConnection.get();

            //getting the data from JSONObject and setting to the properties of Question class*/
            final JSONObject[] JsonQuestionObject = {new JSONObject().getJSONObject(jsonString)};


            Handler questionHandler = new Handler();
            questionHandler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        JsonQuestionObject[0] = new JSONObject(cloudConnection.get());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    try {
                        question.setId(Integer.parseInt(JsonQuestionObject[0].getString("id")));
                        question.setTitle(JsonQuestionObject[0].getString("title"));
                        question.setDescription(JsonQuestionObject[0].getString("question"));
                        question.setOptionOne(JsonQuestionObject[0].getString("a"));
                        question.setOptionTwo(JsonQuestionObject[0].getString("b"));
                        question.setOptionThree(JsonQuestionObject[0].getString("c"));
                        question.setOptionFour(JsonQuestionObject[0].getString("d"));

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (question.getId() != 0 &&
                            !question.getTitle().equals("") &&
                            !question.getDescription().equals("") &&
                            !question.getOptionOne().equals("") &&
                            !question.getOptionTwo().equals("") &&
                            !question.getOptionThree().equals("") &&
                            !question.getOptionFour().equals("")) {

                    } else
                        answer = null;
                }
            }, 100);

        } else {
            //code to be executed when no internet connectivity
            question = null;
        }
        return question;
    }

    @Override
    public Answer getAnswer(final int questionId) throws MalformedURLException, ExecutionException, InterruptedException, JSONException {
        if (isConnected()) {
            String stringUrl = "http://www.glarimy.com/q?id=" + questionId;
            URL uri = new URL(stringUrl);

            final CloudConnection cloudConnection = new CloudConnection((MainActivity) context);
            cloudConnection.execute(uri);

            String jsonString = cloudConnection.get();
            final JSONObject[] JsonQuestionObject = {new JSONObject().getJSONObject(jsonString)};


            Handler answerHandler = new Handler();
            answerHandler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        JsonQuestionObject[0] = new JSONObject(cloudConnection.get());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    try {
                        switch (JsonQuestionObject[0].getString("key")) {
                            case "a":
                                answer.setCorrectOption(1);
                                break;
                            case "b":
                                answer.setCorrectOption(2);
                                break;
                            case "c":
                                answer.setCorrectOption(3);
                                break;
                            case "d":
                                answer.setCorrectOption(4);
                                break;
                        }
                        answer.setQuestionId(JsonQuestionObject[0].getInt("id"));

                        if (JsonQuestionObject[0].getInt("id") < 0)
                            answer = null;
                        if (JsonQuestionObject[0].getInt("id") != questionId)
                            answer = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }, 100);
        } else {
            answer = null;
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
        private ProgressDialog progress;

        public CloudConnection(MainActivity context)
        {
            progress = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress = new ProgressDialog(context);
            progress.setMessage("Loading...");
            progress.show();
        }

        @Override
        protected String doInBackground(URL... params) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = params[0];
                urlConnection.setDoInput(true);
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(10 * 1000);
                urlConnection.setReadTimeout(10 * 1000);

                if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                    String line;
                    while ((line = reader.readLine()) != null)
                        result.append(line);

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
            progress.dismiss();
        }
    }
}
