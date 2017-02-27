package com.github.Hugal31.imgur;

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

}
