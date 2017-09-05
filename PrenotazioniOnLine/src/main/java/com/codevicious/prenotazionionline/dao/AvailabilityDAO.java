package com.codevicious.prenotazionionline.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

import com.codevicious.prenotazionionline.representations.Availability;
import com.codevicious.prenotazionionline.representations.Place;

import io.dropwizard.hibernate.AbstractDAO;
import jersey.repackaged.com.google.common.base.Optional;

public class AvailabilityDAO extends AbstractDAO<Availability> {
	/*
	 * @Mapper(AvailabilityMapper.class)
	 * 
	 * @SqlQuery("select * from availability where ID = :id") Availability
	 * getAvailabilityById(@Bind("id") int id);
	 * 
	 * @Mapper(AvailabilityMapper.class)
	 * 
	 * @SqlQuery("select * from availability where 1") List<Availability>
	 * getAllAvailability();
	 * 
	 * @Mapper(PlacesMapper.class)
	 * 
	 * @SqlQuery("SELECT DISTINCT places.ID as ID, name as place FROM places INNER JOIN availability on places.ID = availability.FKplaces ORDER BY place"
	 * ) List<Place> getPlaces();
	 * 
	 * @Mapper(AvailabilityMapper.class)
	 * 
	 * @SqlQuery("SELECT places.name as place, availability.ID as ID, concat(availability.Data,'T',availability.Ora) as start "
	 * + "FROM places INNER JOIN availability ON places.ID = availability.FK_places"
	 * +
	 * "	WHERE (MONTH(availability.Data) = :month AND availability.FKplaces = :place AND YEAR(availability.Data) = :year)"
	 * + "	ORDER BY availability.Data") List<Availability>
	 * getAvailabilityByMonthYearPlace(@Bind("year") String year, @Bind("month")
	 * String month, @Bind("place") String place);
	 * 
	 * @GetGeneratedKeys
	 * 
	 * @SqlUpdate("INSERT INTO availability (ID, Data, Ora, FK_places) VALUES (NULL,:Data,:Ora,:FKplaces)"
	 * ) int createAvailability(@Bind("Data") Date Data, @Bind("Ora") Time
	 * Ora, @Bind("FK_places") int FK_places);
	 * 
	 * @SqlUpdate("UPDATE availability SET Data=:Data, Ora=:Ora,FKplaces=:FKplaces WHERE ID = :id"
	 * ) void updateAvailability(@Bind("id") int id, @Bind("Data") Date
	 * Data, @Bind("Ora") Time Ora,
	 * 
	 * @Bind("FKplaces") int FKplaces);
	 * 
	 * @SqlUpdate("DELETE FROM availability WHERE ID = :id") void
	 * deleteContact(@Bind("id") int id);
	 */

	public AvailabilityDAO(SessionFactory sessionF) {
		super(sessionF);
	}

	public List<Availability> findAll() {
		return list(namedQuery("Availability.findAll"));
	}
	
	public Optional<Availability> findById(long id) {
        return Optional.fromNullable(get(id));
    }

	public List<Availability> getAvailabilityByMonthYearPlace(String year, String month, String place) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Place> getPlaces() {
			
		return (List<Place>) namedQuery("Availability.findPlaces").list();
	}

	public Availability getAvailabilityById(long id) {
		
		return get(id);
	}

	public int createAvailability(Date data, Place place) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void deleteContact(int id) {
		// TODO Auto-generated method stub
		
	}

	public void updateAvailability(int id, Date data, Place place) {
		// TODO Auto-generated method stub
		
	}

}
