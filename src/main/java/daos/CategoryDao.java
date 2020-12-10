package daos;

import models.Category;
import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CategoryDao {
    private final SessionFactory sf;

    private CategoryDao(SessionFactory sf) {
        this.sf = sf;
    }

    private static final class Holder {
        private static final CategoryDao INSTANCE = new CategoryDao(SessionFactoryUtils.getInstance());
    }

    public static CategoryDao instOf() {
        return CategoryDao.Holder.INSTANCE;
    }


    public List<Category> findAll() {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Category> result = session.createQuery("from models.Category").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(CategoryDao.instOf().findAll());
    }
}
