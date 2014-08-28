package Entidades;

import java.util.ArrayList;

public class Opciones {
    private ArrayList<String> lista = new ArrayList<String>();
    private int id_lamina;
    private int codigo;
    
    public ArrayList<String> getLista() {
        return lista;
    }

    public void setLista(ArrayList<String> lista) {
        this.lista = lista;
    }
    public int getId_lamina() {
        return id_lamina;
    }
    
    public void setId_lamina(int id_lamina) {
        this.id_lamina = id_lamina;
    }
    
    public void addLista(String añadir){
        this.lista.add(añadir);
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
}
