package servlets;

import com.google.gson.Gson;
import daos.ItemDao;
import models.Item;
import models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Item> items = ItemDao.instOf().findAll();
        for(Item item : items) {
            item.getUser().setPassword(null);
        }
        Gson gson = new Gson();
        resp.getWriter().print(gson.toJson(items));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Item item = gson.fromJson(req.getReader().readLine(), Item.class);
        User user = (User) req.getSession().getAttribute("user");
        item.setUser(user);
        ItemDao.instOf().save(item);
    }
}
