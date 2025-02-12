package jpa;


import concert.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class JpaTest {


	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager manager = EntityManagerHelper.getEntityManager();

		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {

			// test.createEmployees();
			// test.listEmployees();
			test.createAcheteur();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

			
   	 manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		System.out.println(".. done");
	}

	private void createConcert() {
		Organisateur organisateur = new Organisateur("yéo", "johndoe@email.com");
		Concert concert = new Concert("Paiya", "Bingerville", new Date(2025, Calendar.DECEMBER, 25), organisateur);

		organisateur.ajouterConcert(concert);
		manager.persist(organisateur);
	}

	private void createAcheteur() {
		try {
			Organisateur organisateur = new Organisateur("Doe", "jdoe@email.com");
			Concert concert = new Concert("Bonjour 2026", "Bingerville", new Date(2025, Calendar.DECEMBER, 25), organisateur);

			organisateur.ajouterConcert(concert);
			manager.persist(organisateur);

			Acheteur acheteur = new Acheteur("Johann Doe", "johanndoe@email.com");

			Categorie categorie = new Categorie("VIP", 10.0);
			manager.persist(categorie);
			acheteur.acheterBillet(concert, categorie);

			manager.persist(acheteur);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}



