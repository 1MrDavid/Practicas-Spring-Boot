CREATE TABLE Project (
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    employee_id BIGINT REFERENCES Employee(id) -- Relación con Employee
);

CREATE TABLE Task (
    id SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    status VARCHAR(20) NOT NULL, 
    dueDate DATE NOT NULL,
    project_id BIGINT REFERENCES Project(id)  -- Relación con Project
);

-- Employee: corregir columnas
CREATE TABLE Employee (
    id SERIAL PRIMARY KEY,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    role TEXT NOT NULL 
);