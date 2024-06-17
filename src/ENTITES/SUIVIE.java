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
public class SUIVIE extends QueryDB{
  private String MAT_D,MAT, ID_PROGRES, Pourcentage, MENTION, PROMOTION,FACULTE, PAYER_FRAIS,FINIR_ANNEE,ANNEE_ACAD;
  private ResultSet next;

    public SUIVIE() {
        Tables.Name="suivie";
    }
  

    public SUIVIE(String MAT_D, String MAT, String ID_PROGRES, String Pourcentage, String MENTION, String PROMOTION, String FACULTE, String PAYER_FRAIS, String FINIR_ANNEE, String ANNEE_ACAD) {
        this.MAT_D = MAT_D;
        this.MAT = MAT;
        this.ID_PROGRES = ID_PROGRES;
        this.Pourcentage = Pourcentage;
        this.MENTION = MENTION;
        this.PROMOTION = PROMOTION;
        this.FACULTE = FACULTE;
        this.PAYER_FRAIS = PAYER_FRAIS;
        this.FINIR_ANNEE = FINIR_ANNEE;
        this.ANNEE_ACAD = ANNEE_ACAD;
        Tables.Name="suivie";
    }
       public int Create()
      {
         return this.Insert("`MAT_D`, `MAT`, `ID_PROGRES`, `Pourcentage`, `MENTION`, `PROMOTION`, `FACULTE`, `PAYER_FRAIS`, `FINIR_ANNEE`, `ANNEE_ACAD`").Values("'"+this.MAT_D+"','"+this.MAT+"',"+this.ID_PROGRES+",'"+this.Pourcentage+"','"+this.MENTION+"','"+this.PROMOTION+"','"+this.FACULTE+"','"+this.PAYER_FRAIS+"','"+this.FINIR_ANNEE+"','"+this.ANNEE_ACAD+"'");
      }
       
       public void AfficherAll(JTable jt) throws SQLException
    {
        Tables.Name="`suivie` s inner join decanat d inner join etudiants et inner join progres p";
        Object rowData[]=new Object[10];
        try{
        this.next=this.Select("et.MAT,et.NOM,s.Pourcentage,s.MENTION,s.PROMOTION,s.ANNEE_ACAD,d.LIBELLE").get();
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
            Model.addRow(new Object[]{this.next.getString("MAT"),
            (this.next.getString("NOM")),
            (this.next.getString("pourcentage")),
            (this.next.getString("MENTION")),
            (this.next.getString("PROMOTION")),
            (this.next.getString("ANNEE_ACAD")),
            (this.next.getString("LIBELLE"))});
            
        }jt.setModel(Model);
         
    
        }catch(SQLException ex)
        {
            System.err.println(ex.getNextException());
            new Throwable(ex.getMessage());
        }
       
    }
  
    
}
