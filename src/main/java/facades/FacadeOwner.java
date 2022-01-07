package facades;

import dtos.OwnerDTO;
import entities.Owner;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeOwner {

    private static FacadeOwner instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private FacadeOwner() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static FacadeOwner getFacadeOwner(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FacadeOwner();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public OwnerDTO create(OwnerDTO rm){
        Owner rme = new Owner(rm.getName(), rm.getAddress(), rm.getPhone());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(rme);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new OwnerDTO(rme);
    }
    public OwnerDTO getById(long id) { //throws RenameMeNotFoundException {
        EntityManager em = emf.createEntityManager();
        Owner rm = em.find(Owner.class, id);
//        if (rm == null)
//            throw new RenameMeNotFoundException("The RenameMe entity with ID: "+id+" Was not found");
        return new OwnerDTO(rm);
    }
    
    //TODO Remove/Change this before use
    public long getOwnerCount(){
        EntityManager em = getEntityManager();
        try{
            long OwnerCount = (long)em.createQuery("SELECT COUNT(r) FROM Owner r").getSingleResult();
            return OwnerCount;
        }finally{  
            em.close();
        }
    }
    
    public List<OwnerDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query = em.createQuery("SELECT r FROM Owner r", Owner.class);
        List<Owner> rms = query.getResultList();
        return OwnerDTO.getDtos(rms);
    }
    
    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeOwner fe = getFacadeOwner(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
