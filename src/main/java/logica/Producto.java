
package logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;

public class Producto {
   private int id;
   private String nombre;
   private int unidades;
   private String proveedor ;
   private String marca;
   private double precio;
   private double costo;

    public Producto() {
    }

    public Producto(int id, String nombre, int unidades, String proveedor, String marca, double precio, double costo) {
        this.id = id;
        this.nombre = nombre;
        this.unidades = unidades;
        this.proveedor = proveedor;
        this.marca = marca;
        this.precio = precio;
        this.costo = costo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
   
   public boolean guardarContacto() {
        ConexionBD conexion = new ConexionBD();
        
        String sentencia = "INSERT INTO producto(id, nombre, unidades, proveedor, marca, precio, costo) "
                + " VALUES ( '" + this.id + "','" + this.nombre + "',"
                + "'" + this.unidades + "','" + this.proveedor + "','" + this.marca + "',"
                + "'" + this.precio + "','" + this.costo +  "');  ";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean borrarContacto(int id) {
        String Sentencia = "DELETE FROM `producto` WHERE `id`='" + id + "'";
        ConexionBD conexion = new ConexionBD();
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public boolean actualizarContacto() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "UPDATE `producto` SET nombre='" + this.nombre + "',unidades='" + this.unidades + "',proveedor='" + this.proveedor
                + "',marca='" + this.marca + "',precio='" + this.precio + "',costo='" + this.costo 
                +  "' WHERE id=" + this.id + ";";
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }
    }

    public List<Producto> listarContactos() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        List<Producto> listaContactos = new ArrayList<>();
        String sql = "select * from producto order by identificacion asc";
        ResultSet rs = conexion.consultarBD(sql);
        Producto c;
        while (rs.next()) {
            c = new Producto();
            c.setId(rs.getInt("id"));
            c.setNombre(rs.getString("nombre"));
            c.setUnidades(rs.getInt("unidades"));
            c.setProveedor(rs.getString("proveedor"));
            c.setMarca(rs.getString("marca"));
            c.setPrecio(rs.getDouble("precio"));
            c.setCosto(rs.getDouble("costo"));
            listaContactos.add(c);

        }
        conexion.cerrarConexion();
        return listaContactos;
    }

    public Producto getContacto() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        String sql = "select * from producto where identificacion='" + this.id + "'";
        ResultSet rs = conexion.consultarBD(sql);
        if (rs.next()) {
            this.id = rs.getInt("id");
            this.nombre = rs.getString("nombre");
            this.unidades = rs.getInt("unidades");
            this.proveedor = rs.getString("proveedor");
            this.marca = rs.getString("marca");
            this.precio = rs.getDouble("telefono");
            this.costo = rs.getDouble("direccion");
            conexion.cerrarConexion();
            return this;

        } else {
            conexion.cerrarConexion();
            return null;
        }

    }

    @Override
    public String toString() {
        return "Producto{" + "ID=" + id + ", NOMBRE=" + nombre + ", UNIDADES=" + unidades + ", PROVEEDOR=" + proveedor + ", MARCA=" + marca + ", PRECIO=" + precio + ", COSTO=" + costo  + '}';
    }
}



