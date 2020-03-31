import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
public class Vols {
	private static final String configurationFile = "src/AirchanceF.propreties";
	static Connection conn;
	public static void GestionVol(Connection Conn) throws SQLException{
		try {
			// 1. Get a connection to database
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());     
			DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
			conn = DriverManager.getConnection(dap.getDatabaseUrl(), dap.getUsername(),dap.getPassword());
		}catch (Exception exc) {
				exc.printStackTrace();
			}
		System.out.println("---------------------Gestion des Mod�les-------------------------");
		System.out.println("----------------Veuillez choisir une op�ration--------------");
		System.out.println("1-Ajouter un Vol");
		System.out.println("2-Afficher tous Les Vols");
		System.out.println("3-Supprimer un Vols");
		System.out.println("4-Affectation du Vol à une equipe");

		int n = Integer.parseInt(LectureClavier.lireChaine());

		switch(n) {
		case 1:
			AjouterVol(conn);
			break;
		case 2:
			AfficherVols(conn);
			break;
		case 3:
			SupprimerVol(conn);
			break;
		case 4:
			EffectueVol(conn);
			break;
		}
	}
public static void AjouterVol(Connection Conn)throws SQLException{
		
		/*  Selectionner un nuouveau numero de Vols */
		Statement req0 =conn.createStatement();
		ResultSet resultat =req0.executeQuery("SELECT NumVol FROM Vols");
		ArrayList<Integer> listeNum=new ArrayList<Integer>();
		if(resultat.next()) {
			listeNum.add(Integer.parseInt(resultat.getString("numAvion")));
			while(resultat.next()) {
				listeNum.add(Integer.parseInt(resultat.getString("numAvion")));
			}
		}
		Random n = new Random();
		int numV = n.nextInt(1000);
		while(listeNum.contains(numV)) {
			numV = n.nextInt(1000);
		}
		/*   Selectionner les Avion compatible */
		System.out.println("entre le nombre minimum des places économique");
	  	int min_nb_place_eco= LectureClavier.lireEntier("");
	  	System.out.println("entre le nombre minimum des places premiere");
	  	int min_nb_place_premiere= LectureClavier.lireEntier("");
	  	System.out.println("entre le nombre minimum des places affaire");
	  	int min_nb_place_affaire= LectureClavier.lireEntier("");
	  	System.out.println("Date du Vol(YYYY-MM-DD): ");
		String dateString =LectureClavier.lireChaine();

		Date date = Date.valueOf(dateString);
	  	Statement req1 =conn.createStatement();
		resultat =req1.executeQuery("SELECT distinct numAvion FROM Avions Where"
				+ "(nb_place_eco >="+min_nb_place_eco+") AND (nb_place_premiere>="+min_nb_place_premiere+") AND ( nb_place_affaire >="+min_nb_place_affaire+")"
				
				+ "minus select distinct numAvion from Vols natural join Reservations where date_res= Date '"+date+"'") ;

		
		ArrayList<Integer> listeAvion=new ArrayList<Integer>();
		int numA =0 ;
		if(resultat.next()) {
			listeAvion.add(Integer.parseInt(resultat.getString("numAvion")));
			while(resultat.next()) {
				listeAvion.add(Integer.parseInt(resultat.getString("numAvion")));
			}

			System.out.println("Veuillez s�lectionnez un numero d'avion: ");
			for(int c:listeAvion) {
				System.out.println("["+c+"]");
			}

			numA=Integer.parseInt(LectureClavier.lireChaine());

			while(!listeAvion.contains(numA)){
				System.out.println("Veuillez re-s�lectionner un Nom: ");
				numA=Integer.parseInt(LectureClavier.lireChaine());
			}
		}else{
			System.out.println("Aucun avion qui correspond!");
		}
		System.out.println("Aeroport de Depart:");
		String aeroD = LectureClavier.lireChaine();	
		System.out.println("Aeroport d'Arrivee: ");
		String aeroA = LectureClavier.lireChaine();
		System.out.println("Horaire (HH:MI:SS): ");
		String horaire=LectureClavier.lireChaine();
		System.out.println("Duree: ");
		String duree=LectureClavier.lireChaine();
		System.out.println("Distance: ");
		int dist=Integer.parseInt(LectureClavier.lireChaine());
		
		String query = "INSERT INTO Vols VALUES(?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt =conn.prepareStatement(query);
		stmt.setInt(1,numV);
		stmt.setInt(2,numA);
		stmt.setString(3,aeroD);
		stmt.setString(4,aeroA);
		stmt.setString(5,horaire);
		stmt.setDate(6,date);
		stmt.setString(7,duree);
		stmt.setInt(8,dist);
		stmt.setInt(9,min_nb_place_eco);
		stmt.setInt(10,min_nb_place_premiere);
		stmt.setInt(11,min_nb_place_affaire);
		System.out.println("Ajouter avec succe") ;
	}
	public static void AfficherVols(Connection Conn)throws SQLException{
		System.out.println("Liste des Vols: ");
		Statement requete =conn.createStatement();
		ResultSet res =requete.executeQuery("SELECT * FROM Vols");
		ResultSetMetaData resultMeta = res.getMetaData();
		System.out.println("|Numero Vol|Num Avion|Aero.Depart|Aero.Arrivee|Horaire|Date depart|Duree|Distance|Min.Place eco|Min.Place Premiere|Min.Place Affaire");
		System.out.println("\n");
		while(res.next())
		{      
			for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
				System.out.print("|"+res.getObject(i).toString());
			System.out.println("\n");
		}
	}
	public static void SupprimerVol(Connection Conn) throws SQLException{
		Statement requete =conn.createStatement();
		ResultSet res2 =requete.executeQuery("SELECT numVol FROM Vols");
		int numv=0;
		ArrayList<Integer> l=new ArrayList<Integer>();
		if(res2.next()) {
			l.add(Integer.parseInt(res2.getString("numVol")));
			while(res2.next()) {
				l.add(Integer.parseInt(res2.getString("numVol")));
			}

			System.out.println("Veuillez s�lectionnez un Num�ro de Vol: ");
			for(int c:l) {
				System.out.println("["+c+"]");
			}

			numv=Integer.parseInt(LectureClavier.lireChaine());

			while(!l.contains(numv)){
				System.out.println("Veuillez re-s�lectionner un Num�ro de Vol: ");
				numv=Integer.parseInt(LectureClavier.lireChaine());
			}
			
		}else{
			System.out.println("Num�ro de Vol Incorrecte !");
		}
		Statement requete1= conn.createStatement();
		int delete=requete1.executeUpdate("DELETE FROM Vols WHERE numVol='"+numv+"'");
		if (delete==0) {
			System.out.println("Erreur!");
		}else {
			System.out.println("Vol Supprimer avec Succ�s!");
		}
	}
	public static void EffectueVol(Connection Conn)throws SQLException{
		
		System.out.println("Inserer un numéro de Vol : ");
		int numVol = LectureClavier.lireEntier("") ;  
		System.out.println(" !!!! ") ;
        ResultSet resultatPilote, resultatHotesse;      
        Statement requete = conn.createStatement();
        
		resultatPilote = requete.executeQuery("select NUMPILOTE from Qualifie natural join Avions natural join Vols  where numVol ='"+numVol+"'"
				+ " minus select NUMPILOTE from Effectue natural join  Vol where date_dep in (select date_dep form Vols where numVol="+numVol+")");
		System.out.println(" !!!") ;
		int numP = 0, numH = 0 ;
		if(resultatPilote.next()) {
            ArrayList<Integer> listePilote=new ArrayList<>();
            listePilote.add(Integer.parseInt(resultatPilote.getString("NUMPILOTE")));
            while(resultatPilote.next()) {
            	listePilote.add(Integer.parseInt(resultatPilote.getString("NUMPILOTE")));
            }
            System.out.println("voici les Pilotes  qualifer au Vol  :");
            System.out.print("NumP : ") ;
            for(Integer n : listePilote) {
            	System.out.print(n+" | ") ;
            }
            System.out.println();
            System.out.println("Veulliez entrer le numéro de pilote  :");
            numP = Integer.parseInt(LectureClavier.lireChaine());
            while(!listePilote.contains(numP)) {
                System.out.println("Veulliez re-entrer le numéro de pilote:");
                numP = LectureClavier.lireEntier("") ; 
            }
            
         }else {
                System.out.println("Aucun Pilote disponible ! ");
                }
		Statement requete1 = conn.createStatement();
		resultatHotesse= requete1.executeQuery("select numHotesse from Hotesses "
				+ "minus"
				+ " select NUMHOTESSE from Effectue natural join  Vol where date_dep in (select date_dep form Vols where numVol="+numVol+")") ;
		
		ArrayList<Integer> hotesse=new ArrayList<>();
		if(resultatHotesse.next()) {
            ArrayList<Integer> listeHotesse=new ArrayList<>();
            listeHotesse.add(Integer.parseInt(resultatHotesse.getString("NUMPILOTE")));
            while(resultatHotesse.next()) {
            	listeHotesse.add(Integer.parseInt(resultatHotesse.getString("NUMPILOTE")));
            }
            System.out.println("voici les hotesse  disponible au Vol  :");
            System.out.print("NumP : ") ;
            for(Integer n : listeHotesse) {
            	System.out.print(n+" | ") ;
            }
            System.out.println("donner le nombre des hotesses souhaiter  ");
            int  nb  = LectureClavier.lireEntier("");
            
            for(int i=0; i<nb; i++) {
            	System.out.println("Veulliez entrer le numéro de hotesse  :");
                numH = Integer.parseInt(LectureClavier.lireChaine());
                hotesse.add(numH) ;
                while(!listeHotesse.contains(numP)) {
                    System.out.println("Veulliez re-entrer le numéro de pilote:");
                    numH = LectureClavier.lireEntier("") ; 
                    hotesse.add(numH) ;
                }
            }
            
            
         }else {
                System.out.println("Aucun hotesse disponible ! ");
                }
		Statement requete3 = conn.createStatement() ;
		for (Integer  e : hotesse) {
	          int na =  requete3.executeUpdate("INSERT INTO Effectue VALUES("+numVol+","+e+","+numP+")");
	          
	        }
		
	}
}
