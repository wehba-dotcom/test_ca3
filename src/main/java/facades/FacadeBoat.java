package facades;

import dtos.BoatDTO;
import entities.Boat;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeBoat {

    private static FacadeBoat instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeBoat() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeBoat getFacadeBoat(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeBoat();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public BoatDTO create(BoatDTO rm){
        Boat boat = new Boat(rm.getBrand(), rm.getMake(), rm.getName());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boat);
    }
    public BoatDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Boat boat = em.find(Boat.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new BoatDTO(boat);
    }
    
    //TODO Remove/Change this before use
    public long getBoatCount(){
        EntityManager em = getEntityManager();
        try{
            long BoatCount = (long)em.createQuery("SELECT COUNT(r) FROM Boat r").getSingleResult();
            return BoatCount;
        }finally{  
            em.close();
        }
    }
    
    public List<BoatDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query = em.createQuery("SELECT r FROM Boat r", Boat.class);
        List<Boat> rms = query.getResultList();
        return BoatDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeBoat fe = getFacadeBoat(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
