-- Insert departments
INSERT INTO departments (name, description) VALUES
('IT', 'Information Technology Department'),
('HR', 'Human Resources Department'),
('Finance', 'Finance Department');

-- Insert admin user (password: admin123)
INSERT INTO users (username, password, role, department_id) VALUES
('admin', '$2a$10$rPiEAgQNIT1TCoKi3Eqq8eVaRYIRlR29Vk.dqWcKsX2pYoqX3Zv2', 'ADMIN', 1);
