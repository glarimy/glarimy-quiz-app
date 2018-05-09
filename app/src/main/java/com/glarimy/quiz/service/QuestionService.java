package com.glarimy.quiz.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public interface QuestionService {
    public  boolean isConntected(Context context);
    public int get();
    public int getAnswer();
}
class QuestionServic extends BroadcastReceiver implements QuestionService{
    /**
     * <h1>onReceive</h1>
     * <p>
     * calls all the registered broadast recivers.
     * </p>
     *
     *
     * @param context allows access to application specific resources.
     * @param intent unused.
     *
     * @since 2018-05-09.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        if (isConntected(context)){
            // if connected with internet
            Toast.makeText(context, " Connected ", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, " Not Connected ", Toast.LENGTH_LONG).show();
        }
    }

    /**
     *<h1>isConnected</h1>
     *
     * <p>this method is used to check the active network connection
     * it works for both wi-fi and mobile data.</p>
     *
     *
     * @param context allows access to application specific resources.
     * @return boolean.
     *
     *
     * @since 2081-05-09.
     */
    @Override
    public boolean isConntected(Context context) {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // Check for network connections
        assert connec != null;
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet
            Toast.makeText(context, " Connected ", Toast.LENGTH_LONG).show();
            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            return false;
        }
        return false;
    }

    @Override
    public int get() {
        return 0;
    }

    @Override
    public int getAnswer() {
        return 0;
    }
}
