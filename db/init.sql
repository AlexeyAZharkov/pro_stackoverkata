CREATE TABLE user_entity (
    id SERIAL PRIMARY KEY,
    fullName character varying(255) UNIQUE,
    password character varying(255) NOT NULL,
    persist_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    role_id BIGINT,
    last_redaction_date timestamp with time zone DEFAULT CURRENT_TIMESTAMP,
    email character varying(255) NOT NULL UNIQUE,
    about character varying(255),
    city character varying(255),
    link_site character varying(255),
    link_github character varying(255),
    link_vk character varying(255),
    is_enabled boolean,
    is_deleted boolean,
    image_link character varying(255)
);

INSERT INTO user_entity (id, password, email) VALUES
      (1, 'pass', 'mail@mail.ru')
