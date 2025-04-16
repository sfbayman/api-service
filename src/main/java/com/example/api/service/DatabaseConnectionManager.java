/**
 * This file was created for TEST-1001
 * 
 * Original path requested: src/main/java/com/example/api/service/DatabaseConnectionManager.java
 * 
 * Generated implementation based on issue analysis:
 */

--- src/main/java/com/example/api/service/DatabaseConnectionManager.java
+++ src/main/java/com/example/api/service/DatabaseConnectionManager.java
@@ -45,6 +45,10 @@
             connection = dataSource.getConnection();
             // database operations
         } catch (SQLException e) {
+            // Log exception here
+            logger.error("Error while getting database connection", e);
+        } finally {
+            // Ensure connection is closed
+            if (connection != null) try { connection.close(); } catch (SQLException e) { logger.error("Error closing connection", e); } 
         }
         return connection;
     }