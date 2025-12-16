-- Sample Categories
INSERT INTO Category (category_code, category_name, description) VALUES ('FRU', 'Fruits', 'Fresh and juicy fruits');
INSERT INTO Category (category_code, category_name, description) VALUES ('VEG', 'Vegetables', 'Fresh and organic vegetables');
INSERT INTO Category (category_code, category_name, description) VALUES ('DAI', 'Dairy', 'Milk, cheese, and other dairy products');
INSERT INTO Category (category_code, category_name, description) VALUES ('BAK', 'Bakery', 'Freshly baked bread and pastries');
INSERT INTO Category (category_code, category_name, description) VALUES ('MEA', 'Meat', 'Fresh meat and poultry');

-- Sample Suppliers
INSERT INTO Supplier (supplier_code, supplier_name, contact_name, phone, email, address) VALUES ('SUP-001', 'Fresh Farms', 'John Doe', '123-456-7890', 'contact@freshfarms.com', '123 Farm Road');
INSERT INTO Supplier (supplier_code, supplier_name, contact_name, phone, email, address) VALUES ('SUP-002', 'Green Grocers', 'Jane Smith', '098-765-4321', 'sales@greengrocers.com', '456 Grocer Ave');
INSERT INTO Supplier (supplier_code, supplier_name, contact_name, phone, email, address) VALUES ('SUP-003', 'Happy Cow Dairy', 'Peter Jones', '555-555-5555', 'orders@happydairy.com', '789 Dairy Lane');

-- Sample Stalls (Associating with existing categories)
INSERT INTO Stall (stall_code, stall_name, location_note, category_id) VALUES ('STL-001', 'Fruit Stand', 'Section A, near entrance', 1);
INSERT INTO Stall (stall_code, stall_name, location_note, category_id) VALUES ('STL-002', 'Vegetable Corner', 'Section B', 2);
INSERT INTO Stall (stall_code, stall_name, location_note, category_id) VALUES ('STL-003', 'The Fridge', 'Section C, cold aisle', 3);
