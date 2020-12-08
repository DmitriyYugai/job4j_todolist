package daos;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ItemDao {
    private final SessionFactory sf;

    private ItemDao(SessionFactory sf) {
        this.sf = sf;
    }

    private static final class Holder {
        private static final ItemDao INSTANCE = new ItemDao(SessionFactoryUtils.getInstance());
    }

    public static ItemDao instOf() {
        return Holder.INSTANCE;
    }

    public Item save(Item item) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public List<Item> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("from models.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static void main(String[] args) {
        ItemDao itemDao = ItemDao.instOf();
        itemDao.save(new Item("New Task 333", true));
        System.out.println(itemDao.findAll());
    }

}
