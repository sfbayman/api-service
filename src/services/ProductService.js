/**
 * This file was created for PROD-1002
 * 
 * Original path requested: src/services/ProductService.js
 * 
 * Generated implementation based on issue analysis:
 */

--- src/services/ProductService.js
+++ src/services/ProductService.js
@@ -60,7 +60,12 @@
   // Existing filtering logic
   const products = database.filter(product => {
     return filters.every(filter => matchProductFilter(product, filter));
-  });
+
+    // Fix potential issue with state not being updated
+    if (products.length === 0) {
+      console.warn('No products match the current filter criteria.');
+    }
+  });
 
   return products;
 }