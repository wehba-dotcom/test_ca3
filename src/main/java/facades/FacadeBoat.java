package facades;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

import exceptions.BoatNotFoundException;
/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class FacadeBoat {

    private static FacadeBoat instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private FacadeBoat() {
    }


    /**
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

    public BoatDTO create(BoatDTO rm) {
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
    public long getBoatCount() {
        EntityManager em = getEntityManager();
        try {
            long BoatCount = (long) em.createQuery("SELECT COUNT(r) FROM Boat r").getSingleResult();
            return BoatCount;
        } finally {
            em.close();
        }
    }

    public List<BoatDTO> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query = em.createQuery("SELECT r FROM Boat r", Boat.class);
        List<Boat> rms = query.getResultList();
        return BoatDTO.getDtos(rms);
    }
   /* public BoatDTO addBoat(String brand, String make, String name,  List<Owner> owners) throws MissingInputException {
        if ((brand.length() == 0) || (name.length() == 0)){
            throw new MissingInputException("First Name and/or Last Name is missing");
        }
        EntityManager em = getEntityManager();
        Boat boat = new Boat(brand, make, name);

        try {
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a FROM Owner a WHERE a.name = :name AND a.address = :address AND a.phone = :phone");
            query.setParameter("name", name);
            query.setParameter("address",address);
            query.setParameter("phone", phone);
            List<Owner> owners1 = query.getResultList();
            if (owners1.size() > 0){
                boat.AddOwner(owners1.get(0));
            } else {
                boat.AddOwner(new Owner(name,address,phone));
            }
            em.persist(boat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boat);
    }*/

    public List<OwnerDTO> getOwnersByBoatName(String name) throws BoatNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Owner> query =
                em.createQuery("select o from Owner o " +
                        "inner join o.boates b where b.name= :name ", Owner.class);
        query.setParameter("name", name);
        List<Owner> ownerList = query.getResultList();
        return OwnerDTO.getDtos(ownerList);
    }


    public List<BoatDTO> getBoatsByHarbourName(String name) throws BoatNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query =
                em.createQuery("select p from Boat p " +
                        "inner join p.harbour h where h.name= :name", Boat.class);
        query.setParameter("name", name);
        List<Boat> boatList = query.getResultList();
        return BoatDTO.getDtos(boatList);
    }




    public List<BoatDTO> getAllBoatsWithH() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query =
                em.createQuery("SELECT p FROM Boat p " + "inner join p.harbour h where h.h_id= :h_id", Boat.class);
        List<Boat> rms = query.getResultList();
        return BoatDTO.getDtos(rms);
    }
    public BoatDTO getBoatById(long id) throws BoatNotFoundException {
        EntityManager em = emf.createEntityManager();
        Boat b = em.find(Boat.class,id);
        return new BoatDTO(b);
    }

    public BoatDTO editBoat(String brand,String make,String name) throws BoatNotFoundException {
        Boat boat = new Boat(brand,make,name);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            boat = em.merge(boat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boat);
    }

    public BoatDTO createBoat(String brand,String make,String name)throws BoatNotFoundException{
        Boat boat = new Boat(brand,make,name);
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(boat);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boat);
    }

    public BoatDTO deleteBoat(int id) throws BoatNotFoundException {
        EntityManager em = getEntityManager();
        Boat boat = em.find(Boat.class, id);
    Harbour harbour = em.find(Harbour.class, boat.getHarbour().getH_id());
        if (boat == null) {
            throw new BoatNotFoundException(404, "Could not delete, provided id does not exist");
        }
        try {
            em.getTransaction().begin();
            em.remove(boat);
            em.remove(harbour);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new BoatDTO(boat);
    }



    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        FacadeBoat fe = getFacadeBoat(emf);
        fe.getAll().forEach(dto->System.out.println(dto));
    }

}
