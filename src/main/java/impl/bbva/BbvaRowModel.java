package impl.bbva;

import model.AbstractRowModel;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BbvaRowModel extends AbstractRowModel {
    private Date fecha;
    private String comprobante;
    private String descripcion;
    private BigDecimal valor;

    public BbvaRowModel() {
    }

    public BbvaRowModel( Date fecha, String comprobante, String descripcion, BigDecimal valor ) {
        this.fecha = fecha;
        this.comprobante = comprobante;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha( Date fecha ) {
        this.fecha = fecha;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante( String comprobante ) {
        this.comprobante = comprobante;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion( String descripcion ) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor( BigDecimal valor ) {
        this.valor = valor;
    }

    @Override
    public String toString(){
        DateFormat formatter = new SimpleDateFormat( "dd/MM/yyyy" );

        return String.format( "Fecha: %s, Comprobante: %s, Descripcion: %s, Valor: %s",
                formatter.format( fecha ), comprobante, descripcion, valor );
    }
}
