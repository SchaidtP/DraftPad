CREATE TABLE notes (
    id UUID PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    publication_date TIMESTAMP,
    user_id UUID REFERENCES users(id) NOT NULL
);