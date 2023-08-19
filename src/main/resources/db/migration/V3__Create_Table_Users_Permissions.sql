CREATE TABLE users_permissions (
    id_user UUID REFERENCES users(id),
    id_permission SERIAL REFERENCES permission(id),
    PRIMARY KEY (id_user, id_permission)
);