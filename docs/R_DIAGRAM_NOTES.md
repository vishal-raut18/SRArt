# SR Arts ER Diagram Notes

## Authentication
Role → User (1:N)
User → UserProfile (1:1)
User → Address (1:N)

## Product
Category → Product (1:N)
Product → ProductImage (1:N)
Product → ProductCustomization (1:N)
CustomizationGroup → CustomizationField (1:N)
CustomizationField → CustomizationOption (1:N)

## Shopping
User → Cart (1:1)
Cart → CartItem (1:N)
CartItem → CartItemCustomization (1:N)
CartItem → Attachment (1:N)

## Orders
User → Order (1:N)
Order → OrderItem (1:N)
Order → OrderTimeline (1:N)

## Payment
Order → Payment (1:1)
Payment → PaymentTransaction (1:N)
Order → Invoice (1:1)

## Reviews
User → Review (1:N)
Product → Review (1:N)

## Quotes
User → QuoteRequest (1:N)

## Audit
User → AuditLog (1:N)
User → ActivityLog (1:N)