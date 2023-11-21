package com.projeto.projetosistema.DAO;

import java.util.List;

public interface DAOInterface<T> extends AutoCloseable{
    public void inserir(T obj) throws ErroDAO;
    public void deletar(T obj) throws ErroDAO;
    public void deletar(int id) throws ErroDAO;
    public void editar(T obj) throws ErroDAO;
    public T buscar(int id) throws ErroDAO;
    public List<T> buscar() throws ErroDAO;
    @Override
    void close() throws ErroDAO;
}
