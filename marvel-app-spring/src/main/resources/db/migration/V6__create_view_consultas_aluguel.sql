CREATE VIEW v_aluguelhq_marvelhq AS
SELECT 
    h.aluguelhq_id,
    h.aluguelhq_usuario_id,
    h.aluguelhq_marvelhq_id,
    m.hq_titulo,
    m.hq_imagem,
    h.aluguelhq_data_devolucao
FROM 
    aluguelhq h
JOIN 
    marvelhq m ON m.hq_id = h.aluguelhq_marvelhq_id;
