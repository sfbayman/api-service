/**
 * This file was created for TEST-1001
 * 
 * Original path requested: src/main/java/com/example/api/service/UserService.java
 * 
 * Generated implementation based on issue analysis:
 */

--- src/main/java/com/example/api/service/UserService.java
+++ src/main/java/com/example/api/service/UserService.java
@@ -98,7 +98,11 @@
     public User getUserDetails(String userId) {
         Connection connection = null;
         try {
-            connection = databaseConnectionManager.getConnection();
+            connection = dataSource.getConnection();
             // Execute query and process result
         } catch (SQLException e) {
+            // Handle SQL exception
+            logger.error("SQL exception while retrieving user details", e);
+        } finally {
+            // Ensure proper closure of connection
+            if (connection != null) try { connection.close(); } catch (SQLException ex) { logger.error("Error closing connection", ex); }
         }
     }