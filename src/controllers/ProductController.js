/**
 * This file was created for PROD-1002
 * 
 * Original path requested: src/controllers/ProductController.js
 * 
 * Generated implementation based on issue analysis:
 */

--- src/controllers/ProductController.js
+++ src/controllers/ProductController.js
@@ -45,6 +45,10 @@
     const filters = req.body.filters;
     const products = await ProductService.getFilteredProducts(filters);
 
+    // Ensure client receives fresh data by correctly setting cache headers
+    res.set('Cache-Control', 'no-store');
+
     // Update state for client side
     res.json({
       success: true,
