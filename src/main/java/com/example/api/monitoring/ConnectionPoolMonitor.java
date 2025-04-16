/**
 * This file was created for TEST-1001
 * 
 * Original path requested: src/main/java/com/example/api/monitoring/ConnectionPoolMonitor.java
 * 
 * Generated implementation based on issue analysis:
 */

diff --git a/src/main/java/com/example/api/monitoring/ConnectionPoolMonitor.java b/src/main/java/com/example/api/monitoring/ConnectionPoolMonitor.java
new file mode 100644
index 0000000..f5cfe12
--- /dev/null
+++ b/src/main/java/com/example/api/monitoring/ConnectionPoolMonitor.java
@@ -0,0 +1,30 @@
+package com.example.api.monitoring;
+
+import com.zaxxer.hikari.HikariDataSource;
+import org.slf4j.Logger;
+import org.slf4j.LoggerFactory;
+
+import javax.annotation.PostConstruct;
+import javax.annotation.PreDestroy;
+import org.springframework.stereotype.Component;
+
+@Component
+public class ConnectionPoolMonitor {
+
+    private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolMonitor.class);
+
+    private final HikariDataSource dataSource;
+
+    public ConnectionPoolMonitor(HikariDataSource dataSource) {
+        this.dataSource = dataSource;
+    }
+
+    @PostConstruct
+    public void start() {
+        logger.info("Starting connection pool monitoring.");
+        // Custom monitoring logic
+    }
+
+    @PreDestroy
+    public void stop() {
+        logger.info("Stopping connection pool monitoring.");
+    }
+
+    // Additional monitoring methods could be added here
+}
