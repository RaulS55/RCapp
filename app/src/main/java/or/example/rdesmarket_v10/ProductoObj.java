package or.example.rdesmarket_v10;

import java.io.Serializable;

public class ProductoObj implements Serializable {
    private String precioCom;
    private String precioCar;
    private String precioCha;
    private String precioDia;
    private String precioVea;
    private String nombre;
    private String marca;
    private String rubro;
    private String subrubro;
    private int local;
    private String ean;



    public ProductoObj() {

    }

    public ProductoObj(String precioCom, String precioCar, String precioCha,
                       String precioDia, String precioVea, String nombre, String marca,
                       String rubro, String subrubro, int local, String ean) {
        this.precioCom = precioCom;
        this.precioCar = precioCar;
        this.precioCha = precioCha;
        this.precioDia = precioDia;
        this.precioVea = precioVea;
        this.nombre = nombre;
        this.marca = marca;
        this.rubro = rubro;
        this.subrubro = subrubro;
        this.local = local;
        this.ean = ean;
    }

    public String getPrecioCom() {
        return precioCom;
    }

    public void setPrecioCom(String precioCom) {
        this.precioCom = precioCom;
    }

    public String getPrecioCar() {
        return precioCar;
    }

    public void setPrecioCar(String precioCar) {
        this.precioCar = precioCar;
    }

    public String getPrecioCha() {
        return precioCha;
    }

    public void setPrecioCha(String precioCha) {
        this.precioCha = precioCha;
    }

    public String getPrecioDia() {
        return precioDia;
    }

    public void setPrecioDia(String precioDia) {
        this.precioDia = precioDia;
    }

    public String getPrecioVea() {
        return precioVea;
    }

    public void setPrecioVea(String precioVea) {
        this.precioVea = precioVea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public String getSubrubro() {
        return subrubro;
    }

    public void setSubrubro(String subrubro) {
        this.subrubro = subrubro;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }
}