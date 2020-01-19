package com.lihaiyang.learn.core.account;

import java.io.Serializable;

public interface Account extends Serializable {

    Account getAccount();

    String getPassword();

}