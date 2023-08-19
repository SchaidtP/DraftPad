CREATE TABLE note (
    id UUID PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    publication_date TIMESTAMP,
    user_id UUID REFERENCES users(id)
);