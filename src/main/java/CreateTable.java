import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;


public class CreateTable {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        Connection connection = ConnectionFactory.createConnection(conf);

        try {
            Admin admin = connection.getAdmin();
            HTableDescriptor tableName = new HTableDescriptor(TableName.valueOf("newTable2"));
            tableName.addFamily(new HColumnDescriptor("family 1"));
            tableName.addFamily(new HColumnDescriptor("family 2"));

            if (!admin.tableExists(tableName.getTableName())) {
                System.out.println("Creating new table");
                admin.createTable(tableName);
                System.out.println("DONE");

            } else {
                System.out.println("Table already exists");
            }

        } finally {
            connection.close();
        }
    }
}
