package gui;

import javax.swing.table.AbstractTableModel;

import aplicacion.MaquinaExpendedora;

public class ModeloTablaMaquinas extends AbstractTableModel {
	private java.util.List<MaquinaExpendedora> maquinas;

	public ModeloTablaMaquinas(){
        this.maquinas=new java.util.ArrayList<MaquinaExpendedora>();
    }

    public int getColumnCount (){
        return 1;
    }

    public int getRowCount(){
        return maquinas.size();
    }

    @Override
    public String getColumnName(int col){
        String nombre="";

        switch (col){
            case 0: nombre= "ID"; break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col){
        Class clase=null;

        switch (col){
            case 0: clase= java.lang.String.class; break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col){
        return false;
    }

    public Object getValueAt(int row, int col){
        Object resultado=null;

        switch (col){
            case 0: resultado = maquinas.get(row).getID(); break;
        }
        return resultado;
    }
    
    public MaquinaExpendedora obtenerMaquina(int row) {
    	return this.maquinas.get(row);
    }

    public void setFilas(java.util.List<MaquinaExpendedora> maquinas){
        this.maquinas=maquinas;
        fireTableDataChanged();
    }

}
