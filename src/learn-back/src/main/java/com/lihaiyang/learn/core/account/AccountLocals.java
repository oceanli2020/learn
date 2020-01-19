package com.lihaiyang.learn.core.account;

import org.springframework.stereotype.Component;

@Component
public class AccountLocals {
    private static final ThreadLocal<Account> ACCOUNTS = new ThreadLocal();

    public static Account getAccount() {
        return ACCOUNTS.get();
    }

    public static void setAccount(Account account) {
        ACCOUNTS.set(account);
    }

    public static void removeAccount() {
        ACCOUNTS.remove();
    }
}