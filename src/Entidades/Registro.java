package Entidades;

import java.sql.Date;

public class Registro {
    private int codigo;
    private int ci;
    private String nombre;
    private String apellido;
    private String descripcion;
    private Usuario usuario;
    private int Codigo_grupo;
    private String[] Respuestas;
    private Date fecha;
    private int tiempo_total;
    private String perfil;
    /**
     * @return the ci
     */
    public int getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(int ci) {
        this.ci = ci;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    /**
     * @return the Codigo_grupal
     */
    public int getCodigo_grupo() {
        return Codigo_grupo;
    }

    /**
     * @param Codigo_grupal the Codigo_grupal to set
     */
    public void setCodigo_grupo(int Codigo_grupo) {
        this.Codigo_grupo = Codigo_grupo;
    }

    /**
     * @return the Respuestas
     */
    public String[] getRespuestas() {
        return Respuestas;
    }

    /**
     * @param Respuestas the Respuestas to set
     */
    public void setRespuestas(String[] Respuestas) {
        this.Respuestas = Respuestas;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

 
    /**
     * @return the tiempo_total
     */
    public int getTiempo_total() {
        return tiempo_total;
    }

    /**
     * @param tiempo_total the tiempo_total to set
     */
    public void setTiempo_total(int tiempo_total) {
        this.tiempo_total = tiempo_total;
    }

    /**
     * @return the perfil
     */
    public String getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
}
