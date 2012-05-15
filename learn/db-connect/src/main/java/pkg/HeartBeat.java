package pkg;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.UUID;

/**
 *
 */
public class HeartBeat extends TimerTask {
    private static final SimpleDateFormat ISO_8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    private final UUID id;

    public HeartBeat(UUID id) {
        this.id = id;
        final String date = getDate();
        Util.withConnection(new ConnectionOp() {
            @Override
            public void run(Connection conn) throws Exception {
                String sql = "create table if not exists heartbeat (id UUID primary key, started varchar(32), last_beat varchar(32));";
                sql += "\n insert into heartbeat values ( '" + HeartBeat.this.id.toString() + "', '" + date + "', '" + date + "');";
                conn.createStatement().execute(sql);
            }
        });
        Log.info("Heartbeat session " + id + " created at " + date);
    }

    @Override
    public void run() {
        final String date = getDate();
        Util.withConnection(new ConnectionOp() {
            @Override
            public void run(Connection conn) throws Exception {
                String sql = "update heartbeat set last_beat = '" + date + "' where id = '" + id + "'";
                conn.createStatement().executeUpdate(sql);
            }
        });
        Log.info("Heartbeat at " + date);
    }

    private String getDate() {
        return ISO_8601.format(new Date());
    }
}
