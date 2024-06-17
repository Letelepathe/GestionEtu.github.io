/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENTITES;
import DB.QueryDB;
import DB.Tables;
import Config.GenerateMatricule;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lecryptologue
 */
public class Progres extends QueryDB{
    private static String PROGRES_ID;
    private String ID_PROGRES;
    private String LIBELLE;
    private String DESCRIPTION;
    private ResultSet next;

    public Progres() {
        Tables.Name="progres";
    }

    public void setID_PROGRES(String ID_PROGRES) {
        this.ID_PROGRES = ID_PROGRES;
    }
       public void AfficherAll(JTable jt) throws SQLException
    {
        Object rowData[]=new Object[10];
        try{
        this.next=this.Select("").get();
        DefaultTableModel Model=(DefaultTableModel) jt.getModel();
        if(Model.getRowCount()>0)
        {
            for(int i=Model.getRowCount()-1;i>=0;i--)
            {
                Model.removeRow(i);
            }
        }
      
        while(this.next.next())
        { 
           /* rowData[0]=(this.next.getString("MAT"));
            rowData[1]=(this.next.getString("NOM"));
            rowData[2]=(this.next.getString("POSTNOM"));
            rowData[3]=(this.next.getString("PRENOM"));
            rowData[4]=(this.next.getString("SEXE"));
            rowData[5]=(this.next.getString("ADRESSE"));
            rowData[6]=(this.next.getString("TEL"));
            rowData[7]=(this.next.getString("PHOTO"));*/
            Model.addRow(new Object[]{this.next.getString("ID_PROGRES"),
                (this.next.getString("LIBELLE")),
                (this.next.getString("DESCRIPTION"))});
            
        }jt.setModel(Model);
         
    
        }catch(SQLException ex)
        {
            System.err.println(ex.getNextException());
            new Throwable(ex.getMessage());
        }
       
    }
    
    
}
