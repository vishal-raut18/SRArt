# SR Arts Database Design

Version: 1.0

Author: Vishal Raut

Status: In Progress

# Module 1 - Authentication

## Entity: Role

Purpose:
Stores the different roles available in the application.

Relationships:
- One Role → Many Users

---

## Entity: User

Purpose:
Stores authentication information for all users.

Relationships:
- Many Users → One Role
- One User → One UserProfile
- One User → Many Addresses

---

## Entity: UserProfile

Purpose:
Stores personal information of the user.

Relationships:
- One UserProfile → One User

---

## Entity: Address

Purpose:
Stores delivery addresses for customers.

Relationships:
- Many Addresses → One User


# Module 2 - Product Catalog

## Entity: Category

Purpose:
Groups similar products.

Relationships:
- One Category → Many Products

Key Fields:
- id
- name
- slug
- description
- image_url
- display_order
- active

---

## Entity: Product

Purpose:
Stores all products sold by SR Arts.

Relationships:
- Many Products → One Category
- One Product → Many Product Images
- One Product → Many Product Customizations

Key Fields:
- id
- category_id
- name
- short_description
- description
- base_price
- sku
- estimated_days
- status
- customizable
- featured
- active

---

## Entity: ProductImage

Purpose:
Stores all product images.

Relationships:
- Many Product Images → One Product

Key Fields:
- id
- product_id
- image_url
- cover_image
- display_order

---

## Entity: CustomizationGroup

Purpose:
Groups customization fields.

Relationships:
- One Group → Many Fields

Key Fields:
- id
- name
- description
- display_order

---

## Entity: CustomizationField

Purpose:
Defines customization fields shown to customers.

Relationships:
- Many Fields → One Group
- One Field → Many Options

Key Fields:
- id
- customization_group_id
- field_name
- label
- field_type
- required
- placeholder
- display_order

---

## Entity: CustomizationOption

Purpose:
Stores predefined options.

Relationships:
- Many Options → One Field

Key Fields:
- id
- customization_field_id
- option_name
- option_value
- additional_price
- display_order

---

## Entity: ProductCustomization

Purpose:
Maps products with customization fields.

Relationships:
- Many Product Customizations → One Product
- Many Product Customizations → One Customization Field

Key Fields:
- id
- product_id
- customization_field_id
- required
- display_order


# Module 3 - Shopping & Order Management

Entities

• Cart
• CartItem
• CartItemCustomization
• Attachment
• SavedDesign
• Order
• OrderItem
• OrderItemCustomization
• OrderTimeline
• Reorder