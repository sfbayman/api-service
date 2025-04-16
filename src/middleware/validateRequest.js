/**
 * This file was created for PROD-1002
 * 
 * Original path requested: src/middleware/validateRequest.js
 * 
 * Generated implementation based on issue analysis:
 */

--- src/middleware/validateRequest.js
+++ src/middleware/validateRequest.js
@@ -20,6 +20,10 @@
   const { filters } = req.body;
   if (!filters || !Array.isArray(filters)) {
     return res.status(400).json({ error: 'Invalid filter format.' });
+  }
+
+  // Log the incoming filters for better traceability
+  console.info('Applying filters:', filters);
 
   next();
 }
