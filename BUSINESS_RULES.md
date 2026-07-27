# SR Arts Business Rules

Version: 1.0

Author: Vishal Raut

---

# Module 1 - Authentication Rules

1. Email must be unique.

2. Password must be encrypted.

3. One user can have only one role.

4. Users are never permanently deleted.

5. Inactive users cannot log in.

---

# Module 2 - Product Catalog Rules

1. A category can contain multiple products.

2. A product belongs to only one category.

3. A product can have multiple images.

4. Only one image can be the cover image.

5. Products are never physically deleted.

6. Admin can activate or deactivate products.

7. A customization field can be reused by multiple products.

8. A customization option may increase the product price.

9. Product Price = Base Price + Additional Customization Price.

10. Products marked "Coming Soon" cannot be ordered.

11. Products marked "Out of Stock" remain visible but cannot be purchased.

12. Products marked "Discontinued" remain visible in previous orders but cannot be purchased again.

13. Every product must belong to a category.

14. Every product must have at least one cover image.

# Module 3 - Shopping & Order Rules

1. Every customer has only one active cart.

2. A cart can contain multiple cart items.

3. A cart item belongs to only one product.

4. Every cart item stores selected customizations.

5. Cart items may contain multiple attachments.

6. Customers can save designs for future orders.

7. Order numbers must be unique.

8. Orders are never physically deleted.

9. Order items store product snapshots.

10. Payment does not modify previous orders.

11. Every status change creates an Order Timeline record.

12. Delivered orders can be reordered.

13. Cancelled orders cannot be reordered directly; the customer must create a new order if the business allows it.

14. Customers can upload multiple attachments for one customized product.

15. Attachments are validated for allowed file types and maximum file size.


# Module 4 - Payment Rules

1. Every order has one payment record.

2. One payment can have multiple transactions.

3. Payment success confirms the order.

4. Failed payments remain available for retry.

5. Every transaction stores the payment gateway response.

---

# Module 5 - Review Rules

1. Only customers who purchased a product can review it.

2. One customer can review one order item once.

3. Reviews may include multiple attachments.

4. Admin can hide inappropriate reviews.

---

# Module 6 - Quote Rules

1. Customers may upload multiple attachments.

2. Admin may revise quotations before approval.

3. Accepted quotations can later become orders.

---

# Module 7 - Coupon Rules

1. Coupons expire automatically.

2. Coupons may have minimum purchase limits.

3. One coupon per order.

---

# Module 8 - Invoice Rules

1. Every successful order generates one invoice.

2. Invoice numbers are unique.

3. Invoices are never deleted.

---

# Module 9 - Business Rules

1. Business settings are managed only by administrators.

2. Changes are recorded in the activity log.

3. Only one active business profile exists.

---

# Module 10 - Notification Rules

1. Every notification has a delivery status.

2. Failed notifications can be retried.

3. Notifications are stored for audit purposes.

# Module 11 - Audit Log Rules

1. Every important admin action must generate an audit log.

2. Audit logs are never physically deleted.

3. Audit logs cannot be edited.

4. Every audit log records the acting user.

5. Old and new values are stored in JSON format when applicable.

6. Login and logout events are recorded.

7. Audit logs are visible only to administrators.

8. Audit logs can be filtered by user, module, action, and date.