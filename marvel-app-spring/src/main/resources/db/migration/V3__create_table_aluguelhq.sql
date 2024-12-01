CREATE TABLE aluguelhq (
    aluguelhq_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    aluguelhq_usuario_id INTEGER NOT NULL,
    aluguelhq_marvelhq_id INTEGER NOT NULL,
    aluguelhq_data_devolucao DATE,
    CONSTRAINT fk_usuario_marvelhq FOREIGN KEY (aluguelhq_usuario_id) REFERENCES usuarios(user_id),
    CONSTRAINT fk_marvelhq_usuario FOREIGN KEY (aluguelhq_marvelhq_id) REFERENCES marvelhq(hq_id)
);
