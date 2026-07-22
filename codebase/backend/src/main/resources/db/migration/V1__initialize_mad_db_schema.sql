DO $$
BEGIN
    IF current_schema() <> 'mad_db' THEN
        RAISE EXCEPTION 'Schema ativo invalido: %, esperado: mad_db', current_schema();
    END IF;
END
$$;
