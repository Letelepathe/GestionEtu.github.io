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
public class T_ETUDIANTS extends QueryDB{
    private static String MAT;
    private String mat;
    private String NOM;
    private String POSTNOM;
    private String PRENOM;
    private char SEXE;
    private String ADRESSE;
    private String TEL;
    private ResultSet next;
    private ArrayList<String> BUAKA=new ArrayList<>();
    
    public T_ETUDIANTS()
    {
        Tables.Name="etudiants";
    }

    public T_ETUDIANTS(String NOM, String POSTNOM, String PRENOM, char SEXE, String ADRESSE, String TEL) {
        this.mat=GenerateMatricule.GenerateMat();
        this.NOM = NOM;
        this.POSTNOM = POSTNOM;
        this.PRENOM = PRENOM;
        this.SEXE = SEXE;
        this.ADRESSE = ADRESSE;
        this.TEL = TEL;
        Tables.Name="etudiants";
    }
    
    public ArrayList<String> ID_ETU() throws SQLException
    {
        try{
        this.next=this.Select("mat").Where("mat=1").get();
        while(this.next.next())
        {
            BUAKA.add(this.next.getString("mat"));
        }
        return BUAKA;
        }catch(SQLException ex)
        {
            System.err.println(ex.getNextException());
            new Throwable(ex.getMessage());
        }
       return null;
    }
      public ArrayList<String> AfficherAll() throws SQLException
    {
        try{
        this.next=this.Select("").get();
        while(this.next.next())
        {
            BUAKA.add(this.next.getString("MAT"));
            BUAKA.add(this.next.getString("NOM"));
            BUAKA.add(this.next.getString("POSTNOM"));
            BUAKA.add(this.next.getString("PRENOM"));
            BUAKA.add(this.next.getString("SEXE"));
            BUAKA.add(this.next.getString("ADRESSE"));
            BUAKA.add(this.next.getString("TEL"));
            BUAKA.add(this.next.getString("PHOTO"));
        }
        return BUAKA;
        }catch(SQLException ex)
        {
            System.err.println(ex.getNextException());
            new Throwable(ex.getMessage());
        }
       return null;
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
            Model.addRow(new Object[]{this.next.getString("MAT"),
                this.next.getString("NOM"),
                (this.next.getString("POSTNOM")),
                (this.next.getString("PRENOM")),
                (this.next.getString("SEXE")),
                (this.next.getString("ADRESSE")),
                (this.next.getString("TEL"))});
            
        }jt.setModel(Model);
         
    
        }catch(SQLException ex)
        {
            System.err.println(ex.getNextException());
            new Throwable(ex.getMessage());
        }
       
    }
       public ArrayList<String> Recherche(String mat) throws SQLException
    {
        try{
        this.next=this.Select("").Where("MAT="+mat+"'").OrWhere("MAT_B='"+mat+"'").get();
        while(this.next.next())
        {
            BUAKA.add(this.next.getString("MAT"));
        }
        return BUAKA;
        }catch(SQLException ex)
        {
            System.err.println(ex.getNextException());
            Throwable throwable = new Throwable(ex.getMessage());
            System.out.println(throwable);
        }
       return null;
    }

    public static void setMat(String mat) {
        
        MAT = mat;
        JOptionPane.showMessageDialog(null, "REUSSI dans la classe "+MAT);
    }
      
      public int Create()
      {
          JOptionPane.showMessageDialog(null, ""+this.mat+"','"+this.NOM+"','"+this.POSTNOM+"','"+this.PRENOM+"','"+this.SEXE+"','"+this.ADRESSE+"','"+this.TEL+"'");
         return this.Insert().Values("'"+this.mat+"','"+this.NOM+"','"+this.POSTNOM+"','"+this.PRENOM+"','"+this.SEXE+"','"+this.TEL+"','"+this.ADRESSE+"'");
      }
      
      public int Delete()
      {
          return this.Delete("MAT='"+MAT+"'");
      }

    /**
     *
     * @param SET
     * @return
     */
    public int Updated(String SET)
      {
          this.Where(" MAT='"+MAT+"'");
          return this.Update(SET);
      }
    
     public int Updated()
      {
          this.Where(" MAT='"+MAT+"'");
          return this.Update("MAT='"+this.mat+"',NOM='"+this.NOM+"',POSTNOM='"+this.POSTNOM+"',PRENOM='"+this.PRENOM+"',SEXE='"+this.SEXE+"',ADRESSE='"+this.ADRESSE+"',TEL='"+this.TEL+"'");
      }
      
    
    
}
