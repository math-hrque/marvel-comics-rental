CREATE TABLE usuarios (
    user_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_nome VARCHAR(150) NOT NULL,
    user_email VARCHAR(150) NOT NULL,
    user_senha VARCHAR(256) NOT NULL,
    user_telefone VARCHAR(11) NOT NULL,
    user_foto VARCHAR(255),
    active boolean NOT NULL DEFAULT true
);
