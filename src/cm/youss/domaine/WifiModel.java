package cm.youss.domaine;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import cm.youss.presentation.Wifi;

public class WifiModel extends AbstractTableModel{
	
	private String[] nomColonnes=new String[] {"ProfilS des utulisateurs"};
	List<String[]> tableValues=new ArrayList<String[]>(); 

	public WifiModel() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return tableValues.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return nomColonnes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return tableValues.get(rowIndex)[columnIndex];
	}
	@Override
	public String getColumnName(int column) {
		
		return nomColonnes[column];
	}
	
	public void setData(List<String> list) {
		Wifi wifi = new Wifi();
		tableValues=new ArrayList<>();
		for(int i=0 ; i<list.size() ; i++) {
			tableValues.add(new String[] {
			list.get(i),
			});
		}
		fireTableDataChanged();
	}

}
