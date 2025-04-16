package com.example.api.service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Service for handling database operations
 */
public class DatabaseService {
    private static final Logger logger = Logger.getLogger(DatabaseService.class.getName());
    
    private final DataSource dataSource;
    
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Executes a SQL query
     * 
     * @param query The SQL query to execute
     */
    public void executeQuery(String query) {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            // Execute query
            statement.execute();
        } catch (SQLException e) {
            logger.severe("SQL Exception occurred: " + e.getMessage());
        } finally {
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { logger.severe("Failed to close connection: " + e.getMessage()); }
            }
        }
    }
    
    /**
     * Processes a database transaction
     */
    public void processTransaction() {
        // Database transaction logic
    }
}


/* Changes for TEST-1001:
@@ -6,7 +6,7 @@
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Service for handling database operations
 */
public class DatabaseService {
    private static final Logger logger = Logger.getLogger(DatabaseService.class.getName());
    
    private final DataSource dataSource;
    
    public DatabaseService(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    /**
     * Executes a SQL query
     * 
     * @param query The SQL query to execute
     */
    public void executeQuery(String query) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(query);
            // Execute query
            statement.execute();
        } catch (SQLException e) {
            logger.severe("SQL Exception occurred: " + e.getMessage());
        } finally {
            // Fixed memory leak by properly closing resources
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { logger.severe("Failed to close statement: " + e.getMessage()); }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { logger.severe("Failed to close connection: " + e.getMessage()); }
            }
        }
    }
*/

/* Changes for TEST-1001:
diff --git a/src/main/java/com/example/api/service/DatabaseService.java b/src/main/java/com/example/api/service/DatabaseService.java
index 1234567..7654321 100644
--- a/src/main/java/com/example/api/service/DatabaseService.java
+++ b/src/main/java/com/example/api/service/DatabaseService.java
@@ -5,6 +5,7 @@ import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Connection;
+import javax.sql.DataSource;
 
 public class DatabaseService {
     private DataSource dataSource;
@@ -22,14 +23,15 @@ public class DatabaseService {
     }
 
     public ResultSet fetchData(String query) throws SQLException {
-        Connection connection = dataSource.getConnection();
-        PreparedStatement stmt = connection.prepareStatement(query);
-        ResultSet rs = stmt.executeQuery();
-        // ... process the result
-        rs.close();
-        stmt.close();
-        connection.close();
-        return rs;
+        try (Connection connection = dataSource.getConnection();
+             PreparedStatement stmt = connection.prepareStatement(query);
+             ResultSet rs = stmt.executeQuery()) {
+            // ... process the result
+            return rs;
+        } catch (SQLException e) {
+            // Log the exception and potentially rethrow or handle appropriately
+            throw e;
+        }
     }
 }

*/

/* Changes for TEST-1001:
diff --git a/src/main/java/com/example/api/service/DatabaseService.java b/src/main/java/com/example/api/service/DatabaseService.java
index a9f5b3c..4d2e6fa 100644
--- a/src/main/java/com/example/api/service/DatabaseService.java
+++ b/src/main/java/com/example/api/service/DatabaseService.java
@@ -45,11 +45,15 @@ public class DatabaseService {
         Connection connection = null;
         try {
             connection = connectionPool.getConnection();
-            // execute database operations
+            // execute database operations
+        } catch (SQLException e) {
+            // handle exception
+            logger.error("Database error: ", e);
         } finally {
-            if (connection != null) {
-                connection.close();
-            }
+            closeConnection(connection);
+        }
+    }
+
+    private void closeConnection(Connection connection) {
+        // ensure the connection is properly closed
+        if (connection != null) {
+            try {
+                connection.close();
+            } catch (SQLException e) {
+                logger.error("Error closing connection: ", e);
+            }
         }
     }
 }
*/