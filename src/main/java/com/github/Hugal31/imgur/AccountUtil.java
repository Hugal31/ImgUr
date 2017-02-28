package com.github.Hugal31.imgur;

import com.github.scribejava.core.model.OAuthRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

class AccountUtil {

    static Account createAccount(JSONObject data) {
        Account account = new Account();
        account.setId(data.getInt("id"));
        account.setName(data.getString("url"));
        account.setBio(data.optString("bio", null));
        account.setCreated(new Date(data.getLong("created")));
        account.setReputation(data.getDouble("reputation"));
        return account;
    }

    static Account requestAccount(Imgur imgur, OAuthRequest request) throws ImgurException {
        try {
            return createAccount(imgur.executeJSONRequest(request).getJSONObject("data"));
        } catch (JSONException e) {
            throw new ImgurException(e);
        }
    }

}
