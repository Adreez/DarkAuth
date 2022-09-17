package sk.adr3ez.darkauth.shared.sql;

public class SQLGetter {

    public SQLGetter() {
        data().createTable();
        sessions().createTable();
    }
    public SQLData data() {
        return new SQLData("data");
    }
    public SQLSessions sessions() {
        return new SQLSessions("sessions");
    }

}
