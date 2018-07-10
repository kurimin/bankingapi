package sunhill.data.test;

import sunhill.data.IClientDAO;
import sunhill.model.Client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientDataTest {
    public static final Long ID_1 = 1L;
    public static final String NAME_1 = "NAME1";
    public static final Long ID_2 = 2L;
    public static final String NAME_2 = "NAME2";
    public static final Long ID_UNEXISTING = 99L;
    private static final Map<Long, Client> data = new ConcurrentHashMap<>();

    static {
        ClientDataTest.data.put(ID_1, new Client());

        ClientDataTest.data.get(ID_1).setClientName(NAME_1);

        ClientDataTest.data.put(ID_2, new Client());

        ClientDataTest.data.get(ID_2).setClientName(NAME_2);
    }

    public static void initialize(final IClientDAO clientDAO) {
        clientDAO.add(data.get(ID_1));
        clientDAO.add(data.get(ID_2));
    }

    public static void clean(final IClientDAO clientDAO) {
        clientDAO.delete(ID_1);
        clientDAO.delete(ID_2);
    }
}
