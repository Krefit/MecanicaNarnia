/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

/**
 *
 * @author tanak
 * @param <T>
 */
public interface IManipulaBanco<T> {

    public void incluir(T obj) throws Exception;

    public T buscar(T obj) throws Exception;

    public void remover(T obj) throws Exception;

    public void editar(T objParaRemover, T objParaAdicionar) throws Exception;

}