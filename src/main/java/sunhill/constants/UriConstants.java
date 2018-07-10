package sunhill.constants;

public final class UriConstants {

    public static final String BASE_PATH = "/bankingApi";

    public static final String CLIENT_PATH = "clients";
    public static final String CLIENT_PATHVAR = "clientId";
    public static final String CLIENT_URI_ROOT = BASE_PATH + "/" + CLIENT_PATH;
    public static final String CLIENT_URI = CLIENT_URI_ROOT + "/{" + CLIENT_PATHVAR + "}";

    public static final String ACCOUNT_PATH = "accounts";
    public static final String ACCOUNT_PATHVAR = "accountId";
    public static final String ACCOUNT_URI_ROOT = CLIENT_URI + "/" + ACCOUNT_PATH;
    public static final String ACCOUNT_URI = ACCOUNT_URI_ROOT + "/{" + ACCOUNT_PATHVAR + "}";
    public static final String ACCOUNT_URI_DEPOSIT = ACCOUNT_URI + "/deposit";
    public static final String ACCOUNT_URI_WITHDRAW = ACCOUNT_URI + "/withdraw";
    public static final String ACCOUNT_URI_BALANCE = ACCOUNT_URI + "/balance";

}
