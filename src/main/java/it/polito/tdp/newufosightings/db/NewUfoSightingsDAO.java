package it.polito.tdp.newufosightings.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.newufosightings.model.Arco;
import it.polito.tdp.newufosightings.model.Sighting;
import it.polito.tdp.newufosightings.model.State;

public class NewUfoSightingsDAO {

	public List<Sighting> loadAllSightings() {
		String sql = "SELECT * FROM sighting";
		List<Sighting> list = new ArrayList<>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);	
			ResultSet res = st.executeQuery();

			while (res.next()) {
				list.add(new Sighting(res.getInt("id"), res.getTimestamp("datetime").toLocalDateTime(),
						res.getString("city"), res.getString("state"), res.getString("country"), res.getString("shape"),
						res.getInt("duration"), res.getString("duration_hm"), res.getString("comments"),
						res.getDate("date_posted").toLocalDate(), res.getDouble("latitude"),
						res.getDouble("longitude")));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

		return list;
	}

	public List<State> loadAllStates() {
		String sql = "SELECT * FROM state";
		List<State> result = new ArrayList<State>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				State state = new State(rs.getString("id"), rs.getString("Name"), rs.getString("Capital"),
						rs.getDouble("Lat"), rs.getDouble("Lng"), rs.getInt("Area"), rs.getInt("Population"),
						rs.getString("Neighbors"));
				result.add(state);
			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<String> getForme(Integer anno){
		String sql = "SELECT DISTINCT s.shape as forma " + 
				"FROM sighting AS s " + 
				"WHERE Year(s.datetime) = ? " + 
				"ORDER BY s.shape ";
		List<String> forme = new ArrayList<String>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				forme.add(rs.getString("forma"));
			}

			conn.close();
			return forme;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<String> getStati(){
		String sql = "SELECT DISTINCT id FROM state ";
		List<String> stati = new ArrayList<String>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				stati.add(rs.getString("id"));
			}

			conn.close();
			return stati;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	public List<Arco> getArchi(Integer anno, String forma){
		String sql = "SELECT DISTINCT s1.id AS id1, s2.id AS id2, COUNT(DISTINCT(a.id)) as peso "+
					 "FROM state AS s1, state AS s2, neighbor AS n, sighting AS a " +
					 "WHERE s1.id = n.state1 "+
					 "AND s2.id = n.state2 "+
					 "AND s1.id > s2.id "+
					 "AND (a.state = s1.id OR a.state = s2.id) "+
					 "AND YEAR(a.datetime) = ? "+
					 "AND a.shape = ? "+
					 "GROUP BY s1.id, s2.id ";
		List<Arco> archi = new ArrayList<Arco>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			st.setString(2, forma);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				archi.add(new Arco(rs.getString("id1"), rs.getString("id2"), rs.getInt("peso")));
			}

			conn.close();
			return archi;

		} catch (SQLException e) {
			e.printStackTrace();	
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
} 

